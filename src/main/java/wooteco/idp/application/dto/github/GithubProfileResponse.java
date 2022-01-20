package wooteco.idp.application.dto.github;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import wooteco.idp.domain.Account;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class GithubProfileResponse {

    @JsonProperty("login")
    private String loginName;
    @JsonProperty("name")
    private String nickname;
    @JsonProperty("id")
    private String githubId;
    @JsonProperty("avatar_url")
    private String imageUrl;

    public Account toAccount() {
        // TODO : get user information from Github
        return null;
    }
}
