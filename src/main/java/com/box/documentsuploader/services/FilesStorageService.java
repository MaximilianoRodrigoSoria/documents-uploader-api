package com.box.documentsuploader.services;


import com.box.documentsuploader.api.handler.errors.AlgorithmNotFoundException;
import com.box.documentsuploader.api.handler.errors.SaveDocumentException;
import com.box.documentsuploader.domain.dtos.DocumentDTO;
import com.box.documentsuploader.domain.dtos.DocumentsResponse;
import com.box.documentsuploader.domain.entities.Document;
import com.box.documentsuploader.domain.repositories.DocumentRepository;
import com.google.common.hash.Hashing;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class FilesStorageService {
    private  final static String PATH = "/api/documents/hash";
    private final static String SHA256 = "SHA-256";
    private final static String SHA512 = "SHA-512";

    private DocumentRepository repository;;

    public DocumentsResponse saveFiles(Set<MultipartFile> files, String algorithm) {
       Set<DocumentDTO> documents = new HashSet<>();
       Long accountId = 1L;
       if(!algorithm.equals(SHA512) && !algorithm.equals(SHA256)) {
           throw new AlgorithmNotFoundException("El parámetro ‘hash’ solo puede ser ‘SHA-256’ o ‘SHA-512’", PATH);
       }

       documents = files.stream()
               .map(file -> {
                   String fileName = file.getOriginalFilename();
                   Document document = new Document();
                   document.setAccountId(1L);
                   document.setFileName(fileName);
                   if (algorithm.equals(SHA256)) {
                       document.setHashSha256(hashFunction.apply(fileName, SHA256));
                   } else {
                       document.setHashSha512(hashFunction.apply(fileName, SHA512));
                   }
                   document.setAccountId(accountId);
                   document.setLastUpload(new Date());
                   return document;
               })
               .map(d -> {
                   try {
                       repository.save(d);
                   }catch (Exception e){
                       String msg = "Hubo un error al salvar el registro. Message: ".concat(e.getMessage());
                       log.error(msg);
                       throw  new SaveDocumentException(msg,PATH);
                   }

                   return d;
               })
               .map(entityToDTO::apply)
               .collect(Collectors.toSet());
        return DocumentsResponse.builder()
                .documents(documents)
                .algorithm(algorithm)
                .build();

    }


    static BiFunction<String, String, String> hashFunction = (filename, algorithm) -> {
        String hashFile;
        Charset UTF8 = StandardCharsets.UTF_8;
        if (algorithm.equals("SHA-256")) {
            hashFile = Hashing
                            .sha256()
                            .hashString(filename, UTF8)
                            .toString();
        } else {
            hashFile = Hashing
                            .sha512()
                            .hashString(filename, UTF8)
                            .toString();
        }
        return hashFile;
    };

    static Function<Document, DocumentDTO> entityToDTO = (n) -> {
        var response = new DocumentDTO();
        BeanUtils.copyProperties(n, response);
        return response;
    };
}
