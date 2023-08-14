package com.cagemini.lifescience.service;

import com.cagemini.lifescience.entity.Manager;
import com.cagemini.lifescience.entity.ManagerCoursId;
import com.cagemini.lifescience.model.ManagerInfo;

import java.util.List;

public interface ManagerCoursService {
    void addManagerCours(Long managerId, Long coursId);
    void deleteManagerCours(ManagerCoursId managerCoursId);
    List<ManagerInfo> getManagersByCoursId(Long coursId);
    List<ManagerInfo> getManagersNotInManagerCoursByCoursId(Long coursId);
}
