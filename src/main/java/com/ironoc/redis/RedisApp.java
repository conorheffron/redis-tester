package com.ironoc.redis;
import java.net.URI;
import java.net.URISyntaxException;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

public class RedisApp {

	public static void main(String[] args) {
		String serverUri = args[0];
		String key = args[1];
		String value = args[2];

		JedisPool jedisPool = getConnection(serverUri);

		Jedis jedis = jedisPool.getResource();
		
		// add token
		jedis.set(key, value);
		
		// get token
		String lookup = jedis.get(key);
		System.out.println(lookup);
		
		// remove token
		jedis.del(key);
		System.out.println(jedis.get(key));
		
		// return the instance to the pool when you're done
		jedisPool.returnResource(jedis);
	}

	private static JedisPool getConnection(String serverUri) {
		try {
			URI redisUri = new URI(serverUri);
			String userInfo = redisUri.getUserInfo();
			String password = userInfo.split(":", 2)[1];
			JedisPool pool = new JedisPool(new JedisPoolConfig(), redisUri.getHost(), redisUri.getPort(),
					Protocol.DEFAULT_TIMEOUT, password);
			return pool;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

}
