package dtu.planner.app;

public class ProjectPlannerTest extends ProjectPlannerTestSetup {
/*
    @Test
    public void getName() throws Exception {
        assertEquals("Software Huset", ppApp.getName());
    }

    // <editor-fold desc = "developer related tests">
    @Test
    public void registerDeveloper() throws Exception {
        assertTrue(ppApp.getInitialsOfParticipants().isEmpty());

        ppApp.registerDeveloper("MAA");
        assertEquals(1, ppApp.getInitialsOfParticipants().size());

        ppApp.registerDeveloper("BA");
        assertEquals(2, ppApp.getInitialsOfParticipants().size());
    }

    @Test
    public void unregisterDeveloper() throws Exception {

        registerDeveloper();
        assertEquals(2, ppApp.getInitialsOfParticipants().size());

        ppApp.unregisterDeveloper("MAA");
        assertEquals(1, ppApp.getInitialsOfParticipants().size());

        ppApp.unregisterDeveloper("BA");
        assertEquals(0, ppApp.getInitialsOfParticipants().size());
    }

    @Test
    public void getInitialsOfParticipants() throws Exception {
        assertTrue(ppApp.getInitialsOfParticipants().isEmpty());
    }

    @Test
    public void getDeveloper() throws Exception {
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
    // </editor-fold>

    // <editor-fold desc = "project related tests">
    @Test
    public void registerProject() throws Exception {
        assertTrue(ppApp.getNamesOfProjects().isEmpty());

        ppApp.registerProject("Project Planner");
        assertEquals(1, ppApp.getNamesOfProjects().size());

        ppApp.registerProject("Bachelor Thesis");
        assertEquals(2, ppApp.getNamesOfProjects().size());
    }

    @Test
    public void unregisterProject() throws Exception {

        registerProject();
        assertEquals(2, ppApp.getNamesOfProjects().size());

        ppApp.unregisterProject("Bachelor Thesis");
        assertEquals(1, ppApp.getNamesOfProjects().size());

        ppApp.unregisterProject("Project Planner");
        assertEquals(0, ppApp.getNamesOfProjects().size());
    }

    @Test
    public void getNamesOfProjects() throws Exception {
        assertTrue(ppApp.getNamesOfProjects().isEmpty());
    }

    @Test
    public void getProject() throws Exception {
        ppApp.registerProject("Project Planner");
        ppApp.registerProject("Bachelor Thesis");
        try {
            assertEquals("Bachelor Thesis", ppApp.getProject("Bachelor Thesis").getName());
        } catch(NotFoundException e) {
            assertEquals("\"Bachelor Thesis\" was not found.", e.getMessage());
            assertEquals("Bachelor Thesis", e.getName());
        }

        try {
            assertEquals("Project Planner", ppApp.getProject("Project lanner").getName());
            fail("A NotFoundException should have been thrown.");
        } catch (NotFoundException e) {
            assertEquals("\"Project lanner\" was not found.", e.getMessage());
            assertEquals("Project lanner", e.getName());
        }
    }
    // </editor-fold>

    */
}