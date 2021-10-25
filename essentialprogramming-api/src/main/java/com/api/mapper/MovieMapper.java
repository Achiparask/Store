package com.api.mapper;

import com.api.entities.Movie;
import com.api.model.MovieInput;
import com.api.output.MovieJSON;

import java.util.Random;

public class MovieMapper {

    public static Movie inputToMovie(MovieInput input) {
        return Movie.builder()
                .name(input.getName())
                .build();
    }

    public static MovieJSON movieToJson(Movie movie) {
        String newCode = "";
        randomeCode(newCode);
        return MovieJSON.builder()
                .name(movie.getName())
                .build();
    }

    private static String randomeCode(String newCode) {

        newCode = generateRandomChars("ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890", 20);

        return newCode;
    }

    private static String generateRandomChars(String candidateChars, int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(candidateChars.charAt(random.nextInt(candidateChars
                    .length())));
        }

        return sb.toString();
    }
}
