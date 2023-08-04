package com.box.documentsuploader.services;


import com.box.documentsuploader.api.handler.errors.AlgorithmNotFoundException;
import com.box.documentsuploader.api.handler.errors.SaveDocumentException;
import com.box.documentsuploader.domain.dtos.DocumentDTO;
import com.box.documentsuploader.domain.dtos.DocumentResponse;
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

import javax.print.Doc;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Transactional
@Service
@Slf4j
@AllArgsConstructor
public class DocumentService {
    private  final static String PATH = "/api/documents/hash";
    private final static String SHA256 = "SHA-256";
    private final static String SHA512 = "SHA-512";

    private DocumentRepository repository;;

    public DocumentsResponse saveFiles(Set<MultipartFile> files, String algorithm) {
       Set<DocumentDTO> documents = new HashSet<>();
       Long accountId = 3L;
       if(!algorithm.equals(SHA512) && !algorithm.equals(SHA256)) {
           throw new AlgorithmNotFoundException("El parámetro ‘hash’ solo puede ser ‘SHA-256’ o ‘SHA-512’", PATH);
       }
       documents = files.stream()
               .map(file -> {
                   String fileName = file.getOriginalFilename();
                   Document document = newDocument.get();
                   var doc = this.repository.findByAccountIdAndFileName(accountId,fileName);
                   if(doc.isPresent()){
                       doc.get().setLastUpload(new Date());
                       document = doc.get();
                   } else{
                       document.setAccountId(1L);
                       document.setFileName(fileName);
                       document.setHashSha256(algorithm.equals(SHA256)?SHA256:null);
                       document.setHashSha512(algorithm.equals(SHA512)?SHA512:null);
                       document.setAccountId(accountId);
                   }
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


    public DocumentResponse getDocumentResponseByHash(String algorithm, String hash){
        if(!algorithm.equals(SHA512) && !algorithm.equals(SHA256)) {
            throw new AlgorithmNotFoundException("El parámetro ‘hash’ solo puede ser ‘SHA-256’ o ‘SHA-512’", PATH);
        }
        Long userAccount = 1L;
        String sha256 = algorithm.equals(SHA256)?hash:"";
        String sha512 = algorithm.equals(SHA512)?hash:"";
        var document = repository.findDocumentByAccountIdAndHashSha256OrHashSha512(userAccount,sha256,sha512);
        return document.isEmpty() ? null :entityToResponse.apply(document.get());
    }

    public Set<DocumentResponse> findAll() {
        Long accountId = 1L;
        var documentsResponse = new HashSet<DocumentResponse>();
        var documents = repository.findDocumentByAccountId(accountId);
        if (documents.isPresent()) {
            return  documents.get().stream().map(entityToResponse::apply).collect(Collectors.toSet());
        } else {
            return documentsResponse;
        }

    }


    static BiFunction<String, String, String> hashFunction = (filename, algorithm) -> {
        Charset UTF8 = StandardCharsets.UTF_8;
        return algorithm.equals(SHA256)?Hashing.sha256().hashString(filename, UTF8).toString():Hashing.sha512().hashString(filename, UTF8).toString();
    };

    static Function<Document, DocumentDTO> entityToDTO = (n) -> {
        var response = new DocumentDTO();
        BeanUtils.copyProperties(n, response);
        return response;
    };

    static Function<Document, DocumentResponse> entityToResponse = (n) -> {
        var response = DocumentResponse.builder().build();
        BeanUtils.copyProperties(n, response);
        return response;
    };

    static Supplier<Document> newDocument = Document::new;

}
