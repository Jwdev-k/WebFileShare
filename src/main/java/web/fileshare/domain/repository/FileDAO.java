package web.fileshare.domain.repository;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.stereotype.Repository;
import web.fileshare.domain.FileDTO;

import java.io.InputStream;
import java.util.ArrayList;

@Repository
public class FileDAO implements FileMapper {

    private static SqlSession getSqlSession() throws Exception {
        String resource = "java-mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        sqlSessionFactory.getConfiguration().addMapper(FileMapper.class);
        return sqlSessionFactory.openSession(true); // true 자동 커밋
    }

    @Override
    public void saveFile(FileDTO data) throws Exception {
        var mapper = getSqlSession().getMapper(FileMapper.class);
        getSqlSession().close();
        mapper.saveFile(data);
    }

    @Override
    public FileDTO getFile(int num) throws Exception {
        var mapper = getSqlSession().getMapper(FileMapper.class);
        getSqlSession().close();
        return mapper.getFile(num);
    }

    @Override
    public ArrayList<FileDTO> fileList() throws Exception {
        var mapper = getSqlSession().getMapper(FileMapper.class);
        getSqlSession().close();
        return mapper.fileList();
    }
}
