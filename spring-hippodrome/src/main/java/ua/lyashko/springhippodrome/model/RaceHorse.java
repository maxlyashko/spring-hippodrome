package ua.lyashko.springhippodrome.model;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "race_horse")
public class RaceHorse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "race_horse_id")
    private Integer id;

    @Column (name = "race_id")
    private Integer raceId;

    @Column (name = "horse_id")
    private Integer horseId;

    @Column (name = "place")
    private Integer place;

    public RaceHorse ( Integer raceId , Integer horseId , Integer place ) {
        this.raceId = raceId;
        this.horseId = horseId;
        this.place = place;
    }

    @Override
    public String toString () {
        return "RaceHorse{" +
                "raceId=" + raceId +
                ", horseId=" + horseId +
                ", place=" + place +
                '}';
    }
}
