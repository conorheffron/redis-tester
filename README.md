# redis-tester

[![License](https://img.shields.io/badge/License-Apache_2.0-blue.svg)](https://opensource.org/licenses/Apache-2.0)

[![Java CI with Maven](https://github.com/conorheffron/redis-tester/actions/workflows/maven.yml/badge.svg)](https://github.com/conorheffron/redis-tester/actions/workflows/maven.yml)

## Overview
- Tool for testing Redis instance by caching key-value pairs (setup of redis instance required).

## Technologies
- JDK 17, Jedis Client 7, & Redis Instance (Version 8.2.3)

## Build Project
```shell
./mvnw clean package
```

### JAR / Program arguments:
```
redis://$USER_ID:$PASSW@localhost:6379/0 \
    key-prefix value-prefix token_count
```

### Usage Example
```shell
java -cp target/redis-cache-tester-2.7.2-RELEASE.jar net.ironoc.redis.RedisApp \
    redis://redis:your_password@localhost:6379/0 \
    key-prefix value-prefix 7
```

#### Console Output
```shell
------------ START ADDING TOKENS --------------
The key is: key-prefix_0 & set value is: value-prefix_0
The key is: key-prefix_1 & set value is: value-prefix_1
The key is: key-prefix_2 & set value is: value-prefix_2
The key is: key-prefix_3 & set value is: value-prefix_3
The key is: key-prefix_4 & set value is: value-prefix_4
The key is: key-prefix_5 & set value is: value-prefix_5
The key is: key-prefix_6 & set value is: value-prefix_6
The key is: key-prefix_7 & set value is: value-prefix_7
------------ FINISHED ADDING TOKENS --------------
------------ START LOOKUP & DELETION OF TOKENS --------------
The lookup key is: key-prefix_7 and the value is: value-prefix_7
The key/value pair was deleted for look-up value: key-prefix_7
The lookup key is: key-prefix_6 and the value is: value-prefix_6
The key/value pair was deleted for look-up value: key-prefix_6
The lookup key is: key-prefix_5 and the value is: value-prefix_5
The key/value pair was deleted for look-up value: key-prefix_5
The lookup key is: key-prefix_4 and the value is: value-prefix_4
The key/value pair was deleted for look-up value: key-prefix_4
The lookup key is: key-prefix_3 and the value is: value-prefix_3
The key/value pair was deleted for look-up value: key-prefix_3
The lookup key is: key-prefix_2 and the value is: value-prefix_2
The key/value pair was deleted for look-up value: key-prefix_2
The lookup key is: key-prefix_1 and the value is: value-prefix_1
The key/value pair was deleted for look-up value: key-prefix_1
The lookup key is: key-prefix_0 and the value is: value-prefix_0
The key/value pair was deleted for look-up value: key-prefix_0
------------ FINISHED LOOKUP & DELETION OF TOKENS --------------
```

#### Redis Client Verification
![jedis-debug-with-redis-client-verification](./src/test/resources/jedis-debug-with-redis-client-verification.png)
