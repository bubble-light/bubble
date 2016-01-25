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
package net.bubble.persistence.framework.engine;

import net.bubble.common.utils.StringUtil;
import net.bubble.persistence.framework.redis.datasource.RedisDataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import redis.clients.jedis.JedisPoolConfig;

/**
 * @author shiwen_xiao<xiaosw@msn.cn>
 * @since 2015年12月28日
 */
@Configuration
public class BubbleRedisEngine {

	private RedisDataSource redisDatasource;

	/**
	 * @param redisDatasource
	 *            the redisDatasource to set
	 */
	public void setRedisDatasource(RedisDataSource redisDatasource) {
		this.redisDatasource = redisDatasource;
	}

	@Bean
	JedisPoolConfig jedisPoolConfig() throws Exception {
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(redisDatasource.getMaxTotal());
		jedisPoolConfig.setMaxIdle(redisDatasource.getMaxIdle());
		jedisPoolConfig.setMaxWaitMillis(redisDatasource.getMaxWaitMillis());
		jedisPoolConfig.setMinIdle(redisDatasource.getMinIdle());
		jedisPoolConfig.setTestOnBorrow(redisDatasource.isTestOnBorrow());
		jedisPoolConfig.setTestOnReturn(true);
		jedisPoolConfig.setTestWhileIdle(true);
		return jedisPoolConfig;
	}

	@Bean
	RedisConnectionFactory jedisConnectionFactory() throws Exception {
		JedisConnectionFactory connectionFactory = new JedisConnectionFactory(redisSentinelConfiguration(), jedisPoolConfig());
		connectionFactory.setHostName(redisDatasource.getHostName());
		connectionFactory.setPort(redisDatasource.getPort());
		connectionFactory.setPassword(redisDatasource.getPassword());
		connectionFactory.setTimeout(redisDatasource.getTimeOut());
		connectionFactory.afterPropertiesSet();
		return connectionFactory;
	}

	@Bean
	RedisSentinelConfiguration redisSentinelConfiguration() throws Exception {
		if (StringUtil.isEmpty(redisDatasource.getMasterName()) || redisDatasource.getSentinelNodes().isEmpty()) {
			return null;//No sentinel model,return null object.
		}
		RedisSentinelConfiguration redisSentinelConfiguration = new RedisSentinelConfiguration(redisDatasource.getMasterName(),
				redisDatasource.getSentinelNodes());
		return redisSentinelConfiguration;
	}

	@Bean
	RedisTemplate<String, Object> redisTemplate() throws Exception {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
		redisTemplate.setConnectionFactory(jedisConnectionFactory());
		redisTemplate.afterPropertiesSet();
		return redisTemplate;
	}

}
