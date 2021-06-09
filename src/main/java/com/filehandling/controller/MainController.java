package com.filehandling.controller;

import com.filehandling.entity.FileInfoTb;
import com.filehandling.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final MainService mainService;

    @GetMapping("/")
    public String main(){
        return "index";
    }

    @PostMapping("/ioFile")
    public String fileSubmit(@RequestParam("uploadFile") MultipartFile multipartFile, RedirectAttributes redirectAttributes){
        try{
            String filename = multipartFile.getOriginalFilename();
            FileInfoTb fileInfoTb = new FileInfoTb();
            File file = new File("D:"+ File.separator + filename);
            multipartFile.transferTo(file);
            redirectAttributes.addFlashAttribute("message", filename + "파일이 성공적으로 등록되었습니다.");
            if(mainService.fileComparison(filename)){
                mainService.setUpdateFile(multipartFile, fileInfoTb);
            } else{
                mainService.setFileInfo(multipartFile);
            }

        } catch(IOException e){
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("message", "등록에 실패하였습니다. 다시 시도해주세요.");
        }

        return "redirect:/";
    }

    @PostMapping("/nioFile")
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
