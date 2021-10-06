package com.kingname.insta.modules.tag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {

    private final TagRepository tagRepository;

    public Tag createTag(String name) {
        if(!tagRepository.existsByName(name)) {
            Tag save = tagRepository.save(Tag.builder().name(name).build());
        }
        return tagRepository.findByName(name);
    }

    public List<Tag> findAllByName(String name) {
        return tagRepository.findAllByName(name);
    }

}
