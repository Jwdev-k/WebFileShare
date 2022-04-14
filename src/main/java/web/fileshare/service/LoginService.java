package web.fileshare.service;

import web.fileshare.domain.UserDTO;

public interface LoginService {
    int login(UserDTO user) throws Exception;
}
