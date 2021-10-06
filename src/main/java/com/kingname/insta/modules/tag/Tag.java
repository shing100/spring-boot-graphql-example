package com.kingname.insta.modules.tag;

import io.leangen.graphql.annotations.GraphQLQuery;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity @ToString
@AllArgsConstructor @NoArgsConstructor @Builder
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @GraphQLQuery(name = "id")
    private Long id;

    @GraphQLQuery(name = "name")
    private String name;
}
