package com.example.book.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.concurrent.TimeUnit;

/**
 * mvc拦截器
 * zhangyd
 * 2020/4/2 11:37
 */
@Slf4j
public class DemoInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    StringRedisTemplate redisTemplate;

    private static final String NO_REPEAT_COMMIT = "NO_REPEAT_COMMIT:";

    /**
     * 请求开始前
     *
     * @param request
     * @param response
     * @param handler
     * @return
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        long start = System.currentTimeMillis();
        String token = request.getHeader("access_token");
        String requestUrl = request.getServletPath();
        String key = NO_REPEAT_COMMIT + token + "-" + requestUrl;
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        //请求判断
        if (opsForValue.get(key) == null) {
            opsForValue.set(key, NO_REPEAT_COMMIT, 3, TimeUnit.SECONDS);
            return true;
        }
        log.info("key:{} 重复提交", key);
        System.out.println("============== preHandle");
        return false;
    }

    /**
     * 请求结束后
     *
     * @param request
     * @param response
     * @param handler
     * @param modelAndView
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) {
        System.out.println("============== postHandle");
    }

}
