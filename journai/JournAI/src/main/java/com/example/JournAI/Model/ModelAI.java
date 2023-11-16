package com.example.JournAI.Model;
import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "journai")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ModelAI {


    @Id
    @GeneratedValue
    private Long id;
    private String start;
    private String destination;
    private int range;
    @Column
    private List<String> option;



}
