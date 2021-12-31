package moviebuddy.data;

import moviebuddy.MovieBuddyFactory;
import moviebuddy.MovieBuddyProfile;
import moviebuddy.data.JaxbMovieReader;
import moviebuddy.domain.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@ActiveProfiles(MovieBuddyProfile.XML_MODE)
@SpringJUnitConfig(MovieBuddyFactory.class)
public class JaxbMovieReaderTest {

    @Autowired
    JaxbMovieReader jaxbMovieReader;

    @Test
    void NotEmpty_LoadedMovies() {
        // JaxbMovieReader movieReader = new JaxbMovieReader();
        // List<Movie> movies = movieReader.loadMovies();

        List<Movie> movies = jaxbMovieReader.loadMovies();
        Assertions.assertEquals(1375, movies.size());

        // MovieFinderTest.assertEquals(1375, movies.size());

    }
}
