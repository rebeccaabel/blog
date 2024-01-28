package com.example.springblog.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Authority implements Serializable {

    @Id
    @Column(length = 16 )
    private String authName;

    @Override
    public String toString() {
    return "Auth{" +
            "name= '" + authName + "'" +
            "}";
}


}
