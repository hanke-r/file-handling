package com.filehandling.service;


import com.filehandling.entity.FileInfoTb;
import com.filehandling.file.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Transactional
@Service
@RequiredArgsConstructor
public class MainService {

    private final FileRepository fileRepository;

    public void setFileInfo(MultipartFile multipartFile) {
        FileInfoTb fileInfoTb = new FileInfoTb();

        fileInfoTb.setFileName(multipartFile.getOriginalFilename());
        fileInfoTb.setFileSize(multipartFile.getSize());
        fileInfoTb.setRegDate(new Date());

        fileRepository.save(fileInfoTb);


//        FileInfoTb fileInfoTb = FileInfoTb.builder()
//                .fileName(multipartFile.getOriginalFilename())
//                .fileSize(multipartFile.getSize())
//                .regDate(new Date())
//                .build();

    }

    public void setUpdateFile(MultipartFile multipartFile, FileInfoTb fileInfoTb) {
        fileInfoTb = fileRepository.findByFileName(multipartFile.getOriginalFilename());
        fileInfoTb.setRegDate(new Date());

    }

    public boolean fileComparison(String filename) {
        FileInfoTb fileInfoTb = new FileInfoTb();
        boolean file = fileRepository.existsByFileName(filename);

        return file;
    }


}
