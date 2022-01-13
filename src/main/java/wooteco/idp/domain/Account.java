package wooteco.idp.domain;

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
    private String username;

    @Column(nullable = false, unique = true)
    private String githubId;

    @Column(nullable = false)
    private String githubUsername;

    @Column
    private String name;

    @Column
    private String nickname;

    @Column
    private String picture;

    @Column
    private String email;

    @Column
    private String phoneNumber;

    protected Account() {}

    public Account(Long id, String username, String githubId, String githubUsername, String name, String nickname,
                   String picture, String email, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.githubId = githubId;
        this.githubUsername = githubUsername;
        this.name = name;
        this.nickname = nickname;
        this.picture = picture;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
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

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
