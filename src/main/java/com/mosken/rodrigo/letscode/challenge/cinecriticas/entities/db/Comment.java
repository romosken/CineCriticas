package com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.db;


import com.mosken.rodrigo.letscode.challenge.cinecriticas.entities.exceptions.UserException;
import lombok.Builder;
import lombok.Data;

import java.util.Objects;

@Builder
@Data
public class Comment {

    private int id;
    private String movieId;
    private String username;
    private String text;
    private int likes;
    private int dislikes;
    private int commentReference;
    private int commentReply;
    private boolean repeated;

    public Comment(int id, String movieId, String username, String text, int likes, int dislikes, int commentReference, int commentReply, boolean repeated) {
       setId(id);
       setMovieId(movieId);
       setUsername(username);
       setText(text);
       setLikes(likes);
       setDislikes(dislikes);
       setCommentReference(commentReference);
       setCommentReply(commentReply);
       setRepeated(repeated);
    }

    public void setId(int id) {
        validateAttributeObject(id, "COMMENT_ID");
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

    public void setText(String text) {
        validateAttribute(text, "COMMENT_TEXT");
        this.text = text;
    }

    public void setLikes(int likes) {
        validateAttributeObject(likes, "LIKES");
        this.likes = likes;
    }

    public void setDislikes(int dislikes) {
        validateAttributeObject(dislikes, "DISLIKES");
        this.dislikes = dislikes;
    }

    public void setRepeated(boolean repeated) {
        validateAttributeObject(repeated, "REPEATED");
        this.repeated = repeated;
    }
    private void validateAttribute(String attribute, String attributeName) {
        if (Objects.isNull(attribute) || attribute.isBlank())
            throw new UserException(attributeName + " cannot be null or empty!");

    }

    private void validateAttributeObject(Object attribute, String attributeName) {
        if (Objects.isNull(attribute))
            throw new UserException(attributeName + " cannot be null or empty!");

    }

    public static class CommentBuilder {
        public Comment build() {
            return new Comment(
                    id,
                    username,
                    movieId,
                    text,
                    likes,
                    dislikes,
                    commentReference,
                    commentReply,
                    repeated
            );
        }
    }

}