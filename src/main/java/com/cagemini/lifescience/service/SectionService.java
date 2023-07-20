package com.cagemini.lifescience.service;

import com.cagemini.lifescience.entity.Section;
import com.cagemini.lifescience.model.ChapitreDTO;

import java.util.List;
import java.util.Optional;

public interface SectionService {

    Section save(Long chapitreId, Section theSection);
    List<ChapitreDTO> getChapitresWithSections(Long coursId);
    Optional<Section> findById(Long theId);
    Section updateSection(Long chapitreId, Long sectionId, Section theSection);
    void  deleteById(Long theId, Long chapitreId);
}
