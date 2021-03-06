package web.fileshare.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import web.fileshare.domain.FileDTO;
import web.fileshare.security.CustomUserDetails;
import web.fileshare.service.impl.FileServiceimpl;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
@Slf4j
public class FileController {
    @Autowired
    private FileServiceimpl fs;

    @RequestMapping(value = "/service", method = {RequestMethod.GET, RequestMethod.POST})
    public String service(Model model, Authentication authentication
            , @RequestParam(value = "data",required = false) MultipartFile data) throws Exception {
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
            return "redirect:/service";
        }
        ArrayList<FileDTO> filelist = fs.fileList();
        if (!filelist.isEmpty()) {
            model.addAttribute("FileList", filelist);
        }
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        if (userDetails != null) {
            model.addAttribute("userID", userDetails.getUsername());
            Date nowDate = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd a HH:mm:ss");
            log.info(userDetails.getUsername() + "님이 서비스에 접속 " + simpleDateFormat.format(nowDate));
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

    @RequestMapping(value = "service/delete", method = RequestMethod.GET)
    public String deleteFile(@RequestParam(value = "num") int num) throws Exception {
        FileDTO getFile = fs.getFile(num);
        File deleteFile= new File(getFile.getDataPath());
        if (deleteFile.exists()) {
            deleteFile.delete();
            fs.deleteFile(num);
            log.info(num + "번호의 파일을 삭제하였습니다.");
        }
        return "redirect:/service";
    }
}
