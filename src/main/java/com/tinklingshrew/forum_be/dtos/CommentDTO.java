package com.tinklingshrew.forum_be.dtos;

public class CommentDTO implements Comparable<CommentDTO>{
    private Long id;

    private String content;

    private Long userId;

    private Long postId;

    private Long parentId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    @Override
    public int compareTo(CommentDTO o) {
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
