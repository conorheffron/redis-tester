package net.ironoc.redis;
import java.net.URI;
import java.net.URISyntaxException;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Protocol;

public class RedisApp {

	public static void main(String[] args) {
		String serverUri = args[0];
		String keyPrefix = args[1];
		String valuePrefix = args[2];
        int tokenCount = Integer.parseInt(args[3]);

		JedisPool jedisPool = getConnection(serverUri);

		Jedis jedis = jedisPool.getResource();
		
		// add tokens
        System.out.println("------------ START ADDING TOKENS --------------");
        for (int i = 0; i <= tokenCount; i++) {
            String key =  keyPrefix + '_' + i;
            String value = valuePrefix + '_' + i;

            System.out.println(String.format("The key is: %s & set value is: %s", key, value));
            jedis.set(key, value);
        }
        System.out.println("------------ FINISHED ADDING TOKENS --------------");

        System.out.println("------------ START LOOKUP & DELETION OF TOKENS --------------");
        for (int i = tokenCount; i >= 0; i--) {
            // get tokens
            String lookup =  keyPrefix + '_' + i;
            String value = jedis.get(lookup);
            System.out.println(String.format("The lookup key is: %s and the value is: %s", lookup, value));

            // remove tokens
            jedis.del(lookup);
            System.out.println(String.format("The key/value pair was deleted for look-up value: %s", lookup));
        }
        System.out.println("------------ FINISHED LOOKUP & DELETION OF TOKENS --------------");

		
		// return the instance to the pool when you're done
		jedisPool.returnResource(jedis);
	}

	private static JedisPool getConnection(String serverUri) {
		try {
			URI redisUri = new URI(serverUri);
			String userInfo = redisUri.getUserInfo();
			String password = userInfo.split(":", 2)[1];
            return new JedisPool(new JedisPoolConfig(), redisUri.getHost(), redisUri.getPort(),
					Protocol.DEFAULT_TIMEOUT, password);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return null;
	}

}
