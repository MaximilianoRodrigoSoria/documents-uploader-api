package com.box.documentsuploader.domain.entities;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="documents")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data

public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    @Column(name = "hash_sha_256")
    private String hashSha256;

    @Column(name = "hash_sha_512")
    private String hashSha512;

    @Column(name = "last_upload")
    private Date lastUpload;

    @Column(name = "account_id")
    private Long accountId;

}



