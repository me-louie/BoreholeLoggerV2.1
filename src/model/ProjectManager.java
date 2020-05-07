package model;

public class ProjectManager {
    private Project project;

    public ProjectManager(){

    }
    public ProjectManager(Project project){
        this.project = project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
