package com.kingname.insta.modules.tag;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TagRepository extends JpaRepository<Tag, Long> {
    boolean existsByName(String name);

    Tag findByName(String name);

    List<Tag> findAllByName(String name);
}
