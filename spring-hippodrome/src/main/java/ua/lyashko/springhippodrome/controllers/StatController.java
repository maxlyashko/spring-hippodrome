package ua.lyashko.springhippodrome.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ua.lyashko.springhippodrome.model.Race;
import ua.lyashko.springhippodrome.model.RaceHorse;
import ua.lyashko.springhippodrome.repository.RaceHorseRepository;
import ua.lyashko.springhippodrome.repository.RaceRepository;

import java.util.ArrayList;
import java.util.List;

@Controller
public class StatController {
    private final RaceRepository raceRepository;
    private final RaceHorseRepository raceHorseRepository;

    public StatController ( RaceRepository raceRepository , RaceHorseRepository raceHorseRepository ) {
        this.raceRepository = raceRepository;
        this.raceHorseRepository = raceHorseRepository;
    }

    @GetMapping("/stats")
    public String getStat ( Model model ) {
        List<Race> races = (List<Race>) raceRepository.findAll ( );
        List<RaceHorse> raceHorses = new ArrayList<> ( );
        for (Race race : races) {
            String tempNumber = String.valueOf ( race.getNumber ( ) );
            String tempId = String.valueOf ( race.getId ( ) );
            RaceHorse raceHorse = raceHorseRepository.findByHorseIdAndRaceId ( Integer.parseInt ( tempNumber ) , Integer.parseInt ( tempId ) );
            raceHorses.add ( raceHorse );
        }
        model.addAttribute ( "count", raceRepository.count () );
        model.addAttribute ( "raceHorse", raceHorses );
        return "raceStat";
    }
}
