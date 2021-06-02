package com.filehandling.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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

            File file = new File("D:/" + filename);
            multipartFile.transferTo(file);
        } catch(IOException e){
            e.printStackTrace();
        }

        return "redirect:/";

    }

}
