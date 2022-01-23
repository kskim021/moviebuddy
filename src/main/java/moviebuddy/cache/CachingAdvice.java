package moviebuddy.cache;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Objects;

public class CachingAdvice implements MethodInterceptor {

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
            return cachedValue;
        }

        //  캐쉬된 데이터 없으면, 대상 객체에 명령을 위임하고, 반환된 값을 캐시에 저장한 후 반환 처리
        cachedValue = invocation.proceed();
        cache.put(invocation.getMethod().getName(), cachedValue);

        return cachedValue;
    }
}
