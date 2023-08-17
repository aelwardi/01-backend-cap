package com.cagemini.lifescience.model;

import java.util.List;

public class ProjetDTO {
    private ProjetInfo projetInfo;
    private List<CoursInfo> coursInfos;

    public ProjetDTO() {
    }

    public ProjetDTO(ProjetInfo projetInfo, List<CoursInfo> coursInfos) {
        this.projetInfo = projetInfo;
        this.coursInfos = coursInfos;
    }

    public ProjetInfo getProjetInfo() {
        return projetInfo;
    }

    public void setProjetInfo(ProjetInfo projetInfo) {
        this.projetInfo = projetInfo;
    }

    public List<CoursInfo> getCoursInfos() {
        return coursInfos;
    }

    public void setCoursInfos(List<CoursInfo> coursInfos) {
        this.coursInfos = coursInfos;
    }
}
