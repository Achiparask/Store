package com.api.service;

import com.api.entities.Movie;
import com.api.mapper.MovieMapper;
import com.api.model.MovieInput;
import com.api.output.MovieJSON;
import com.api.repository.MovieRepository;
import com.aventrix.jnanoid.jnanoid.NanoIdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional
    public MovieJSON addMovie(final MovieInput movieInput) {

        final Movie movie = MovieMapper.inputToMovie(movieInput);
        movie.setCode(NanoIdUtils.randomNanoId());

        movieRepository.save(movie);
        return MovieMapper.movieToJson(movie);
    }
}
