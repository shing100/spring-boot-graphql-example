package com.kingname.insta.up;

import com.kingname.insta.user.User;
import com.kingname.insta.post.Post;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;

import javax.annotation.Nonnull;
import javax.persistence.*;


@Entity
@Getter @Setter
@EqualsAndHashCode(of = "id")
@Builder @AllArgsConstructor @NoArgsConstructor @ToString
public class Up {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GraphQLQuery(name = "id")
    private Long id;

    @Nonnull
    @GraphQLQuery(name = "user")
    @OneToOne(targetEntity = User.class)
    private User user;

    @Nonnull
    @GraphQLQuery(name = "post")
    @OneToOne(targetEntity = Post.class)
    private Post post;
}
