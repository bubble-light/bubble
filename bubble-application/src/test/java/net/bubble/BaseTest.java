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
package net.bubble;

import org.junit.runner.RunWith;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author shiwen_xiao<xiaosw@msn.cn>
 * @since 2015年1月28日
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ComponentScan(basePackages={"net.bubble.application.**.controller", "net.bubble.application.**.service"})
@ContextConfiguration(locations={"classpath*:config/applicationContext.xml", "classpath*:config/persistence/persistence.xml","classpath*:config/app/application.xml"})
public class BaseTest {
}
