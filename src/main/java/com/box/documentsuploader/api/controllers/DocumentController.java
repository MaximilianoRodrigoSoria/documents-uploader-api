package com.box.documentsuploader.api.controllers;


import com.box.documentsuploader.domain.dtos.DocumentsResponse;
import com.box.documentsuploader.services.FilesStorageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

@RestController
@RequestMapping(path = "/api/documents/hash")
@AllArgsConstructor
@Tag(name = "Hash Documents")
public class DocumentController {


    private FilesStorageService filesStorageService;
    @ApiResponse(
            responseCode = "400",
            description = "When the request have a field invalid we response this",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
            }
    )
    @PostMapping(value = "/", consumes = {"multipart/form-data"})
    @Operation(summary = "Upload a single File")
    public ResponseEntity<?> uploadFile(@RequestParam("files") Set<MultipartFile> files, @RequestParam(name = "algorithm") String algorithm) {
        DocumentsResponse fileName= filesStorageService.saveFiles(files, algorithm);
        return ResponseEntity.ok(fileName);
    }

}
