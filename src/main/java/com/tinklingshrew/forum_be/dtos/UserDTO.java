package com.tinklingshrew.forum_be.dtos;


import java.util.Date;

public class UserDTO implements Comparable<UserDTO> {
    private Long id;

    private String role;

    private Long karma;

    private String description;

    private String username;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;

    private Date date_of_creation;

    private String password;

    private String pfp_url;

    private String header_url;

    public UserDTO() {
    }

    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Long getKarma() {
        return karma;
    }

    public void setKarma(Long karma) {
        this.karma = karma;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getDate_of_creation() {
        return date_of_creation;
    }

    public void setDate_of_creation(Date date_of_creation) {
        this.date_of_creation = date_of_creation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPfp_url() {
        return pfp_url;
    }

    public void setPfp_url(String pfp_url) {
        this.pfp_url = pfp_url;
    }

    public String getHeader_url() {
        return header_url;
    }

    public void setHeader_url(String header_url) {
        this.header_url = header_url;
    }

    @Override
    public int compareTo(UserDTO o) {
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
