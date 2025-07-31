package com.mwahdin.library.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

@Getter
@Setter
@Entity
@Table(schema = SchemaName.schemaName)
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class User extends baseEntity{

    String userName;
    String passWord;
}
