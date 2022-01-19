package moviebuddy.data;

import moviebuddy.domain.Movie;
import moviebuddy.domain.MovieReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CachingMovieReaderTest {


    @Test
    void caching() {
        CacheManager cacheManager = new ConcurrentMapCacheManager();
        MovieReader movieReader = new DummyMovieReader();

        CachingMovieReader movieReader1 = new CachingMovieReader(cacheManager, movieReader);

        Assertions.assertNull(movieReader1.getCachedData());

        List<Movie> movies = movieReader1.loadMovies();
        Assertions.assertNotNull(movieReader1.getCachedData());
        Assertions.assertSame(movieReader1.loadMovies(), movies);


    }

    class DummyMovieReader implements MovieReader{

        @Override
        public List<Movie> loadMovies() {
            return new ArrayList<>();
        }
    }


}