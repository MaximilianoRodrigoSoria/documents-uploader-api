package com.box.documentsuploader.domain.dtos;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class DocumentResponse {

    private String fileName;

    private String hashSha256;

    private String hashSha512;
}
