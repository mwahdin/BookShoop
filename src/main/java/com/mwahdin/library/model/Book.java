package com.mwahdin.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Where(clause = "deleted is null")
@SQLDelete(sql ="update Shop.Book set deleted = now() where id = ?")
@Table(schema = SchemaName.schemaName)
public class Book extends baseEntity{
    private String name;
    private long price;
}
