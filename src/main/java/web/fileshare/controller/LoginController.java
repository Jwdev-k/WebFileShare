package web.fileshare.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.fileshare.Enum.ERoleType;
import web.fileshare.domain.UserDTO;
import web.fileshare.service.impl.LoginSecurityService;
import web.fileshare.service.impl.LoginServiceimpl;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private LoginServiceimpl ls;
    @Autowired
    private LoginSecurityService loginSecurity;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public void login(@RequestParam(value = "id", required = false) String id
            , @RequestParam(value = "password", required = false) String password) throws Exception {
        UserDTO user = new UserDTO(id,password, ERoleType.USER);
        int login = ls.login(user);
        if (login == 1) {
            loginSecurity.loadUserByUsername(id);
        }
    }
}
