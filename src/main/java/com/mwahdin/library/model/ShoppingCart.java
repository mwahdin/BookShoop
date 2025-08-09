package com.mwahdin.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = SchemaName.schemaName)
public class ShoppingCart extends baseEntity{
    private int count;

    @ManyToOne
    private Book book;

    @ManyToOne
    private Factor factor;

}
