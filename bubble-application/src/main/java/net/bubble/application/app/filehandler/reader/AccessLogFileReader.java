/**
 * Copyright [2015-2017] 
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions
 * and limitations under the License.
 */
package net.bubble.application.app.filehandler.reader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import net.bubble.application.app.filehandler.reader.AbstractReader;
import net.bubble.application.app.service.IAccessLogService;
import net.bubble.common.utils.CharsetUtil;
import net.bubble.common.utils.DateUtil;
import net.bubble.common.utils.FileUtil;
import net.bubble.common.utils.PKUtil;
import net.bubble.common.utils.StringUtil;
import net.bubble.persistence.app.entities.AccessLog;
import net.bubble.persistence.app.entities.AccessUrlLog;
import net.bubble.persistence.app.entities.TerminalLog;

import org.apache.commons.io.LineIterator;
import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author shiwen_xiao<xiaosw@msn.cn>
 * @since 2015年12月23日
 */
public class AccessLogFileReader extends AbstractReader {

	private static final Logger logger = LoggerFactory.getLogger(AccessLogFileReader.class);

	private IAccessLogService accessLogService;

	/**
	 * @param accessLogService
	 *            the accessLogService to set
	 */
	public void setAccessLogService(IAccessLogService accessLogService) {
		this.accessLogService = accessLogService;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			readFile();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void readFile() throws IOException {
		if (this.getFile() != null && this.getFile().canRead()) {
			List<AccessLog> accessLogList = new ArrayList<AccessLog>();
			List<TerminalLog> terminalLogList = new ArrayList<TerminalLog>();
			List<AccessUrlLog> accessUrlLogList = new ArrayList<AccessUrlLog>();
			// 读取文件,不考虑大文件情况，用commons-io直接读取，大文件考虑分段读取
			LineIterator lineIterator = FileUtil.lineIterator(this.getFile(), CharsetUtil.CHARSET_GBK);
			while (lineIterator.hasNext()) {
				String line = lineIterator.nextLine();
				String[] accessLogStr = StringUtil
						.getMatherContent(
								line,
								"([\\d]+.[\\d]+.[\\d]+.[\\d]+) - - \\[([\\s\\S]+)\\] \"(GET|POST) ([\\S]+) [^\"]*\" ([\\d]{3}) ([\\d]+) \"([^\"]*)\" \"([^\"]*)\"",
								1, 8);
				// logger.info("Log data: {}", Arrays.toString(accessLogStr));
				AccessUrlLog accessUrlLog = loadAccessUrlLog(accessLogStr);
				if (accessUrlLog != null) {// 有真正请求才进行数据保存
					AccessLog accessLog = loadAccessLog(accessLogStr);
					accessLog.setAccess_url_id(accessUrlLog.getUrl_id());
					accessUrlLogList.add(accessUrlLog);
					TerminalLog terminalLog = loadTerminalLog(accessLogStr);
					if (terminalLog != null) {// 有终端信息才保存终端主键
						accessLog.setTerminal_id(terminalLog.getTerminal_id());
						terminalLogList.add(terminalLog);
					}
					accessLogList.add(accessLog);
					logger.info("Access log :{},Access url :{}, Terminal info :{}", accessLog, accessUrlLog, terminalLog);
					try {
						if (accessLogList.size() % BATCH_PERSISTENCE_COUNT == 0) {
							accessLogService.saveAccessLogBath(accessLogList, accessUrlLogList, terminalLogList);
						}
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			}
			// 保存剩下的记录
			try {
				accessLogService.saveAccessLogBath(accessLogList, accessUrlLogList, terminalLogList);
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
			// TODO 读取完毕后记录读取记录数
			// TODO 读取中断后断点读取
			logger.info("The data of log file named {} was saved!", getFile().getName());
		}
	}

	private AccessLog loadAccessLog(String[] logs) {
		AccessLog accessLog = new AccessLog();
		try {
			accessLog.setAccess_id(PKUtil.getInstance().UUIDPK());
			accessLog.setAccess_ip(logs[0]);
			accessLog.setAccess_time(DateUtil.getDate("dd/MMM/yyyy:HH:mm:ss Z", "yyyy-MM-dd HH:mm:ss", logs[1], Locale.US, Locale.CHINESE));
			accessLog.setAccess_computing_time(Integer.valueOf(StringUtil.isEmpty(logs[5]) ? "0" : logs[5]));
			accessLog.setAccess_method(logs[2]);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return accessLog;
	}

	private AccessUrlLog loadAccessUrlLog(String[] logs) {
		AccessUrlLog accessUrlLog = new AccessUrlLog();
		try {
			String url = logs[3];
			if (StringUtil.isEmpty(url))
				return null;
			String[] urls = url.split("\\?");
			if (urls.length == 2) {
				accessUrlLog.setUrl_id(PKUtil.getInstance().UUIDPK());
				accessUrlLog.setUrl(urls[0]);
				MultiMap<String> multiMap = new MultiMap<String>();
				UrlEncoded.decodeTo(urls[1], multiMap, CharsetUtil.CHARSET_GBK);
				logger.info("Parameter: {}", multiMap);
				for (String key : multiMap.keySet()) {
					Object obj = multiMap.get(key);
					String value = null;
					if(obj instanceof String[]){
						value = ((String[]) obj)[0];
					}else if(obj instanceof List){
						value = String.valueOf(((List)obj).get(0));
					}else{
						value = (String) obj;
					}
					accessUrlLog.getClass().getMethod("setParam_" + key, String.class).invoke(accessUrlLog, value);
				}
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return accessUrlLog;
	}

	private TerminalLog loadTerminalLog(String[] logs) {
		TerminalLog terminal = new TerminalLog();
		try {
			String terminalLog = logs[7];
			if (StringUtil.isEmpty(terminalLog))
				return null;
			terminal.setTerminal_id(PKUtil.getInstance().UUIDPK());
			if (terminalLog.indexOf("(") != -1)
				terminal.setTerminal_machine(terminalLog.substring(terminalLog.indexOf("(") + 1, terminalLog.indexOf(")")));
			String[] terminalInfoArray = terminalLog.substring(terminalLog.indexOf(")") + 1, terminalLog.length()).split(" ");
			Map<String, String> map = convertArray2Map(terminalInfoArray);
			if (map.containsKey("Firefox")) {
				terminal.setTerminal_app("Firefox");
				terminal.setTerminal_appVersion(map.get("Firefox"));
			}
			if (map.containsKey("Safari")) {
				terminal.setTerminal_app("Safari");
				terminal.setTerminal_appVersion(map.get("Safari"));
			}
			if (map.containsKey("Chrome")) {
				terminal.setTerminal_app("Chrome");
				terminal.setTerminal_appVersion(map.get("Chrome"));
			}
			if (map.containsKey("MicroMessenger")) {
				terminal.setTerminal_app("MicroMessenger");
				terminal.setTerminal_appVersion(map.get("MicroMessenger"));
			}
			terminal.setTerminal_netType(map.get("NetType"));
			terminal.setTerminal_language(map.get("Language"));
			terminal.setTerminal_OSVersion(map.get("Version"));
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return terminal;
	}

	private Map convertArray2Map(String[] array) {
		Map<String, String> map = new HashMap<String, String>(array.length);
		for (String str : array) {
			if (str.indexOf("/") == -1)
				continue;
			map.put(str.substring(0, str.indexOf("/")), str.substring(str.indexOf("/") + 1, str.length()));
		}
		return map;
	}

	/*
	 * (non-Javadoc)
	 * @see net.bubble.application.datahandler.file.reader.AbstractReader#newInstance()
	 */
	@Override
	public AbstractReader newInstance() {
		AccessLogFileReader reader = new AccessLogFileReader();
		reader.setAccessLogService(accessLogService);
		return reader;

	}

}
