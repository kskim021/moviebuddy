package moviebuddy.domain;

import moviebuddy.domain.domain.CsvMovieReader;
import moviebuddy.domain.domain.MovieFinder;

public class MovieBuddyFactory {

    public MovieFinder movieFinder() {
        return new MovieFinder(new CsvMovieReader());
    }

}
