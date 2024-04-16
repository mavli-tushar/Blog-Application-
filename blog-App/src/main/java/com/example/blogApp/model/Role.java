package com.example.blogApp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.transform.sax.SAXResult;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Role {

    @Id
    private int id;
    private String name;
}
