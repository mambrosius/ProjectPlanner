package dtu.planner.app;

public class ProjectTest extends ProjectTestSetup {
/*
    ProjectPlanner app = new ProjectPlanner();
    Administrator admin = new Administrator();
    Project pro = new Project("pro1");


    // Tester om 2 udviklere er assignet til et projekt
   @Test
    public void assignDev(){
       //De 2 udvikler registreres, hvorefter de assignes et projekt
        admin.registerDeveloper("bran");
        admin.registerDeveloper("moaa");

        pro.addDeveloper("bran");
        pro.addDeveloper("moaa");
        List<Developer> test = pro.getDevelopers();


        assertEquals("bran",test.getDate(0).getInitials());
        assertEquals("moaa",test.getDate(1).getInitials());
   }

    // Tester hvor en udvikler ud af 4 bliver unassignet af et projekt
    @Test
    public void unassignDev(){
        //De 4 udvikler registreres, hvorefter de assignes et projekt
        admin.registerDeveloper("bran");
        pro.addDeveloper("bran");

        admin.registerDeveloper("moaa");
        pro.addDeveloper("moaa");

        admin.registerDeveloper("bamo");
        pro.addDeveloper("bamo");

        admin.registerDeveloper("moba");
        pro.addDeveloper("moba");
        List<Developer> test = pro.getDevelopers();

        //Tjekker om de alle er assignet projektet
        assertEquals(4,test.size());

        //Vi unassigner nu "bran" af projeket
        pro.unassign("bran");

        //Tjekker om "bran" er unassignet af projektet ved at se om der er 3 personer assignet til projektet
        assertEquals(3,test.size());
        assertEquals("moaa",test.getDate(0).getInitials());
        assertEquals("bamo",test.getDate(1).getInitials());
        assertEquals("moba",test.getDate(2).getInitials());
    }

    //Tilføje aktiviteter til projekt
    @Test
    public void addActivity(){
        pro.addActivity("design");
        pro.addActivity("ledelse");
        pro.addActivity("tests");
        List<Activity> act = pro.getActivities();

        //Tjekker efter om de alle 3 aktiviteter er tilføjet til projektet
        assertEquals(3,act.size());
    }

    //Sletter nu 2 aktiviteter ud af 7
    @Test
    public void deleteAct(){
        pro.addActivity("krav");
        pro.addActivity("analyse");
        pro.addActivity("tests");
        pro.addActivity("design");
        pro.addActivity("ledelse");
        pro.addActivity("grafik");
        pro.addActivity("extratest");

        //Tjekker efter om alle aktiviteter er på projektet
        List<Activity> act = pro.getActivities();
        assertEquals(7,act.size());

        //Sletter nu to aktiviteter
        pro.removeActivity("grafik");
        pro.removeActivity("ledelse");

        //Herefter tjekker vi om de er blevet slettet
        assertEquals("krav",act.getDate(0).getName());
        assertEquals("analyse",act.getDate(1).getName());
        assertEquals("tests",act.getDate(2).getName());
        assertEquals("design",act.getDate(3).getName());
        assertEquals("extratest",act.getDate(4).getName());
    }

    //Deklare en manager
    @Test
    public void setManager(){
        //Udvikler bliver oprettet og sættes som manager til et projekt
        admin.registerDeveloper("bran");
        pro.setManager("bran");

        String manager = pro.getManagerInitials();

        assertEquals("bran",manager);
    }

    //Skifter manageren til et projekt
    @Test
    public void removeManager(){
        //Udvikler bliver oprettet og sættes som manager til et projekt
        admin.registerDeveloper("bran");
        admin.registerDeveloper("moaa");

        pro.setManager("bran");

        String manager1 = pro.getManagerInitials();

        //Tjekker om manageren er deklareret til projektet
        assertEquals("bran",manager1);

        //Fjerner nu manageren, og tilføjer en ny manager
        pro.setManager("moaa");

        String manager2 = pro.getManagerInitials();

        //Tjekker om den nye manager er deklareret til projektet
        assertEquals("moaa",manager2);
    }


    /*
    //Generere projektrapport
    @Test
    public void getRapport(){
        admin.registerDeveloper("bran");
        pro.addDeveloper("bran");

        admin.registerDeveloper("moaa");
        pro.addDeveloper("moaa");

        pro.addActivity("krav");
        pro.addActivity("analyse");

        List<Activity> act = pro.getData();

    }

*/



/*
    @Test
    public void getInitialsOfParticipants() throws Exception {
        assertTrue(bachelorProject.getInitialsOfParticipants().isEmpty());

        bachelorProject.assignActivity(dev1);
        assertFalse(bachelorProject.getInitialsOfParticipants().isEmpty());
    }

    @Test
    public void assignActivity() throws Exception {
        assertTrue(bachelorProject.getInitialsOfParticipants().isEmpty());

        bachelorProject.assignActivity(dev1);
        assertEquals(1, bachelorProject.getInitialsOfParticipants().size());

        plannerProject.assignActivity(dev1);
        plannerProject.assignActivity(dev2);
        assertEquals(2, plannerProject.getInitialsOfParticipants().size());
    }

    @Test
    public void unassignActivity() throws Exception {
        assignActivity();
        assertEquals(1, bachelorProject.getInitialsOfParticipants().size());
        assertEquals(2, plannerProject.getInitialsOfParticipants().size());

        plannerProject.unassignActivity("MAA");
        assertEquals(1, plannerProject.getInitialsOfParticipants().size());

        plannerProject.unassignActivity("BA");
        assertEquals(0, plannerProject.getInitialsOfParticipants().size());
    }

    @Test
    public void getDeveloper() throws NotFoundException {
        ppApp.registerDeveloper("MAA");
        ppApp.registerDeveloper("BA");
        try {
            assertEquals("BA", ppApp.getDeveloper("BA").getInitials());
        } catch (NotFoundException e) {
            assertEquals("\"BA\" was not found.", e.getMessage());
            assertEquals("BA", e.getName());
        }

        try {
            assertEquals("MAA", ppApp.getDeveloper("MA").getInitials());
            fail("A NotFoundException should have been thrown.");
        } catch (NotFoundException e) {
            assertEquals("\"MA\" was not found.", e.getMessage());
            assertEquals("MA", e.getName());
        }
    }

    @Test
    public void assignManager() throws Exception {

        assignActivity();
        assertEquals(null, plannerProject.getManagerInitials());

        plannerProject.assignManager("MAA");
        assertEquals("MAA", plannerProject.getManagerInitials().getInitials());
    }

    @Test
    public void reassignManager() throws Exception {

        assignManager();

        plannerProject.reassignManager();
        assertEquals(null, plannerProject.getManagerInitials());
    }
    */
}