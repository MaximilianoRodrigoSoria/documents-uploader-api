package com.box.documentsuploader.api.controllers;


import com.box.documentsuploader.domain.dtos.DocumentsResponse;
import com.box.documentsuploader.services.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.Set;

@RestController
@RequestMapping(path = "/api/documents")
@AllArgsConstructor
@Tag(name = "Hash Documents")
public class DocumentController {


    private DocumentService filesStorageService;
    @ApiResponse(
            responseCode = "400",
            description = "When the request have a field invalid we response this",
            content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorMessage.class))
            }
    )
    @PostMapping(value = "/hash", consumes = {"multipart/form-data"})
    @Operation(summary = "Upload a single File")
    public ResponseEntity<?> uploadFile(@RequestParam("files") Set<MultipartFile> files, @RequestParam(name = "algorithm") String algorithm) {
        DocumentsResponse fileName= filesStorageService.saveFiles(files, algorithm);
        return ResponseEntity.ok(fileName);
    }


    @Operation(summary = "Return a Document")
    @GetMapping(path = "/{algorithm}")
    public ResponseEntity<?> findByHash(@PathVariable(name = "algorithm") String algorithm,@RequestParam(name = "hash") String hash) {
        var response = filesStorageService.getDocumentResponseByHash(algorithm,hash);
        return Objects.isNull(response) ? ResponseEntity.noContent().build() : ResponseEntity.ok(response);
    }


    @Operation(summary = "Return all documents")
    @GetMapping(path = "/")
    public ResponseEntity<?> findAllDocuments() {
        var response = filesStorageService.findAll();
        return ResponseEntity.ok(response);
    }


    }
