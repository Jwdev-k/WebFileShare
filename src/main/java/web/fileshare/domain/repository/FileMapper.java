package web.fileshare.domain.repository;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import web.fileshare.domain.FileDTO;

import java.util.ArrayList;

public interface FileMapper {
    @Insert("INSERT INTO filedata VALUES(null, #{filename}, #{data})")
    void saveFile(FileDTO data) throws Exception;

    @Select("SELECT * FROM filedata WHERE num = #{num}")
    FileDTO getFile(int num) throws Exception;

    @Select("SELECT * FROM ( SELECT * FROM boardlist LIMIT #{start}, 10 ) sub ORDER BY bno DESC")
    ArrayList<FileDTO> fileList() throws Exception;
}
