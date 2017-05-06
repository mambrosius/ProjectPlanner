package dtu.planner.app;


public class ActivityTest {
    /*
    ProjectPlanner app = new ProjectPlanner();
    Administrator admin = new Administrator();
    Project pro = new Project("pro1");
    Project pro2 = new Project("pro2");
    Activity act1 = new Activity("act1","pro1");
    Activity act2 = new Activity("act2","pro1");
    Activity act3 = new Activity("act2","pro2");
    Developer bran = new Developer("bran");
    Developer moaa = new Developer("moaa");
    Developer bamo = new Developer("bamo");


    //Assigne udviklere til aktivitet
    @Test
    public void assignDev(){
        //Opretter først udviklerne
        admin.registerDeveloper("bran");
        admin.registerDeveloper("moaa");
        admin.registerDeveloper("bamo");

        //Tilføje udviklerne til projekterne
        pro.addDeveloper("bran");
        pro.addDeveloper("moaa");
        pro.addDeveloper("bamo");

        //Tilføjer nu "bran" og "bamo" til act1
        act1.addDeveloper("bran");
        act1.addDeveloper("bamo");
        List<Developer> test1 = act1.getDevelopers();

        //Ser om de er blevet tilføjet
        assertEquals("bran",test1.get(0).getInitials());
        assertEquals("bamo",test1.get(1).getInitials());


        //Tilføjer desuden alle 3 udviklere til act2
        act2.addDeveloper("bran");
        act2.addDeveloper("moaa");
        act2.addDeveloper("bamo");
        List<Developer> test2 = act2.getDevelopers();

        //Ser om alle 3 udviklere er blevet tilføjet
        assertEquals("bran",test2.get(0).getInitials());
        assertEquals("moaa",test2.get(1).getInitials());
        assertEquals("bamo",test2.get(2).getInitials());

        //Tilføjer 2 udviklere til en aktivitet i pro2
        pro2.addDeveloper("bran");
        pro2.addDeveloper("bamo");

        act3.addDeveloper("bran");
        act3.addDeveloper("bamo");

        List<Developer> test3 = act3.getDevelopers();

        assertEquals(2,test3.size());
    }


    //Fjerne udviklere fra aktivitet
    @Test
    public void removeDev(){
        //Opretter først udviklerne
        admin.registerDeveloper("bran");
        admin.registerDeveloper("moaa");
        admin.registerDeveloper("bamo");

        //Tilføje udviklerne til projekterne
        pro.addDeveloper("bran");
        pro.addDeveloper("moaa");
        pro.addDeveloper("bamo");
        pro2.addDeveloper("bran");
        pro2.addDeveloper("moaa");

        //Tilføje udviklerne til aktiviteterne
        act1.addDeveloper("bran");
        act1.addDeveloper("moaa");
        act2.addDeveloper("bamo");
        act3.addDeveloper("bran");
        act3.addDeveloper("moaa");

        List<Developer> test1 = act1.getDevelopers();
        List<Developer> test2 = act2.getDevelopers();
        List<Developer> test3 = act3.getDevelopers();

        //Se om udviklerne er assignet aktiviteten for det pågældende projekt
        assertEquals(2,test1.size());
        assertEquals("bamo",test2.get(0).getInitials());
        assertEquals(2,test3.size());

        //Fjerner en udvikler i aktiviteterne
        act1.removeDeveloper("bran");
        act3.removeDeveloper("moaa");

        //Ser om "bran" er fjernet fra en aktivitet og "moaa" fra en anden
        assertEquals(1,test1.size());
        assertEquals(1,test3.size());
     }


     //Teste antal givet timer til aktivitet
     @Test
    public void assignTime(){
        //Giver en estimeret tid til act1
        act1.setEstimatedWorkHours(1.5);

        double test1 = act1.getEstimatedWorkHours();

        //Da en ½ times nøjeagtighed er tilfredsstillende, sættes delta til 0.49
        assertEquals(1.5,test1,0.49);
        assertEquals(1.4,test1,0.49);
        assertEquals(1.8,test1,0.49);
        assertEquals(1.3,test1,0.49);

        //Hvis vi skriver tiden over/under 30 min, skal den ikke gå igennem
         assertNotEquals(2,test1,0.49);
         assertNotEquals(0.5,test1,0.49);
     }


     //Resterende timer på en aktivitet
    @Test
    public void usedTime(){
        //Giver en estimeret tid til act1
        act1.setEstimatedWorkHours(5.5);

        //Antal anvendte timer
        act1.setHoursUsed(3.5);

        //Skal have resterende timer
        double time = act1.getRemainingHours();

        //Vil nu beregne antal resterende timer
        assertEquals(2,time,0.001);
    }


    //Yderligere resterende timer på en aktivitet
    @Test
    public void remainingTime(){
        //Giver en estimeret tid til act1
        act1.setEstimatedWorkHours(5.5);

        //Antal anvendte timer
        act1.setHoursUsed(3.5);

        //Skal have resterende timer
        double time = act1.getRemainingHours();

        //Vil nu beregne antal resterende timer
        assertEquals(2,time,0.001);

        //Og hvis der bruges yderligere timer, skal de lægges til
        act1.setHoursUsed(1.5);

        double time2 = act1.getRemainingHours();

        assertEquals(0.5,time2,0.0001);
    }

    /*
    //aktivitetsrapport
    @Test
    public void activityReport(){
        //Opretter først udviklerne
        admin.registerDeveloper("bran");
        admin.registerDeveloper("moaa");
        admin.registerDeveloper("bamo");

        //Tilføje udviklerne til projekterne
        pro.addDeveloper("bran");
        pro.addDeveloper("moaa");
        pro.addDeveloper("bamo");
        pro2.addDeveloper("bran");
        pro2.addDeveloper("moaa");

        //Tilføje udviklerne til aktiviteterne
        act1.addDeveloper("bran");
        act1.addDeveloper("moaa");
        act2.addDeveloper("bamo");
        act3.addDeveloper("bran");
        act3.addDeveloper("moaa");


        //Giver en estimeret tid til aktiviterne
        act1.setEstimatedWorkHours(5.5);
        act2.setEstimatedWorkHours(2.5);
        act3.setEstimatedWorkHours(7.0);


        //registrere antal brugte timer på hver aktivitet
        act1.setHoursUsed(3.0);
        act2.setHoursUsed(2.5);
        act2.setHoursUsed(3.5);


        Object[][] data1 = act1.getData();


    }*/


}