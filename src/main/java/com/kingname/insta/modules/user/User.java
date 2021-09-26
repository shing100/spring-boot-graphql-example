package com.kingname.insta.modules.user;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;

import javax.annotation.Nonnull;
import javax.persistence.*;
import java.util.ArrayList;
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

    @Enumerated(EnumType.STRING)
    private Authority authority = Authority.ROLE_USER;

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

    @GraphQLQuery(name = "loginSecret")
    private String loginSecret;

    @GraphQLQuery(name = "following")
    @ManyToMany
    private List<User> following = new ArrayList<>();

    @GraphQLQuery(name = "followers")
    @ManyToMany
    private List<User> followers  = new ArrayList<>();
}
