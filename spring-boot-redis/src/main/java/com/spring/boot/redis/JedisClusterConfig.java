package com.spring.boot.redis;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
public class JedisClusterConfig {

	@Autowired
	private RedisClusterProperties redisProperties;

	/**
	 * 注意： 这里返回的JedisCluster是单例的，并且可以直接注入到其他类中去使用
	 * 
	 * @return
	 */
	@Bean
	public JedisCluster getJedisCluster() {
		// 获取服务器数组(这里要相信自己的输入，所以没有考虑空指针问题)
		String[] serverArray = redisProperties.getClusterNodes().split(",");
		Set<HostAndPort> nodes = new HashSet<>();

		for (String ipPort : serverArray) {
			String[] ipPortPair = ipPort.split(":");
			nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
		}

		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(redisProperties.getMaxActive());
		poolConfig.setMaxIdle(redisProperties.getMaxIdle());
		poolConfig.setMaxWaitMillis(redisProperties.getMaxWait());
		poolConfig.setTestOnBorrow(redisProperties.isTestOnBorrow());

		return new JedisCluster(nodes, redisProperties.getCommandTimeout(),
				RedisClusterProperties.DEFAULT_MAX_REDIRECTIONS, poolConfig);
	}

}
