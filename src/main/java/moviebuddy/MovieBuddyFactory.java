package moviebuddy;

import org.springframework.context.annotation.*;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;


@Configuration
@PropertySource("/application.properties")
@ComponentScan(basePackages = {"moviebuddy"})
@Import({MovieBuddyFactory.DomainModuleConfig.class, MovieBuddyFactory.DataSourceModuleConfig.class})
public class MovieBuddyFactory {

    @Bean
    public Jaxb2Marshaller jaxb2Marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("moviebuddy");

        return marshaller;
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
    }

}
