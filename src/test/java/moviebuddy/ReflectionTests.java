package moviebuddy;

import moviebuddy.data.CsvMovieReader;
import moviebuddy.domain.MovieReader;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.core.io.DefaultResourceLoader;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ReflectionTests {

    @Test
    void objectCreateAndMethodCall() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        // without reflection
        Duck duck = new Duck();
        duck.quack();

        // with reflection
        Class<?> duckClass = Class.forName("moviebuddy.ReflectionTests$Duck");
        Object duckObject = duckClass.getDeclaredConstructor().newInstance();
        Method quackMethod = duckObject.getClass().getDeclaredMethod("quack", new Class<?>[0]);
        quackMethod.invoke(duckObject);


    }

    static class Duck {
        void quack() {
            System.out.println("꽥꽥");
        }
    }


    @Test
    void useDynamicProxy() throws Exception {
        CsvMovieReader csvMovieReader = new CsvMovieReader();
        csvMovieReader.setResourceLoader(new DefaultResourceLoader());
        csvMovieReader.setMetadata("movie_metadata.csv");
        csvMovieReader.afterPropertiesSet();

        ClassLoader classLoader = ReflectionTests.class.getClassLoader();
        Class<?>[] interfaces = new Class[]{MovieReader.class};
        InvocationHandler invocationHandler = new PerformanceInvocationHandler(csvMovieReader);

        MovieReader movieReader = (MovieReader) Proxy.newProxyInstance(classLoader, interfaces, invocationHandler);

        movieReader.loadMovies();
        movieReader.loadMovies();

    }

    static class PerformanceInvocationHandler implements InvocationHandler {

        Logger log = LoggerFactory.getLogger(getClass());
        Object target;

        PerformanceInvocationHandler(Object target) {
            this.target = target;
        }


        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            long start = System.currentTimeMillis();
            Object result = method.invoke(target, args);
            long end = System.currentTimeMillis() - start;

            log.info("Excution {} method finshed in {} ms", method.getName(), end);

            return result;
        }
    }


}
