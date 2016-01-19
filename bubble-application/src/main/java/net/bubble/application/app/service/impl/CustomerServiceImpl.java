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
package net.bubble.application.app.service.impl;

import java.util.List;
import java.util.Map;

import net.bubble.application.app.service.ICustomerService;
import net.bubble.application.framework.exception.ServiceException;
import net.bubble.persistence.app.entities.Customer;
import net.bubble.persistence.app.repositories.CustomerRepository;
import net.bubble.persistence.framework.page.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author shiwen_xiao<xiaosw@msn.cn>
 * @since 2015年2月6日
 */
@Component
public class CustomerServiceImpl implements ICustomerService {

	@Autowired
	CustomerRepository customerRepository;

	@Override
	public Customer getCustomerInfo(String customerId) throws ServiceException {
		return this.customerRepository.getRegisterCutomer(customerId);
	}

	@Override
	public List<Customer> getCustomerList() throws ServiceException {
		return (List<Customer>) customerRepository.findAll();
	}

	@Override
	public List<Customer> getCustomerListByPage(int pageNo, int pageSize, Map<String, Object> params) throws ServiceException {
		return customerRepository.getCustomerByPage(new Page<Customer>(pageNo, pageSize, params));
	}

}
