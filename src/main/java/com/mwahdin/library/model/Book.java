package com.mwahdin.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(schema = SchemaName.schemaName)
public class Book extends baseEntity{
    private String name;
    private long price;
}
