package com.cagemini.lifescience.service;

import com.cagemini.lifescience.dao.ChapitreRepository;
import com.cagemini.lifescience.dao.SupportCoursRepository;
import com.cagemini.lifescience.entity.Chapitre;
import com.cagemini.lifescience.entity.Cours;
import com.cagemini.lifescience.entity.Projet;
import com.cagemini.lifescience.entity.SupportCours;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SupportCoursServiceImpl implements SupportCoursService {

    private final SupportCoursRepository supportCoursRepository;
    private final ChapitreRepository  chapitreRepository;

    @Autowired
    public SupportCoursServiceImpl(SupportCoursRepository supportCoursRepository, ChapitreRepository chapitreRepository) {
        this.supportCoursRepository = supportCoursRepository;
        this.chapitreRepository = chapitreRepository;
    }


    @Override
    public List<SupportCours> findAll() {
        return supportCoursRepository.findAll();
    }

    @Override
    public SupportCours findById(Long theId) {
        Optional<SupportCours> result=supportCoursRepository.findById(theId);

        SupportCours theSupportCours = null;
        if (result.isPresent()){
            theSupportCours = result.get();
        }
        else {
            throw new RuntimeException("didn't find SupportCours id:"+theId);
        }
        return theSupportCours;
    }

    //get all spport course by chapitre
    @Override
    public SupportCours getSupportCoursesByChapitre(Chapitre chapitre) {
        return supportCoursRepository.findByChapitre(chapitre);
    }

    @Override
    public SupportCours addSupportCoursTochapitre(Long chapitreId, MultipartFile file, String title) {
        Chapitre chapitre = chapitreRepository.findById(chapitreId)
                .orElseThrow(() -> new ResourceNotFoundException("chapitre not found with ID: " + chapitreId));

        // Save the file and get the file path
        String filePath = saveFile(file);

        // Create the SupportCours entity with the file path, title, and other attributes
        SupportCours supportCours = new SupportCours(title, new Date(), filePath, chapitre);

        // Save the SupportCours entity to the database
        return supportCoursRepository.save(supportCours);
    }
    @Override
    public SupportCours saveSupportCour(MultipartFile file, String title, Long idChapitre) throws IOException {
        //save the file first

        String filePath = saveFileCource(file);
        SupportCours supportCour = new SupportCours();
        supportCour.setPathFile(filePath);
        supportCour.setTitle(title);
        supportCour.setChapitre(chapitreRepository.findById(idChapitre).get());
        return supportCoursRepository.save(supportCour);

    }

    private String saveFileCource(MultipartFile file) throws IOException {
        String fileDirectory = "C:\\Users\\heisen\\Desktop\\testing\\02-frontend-cap\\src\\assets\\pdfs";
        String fileName = UUID.randomUUID().toString() + "_"+ file.getOriginalFilename();
        Path filePath = Paths.get(fileDirectory , fileName);
        Files.copy(file.getInputStream() , filePath , StandardCopyOption.REPLACE_EXISTING);
        return filePath.toString();
    }




    private String saveFile (MultipartFile file){
        try {
            String fileName = file.getOriginalFilename();
            Path filePath = Paths.get("/path/to/upload/directory/" + fileName);
            Files.write(filePath, file.getBytes());
            return "/path/to/upload/directory/" + fileName; // Return the file path
        } catch (IOException e) {
            throw new RuntimeException("Failed to upload file!");
        }
    }

    @Override
    public SupportCours save(SupportCours theSupportCoursCours) {
        return supportCoursRepository.save(theSupportCoursCours);
    }

    @Override
    public void deleteById(Long theId) {
        Optional<SupportCours> supportCoursOptional = supportCoursRepository.findById(theId);

        if (supportCoursOptional.isPresent()) {
            SupportCours supportCours = supportCoursOptional.get();

            // Delete the associated file from the file system
            deleteFile(supportCours.getPathFile());

            // Delete the support course entry from the database
            supportCoursRepository.deleteById(theId);
        } else {
            throw new ResourceNotFoundException("SupportCours not found with ID: " + theId);
        }
    }

    private void deleteFile(String filePath) {
        try {
            Path fileToDelete = Paths.get(filePath);
            Files.deleteIfExists(fileToDelete);
        } catch (IOException e) {
            // Handle the exception if necessary
            e.printStackTrace();
        }
    }

}
