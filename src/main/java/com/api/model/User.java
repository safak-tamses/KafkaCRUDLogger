package com.api.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "`user`")
public class User extends BaseEntity{
    private String name;
    private String surname;
    private String email;
}
