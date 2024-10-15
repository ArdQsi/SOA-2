package ru.lab2.library;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Embeddable
@Data
@NoArgsConstructor
public class Coordinates {
    @NotNull
    @Column(nullable=false)
    @Min(-886)
    private Double x; //Значение поля должно быть больше -886, Поле не может быть null

    @Min(-53)
    private double y; //Значение поля должно быть больше -53
}
