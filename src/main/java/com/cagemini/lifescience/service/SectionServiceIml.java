package com.cagemini.lifescience.service;

import com.cagemini.lifescience.dao.ChapitreRepository;
import com.cagemini.lifescience.dao.CoursRepository;
import com.cagemini.lifescience.dao.SectionRepository;
import com.cagemini.lifescience.entity.Chapitre;
import com.cagemini.lifescience.entity.Cours;
import com.cagemini.lifescience.entity.Departement;
import com.cagemini.lifescience.entity.Section;
import com.cagemini.lifescience.model.ChapitreDTO;
import com.cagemini.lifescience.model.ChapitreInfo;
import com.cagemini.lifescience.model.SectionInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SectionServiceIml implements SectionService {
    private final SectionRepository sectionRepository;
    private final ChapitreRepository chapitreRepository;
    private final CoursRepository coursRepository;

    @Autowired
    public SectionServiceIml(SectionRepository sectionRepository, ChapitreRepository chapitreRepository, CoursRepository coursRepository) {
        this.sectionRepository = sectionRepository;
        this.chapitreRepository = chapitreRepository;
        this.coursRepository = coursRepository;
    }
    @Override
    public List<ChapitreDTO> getChapitresWithSections(Long coursId) {
        List<ChapitreDTO> chapitreDTOS = new ArrayList<>();
        Cours cours = coursRepository.findById(coursId)
                .orElseThrow(() -> new IllegalArgumentException("Cours not found with ID: " + coursId));
        List<Chapitre> chapitreList = cours.getChapitres();
        for (Chapitre chapitre: chapitreList){
            ChapitreInfo chapitreInfo = new ChapitreInfo(chapitre.getId(), chapitre.getTitre(), chapitre.getDescription(), chapitre.getDateCreation(), chapitre.getDateUpdate());
            List<SectionInfo> sectionInfos = new ArrayList<>();
            List<Section> sectionList = chapitre.getSection();
            for (Section section: sectionList){
                sectionInfos.add(new SectionInfo(section.getId(),section.getTitre(),section.getType(),section.getDescription(),section.getFile(),section.getDateCreated(),section.getDateUpdated(),section.getTempsestimer()));
            }
            chapitreDTOS.add(new ChapitreDTO(chapitreInfo, sectionInfos));
        }
        return chapitreDTOS;
    }

    @Override
    public Section save(Long chapitreId, Section theSection) {
        Chapitre chapitre = chapitreRepository.findById(chapitreId)
                .orElseThrow(() -> new IllegalArgumentException("Chapitre not found with ID: " + chapitreId));
        theSection.setChapitre(chapitre);
        theSection.setDateCreated(new Date());
        theSection.setDateUpdated(new Date());
        return this.sectionRepository.save(theSection);
    }

    @Override
    public Optional<Section> findById(Long theId) {
        return Optional.empty();
    }
    @Override
    public Section findByIds(Long theId) {
        Section section =sectionRepository.findById(theId)
                .orElseThrow(() -> new IllegalArgumentException("Chapitre not found with ID: " + theId));
        return section;
    }

    @Override
    public Section updateSection(Long chapitreId, Long sectionId, Section theSection) {
        Chapitre chapitre = chapitreRepository.findById(chapitreId)
                .orElseThrow(() -> new IllegalArgumentException("Chapitre not found with ID: " + chapitreId));
        Section section = sectionRepository.findById(sectionId)
                .orElseThrow(() -> new IllegalArgumentException("Sextion not found with ID: " + sectionId));
        if(!section.getChapitre().getId().equals(chapitreId)){
            throw new IllegalArgumentException("Section with ID " + sectionId + " is not associated with Chapitre with ID " + chapitreId);
        }
        section.setTitre(theSection.getTitre());
        section.setDescription(theSection.getDescription());
        section.setFile(theSection.getFile());
        section.setTempsestimer(theSection.getTempsestimer());
        section.setType(theSection.getType());
        section.setDateUpdated(new Date());
        return sectionRepository.save(section);
    }

    @Override
    public void deleteById(Long theId, Long chapitreId) {
        Chapitre chapitre = chapitreRepository.findById(chapitreId)
                .orElseThrow(() -> new IllegalArgumentException("Chapitre not found with ID: " + chapitreId));
        Section section = sectionRepository.findById(theId)
                .orElseThrow(() -> new IllegalArgumentException("Sextion not found with ID: " + theId));
        if(!section.getChapitre().getId().equals(chapitreId)){
            throw new IllegalArgumentException("Section with ID " + theId + " is not associated with Chapitre with ID " + chapitreId);
        }
        sectionRepository.deleteBySectionIdAndChapitreId(theId, chapitreId);
    }
}
