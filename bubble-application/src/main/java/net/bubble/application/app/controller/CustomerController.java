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
package net.bubble.application.app.controller;

import java.util.List;
import java.util.Map;

import net.bubble.application.app.service.ICustomerService;
import net.bubble.application.framework.annotaions.Jsonp;
import net.bubble.application.framework.exception.ServiceException;
import net.bubble.persistence.app.entities.Customer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author shiwen_xiao<xiaosw@msn.cn>
 * @since 2015年2月6日
 */
@RestController
@RequestMapping({ "/customer" })
@Jsonp
public class CustomerController {

	Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	ICustomerService customerService;

	@RequestMapping(value = { "/{id}" }, method = { RequestMethod.GET, RequestMethod.POST })
	public Customer queryCustomer(@PathVariable("id") String customerId) {
		try {
			return this.customerService.getCustomerInfo(customerId);
		} catch (ServiceException e) {
			this.logger.error("Query customer info has been error!", e);
		}
		return null;
	}

	@RequestMapping(value = { "" }, method = { RequestMethod.GET, RequestMethod.POST })
	public List<Customer> queryAllCustomers() {
		try {
			return this.customerService.getCustomerList();
		} catch (ServiceException e) {
			this.logger.error("Query customer info has been error!", e);
		}
		return null;
	}

	@RequestMapping(value = { "/page" }, method = { RequestMethod.GET, RequestMethod.POST })
	public List<Customer> queryCustomersByPage(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize,
			Map<String, Object> params) {
		try {
			return this.customerService.getCustomerListByPage(pageNo, pageSize, params);
		} catch (ServiceException e) {
			this.logger.error("Query customer info has been error!", e);
		}
		return null;
	}
}
