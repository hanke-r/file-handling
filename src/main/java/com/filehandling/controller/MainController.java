package com.filehandling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class MainController {

    @GetMapping("/")
    public String main(){
        return "index";
    }

    @PostMapping("/ioFile")
    public String fileSubmit(@RequestParam("uploadFile") MultipartFile multipartFile){
        try{
            String filename = multipartFile.getOriginalFilename();
            System.out.println(filename);

            File file = new File("D:"+ File.separator + filename);
            multipartFile.transferTo(file);
        } catch(IOException e){
            e.printStackTrace();
        }

        return "redirect:/";
    }

    @PostMapping("nioFile")
    public String nioFileSubmit(@RequestParam("nioUploadFile") MultipartFile multipartFile){
        Path path = Paths.get("D:" + File.separator + StringUtils.cleanPath(multipartFile.getOriginalFilename()));

        try{
            Files.copy(multipartFile.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        } catch(IOException e){
            e.printStackTrace();
        }

        return "redirect:/";
    }
}
