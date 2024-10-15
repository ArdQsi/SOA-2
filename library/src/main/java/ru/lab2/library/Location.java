package ru.lab2.library;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name= "location")
@Data
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotNull
    @Column(nullable=false)
    private Double x; //Поле не может быть null

    @NotNull
    @Column(nullable=false)
    private Double y; //Поле не может быть null

    private float z;
}
