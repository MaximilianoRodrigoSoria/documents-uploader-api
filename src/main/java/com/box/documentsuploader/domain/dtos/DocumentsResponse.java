package com.box.documentsuploader.domain.dtos;


import lombok.Builder;
import lombok.Data;

import java.util.Set;


@Builder
@Data
public class DocumentsResponse {

    private String algorithm;
    private Set<DocumentDTO> documents;
}
