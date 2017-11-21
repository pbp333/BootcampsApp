package org.academiadecodigo.bootcampsapp;

import javafx.application.Application;
import javafx.stage.Stage;
import org.academiadecodigo.bootcampsapp.model.Bootcamp;
import org.academiadecodigo.bootcampsapp.model.CodeCadet;
import org.academiadecodigo.bootcampsapp.model.Gender;
import org.academiadecodigo.bootcampsapp.persistence.ConnectionManager;
import org.academiadecodigo.bootcampsapp.service.JdbcUserService;
import org.academiadecodigo.bootcampsapp.service.MockBootcampService;
import org.academiadecodigo.bootcampsapp.service.ServiceRegistry;

import java.util.Date;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        Navigation.getInstance().setStage(primaryStage);
        Navigation.getInstance().loadScreen("loginregisterForm");


    }

    @Override
    public void init() throws Exception {


        Bootcamp bootcamp1 = new Bootcamp("Lisboa", new Date(119,10,20), new Date(120, 02, 20));
        Bootcamp bootcamp2 = new Bootcamp("Porto", new Date(131, 03, 12), new Date(131, 07, 01));

        bootcamp1.setId(0);
        bootcamp2.setId(1);

        CodeCadet codeCadet1 = new CodeCadet();
        codeCadet1.setName("Blabla");
        codeCadet1.setAddress("Rua 99");
        codeCadet1.setGender(Gender.FEMALE);
        codeCadet1.setPhone("90000");
        codeCadet1.setBirthday(new Date(01 - 01 - 1999));
        codeCadet1.setBootcamp(bootcamp1);
        bootcamp1.addCadet(codeCadet1);

        CodeCadet codeCadet2 = new CodeCadet();
        codeCadet2.setName("Blibla");
        codeCadet2.setAddress("Rua 99");
        codeCadet2.setGender(Gender.FEMALE);
        codeCadet2.setPhone("90000");
        codeCadet2.setBirthday(new Date(01 - 01 - 1999));
        codeCadet2.setBootcamp(bootcamp1);
        codeCadet2.setBootcamp(bootcamp1);
        bootcamp1.addCadet(codeCadet2);

        CodeCadet codeCadet3 = new CodeCadet();
        codeCadet3.setName("Blobla");
        codeCadet3.setAddress("Rua 99");
        codeCadet3.setGender(Gender.FEMALE);
        codeCadet3.setPhone("90000");
        codeCadet3.setBirthday(new Date(01 - 01 - 1999));
        codeCadet3.setBootcamp(bootcamp2);
        bootcamp2.addCadet(codeCadet3);

        CodeCadet codeCadet4 = new CodeCadet();
        codeCadet4.setName("Blablo");
        codeCadet4.setAddress("Rua 99");
        codeCadet4.setGender(Gender.MALE);
        codeCadet4.setPhone("90000");
        codeCadet4.setBirthday(new Date(01 - 01 - 1999));
        codeCadet4.setBootcamp(bootcamp2);
        bootcamp2.addCadet(codeCadet4);

        CodeCadet codeCadet5 = new CodeCadet();
        codeCadet5.setName("Bla");
        codeCadet5.setAddress("Rua 99");
        codeCadet5.setGender(Gender.FEMALE);
        codeCadet5.setPhone("90000");
        codeCadet5.setBirthday(new Date(01 - 01 - 1999));
        codeCadet5.setBootcamp(bootcamp2);
        bootcamp2.addCadet(codeCadet5);



        MockBootcampService mockBootcampService = new MockBootcampService();

        mockBootcampService.addBootcamp(bootcamp1);
        mockBootcampService.addBootcamp(bootcamp2);

        ServiceRegistry.getInstance().addServiceList("User", new JdbcUserService(new ConnectionManager()));
        ServiceRegistry.getInstance().addServiceList("Bootcamp", mockBootcampService);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
