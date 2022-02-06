package moviebuddy.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;

import java.util.Objects;

@Aspect
public class CachingAspect {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final CacheManager cacheManager;

    public CachingAspect(CacheManager cacheManager) {
        this.cacheManager = Objects.requireNonNull(cacheManager);
    }

    @Around("target(moviebuddy.domain.MovieReader)")
    public Object doCachingReturnValue(ProceedingJoinPoint pjp) throws Throwable {
        // 캐시된 데이터가 있으면 즉시 반환 처리
        Cache cache = cacheManager.getCache(pjp.getThis().getClass().getName());
        Object cacheValue = cache.get(pjp.getSignature().getName(), Object.class);

        if (Objects.nonNull(cacheValue)) {
            log.info("returns cached data [{}]", pjp);
            return cacheValue;
        }

        //캐시된 데이터가 없으면 대상 객체에 명령을 위임하고 반환받은 값을 캐시에 저장 후 반환처리
        cacheValue = pjp.proceed();
        cache.put(pjp.getSignature().getName(), cacheValue);
        log.info("caching return Value. [{}]", pjp);

        return cacheValue;
    }

}
