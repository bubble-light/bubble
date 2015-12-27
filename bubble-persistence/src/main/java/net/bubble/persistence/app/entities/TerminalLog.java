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
package net.bubble.persistence.app.entities;

/**
 * @author shiwen_xiao<xiaosw@msn.cn>
 * @since 2015年12月23日
 */
public class TerminalLog {

	private String terminal_id;

	private String terminal_machine;

	private String terminal_app;
	
	private String terminal_appVersion;
	
	private String terminal_language;
	
	private String terminal_netType;
	
	private String terminal_OSVersion;

	
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

	
	/**
	 * @return the terminal_machine
	 */
	public String getTerminal_machine() {
		return terminal_machine;
	}

	
	/**
	 * @param terminal_machine the terminal_machine to set
	 */
	public void setTerminal_machine(String terminal_machine) {
		this.terminal_machine = terminal_machine;
	}

	
	/**
	 * @return the terminal_app
	 */
	public String getTerminal_app() {
		return terminal_app;
	}

	
	/**
	 * @param terminal_app the terminal_app to set
	 */
	public void setTerminal_app(String terminal_app) {
		this.terminal_app = terminal_app;
	}

	
	/**
	 * @return the terminal_appVersion
	 */
	public String getTerminal_appVersion() {
		return terminal_appVersion;
	}

	
	/**
	 * @param terminal_appVersion the terminal_appVersion to set
	 */
	public void setTerminal_appVersion(String terminal_appVersion) {
		this.terminal_appVersion = terminal_appVersion;
	}

	
	/**
	 * @return the terminal_language
	 */
	public String getTerminal_language() {
		return terminal_language;
	}

	
	/**
	 * @param terminal_language the terminal_language to set
	 */
	public void setTerminal_language(String terminal_language) {
		this.terminal_language = terminal_language;
	}

	
	/**
	 * @return the terminal_netType
	 */
	public String getTerminal_netType() {
		return terminal_netType;
	}

	
	/**
	 * @param terminal_netType the terminal_netType to set
	 */
	public void setTerminal_netType(String terminal_netType) {
		this.terminal_netType = terminal_netType;
	}

	
	/**
	 * @return the terminal_OSVersion
	 */
	public String getTerminal_OSVersion() {
		return terminal_OSVersion;
	}

	
	/**
	 * @param terminal_OSVersion the terminal_OSVersion to set
	 */
	public void setTerminal_OSVersion(String terminal_OSVersion) {
		this.terminal_OSVersion = terminal_OSVersion;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TerminalLog [terminal_id=" + terminal_id + ", terminal_machine=" + terminal_machine + ", terminal_app=" + terminal_app
				+ ", terminal_appVersion=" + terminal_appVersion + ", terminal_language=" + terminal_language + ", terminal_netType="
				+ terminal_netType + ", terminal_OSVersion=" + terminal_OSVersion + "]";
	}
}
