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
package net.bubble.persistence.app.entities;

/**
 * @author shiwen_xiao<xiaosw@msn.cn>
 * @since 2015年12月23日
 */
public class AccessLog {

	private String access_id;

	private String access_ip;

	private String access_time;

	private int access_computing_time;
	
	private String access_method;
	
	private String access_url_id;
	
	private String terminal_id;

	/**
	 * @return the access_id
	 */
	public String getAccess_id() {
		return access_id;
	}

	/**
	 * @param access_id
	 *            the access_id to set
	 */
	public void setAccess_id(String access_id) {
		this.access_id = access_id;
	}

	/**
	 * @return the access_ip
	 */
	public String getAccess_ip() {
		return access_ip;
	}

	/**
	 * @param access_ip
	 *            the access_ip to set
	 */
	public void setAccess_ip(String access_ip) {
		this.access_ip = access_ip;
	}


	/**
	 * @return the access_time
	 */
	public String getAccess_time() {
		return access_time;
	}

	/**
	 * @param access_time
	 *            the access_time to set
	 */
	public void setAccess_time(String access_time) {
		this.access_time = access_time;
	}

	/**
	 * @return the access_computing_time
	 */
	public int getAccess_computing_time() {
		return access_computing_time;
	}

	/**
	 * @param access_computing_time
	 *            the access_computing_time to set
	 */
	public void setAccess_computing_time(int access_computing_time) {
		this.access_computing_time = access_computing_time;
	}

	/**
	 * @return the access_method
	 */
	public String getAccess_method() {
		return access_method;
	}

	
	/**
	 * @param access_method the access_method to set
	 */
	public void setAccess_method(String access_method) {
		this.access_method = access_method;
	}

	
	/**
	 * @return the access_url_id
	 */
	public String getAccess_url_id() {
		return access_url_id;
	}

	
	/**
	 * @param access_url_id the access_url_id to set
	 */
	public void setAccess_url_id(String access_url_id) {
		this.access_url_id = access_url_id;
	}

	
	/**
	 * @return the terminal_id
	 */
	public String getTerminal_id() {
		return terminal_id;
	}

	
	/**
	 * @param terminal_id the terminal_id to set
	 */
	public void setTerminal_id(String terminal_id) {
		this.terminal_id = terminal_id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AccessLog [access_id=" + access_id + ", access_ip=" + access_ip + ", access_time=" + access_time
				+ ", access_computing_time=" + access_computing_time + ", access_method=" + access_method + ", access_url_id="
				+ access_url_id + ", terminal_id=" + terminal_id + "]";
	}
	
}
