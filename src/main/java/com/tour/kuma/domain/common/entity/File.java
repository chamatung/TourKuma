package com.tour.kuma.domain.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
public class File {

    @Id
    private Long fileId;
    @Column(length = 50)
    private String fileName;
    @Column(length = 300)
    private String filePath;
    private Integer fileSize;
    @Column(length = 50)
    private String userFileName;
}
