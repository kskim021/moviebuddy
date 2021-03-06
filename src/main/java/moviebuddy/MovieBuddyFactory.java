package moviebuddy;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.interceptor.*;
import org.springframework.context.annotation.*;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import java.util.concurrent.TimeUnit;


@Configuration
@PropertySource("/application.properties")
@ComponentScan(basePackages = {"moviebuddy"})
@Import({MovieBuddyFactory.DomainModuleConfig.class, MovieBuddyFactory.DataSourceModuleConfig.class})
@EnableCaching
public class MovieBuddyFactory implements CachingConfigurer {

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("moviebuddy");

        return marshaller;
    }

    @Bean
    public CacheManager caffeineCacheManager() {
        CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
        caffeineCacheManager.setCaffeine(Caffeine.newBuilder().expireAfterWrite(3, TimeUnit.SECONDS));

        return caffeineCacheManager;

    }

    @Override
    public CacheManager cacheManager() {
        return caffeineCacheManager();
    }

    @Override
    public CacheResolver cacheResolver() {
        return new SimpleCacheResolver(caffeineCacheManager());
    }

    @Override
    public KeyGenerator keyGenerator() {
        return new SimpleKeyGenerator();
    }

    @Override
    public CacheErrorHandler errorHandler() {
        return new SimpleCacheErrorHandler();
    }


    @Configuration
    static class DomainModuleConfig {

        //@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
        /*@Bean
        public MovieFinder movieFinder(MovieReader movieReader) {
            // 메서드 호출 방식
            //return new MovieFinder(movieReader());
            // 메서드 파마미터 방식
            return new MovieFinder(movieReader);
        }*/

    }

    @Configuration
    static class DataSourceModuleConfig {

        /*private final Environment environment;

        DataSourceModuleConfig(Environment environment) {
            this.environment = environment;
        }*/

        /*@Bean
        public MovieReader movieReader() {
            return new CsvMovieReader();
        }*/

       /* @Profile(MovieBuddyProfile.CSV_MODE)
        @Bean
        public CsvMovieReader csvMovieReader() {
            CsvMovieReader movieReader = new CsvMovieReader();

            //  애플리케이션 외부에서 작성된 설정정보를 읽어, 메타데이터 위치 설정하기
            //movieReader.setMetadata(environment.getProperty("movie.metadata"));
            //movieReader.setMetadata("movie_metadata.csv");

            return movieReader;
        }

        @Profile(MovieBuddyProfile.XML_MODE)
        @Bean
        public XmlMovieReader xmlMovieReader(Unmarshaller unmarshaller) {
            XmlMovieReader movieReader = new XmlMovieReader(unmarshaller);
            //xmlMovieReader.setMetadata("movie_metadata.xml");
            //movieReader.setMetadata(environment.getProperty("movie.metadata"));

            return movieReader;
        }*/

        /*@Primary
        @Bean
        public ProxyFactoryBean cachingMovieReader(ApplicationContext applicationContext) {
            //return new CachingMovieReader(cacheManager, movieReader);

            MovieReader movieReader = applicationContext.getBean(MovieReader.class);
            CacheManager cacheManager = applicationContext.getBean(CacheManager.class);

            ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
            proxyFactoryBean.setTarget(movieReader);
            //  클래스 프락시 비활성화 (프록시 클래스를 두번 호출하는 원리)
            // proxyFactoryBean.setProxyTargetClass(true);


            proxyFactoryBean.addAdvice(new CachingAdvice(cacheManager));

            return proxyFactoryBean;

        }*/

    }

    /*@Bean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        return new DefaultAdvisorAutoProxyCreator();
    }

    @Bean
    public Advisor cachingAdvisor(CacheManager cacheManager) {

        AnnotationMatchingPointcut pointcut = new AnnotationMatchingPointcut(null, CacheResult.class);

        Advice advice = new CachingAdvice(cacheManager);
        return new DefaultPointcutAdvisor(pointcut, advice);
    }*/

    /*@Bean
    public CachingAspect cachingAspect(CacheManager cacheManager) {
        return new CachingAspect(cacheManager);
    }*/


}
