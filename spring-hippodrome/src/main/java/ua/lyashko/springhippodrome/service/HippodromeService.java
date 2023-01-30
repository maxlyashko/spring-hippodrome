package ua.lyashko.springhippodrome.service;

import lombok.SneakyThrows;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.lyashko.springhippodrome.model.Horse;
import ua.lyashko.springhippodrome.model.Race;
import ua.lyashko.springhippodrome.model.RaceHorse;
import ua.lyashko.springhippodrome.repository.RaceHorseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class HippodromeService implements Runnable {
    private final RaceHorseRepository repository;

    @Autowired
    public HippodromeService ( RaceHorseRepository repository ) {
        this.repository = repository;
    }

    private static List<Horse> horses = new ArrayList<> ( );
    private static int horseNumber = 0;
    private static final int distance = 1000;
    private static final Random random = new Random ( );
    private static final Object lock = new Object ( );
    Horse horse = new Horse ( distance , horseNumber );


    @Override
    public void run () {
        increaseHorseNumber ( );
        horse.setDistance ( distance );
        horse.setHorseNumber ( horseNumber );
        while ( horse.getDistance ( ) > 0 ) {
            start ( );
        }
        if (horse.getDistance ( ) < 0) {
            System.out.println ( horse.getHorseNumber ( ) + "finished" );
            horses.add ( horse );
        }
    }

    public void saveHorses ( int horseQuantity , Race race ) {
        while ( horses.size ( ) != horseQuantity ) {
            Thread.onSpinWait ( );
        }
        for (int i = 0; i < horses.size ( ); i++) {
            RaceHorse raceHorse = new RaceHorse ( );
            raceHorse.setRaceId ( race.getId ( ) );
            raceHorse.setHorseId ( horses.get ( i ).getHorseNumber ( ) );
            raceHorse.setPlace ( i + 1 );
            repository.save ( raceHorse );
        }
    }

    @SneakyThrows
    public void start () {
        horse.setDistance ( horse.getDistance ( ) - random.nextInt ( 100 , 200 ) );
        Thread.sleep ( random.nextInt ( 400 , 500 ) );
    }

    public void flush () {
        horseNumber = 0;
        horses = new ArrayList<> ( );
    }


    private void increaseHorseNumber () {
        synchronized (lock) {
            horseNumber++;
        }
    }
}
