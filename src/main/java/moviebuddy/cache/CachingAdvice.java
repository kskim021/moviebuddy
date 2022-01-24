package moviebuddy.cache;

import moviebuddy.domain.MovieReader;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.util.ClassUtils;

import java.util.Objects;

public class CachingAdvice implements MethodInterceptor {

    private final Logger log = LoggerFactory.getLogger(getClass());

    private final CacheManager cacheManager;

    public CachingAdvice(CacheManager cacheManager) {
        this.cacheManager = cacheManager;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // 캐쉬된 데이터가 존재하면, 즉시 반환 처리
        Cache cache = cacheManager.getCache(invocation.getThis().getClass().getName());
        cache.get(invocation.getMethod().getName(), Object.class);
        Object cachedValue = cache.get(invocation.getMethod().getName(), Object.class);

        if (Objects.nonNull(cachedValue)) {
            log.info("returns cached data. [{}]", invocation);
            return cachedValue;
        }

        //  캐쉬된 데이터 없으면, 대상 객체에 명령을 위임하고, 반환된 값을 캐시에 저장한 후 반환 처리
        cachedValue = invocation.proceed();
        cache.put(invocation.getMethod().getName(), cachedValue);

        log.info("caching return value. [{}]", invocation);

        return cachedValue;
    }
}
