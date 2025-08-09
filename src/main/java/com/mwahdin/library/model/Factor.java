package com.mwahdin.library.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor

@Table(schema = SchemaName.schemaName)
public class Factor extends baseEntity{
    @ManyToOne
    private User user;
    @Enumerated(EnumType.STRING)
    Payed payed;
}
