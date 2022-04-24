package web.fileshare.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
@Slf4j
public class LoginController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String login(HttpSession session) {
        String userID = (String) session.getAttribute("userID");
        if (userID != null)
            return "redirect:/service";
        else
            return "login";
    }
}
