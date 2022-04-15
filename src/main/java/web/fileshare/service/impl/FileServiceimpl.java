package web.fileshare.service.impl;

import org.springframework.stereotype.Service;
import web.fileshare.domain.FileDTO;
import web.fileshare.domain.repository.FileDAO;
import web.fileshare.service.FileService;

import java.util.ArrayList;

@Service
public class FileServiceimpl implements FileService {

    private static final FileDAO fd = new FileDAO();

    @Override
    public void saveFile(FileDTO data) throws Exception {
        fd.saveFile(data);
    }

    @Override
    public FileDTO getFile(int num) throws Exception {
        return fd.getFile(num);
    }

    @Override
    public ArrayList<FileDTO> fileList() throws Exception {
        return fd.fileList();
    }
}
