package com.cagemini.lifescience.rest;
import com.cagemini.lifescience.entity.Cours;
import com.cagemini.lifescience.service.CoursService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
public class CoursController {


    private CoursService coursService;


    //quick and dirty :inject Cours service

    public CoursController (CoursService theCoursService){
        coursService=theCoursService;
    }


    //export "/Cours"and return a list of Cours
    @GetMapping("/cours")
    public List<Cours> findAll (){
        return coursService.findAll();
    }


    @GetMapping("/cours/{coursId}")
    public Cours findById(@PathVariable Long CoursId){
        Cours theCours = coursService.findById(CoursId);
        if (theCours == null){
            throw new RuntimeException("the Cours id not found "+CoursId);
        }
        else
            return theCours;
    }

    @PostMapping("/cours" )
    public  Cours addCours(@RequestBody Cours theCours){
        theCours.setId(0L);
        Cours dbCours = coursService.save(theCours);
        return dbCours;
    }

    @PutMapping("/cours/{id}")
    public Cours updateCours(@PathVariable Long id,@RequestBody Cours theCours){
        theCours.setId(id);
        return coursService.updateCours(theCours);
    }

    @DeleteMapping("/cours/{coursId}")
    public  String deleteCours(@PathVariable Long CoursId){
        Cours theCours = coursService.findById(CoursId);

        //throw exception if null

        if (theCours == null){
            throw new RuntimeException("the cours id not found "+CoursId);
        }
        coursService.deleteById(CoursId);
        return ("Deleted cours id :" + CoursId);


    }
    @GetMapping("/cours/search")
    public List<Cours> searchCours(
            @RequestParam("term") String term) {
        return coursService.searchByTitle(term);
    }
}
