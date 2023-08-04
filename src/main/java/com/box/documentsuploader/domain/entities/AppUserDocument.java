package com.box.documentsuploader.domain.entities;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document(collection = "app_users")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AppUserDocument implements Serializable {

    @Id
    private String id;
    private Long accountId;
    private String username;
    private boolean enabled;
    private String password;
    private Role role;
}
