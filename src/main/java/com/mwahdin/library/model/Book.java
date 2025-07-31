package com.mwahdin.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = SchemaName.schemaName)
public class Book extends baseEntity{
    private String name;
    private long price;
}
