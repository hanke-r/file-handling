package com.filehandling.file;

import com.filehandling.entity.FileInfoTb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;


@Transactional(readOnly = true)
public interface FileRepository extends JpaRepository<FileInfoTb, Long> {

    boolean existsByFileName(String filename);

    FileInfoTb findByFileName(String originalFilename);
}
