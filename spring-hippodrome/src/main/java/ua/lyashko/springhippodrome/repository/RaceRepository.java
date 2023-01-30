package ua.lyashko.springhippodrome.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.lyashko.springhippodrome.model.Race;

@Repository
public interface RaceRepository extends CrudRepository<Race, Integer> {
    Race findById (int raceId);
}
