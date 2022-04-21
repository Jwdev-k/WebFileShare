package web.fileshare.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import web.fileshare.Enum.ERoleType;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserDTO {
    private String id;
    private String password;
    private ERoleType role;
}
