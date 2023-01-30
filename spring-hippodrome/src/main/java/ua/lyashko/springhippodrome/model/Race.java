package ua.lyashko.springhippodrome.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor
@Getter
@Setter
@Table(name = "race")

public class Race implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "race_id")
    private Integer id;

    @Column (name = "date")
    private LocalDate date;

    @Column (name = "quantity")
    private Integer quantity;

    @Column (name = "number")
    private Integer number;

    public Race ( Integer quantity , Integer number ) {
        this.quantity = quantity;
        this.number = number;
    }

    @Override
    public String toString () {
        return "Race{" +
                "date=" + date +
                ", quantity=" + quantity +
                ", number=" + number +
                '}';
    }
}
