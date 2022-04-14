package web.fileshare.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import web.fileshare.domain.UserDTO;
import web.fileshare.service.impl.LoginServiceimpl;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    LoginServiceimpl ls;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String login(HttpSession session, @RequestParam(value = "id", required = false) String id
            , @RequestParam(value = "password", required = false) String password) throws Exception {
        int result = ls.login(new UserDTO(id, password));
        if (result == 1) {
            session.setAttribute("userID", id);
            log.info("로그인 시도 감지.");
            return "redirect:service";
        } else {
            return "login";
        }
    }
}
