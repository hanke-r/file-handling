package com.filehandling.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "file_info_tb")
@Builder
public class FileInfoTb {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private Long fileSize;

    private Date regDate;

}
