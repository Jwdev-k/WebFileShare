package web.fileshare.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import web.fileshare.domain.FileDTO;
import web.fileshare.service.impl.FileServiceimpl;

@Controller
@Slf4j
public class FileController {

    @Autowired
    FileServiceimpl fs;

    @RequestMapping(value = "/service", method = {RequestMethod.GET, RequestMethod.POST})
    public String service(@RequestParam(value = "data",required = false)MultipartFile data) throws Exception {
        if (data != null) {
            fs.saveFile(new FileDTO(0, data.getOriginalFilename(), data.getBytes()));
            log.info(data.getOriginalFilename() + "파일이 업로드 되었습니다.");
        }
        return "service";
    }
}
