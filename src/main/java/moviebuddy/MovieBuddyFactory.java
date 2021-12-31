package moviebuddy;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;


@Configuration
@ComponentScan(basePackages = {"moviebuddy"})
@Import({MovieBuddyFactory.DomainModuleConfig.class, MovieBuddyFactory.DataSourceModuleConfig.class})
public class MovieBuddyFactory {

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

/*        @Bean
        public MovieReader movieReader() {
            return new CsvMovieReader();
        }*/
    }

}
