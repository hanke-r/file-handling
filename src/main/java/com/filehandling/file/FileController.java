package com.filehandling.file;


import com.filehandling.entity.FileInfoTb;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class FileController {

    private final FileRepository fileRepository;

    @GetMapping("/fileList")
    public String fileListView(Model model){

        List<FileInfoTb> list = fileRepository.findAll();

        model.addAttribute("list", list);

        return "fileList";
    }


}
