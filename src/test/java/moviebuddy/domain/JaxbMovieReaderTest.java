package moviebuddy.domain;

import moviebuddy.domain.domain.JaxbMovieReader;
import moviebuddy.domain.domain.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JaxbMovieReaderTest {

    @Test
    void NotEmpty_LoadedMovies() {
        JaxbMovieReader movieReader = new JaxbMovieReader();

        List<Movie> movies = movieReader.loadMovies();
        Assertions.assertEquals(1375, movies.size());

        // MovieFinderTest.assertEquals(1375, movies.size());

    }
}
