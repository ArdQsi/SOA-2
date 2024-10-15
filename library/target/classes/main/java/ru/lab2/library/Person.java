package ru.lab2.library;

import com.fasterxml.jackson.databind.annotation.*;
import ru.lab2.library.converter.LocalDateDeserializer;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lab2.library.converter.LocalDateSerializer;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@Table(name= "person")          
public class Person {                                                                                  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически

    @Column(nullable=false)
    @NotNull
    @Size(min=1)
    private String name; //Поле не может быть null, Строка не может быть пустой

    @Embedded
    @NotNull
    private Coordinates coordinates; //Поле не может быть null

    @Column(nullable = false)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    private LocalDateTime creationDate = LocalDateTime.now(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @Min(1)
    private double height; //Значение поля должно быть больше 0

    @Column(nullable = false)
    @NotNull
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDateTime birthday; //Поле не может быть null

    @Column(nullable = false)
    @NotNull
    @Size(max=32)
    private String passportID; //Длина строки не должна быть больше 32, Строка не может быть пустой, Поле не может быть null

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    @NotNull
    private Color eyeColor; //Поле не может быть null

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location; //Поле не может быть null
}
