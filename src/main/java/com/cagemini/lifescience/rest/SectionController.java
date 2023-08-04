package com.cagemini.lifescience.rest;

import com.cagemini.lifescience.entity.Section;
import com.cagemini.lifescience.model.ApiResponse;
import com.cagemini.lifescience.model.ChapitreDTO;
import com.cagemini.lifescience.service.SectionServiceIml;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
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

    @GetMapping("/sections/{theId}")
    public Section getSectionById(@PathVariable Long theId){
        return sectionService.findByIds(theId);
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


    @PostMapping("/upload")
    public ApiResponse handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            String filePath = "C:\\Users\\heisen\\Desktop\\cc\\" + file.getOriginalFilename();
            file.transferTo(new File(filePath));

            if (filePath != null) {
                return new ApiResponse(filePath);
            } else {
                return new ApiResponse("Le fichier n'a pas été enregistré correctement. Le chemin du fichier est nul.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ApiResponse("Erreur lors de l'enregistrement du fichier. Le chemin du fichier est nul.");
        }
    }
    /*
    @PostMapping("/upload")
    public ApiResponse handleFileUpload(@RequestParam("file") MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            String filePath = "C:\\Users\\heisen\\Desktop\\cc\\" + fileName;
            file.transferTo(new File(filePath));

            if (filePath != null) {
                return new ApiResponse(fileName);
            } else {
                return new ApiResponse("Le fichier n'a pas été enregistré correctement. Le chemin du fichier est nul.");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ApiResponse("Erreur lors de l'enregistrement du fichier. Le chemin du fichier est nul.");
        }
    }

     */

}
