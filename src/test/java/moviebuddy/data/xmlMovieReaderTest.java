package moviebuddy.data;

import moviebuddy.MovieBuddyFactory;
import moviebuddy.MovieBuddyProfile;
import moviebuddy.domain.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

@ActiveProfiles(MovieBuddyProfile.XML_MODE)
@SpringJUnitConfig(MovieBuddyFactory.class)
@TestPropertySource(properties = "movie.metadata=movie_metadata.xml")
public class xmlMovieReaderTest {

    @Autowired
    XmlMovieReader xmlMovieReader;

    @Test
    void NotEmpty_LoadedMovies() {
        // JaxbMovieReader movieReader = new JaxbMovieReader();
        // List<Movie> movies = movieReader.loadMovies();

        List<Movie> movies = xmlMovieReader.loadMovies();
        Assertions.assertEquals(1375, movies.size());

        // MovieFinderTest.assertEquals(1375, movies.size());

    }

}
