package web.fileshare.domain.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import web.fileshare.domain.FileDTO;

import java.util.ArrayList;

public interface FileMapper {
    @Insert("INSERT INTO filedata VALUES(null, #{filename}, #{dataPath}, #{size})")
    void saveFile(FileDTO data) throws Exception;

    @Select("SELECT * FROM filedata WHERE num = #{num}")
    FileDTO getFile(int num) throws Exception;

    @Select("SELECT num,filename,size FROM filedata")
    ArrayList<FileDTO> fileList() throws Exception;

    @Delete("DELETE FROM filedata WHERE num = #{num} ")
    void deleteFile(int num) throws Exception;
}
