/**
 * Copyright [2015-2017] 
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package net.bubble.application.framework.exception;


/**
 * @author shiwen_xiao<xiaosw@msn.cn>
 * @since 2015年2月6日
 */
public class ServiceException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8304982728768435576L;

	/**
	 * 
	 */
	public ServiceException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ServiceException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ServiceException(Throwable cause) {
		super(cause);
	}

}
