package web.fileshare.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import web.fileshare.domain.FileDTO;
import web.fileshare.service.impl.FileServiceimpl;

import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;

@Controller
@Slf4j
public class FileController {

    @Autowired
    FileServiceimpl fs;

    @RequestMapping(value = "/service", method = {RequestMethod.GET, RequestMethod.POST})
    public String service(Model model, @RequestParam(value = "data",required = false)MultipartFile data) throws Exception {
        if (data != null) {
            fs.saveFile(new FileDTO(0, data.getOriginalFilename(), data.getBytes(), (int) data.getSize()));
            log.info(data.getOriginalFilename() + "파일이 업로드 되었습니다.");
            return "redirect:service";
        }
        ArrayList<FileDTO> filelist = fs.fileList();
        if (!filelist.isEmpty()) {
            model.addAttribute("FileList", filelist);
        }
        return "service";
    }

    @RequestMapping(value = "service/download")
    public void fileDown(HttpServletResponse response, @RequestParam(value = "num") int num) throws Exception {
        FileDTO getFile = fs.getFile(num);
        response.setContentType("application/octet-stream");
        response.setContentLength(getFile.getData().length);
        response.setHeader("Content-Disposition",  "attachment; fileName=\""+ URLEncoder.encode(getFile.getFilename(), "UTF-8")+"\";");
        response.getOutputStream().write(getFile.getData());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }

}
