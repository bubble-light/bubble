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

import java.io.File;
import java.io.IOException;

import net.bubble.application.app.filehandler.filter.AbstractFileFilter;

/**
 * @author shiwen_xiao<xiaosw@msn.cn>
 * @since 2015年12月23日
 */
public abstract class AbstractReader implements Runnable {

	/**
	 * BATCH_PERSISTENCE_COUNT 批量持久化数量
	 * 
	 */
	protected static final int BATCH_PERSISTENCE_COUNT = 100;

	private File file;

	public AbstractReader() {
	}

	private AbstractFileFilter fileFilter;
	
	/**
	 * @return the fileFilter
	 */
	public AbstractFileFilter getFileFilter() {
		return fileFilter;
	}

	/**
	 * @param fileFilter the fileFilter to set
	 */
	public void setFileFilter(AbstractFileFilter fileFilter) {
		this.fileFilter = fileFilter;
	}

	/**
	 * @param file
	 *            the file to set
	 */
	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * @return the file
	 */
	public File getFile() {
		return file;
	}

	public abstract void readFile() throws IOException;

	public abstract AbstractReader newInstance();

}
