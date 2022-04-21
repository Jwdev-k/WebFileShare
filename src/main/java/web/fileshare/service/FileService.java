package web.fileshare.service;

import web.fileshare.domain.FileDTO;

import java.util.ArrayList;

public interface FileService {
    void saveFile(FileDTO data) throws Exception;
    FileDTO getFile(int num) throws Exception;
    ArrayList<FileDTO> fileList() throws Exception;
    void deleteFile(int num) throws Exception;
}
