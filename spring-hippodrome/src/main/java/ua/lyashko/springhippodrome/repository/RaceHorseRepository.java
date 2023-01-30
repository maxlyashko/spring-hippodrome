package ua.lyashko.springhippodrome.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.lyashko.springhippodrome.model.RaceHorse;

import java.util.List;

@Repository
public interface RaceHorseRepository extends CrudRepository<RaceHorse, Integer> {
    List<RaceHorse> findAllByRaceId (int raceId);
    RaceHorse findByHorseIdAndRaceId ( int horseId, int raceId);
}
