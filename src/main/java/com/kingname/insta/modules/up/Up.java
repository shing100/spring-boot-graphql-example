package com.kingname.insta.modules.up;

import com.kingname.insta.modules.user.Account;
import com.kingname.insta.modules.post.Post;
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
    @OneToOne(targetEntity = Account.class)
    private Account account;

    @Nonnull
    @GraphQLQuery(name = "post")
    @OneToOne(targetEntity = Post.class)
    private Post post;
}
