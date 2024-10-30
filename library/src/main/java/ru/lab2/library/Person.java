package ru.lab2.library;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.*;
import lombok.AllArgsConstructor;
import ru.lab2.library.converter.LocalDateDeserializer;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.lab2.library.converter.LocalDateSerializer;

import javax.json.bind.annotation.JsonbDateFormat;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private LocalDateTime creationDate = LocalDateTime.parse(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)); //Поле не может быть null, Значение этого поля должно генерироваться автоматически

    @Min(1)
    private double height; //Значение поля должно быть больше 0

    @Column(nullable = false)
    @NotNull
    private LocalDateTime birthday; //Поле не может быть null

    @Column(nullable = false)
    @NotNull
    @Size(max=32)
    private String passportID; //Длина строки не должна быть больше 32, Строка не может быть пустой, Поле не может быть null

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    @NotNull
    private Color eyeColor; //Поле не может быть null

    @Enumerated(EnumType.ORDINAL)
    @Column(nullable = false)
    @NotNull
    private Nationality nationality; //Поле не может быть null

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "location_id")
    private Location location; //Поле не может быть null
}
