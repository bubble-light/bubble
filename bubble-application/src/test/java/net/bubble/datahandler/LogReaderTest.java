/**
 * Copyright [2015-2017] [https://github.com/bubble-light/]
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
package net.bubble.datahandler;

import net.bubble.BaseTest;
import net.bubble.application.app.service.IAccessLogService;
import net.bubble.application.datahandler.file.ReaderBootStartup;
import net.bubble.application.datahandler.file.filter.AccessLogFileReaderFilter;
import net.bubble.application.datahandler.file.reader.AccessLogFileReader;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author shiwen_xiao<xiaosw@msn.cn>
 * @since 2015年12月24日
 */
public class LogReaderTest extends BaseTest {

	private static final Logger logger = LoggerFactory.getLogger(LogReaderTest.class);

	@Autowired
	IAccessLogService accessLogService;

	@Test
	public void testAccessLogFileReader() {
		try {
			logger.info("开始进行数据导入");
			long time = System.currentTimeMillis();
			ReaderBootStartup rbs = new ReaderBootStartup();
			rbs.setLogFilePath("E:/work_houpix/log/");
			AccessLogFileReader reader = new AccessLogFileReader();
			reader.setAccessLogService(accessLogService);
			reader.setFileFilter(new AccessLogFileReaderFilter());
			rbs.setReader(reader);
			rbs.startBootStratup();
			// reader.setFile(new File("E:/work_houpix/log/ad-dream-access.log"));
			// reader.setFile(new File("E:/work_houpix/log/log.gif-access.log"));
			logger.info("数据导入完成，共耗时：{}秒",((System.currentTimeMillis() - time) / 1000));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
