package wooteco.idp.application.dto.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import wooteco.idp.domain.Account;

@AllArgsConstructor
@NoArgsConstructor
public class GithubProfileResponse {

    @JsonProperty("name")
    private String nickname;
    @JsonProperty("login")
    private String loginName;
    @JsonProperty("id")
    private String githubId;
    @JsonProperty("avatar_url")
    private String imageUrl;

    public Account toAccount() {
        // TODO : get user information from Github
        return null;
    }

    public String getLoginName() {
        return loginName;
    }

    public String getNickname() {
        return nickname;
    }

    public String getGithubId() {
        return githubId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

}
