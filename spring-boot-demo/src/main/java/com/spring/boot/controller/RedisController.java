package com.spring.boot.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.spring.boot.entity.domain.Foo;
import com.spring.boot.redis.RedisClusterTemplate;
import com.spring.boot.redis.RedisConstants;
import com.spring.boot.service.FooService;
import com.spring.boot.utils.StringUtil;

@Controller
@RequestMapping("/redis")
public class RedisController {

    /**
     * Member Description
     */
    private static final Logger LOG = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private FooService fooService;

    @Autowired
    private RedisClusterTemplate myRedisTemplate;

    @RequestMapping(method = { RequestMethod.GET })
    @ResponseBody
    public String getAction(HttpServletRequest request) {
        LOG.debug("hello world");
        Gson gson = new Gson();
        String value = myRedisTemplate.get(RedisConstants.USER_FORWARD_CACHE_PREFIX, "foos");
        if (StringUtil.isEmpty(value)) {
            List<Foo> foos = fooService.getAll();
            myRedisTemplate.set(RedisConstants.USER_FORWARD_CACHE_PREFIX, "foos", gson.toJson(foos));
            return "aaaaaaaaa";
        }
        return value;
    }

}
