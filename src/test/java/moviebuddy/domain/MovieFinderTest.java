package moviebuddy.domain;

import moviebuddy.domain.domain.Movie;
import moviebuddy.domain.domain.MovieFinder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * @author springrunner.kr@gmail.com
 */
public class MovieFinderTest {

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MovieBuddyFactory.class);

    MovieBuddyFactory movieBuddyFactory = new MovieBuddyFactory();
    //MovieFinder application = movieBuddyFactory.movieFinder();
    MovieFinder application = applicationContext.getBean(MovieFinder.class);

    @Test
    void NotEmpty_directedBy() {
        List<Movie> movies = application.directedBy("Michael Bay");
        Assertions.assertEquals(3, movies.size());
    }

    @Test
    void NotEmpty_ReleasedYearBy() {
        List<Movie> movies = application.releasedYearBy(2015);
        Assertions.assertEquals(225, movies.size());
    }

}
