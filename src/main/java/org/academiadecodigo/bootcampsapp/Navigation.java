package org.academiadecodigo.bootcampsapp;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.academiadecodigo.bootcampsapp.controller.Controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Created by codecadet on 10/11/17.
 */
public final class Navigation {

    private static Navigation instance = null;

    private final int MIN_WIDTH = 1024;
    private final int MIN_HEIGHT = 768;

    private Stack<Scene> scenes = new Stack<>();
    private Map<String, Controller> controllers = new HashMap<>();

    private Stage primaryStage;

    private Navigation() {
    }

    public static synchronized Navigation getInstance() {

        if (instance == null) {

            instance = new Navigation();
        }

        return instance;
    }

    public void setStage(Stage primaryStage) {

        this.primaryStage = primaryStage;
    }


    public void loadScreen(String view) {

        FXMLLoader fxmlLoader;
        fxmlLoader = new FXMLLoader(getClass().getResource("/views/" + view + ".fxml"));
        Parent root = null;

        try {

            root = fxmlLoader.load();

            controllers.put(view, fxmlLoader.<Controller>getController());

            Scene scene = new Scene(root);
            scenes.push(scene);

            // Put the scene on the stage
            setScene(scene);

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Failure to load view " + view + " : " + e.getMessage());
        }
    }

    public void setScene(Scene scene) {

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void back() {

        if (scenes.isEmpty()) {
            return;
        }

        scenes.pop();

        setScene(scenes.peek());
    }

    public Controller getController(String s){

        return controllers.get(s);
    }


}
