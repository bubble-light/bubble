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
package net.bubble.persistence;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * @author shiwen_xiao<xiaosw@msn.cn>
 * @since 2015年12月29日
 */
public class RedisRepositoryTest extends BaseTest{
	
	Logger logger = LoggerFactory.getLogger(RedisRepositoryTest.class);

	@Autowired
	RedisTemplate<String, Object> redisTemplate;
	
	@Test
	public void testRedisTemplate(){
		logger.info("RedisTemplate : {}",redisTemplate);
		redisTemplate.opsForValue().set("key_1", "hello world!");
		logger.info("Redis result: {}",redisTemplate.opsForValue().get("key_1"));
	}
}
