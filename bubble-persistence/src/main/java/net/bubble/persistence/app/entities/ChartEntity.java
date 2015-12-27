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
 * @since 2015年12月27日
 */
public class ChartEntity {

	private Object name;

	private Object data;

	/**
	 * @return the name
	 */
	public Object getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(Object name) {
		this.name = name;
	}

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	/**
	 * @param name
	 * @param data
	 */
	public ChartEntity(Object name, Object data) {
		super();
		if("groupmessage".equals(String.valueOf(name))){
			this.name = "群消息";
		}else if("message".equals(String.valueOf(name))){
			this.name = "消息";
		}else if("qq".equals(String.valueOf(name))){
			this.name = "QQ";
		}else if("qzone".equals(String.valueOf(name))){
			this.name = "QQ空间";
		}else if("singlemessage".equals(String.valueOf(name))){
			this.name = "单发";
		}else if("timeline".equals(String.valueOf(name))){
			this.name = "朋友圈";
		}
		this.data = data;
	}
	

	/**
	 * 
	 */
	public ChartEntity() {
		super();
	}

}
