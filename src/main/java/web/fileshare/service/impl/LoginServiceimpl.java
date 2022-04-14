package web.fileshare.service.impl;

import org.springframework.stereotype.Service;
import web.fileshare.domain.UserDTO;
import web.fileshare.domain.repository.LoginDAO;
import web.fileshare.service.LoginService;

@Service
public class LoginServiceimpl implements LoginService {

    private static final LoginDAO ld = new LoginDAO();

    @Override
    public int login(UserDTO user) throws Exception {
        UserDTO result = ld.login(user);
        if (result == null) {
            return -1;
        } else {
            return 1;
        }
    }
}
