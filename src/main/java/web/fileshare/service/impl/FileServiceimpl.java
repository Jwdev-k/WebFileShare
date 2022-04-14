package web.fileshare.service.impl;

import org.springframework.stereotype.Service;
import web.fileshare.domain.FileDTO;
import web.fileshare.domain.repository.FileDAO;
import web.fileshare.service.FileService;

@Service
public class FileServiceimpl implements FileService {

    private static final FileDAO fd = new FileDAO();

    @Override
    public void saveFile(FileDTO data) throws Exception {
        fd.saveFile(data);
    }
}
