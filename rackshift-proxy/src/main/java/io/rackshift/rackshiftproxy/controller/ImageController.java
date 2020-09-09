package io.rackshift.rackshiftproxy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Date;

@Controller("image")
@RequestMapping("image")
public class ImageController {
    @RequestMapping(value = "/upload")
    @ResponseBody
    public void upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException {
        String fileName = file.getOriginalFilename();
//        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1); // 后缀: "xls"
        String path = "/opt/fit2cloud/rackhd/files/mount/common/" + fileName;
        String date = String.valueOf(new Date().getTime());
        if (!new File(path).exists()) {
            new File(path).mkdir();
        }
        FileOutputStream fs = new FileOutputStream(path);
        byte[] buffer = new byte[1024 * 1024];
        int bytesum = 0;
        int byteread = 0;
        InputStream stream = file.getInputStream();
        while ((byteread = stream.read(buffer)) != -1) {
            bytesum += byteread;
            fs.write(buffer, 0, byteread);
            fs.flush();
        }
        fs.close();

        System.out.println(request);

    }
}