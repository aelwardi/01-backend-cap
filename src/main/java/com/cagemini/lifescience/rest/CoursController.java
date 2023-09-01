package com.cagemini.lifescience.rest;
import com.cagemini.lifescience.dao.ProjetRepository;
import com.cagemini.lifescience.entity.Chapitre;
import com.cagemini.lifescience.entity.Cours;
import com.cagemini.lifescience.entity.Projet;
import com.cagemini.lifescience.model.ApiResponse;
import com.cagemini.lifescience.model.CoursDTO;
import com.cagemini.lifescience.model.CoursInfo;
import com.cagemini.lifescience.model.ProjetDTO;
import com.cagemini.lifescience.service.CoursService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class CoursController {


    private final CoursService coursService;
    private final ProjetRepository projetRepository;

    public CoursController (CoursService theCoursService,ProjetRepository projetRepository){
        coursService=theCoursService;
        this.projetRepository=projetRepository;
    }

    //get all courses by project
    @GetMapping("/cours")
    public List<Cours> getCoursesByProjet(@RequestParam("projetId") Long projetId) {
        // Retrieve the project from the database by its ID
        Projet projet = projetRepository.findById(projetId)
                .orElseThrow(() -> new ResourceNotFoundException("Projet not found with ID: " + projetId));

        // Get the courses associated with the project
        return coursService.getCoursesByProjet(projet);
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

     //Add a course to a project
    @PostMapping("/cours")
    public Cours addCourseToProjet(@RequestParam("managerId") Long managerId, @RequestParam("projetId") Long projetId, @RequestBody Cours cours) {
        return coursService.save(managerId, projetId, cours);
    }

    @PutMapping("/cours/{id}/project/{projectId}")
    public ApiResponse updateCourse(@PathVariable Long id,@PathVariable Long projectId,@RequestBody Cours theCours){
        coursService.updateCours( id, theCours , projectId);
        return new ApiResponse("Cours updated");
    }


    @DeleteMapping("/cours/{coursId}")
    public ApiResponse deleteCours(@PathVariable Long coursId){
        Cours thecours = coursService.findById(coursId);
        //throw exception if null
        if (thecours == null){
            throw new RuntimeException("the cours id not found "+coursId);
        }
        coursService.deleteById(coursId);
        return new ApiResponse("Cours deleted");
    }
    @GetMapping("/cours/search")
    public List<Cours> searchCours(
            @RequestParam("term") String term) {
        return coursService.searchByTitle(term);
    }

    @GetMapping("/cours/{courId}/chapitres")
    public List<Chapitre> getChapitresByCour(@PathVariable Long courId) {
        return coursService.getChapitresByCour(courId);
    }

    @GetMapping("/coursDTO")
    public CoursDTO getCoursDTOById(@RequestParam("coursId") Long coursId){
        return coursService.getCoursDTOById(coursId);
    }

    @GetMapping("/cours/apprenants")
    public ResponseEntity<List<ProjetDTO>> getCoursForApprenant(@RequestParam("apprenantId") Long apprenantId){
        List<ProjetDTO> projetDTOS = coursService.getCoursForApprenant(apprenantId);
        return ResponseEntity.ok(projetDTOS);
    }

    @GetMapping("/cours/search/{apprenantId}")
    public ResponseEntity<List<CoursInfo>> getCoursByTitleForApprenant(
            @PathVariable Long apprenantId,
            @RequestParam("titreCours") String titreCours
    ) {
        List<CoursInfo> coursInfos = coursService.getCoursByTitleForApprenant(apprenantId, titreCours);
        return ResponseEntity.ok(coursInfos);
    }
}
