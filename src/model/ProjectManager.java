package model;

import com.google.gson.Gson;
import network.SiteMap;

import java.io.FileWriter;
import java.io.IOException;

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

    public void saveProject() throws IOException {
        FileWriter file = new FileWriter("src/data/myProject.txt");
        try {
            Gson gson = new Gson();
            String json = gson.toJson(project);
            System.out.println(json);

            file.write(json);

        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            file.flush();
            file.close();
        }


    }
}
