package com.tinklingshrew.forum_be.dtos;


public class PostDTO implements Comparable<PostDTO> {
    private Long id;

    private Long karma;

    private String title;

    private String content;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getKarma() {
        return karma;
    }

    public void setKarma(Long karma) {
        this.karma = karma;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    @Override
    public int compareTo(PostDTO o) {
        if(this.id == o.getId()) {
            return 0;
        }
        else if(this.id > o.getId()) {
            return 1;
        }
        else {
            return -1;
        }

    }
}
