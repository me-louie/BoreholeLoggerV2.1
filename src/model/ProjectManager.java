package model;

import network.SiteMap;

public class ProjectManager {
    private Project project;
    private SiteMap siteMap;

    public ProjectManager(){
        this.project = new Project();
        this.siteMap = null;

    }
    public ProjectManager(Project project){
        this.project = project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
    public Project getProject(){
        return this.project;
    }

    public SiteMap getSiteMap() {
        return siteMap;
    }

    public void setSiteMap(SiteMap siteMap) {
        this.siteMap = siteMap;
    }
}
