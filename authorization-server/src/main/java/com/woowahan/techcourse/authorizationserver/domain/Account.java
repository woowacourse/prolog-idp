package com.woowahan.techcourse.authorizationserver.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column
    private String githubId;

    @Column
    private String githubUsername;

    @Column
    private String name;

    @Column
    private String nickname;

    @Column
    private String picture;

    @Column
    private String phoneNumber;

    protected Account() {}

    public Account(String email, String password, String githubId, String githubUsername, String name,
                   String nickname, String picture, String phoneNumber) {
        this.email = email;
        this.password = password;
        this.githubId = githubId;
        this.githubUsername = githubUsername;
        this.name = name;
        this.nickname = nickname;
        this.picture = picture;
        this.phoneNumber = phoneNumber;
    }

    public boolean incorrectPassword(String password) {
        return !this.password.equals(password);
    }

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getGithubId() {
        return githubId;
    }

    public String getGithubUsername() {
        return githubUsername;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public String getPicture() {
        return picture;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
