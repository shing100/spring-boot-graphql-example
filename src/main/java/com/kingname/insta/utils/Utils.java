package com.kingname.insta.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

@Component
public class Utils {

    public List<String> adjectives;
    public List<String> nouns;

    @PostConstruct
    public void loadWordList() throws IOException {
        ClassPathResource resource = new ClassPathResource("adjectives.txt");
        adjectives.addAll(Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8));
        resource = new ClassPathResource("nouns.txt");
        nouns.addAll(Files.readAllLines(resource.getFile().toPath(), StandardCharsets.UTF_8));
    }

    public String secretGenerator() {
        int randomNumber = (int) Math.floor(Math.random() * adjectives.size());
        String adjective = adjectives.get(randomNumber);
        randomNumber = (int) Math.floor(Math.random() * adjectives.size());
        String noun = nouns.get(randomNumber);
        return adjective + " " + noun;
    }
}
