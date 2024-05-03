package ru.job4j.gc.leak;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class PostStore {

    private final Map<Integer, Post> posts;

    private final AtomicInteger atomicInteger = new AtomicInteger(1);

    public PostStore() {
        posts = new HashMap<>();
    }

    public Post add(Post post) {
        Integer id = atomicInteger.getAndIncrement();
        post.setId(id);
        posts.put(id, post);
        return post;
    }

    public void removeAll() {
        posts.clear();
    }

    public Collection<Post> getPosts() {
        return posts.values();
    }
}