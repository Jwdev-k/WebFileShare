package web.fileshare.domain.repository;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;
import web.fileshare.domain.UserDTO;

import java.io.InputStream;

@Repository
public class LoginDAO implements LoginMapper {

    private static SqlSession getSqlSession() throws Exception {
        String resource = "java-mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSessionFactory.getConfiguration().addMapper(LoginMapper.class);
        return sqlSessionFactory.openSession(true); // true 자동 커밋
    }

    @Override
    public UserDTO login(UserDTO user) throws Exception {
        var mapper = getSqlSession().getMapper(LoginMapper.class);
        getSqlSession().close();
        return mapper.login(user);
    }
}
