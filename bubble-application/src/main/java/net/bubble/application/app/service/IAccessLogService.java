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
package net.bubble.application.app.service;

import java.util.List;
import java.util.Map;

import net.bubble.application.framework.exception.ServiceException;
import net.bubble.persistence.app.entities.AccessLog;
import net.bubble.persistence.app.entities.AccessUrlLog;
import net.bubble.persistence.app.entities.ChartEntity;
import net.bubble.persistence.app.entities.TerminalLog;

/**
 * @author shiwen_xiao<xiaosw@msn.cn>
 * @since 2015年12月24日
 */
public interface IAccessLogService {

	/**
	 * 批量保存日志
	 * 
	 * @param accessLogList
	 * @param accessUrlLogList
	 * @param terminalLogList
	 * @throws ServiceException
	 */
	public void saveAccessLogBath(List<AccessLog> accessLogList, List<AccessUrlLog> accessUrlLogList, List<TerminalLog> terminalLogList)
			throws ServiceException;

	/**
	 * 查询访问渠道统计报表
	 * 
	 * @return List<ChartEntity>
	 * @throws ServiceException
	 */
	public List<ChartEntity> queryAccessChannelChart() throws ServiceException;

}
