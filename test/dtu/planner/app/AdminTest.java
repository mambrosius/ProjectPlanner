package dtu.planner.app;
import static dtu.planner.app.ProjectPlanner.app;

import com.intellij.util.xmlb.annotations.Text;
import org.junit.*;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Branavan on 27-04-2017.
 */
public class AdminTest {
    Administrator admin = new Administrator();

    //Registrere en udvikler
    @Test
    public void registerDev(){
        admin.registerDeveloper("bran");
        List<Developer> devs = app.getDevelopers();

        assertEquals("bran",devs.get(0).getInitials());
    }


    //Unregistrere en udvikler
    @Test
    public void unregisterDev(){
        //Registrerer en udvikler i systemet
        admin.registerDeveloper("bran");
        List<Developer> devs = app.getDevelopers();

        //Sletter udvikleren fra systemet
        admin.unregisterDeveloper("bran");
        devs = app.getDevelopers();

        assertEquals(0,devs.size());
    }


    //Registrere flere projekter
    @Test
    public void registerPro(){
        admin.registerProject("pro1");
        admin.registerProject("pro2");
        admin.registerProject("pro3");
        List<Project> pros = app.getProjects();

        assertEquals("pro1",pros.get(0).getName());
        assertEquals("pro2",pros.get(1).getName());
        assertEquals("pro3",pros.get(2).getName());
    }

    //Slette 1 projekt ud af 3
    @Test
    public void unregisterPro(){
        //Laver først projekterne
        admin.registerProject("pro1");
        admin.registerProject("pro2");
        admin.registerProject("pro3");
        List<Project> pros = app.getProjects();

        //Sletter nu pro2, mens de andre beholdes
        admin.unregisterProject("pro2");
        pros = app.getProjects();

        //Eftertjekker om pro2 er slettet
        assertEquals("pro1",pros.get(0).getName());
        assertEquals("pro3",pros.get(1).getName());
    }
}
