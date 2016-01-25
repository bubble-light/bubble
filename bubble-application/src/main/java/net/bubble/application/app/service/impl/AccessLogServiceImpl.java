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
package net.bubble.application.app.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.bubble.application.app.service.IAccessLogService;
import net.bubble.application.framework.exception.ServiceException;
import net.bubble.persistence.app.entities.AccessLog;
import net.bubble.persistence.app.entities.AccessUrlLog;
import net.bubble.persistence.app.entities.ChartEntity;
import net.bubble.persistence.app.entities.TerminalLog;
import net.bubble.persistence.app.repositories.AccessLogRepository;
import net.bubble.persistence.app.repositories.AccessUrlLogRepository;
import net.bubble.persistence.app.repositories.TerminalLogRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author shiwen_xiao<xiaosw@msn.cn>
 * @since 2015年12月24日
 */
@Transactional
@Component
public class AccessLogServiceImpl implements IAccessLogService {

	private static final Logger logger = LoggerFactory.getLogger(AccessLogServiceImpl.class);

	@Autowired
	AccessLogRepository accessLogRepository;

	@Autowired
	AccessUrlLogRepository accessUrlLogRepository;

	@Autowired
	TerminalLogRepository terminalLogRepository;

	/*
	 * (non-Javadoc)
	 * @see net.bubble.application.app.service.IAccessLogService#saveAccessLogBath(java.util.List, java.util.List, java.util.List)
	 */
	@Override
	public void saveAccessLogBath(List<AccessLog> accessLogList, List<AccessUrlLog> accessUrlLogList, List<TerminalLog> terminalLogList)
			throws ServiceException {
		accessLogRepository.batchSave(accessLogList);
		terminalLogRepository.batchSave(terminalLogList);
		accessUrlLogRepository.batchSave(accessUrlLogList);
		accessLogList.clear();
		accessUrlLogList.clear();
		terminalLogList.clear();
	}

	/*
	 * (non-Javadoc)
	 * @see net.bubble.application.app.service.IAccessLogService#queryChannelChart()
	 */
	@Override
	public List<ChartEntity> queryAccessChannelChart() throws ServiceException {
		try {
			List<ChartEntity> charBeanList = new ArrayList<ChartEntity>();
			List<Map> channelList = accessUrlLogRepository.queryAccessChannel();
			for (Map map : channelList) {
				Object name = map.get("CHANNEL");
				Object[] datas = new Object[] { map.get("CHANNEL_COUNT") };
				charBeanList.add(new ChartEntity(name, datas));
			}
			return charBeanList;
		} catch (Exception e) {
			logger.error(e.getMessage(), e.getCause());
			throw new ServiceException("The chart data analysis failure.", e);
		}
	}
}
