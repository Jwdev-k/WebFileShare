package web.fileshare.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import web.fileshare.Enum.ERoleType;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@ToString
public class UserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = -4028176609335643413L;
    private String id;
    private String password;
    private ERoleType role;
}
