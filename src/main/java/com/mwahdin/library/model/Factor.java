package com.mwahdin.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(schema = SchemaName.schemaName)
public class Factor extends baseEntity{
    @ManyToOne
    private User user;
}
