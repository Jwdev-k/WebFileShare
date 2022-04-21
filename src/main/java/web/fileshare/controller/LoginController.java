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

import javax.servlet.http.HttpSession;

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
    public void login(HttpSession session, @RequestParam(value = "id", required = false) String id
            , @RequestParam(value = "password", required = false) String password) throws Exception {
        var user = ls.login(new UserDTO(id,password, ERoleType.USER));
        if (user == 1) {
            loginSecurity.loadUserByUsername(id);
            //session.setAttribute("userID", id);
        }
    }
}
