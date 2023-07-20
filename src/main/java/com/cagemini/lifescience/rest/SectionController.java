package com.cagemini.lifescience.rest;

import com.cagemini.lifescience.entity.Section;
import com.cagemini.lifescience.model.ApiResponse;
import com.cagemini.lifescience.model.ChapitreDTO;
import com.cagemini.lifescience.service.SectionServiceIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
public class SectionController {

    private SectionServiceIml sectionService;

    @Autowired
    public SectionController(SectionServiceIml sectionService) {
        this.sectionService = sectionService;
    }

    @PostMapping("/sections")
    public Section addSection(@RequestParam Long chapitreId, @RequestBody Section theSection) {
        theSection.setId(0L);
        Section dbSection = sectionService.save(chapitreId, theSection);
        return dbSection;
    }

    @GetMapping("/sections")
    public List<ChapitreDTO> getSectionByChapitre(@RequestParam Long cousId){
        return sectionService.getChapitresWithSections(cousId);
    }

    @PutMapping("/sections/{chapitreId}/{sectionId}")
    public ResponseEntity<Section> updateQuiz(@PathVariable Long chapitreId, @PathVariable Long sectionId, @RequestBody Section theSection){
        Section dbSection = sectionService.updateSection(chapitreId, sectionId, theSection);
        return ResponseEntity.ok(dbSection);
    }

    @DeleteMapping("/sections/{theId}/{chapitreId}")
    public ApiResponse deleteSection(@PathVariable Long theId, @PathVariable Long chapitreId){
        sectionService.deleteById(theId, chapitreId);
        return ( new ApiResponse("Deleted section id :" + theId));
    }
}
