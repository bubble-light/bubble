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
package net.bubble.application.app.filehandler;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import net.bubble.application.app.filehandler.reader.AbstractReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

/**
 * @author shiwen_xiao<xiaosw@msn.cn>
 * @since 2015年12月23日
 */
public class ReaderBootStartup implements InitializingBean {

	private static final Logger logger = LoggerFactory.getLogger(ReaderBootStartup.class);

	private static final ExecutorService executorService = Executors.newCachedThreadPool();

	private String logFilePath;

	private AbstractReader reader;

	@Override
	public void afterPropertiesSet() throws Exception {
		//TODO
	}

	/**
	 * @param logFilePath
	 *            the logFilePath to set
	 */
	public void setLogFilePath(String logFilePath) {
		this.logFilePath = logFilePath;
	}

	/**
	 * @param reader
	 *            the reader to set
	 */
	public void setReader(AbstractReader reader) {
		this.reader = reader;
	}

	public ReaderBootStartup() {
		
	}

	/**
	 * @param logFilePath
	 */
	public ReaderBootStartup(String logFilePath) {
		this.logFilePath = logFilePath;
	}

	public void startBootStratup() {
		try {
			initImporter();
		} catch (Exception e) {
			logger.error(e.getMessage(), e.getCause());
		}
	}

	private void initImporter() {
		try {
			File logPath = new File(this.logFilePath);
			if (!logPath.canRead()) {
				logger.warn("Can't read the path named: {}", logFilePath);
				return;
			}
			if (logPath.isFile()) {
				AbstractReader readThread = reader.newInstance();
				reader.setFile(logPath);
				executorService.execute(readThread);
			} else {
				File[] files = logPath.listFiles(reader.getFileFilter());
				// TODO 考虑多层次文件夹递归问题
				for (int i = 0; i < files.length; i++) {
					// 每个文件用一个线程读取
					File file = files[i];
					AbstractReader readThread = reader.newInstance();
					readThread.setFile(file);
					executorService.execute(readThread);
				}
			}
			logger.info("Load data threads are executing ......");
			executorService.shutdown();
			while(true){
				if(executorService.isTerminated()){
					break;
				}
				Thread.sleep(2000);
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e.getCause());
		}
	}
}
