package web.fileshare.domain.repository;

import org.apache.ibatis.annotations.Select;
import web.fileshare.domain.UserDTO;

public interface LoginMapper {
    @Select("SELECT * FROM user WHERE id = #{id} AND password = #{password}")
    UserDTO login(UserDTO user) throws Exception;
    @Select("SELECT * FROM user WHERE id = #{id}")
    UserDTO loginSecurity(String id) throws Exception;
}
