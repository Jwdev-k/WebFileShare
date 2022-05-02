package web.fileshare.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.fileshare.domain.FileDTO;
import web.fileshare.domain.repository.FileDAO;
import web.fileshare.service.FileService;

import java.util.ArrayList;

@Service
@Transactional
public class FileServiceimpl implements FileService {

    @Autowired
    private FileDAO fd;

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

    @Override
    public void deleteFile(int num) throws Exception {
        fd.deleteFile(num);
    }
}
