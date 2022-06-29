package com.mosken.rodrigo.letscode.challenge.cinecriticas.adapters.mysql.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "Comment")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommentBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "movie_id")
    private String movieId;

    private String username;

    private String text;

    private int likes;

    private int dislikes;

    @OneToOne
    @JoinColumn(name = "comment_reference", referencedColumnName = "id")
    private CommentBean commentReference;

    @OneToOne
    @JoinColumn(name = "comment_reply", referencedColumnName = "id")
    private CommentBean commentReply;

    private boolean repeated;


}