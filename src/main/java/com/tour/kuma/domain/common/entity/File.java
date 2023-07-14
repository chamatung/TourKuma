package com.tour.kuma.domain.common.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
public class File {

    @Id
    @GeneratedValue(generator = "fileIdGenerator")
    @GenericGenerator(name = "fileIdGenerator", strategy = "com.tour.kuma.global.util.FileIdGenerator")
    private Long fileId;
    @Column(length = 50)
    private String fileName;
    @Column(length = 300)
    private String filePath;
    private Integer fileSize;
    @Column(length = 50)
    private String userFileName;
}
