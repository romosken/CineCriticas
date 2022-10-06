package com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.db;


import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.exceptions.UserException;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;


@Builder
@Data
public class Rating {

    private int id;
    private String username;
    private String movieId;
    private int rate;

    public Rating(int id, String movieId, String username, int rate) {
        setId(id);
        setMovieId(movieId);
        setUsername(username);
        setRate(rate);
    }

    public void setId(int id) {
        validateAttributeObject(id, "RATING_ID");
        this.id = id;
    }

    public void setMovieId(String movieId) {
        validateAttribute(movieId, "MOVIE_ID");
        this.movieId = movieId;
    }

    public void setUsername(String username) {
        validateAttribute(username, "USERNAME");
        this.username = username;
    }

    public void setRate(int rate) {
        validateAttributeObject(rate, "RATING");
        this.rate = rate;
    }

    private void validateAttribute(String attribute, String attributeName) {
        if (Objects.isNull(attribute) || attribute.isBlank())
            throw new UserException(attributeName + " cannot be null or empty!");

    }

    private void validateAttributeObject(Object attribute, String attributeName) {
        if (Objects.isNull(attribute))
            throw new UserException(attributeName + " cannot be null or empty!");

    }

    public static class RatingBuilder {
        public Rating build() {
            return new Rating(
                    id,
                    username,
                    movieId,
                    rate
            );
        }
    }
}