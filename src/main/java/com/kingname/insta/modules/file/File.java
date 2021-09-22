package com.kingname.insta.modules.file;

import com.kingname.insta.modules.post.Post;
import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.annotation.Nonnull;
import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@ToString
public class File {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GraphQLQuery(name = "id")
    private Long id;

    @Nonnull
    @GraphQLQuery(name = "url")
    private String url;

    @Nonnull
    @GraphQLQuery(name = "post")
    @ManyToOne(targetEntity = Post.class)
    private Post post;
}
