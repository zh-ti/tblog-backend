package com.tian.tblog.controller;

import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@RestController
@CrossOrigin(origins="*")
@RequestMapping("/tblog")
public class FileController {

    @RequestMapping("/upload/{folder}/{type}/{file}")
    public void getImage(  @PathVariable("folder") String folder,
                           @PathVariable("type") String type,
                           @PathVariable("file") String filename,
                           HttpServletResponse response
                       ) throws IOException {
        String path = "upload/" + folder + "/" + type + "/" + filename;
        File file = ResourceUtils.getFile("classpath:"+path);
        FileInputStream inputStream = new FileInputStream(file);
        byte[] bytes = new byte[inputStream.available()];
        inputStream.read(bytes, 0, inputStream.available());
        response.setContentType("image/jpeg");
        response.setCharacterEncoding("utf-8");
        response.getOutputStream().write(bytes);
    }

    @RequestMapping("/upload/{folder}/{type}/{file}/del")
    public void delImage(@PathVariable("folder") String folder,
                         @PathVariable("type") String type,
                         @PathVariable("file") String filename
                    ) throws FileNotFoundException {
        String path = "upload/" + folder + "/" + type + "/" + filename;
        File file = ResourceUtils.getFile("classpath:"+path);
        if(file.delete()){
            System.out.println(filename + ":删除成功");
        }else {
            System.out.println("删除失败");
        }
    }
}
