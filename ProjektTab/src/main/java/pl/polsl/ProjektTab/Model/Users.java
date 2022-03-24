package pl.polsl.ProjektTab.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@Data
public class Users {
    private Long userId;
    private String permission;
    private String login;
    private String password;
    private String adres;

    public Users(@JsonProperty("userId") Long userId,
                 @JsonProperty("permission") String permission,
                 @JsonProperty("login") String login,
                 @JsonProperty("password") String password,
                 @JsonProperty("adres") String adres) {
        this.userId = userId;
        this.permission = permission;
        this.login = login;
        this.password = password;
        this.adres = adres;
    }

}
