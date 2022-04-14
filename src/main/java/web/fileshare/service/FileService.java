package web.fileshare.service;

import web.fileshare.domain.FileDTO;

public interface FileService {
    void saveFile(FileDTO data) throws Exception;
}
