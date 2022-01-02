package moviebuddy.domain;

import moviebuddy.MovieBuddyFactory;
import moviebuddy.MovieBuddyProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

/**
 * @author springrunner.kr@gmail.com
 */
@ActiveProfiles(MovieBuddyProfile.CSV_MODE)
@SpringJUnitConfig(MovieBuddyFactory.class)
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = MovieBuddyFactory.class)
public class MovieFinderTest {

    //ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MovieBuddyFactory.class);

    //MovieBuddyFactory movieBuddyFactory = new MovieBuddyFactory();
    //MovieFinder application = movieBuddyFactory.movieFinder();
    //MovieFinder application = applicationContext.getBean(MovieFinder.class);

    /*MovieFinderTest(MovieFinder movieFinder) {
        this.application = movieFinder;
    }*/

    @Autowired
    MovieFinder application;

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
