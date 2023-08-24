package com.cagemini.lifescience.rest;

import com.cagemini.lifescience.dao.ChapitreRepository;
import com.cagemini.lifescience.dao.SupportCoursRepository;
import com.cagemini.lifescience.entity.Chapitre;
import com.cagemini.lifescience.entity.SupportCours;
import com.cagemini.lifescience.service.SupportCoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200/")
public class SupportCoursController {

    private SupportCoursService supportCoursService;
    private ChapitreRepository chapitreRepository;
    private final SupportCoursRepository supportCoursRepository;
    @Autowired
    public SupportCoursController(SupportCoursService supportCoursService, ChapitreRepository chapitreRepository, SupportCoursRepository supportCoursRepository) {
        this.supportCoursService = supportCoursService;
        this.chapitreRepository = chapitreRepository;
        this.supportCoursRepository = supportCoursRepository;
    }

    @GetMapping("/supportcours/{supportCoursId}")
    public SupportCours findById(@PathVariable Long supportCoursId){
        SupportCours theSupportCours = supportCoursService.findById(supportCoursId);
        if (theSupportCours == null){
            throw new RuntimeException("the Support Course id not found "+supportCoursId);
        }
        else
            return theSupportCours;
    }
    //get all support cours
    @GetMapping("/supportcours")
    public ResponseEntity<SupportCours> getSupportCoursByChapitre(@RequestParam("chapitreId") Long chapitreId) {
        // Retrieve the project from the database by its ID
        Chapitre chapitre = chapitreRepository.findById(chapitreId)
                .orElseThrow(() -> new ResourceNotFoundException("chapitre not found with ID: " + chapitreId));

        // Get the courses associated with the project
        return new ResponseEntity<>(supportCoursService.getSupportCoursesByChapitre(chapitre) , HttpStatus.OK);
    }

    //Add a support course to chapitre
//    @PostMapping("/supportcours")
//    public SupportCours addSupportCourseToChapitre(@RequestParam("chapitreId") Long chapitreId, @RequestBody SupportCours supportCours) {
//        Chapitre chapitre = chapitreRepository.findById(chapitreId)
//                .orElseThrow(() -> new ResourceNotFoundException("chapitre not found with ID: " + chapitreId));
//
//        supportCours.setChapitre(chapitre);
//        return supportCoursService.save(supportCours);
//    }

    @PostMapping("/supportcours")
    public SupportCours addSupportCourseToChapitre(@RequestParam("chapitreId") Long chapitreId,
                                                   @RequestParam("file") MultipartFile file,
                                                   @RequestParam("title") String title)  throws IOException {
        Chapitre chapitre = chapitreRepository.findById(chapitreId)
                .orElseThrow(() -> new ResourceNotFoundException("chapitre not found with ID: " + chapitreId));

        return supportCoursService.saveSupportCour(file , title , chapitreId);

    }
    @GetMapping("/{id}/file")
    public ResponseEntity<Resource> getFileByIdChapitre(@PathVariable Long id) {
        Chapitre chapitre = chapitreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("chapitre not found with ID: " + id));
        SupportCours supportCourByChapitre = supportCoursService.getSupportCoursesByChapitre(chapitre);
        if (supportCourByChapitre == null) {
            return ResponseEntity.notFound().build();
        }

        // Load the file and return it as a ResponseEntity
        Path filePath = Paths.get(supportCourByChapitre.getPathFile());
        Resource resource = new FileSystemResource(filePath.toFile());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }

    //    @DeleteMapping("/supportcours/{supportCoursId}")
//    public String deleteSupportCours(@PathVariable Long supportCoursId) {
//        SupportCours thesupportCours = supportCoursService.findById(supportCoursId);
//
//        // Throw an exception if the support course is not found
//        if (thesupportCours == null) {
//            throw new RuntimeException("The support course with ID not found: " + supportCoursId);
//        }
//
//        // Delete the file associated with the support course
//        deleteFile(thesupportCours.getPathFile());
//
//        // Delete the support course entry from the database
//        supportCoursService.deleteById(supportCoursId);
//
//        return "Deleted support course with ID: " + supportCoursId;
//    }
//
//    private void deleteFile(String filePath) {
//        try {
//            Path fileToDelete = Paths.get(filePath);
//            Files.deleteIfExists(fileToDelete);
//        } catch (IOException e) {
//            // Handle the exception if necessary
//            e.printStackTrace();
//        }
//    }
//
    @DeleteMapping("/supportcours/{supportCoursId}")
    public String deleteSupportCours(@PathVariable Long supportCoursId) {
        supportCoursService.deleteById(supportCoursId);
        return "Deleted support course with ID: " + supportCoursId;
    }

}
