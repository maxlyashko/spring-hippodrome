package ua.lyashko.springhippodrome.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ua.lyashko.springhippodrome.model.Race;
import ua.lyashko.springhippodrome.repository.RaceHorseRepository;
import ua.lyashko.springhippodrome.repository.RaceRepository;
import ua.lyashko.springhippodrome.service.HippodromeService;

import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Controller
@RequestMapping("/race")
public class RaceController {
    private final RaceRepository raceRepository;
    private final RaceHorseRepository raceHorseRepository;

    public RaceController ( RaceRepository raceRepository , RaceHorseRepository raceHorseRepository ) {
        this.raceRepository = raceRepository;
        this.raceHorseRepository = raceHorseRepository;
    }

    @GetMapping("/{id}")
    public String raceId ( @PathVariable Integer id, Model model) {
        int tempId = id;
        Race race = raceRepository.findById ( tempId );
        model.addAttribute ( "race", raceRepository.findById ( tempId ) );
        model.addAttribute ( "raceHorse", raceHorseRepository.findAllByRaceId ( race.getId () ) );
        return "raceId";
    }

    @GetMapping("/start")
    public String getStartRace(Model model) {
        model.addAttribute ( "race", new Race (  ) );
        return "raceStart";
    }

    @PostMapping("/start")
    public String startRace ( @ModelAttribute Race race ) {
        if (race == null) {
            return "raceStart";
        }
        HippodromeService hippodromeService = new HippodromeService ( raceHorseRepository );
        race.setDate ( LocalDate.now ( ) );
        ExecutorService executorService = Executors.newFixedThreadPool ( race.getQuantity ( ) );
        for (int i = 0; i < race.getQuantity ( ); i++) {
            executorService.submit ( new HippodromeService ( raceHorseRepository ) );
        }
        raceRepository.save ( race );
        hippodromeService.saveHorses ( race.getQuantity ( ) , race );
        executorService.shutdown ( );
        hippodromeService.flush ( );
        return "redirect:/race/" + race.getId ();
    }
}
