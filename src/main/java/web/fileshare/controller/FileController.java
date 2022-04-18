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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.ArrayList;

@Controller
@Slf4j
public class FileController {

    @Autowired
    private FileServiceimpl fs;

    @RequestMapping(value = "/service", method = {RequestMethod.GET, RequestMethod.POST})
    public String service(Model model, HttpServletRequest request, @RequestParam(value = "data",required = false) MultipartFile data) throws Exception {
        if (data != null) {
            //String rootDirectory = request.getSession().getServletContext().getRealPath("/");
            try {
                File folder = new File(System.getProperty("user.dir") + File.separator + "data");
                try {
                    if (!folder.exists()) {
                        folder.mkdir();
                        log.info("폴더를 생성하였습니다.");
                    }
                } catch (Exception e) {
                    throw new RuntimeException("create folder failed.", e);
                }
                File savePath = new File(folder + File.separator + data.getOriginalFilename());
                data.transferTo(savePath);
                fs.saveFile(new FileDTO(0, data.getOriginalFilename(), savePath.toString(), (int) data.getSize()));
                log.info(data.getOriginalFilename() + "파일이 업로드 되었습니다.");
            } catch (RuntimeException e) {
                e.getStackTrace();
            }
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
        FileInputStream inputStream = new FileInputStream(getFile.getDataPath());
        response.setContentType("application/octet-stream");
        response.setContentLength(getFile.getSize());
        response.setHeader("Content-Disposition",  "attachment; fileName=\""+ URLEncoder.encode(getFile.getFilename(), "UTF-8")+"\";");
        response.getOutputStream().write(inputStream.readAllBytes());
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }
}
