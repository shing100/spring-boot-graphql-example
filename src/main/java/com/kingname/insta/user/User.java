package com.kingname.insta.user;

import com.kingname.insta.comment.Comment;
import com.kingname.insta.up.Up;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;

import javax.annotation.Nonnull;
import javax.persistence.*;
import java.util.List;


@Entity
@Getter @Setter
@EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor @ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GraphQLQuery(name = "id")
    private Long id;

    @Nonnull
    @GraphQLQuery(name = "username")
    private String username;

    @Nonnull
    @Column(unique = true, nullable = false)
    @GraphQLQuery(name = "email")
    private String email;

    @GraphQLQuery(name = "firstName")
    private String firstName;

    @GraphQLQuery(name = "lastName")
    private String lastName;

    @GraphQLQuery(name = "bio")
    private String bio;

    @GraphQLQuery(name = "password")
    private String password;

    @GraphQLQuery(name = "comments")
    @ManyToMany
    private List<Comment> comments;

    @GraphQLQuery(name = "likes")
    @ManyToMany
    private List<Up> ups;

    @GraphQLQuery(name = "following")
    @ManyToMany
    private List<User> following;

    @GraphQLQuery(name = "followers")
    @ManyToMany
    private List<User> followers;
}
