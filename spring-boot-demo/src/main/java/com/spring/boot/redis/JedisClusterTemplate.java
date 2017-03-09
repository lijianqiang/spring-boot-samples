package com.spring.boot.redis;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import redis.clients.jedis.JedisCluster;

/**
 * @author lijianqiang
 *
 */
public class JedisClusterTemplate {

    @Autowired
    private JedisCluster jedisCluster;

    /**
     * 半天
     */
    public static final int HALF_DAY = 43200;
    /**
     * 一天
     */
    public static final int ONE_DAY = 86400;

    public JedisCluster getJedisCluster() {
        return jedisCluster;
    }

    public void setJedisCluster(JedisCluster jedisCluster) {
        this.jedisCluster = jedisCluster;
    }

    public Boolean delete(String... keys) {
        return false;
    }

    public String get(String key) {
        return jedisCluster.get(key);
    }

    public Long getAsLong(String key) {
        String result = get(key);
        return result != null ? Long.valueOf(result) : null;
    }

    public Integer getAsInt(String key) {
        String result = get(key);
        return result != null ? Integer.valueOf(result) : null;
    }

    public void set(String key, String value) {
        jedisCluster.set(key, value);
    }

    public void setex(String key, String value, int seconds) {
        jedisCluster.setex(key, seconds, value);
    }

    public Boolean setnx(String key, String value) {
        return jedisCluster.setnx(key, value) == 1 ? true : false;
    }

    public Boolean setnxex(String key, String value, int seconds) {
        String result = jedisCluster.set(key, value, "NX", "EX", seconds);
        return JedisUtils.isStatusOk(result);
    }

    public Long incr(String key) {
        return jedisCluster.incr(key);
    }

    public Long decr(String key) {
        return jedisCluster.decr(key);
    }

    public String hget(String key, String field) {
        return jedisCluster.hget(key, field);
    }

    public void hset(String key, String field, String value) {
        jedisCluster.hset(key, field, value);

    }

    public List<String> hmget(String key, String[] fields) {
        return jedisCluster.hmget(key, fields);
    }

    public void hmset(String key, Map<String, String> map) {
        jedisCluster.hmset(key, map);
    }

    public Long hdel(String key, String... fieldsName) {
        return jedisCluster.hdel(key, fieldsName);
    }

    public Set<String> hkeys(String key) {
        return jedisCluster.hkeys(key);
    }

    public Map<String, String> hgetall(final String key) {
        return jedisCluster.hgetAll(key);
    }

    public void lpush(String key, String... values) {
        jedisCluster.lpush(key, values);
    }

    public String rpop(String key) {
        return jedisCluster.rpop(key);
    }

    public Long llen(String key) {
        return jedisCluster.llen(key);
    }

    public Boolean lremOne(String key, String value) {
        Long count = jedisCluster.lrem(key, 1, value);
        return (count == 1);
    }

    public Boolean lremAll(String key, String value) {
        Long count = jedisCluster.lrem(key, 0, value);
        return (count > 0);
    }

    public List<String> lrange(final String key, final long start, final long end) {
        List<String> lrange = jedisCluster.lrange(key, start, end);
        return lrange;
    }

    public Boolean sadd(String key, String... members) {
        return jedisCluster.sadd(key, members) == 1 ? true : false;
    }

    public Set<String> smembers(String key) {
        return jedisCluster.smembers(key);
    }

    public Boolean srem(String key, String... members) {
        return jedisCluster.srem(key, members) == 1 ? true : false;
    }

    public Boolean zadd(String key, String member, double score) {
        return jedisCluster.zadd(key, score, member) == 1 ? true : false;
    }

    public Boolean zrem(String key, String member) {
        return jedisCluster.zrem(key, member) == 1 ? true : false;
    }

    public Double zscore(String key, String member) {
        return jedisCluster.zscore(key, member);
    }

    public Long zcard(String key) {
        return jedisCluster.zcard(key);
    }

    public Boolean del(String key) {
        return jedisCluster.del(key) == 1 ? true : false;
    }

    public Long expire(String key, Integer seconds) {
        return jedisCluster.expire(key, seconds);
    }

    public Boolean exists(String key) {
        return jedisCluster.exists(key);
    }

    public String getSet(String key, String value) {
        return jedisCluster.getSet(key, value);
    }
}
