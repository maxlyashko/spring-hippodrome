package ua.lyashko.springhippodrome.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class Horse  {

    private Integer distance;
    private Integer horseNumber;

    public Horse ( Integer distance , Integer horseNumber ) {
        this.distance = distance;
        this.horseNumber = horseNumber;
    }
}
