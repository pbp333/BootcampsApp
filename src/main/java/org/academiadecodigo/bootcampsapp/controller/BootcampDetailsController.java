package org.academiadecodigo.bootcampsapp.controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.academiadecodigo.bootcampsapp.Navigation;
import org.academiadecodigo.bootcampsapp.model.Bootcamp;
import org.academiadecodigo.bootcampsapp.model.CodeCadet;
import org.academiadecodigo.bootcampsapp.model.Gender;
import org.academiadecodigo.bootcampsapp.service.BootcampService;
import org.academiadecodigo.bootcampsapp.service.ServiceRegistry;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by codecadet on 14/11/17.
 */
public class BootcampDetailsController implements Controller {

    private BootcampService bootcampService;

    private Bootcamp bootcamp;


    @FXML
    private TableView<CodeCadet> cadetsTable;

    @FXML
    private Button backToBootcampsListButton;

    @FXML
    private TableColumn<CodeCadet, String> name;

    @FXML
    private TableColumn<CodeCadet, Gender> gender;

    @FXML
    private TableColumn<CodeCadet, String> address;

    @FXML
    private TableColumn<CodeCadet, String> city;

    @FXML
    private TableColumn<CodeCadet, String> phone;

    @FXML
    private TableColumn<CodeCadet, Date> birthdate;

    @FXML
    private TableColumn<CodeCadet, Integer> bootcampID;

    @FXML
    private Label iDLabel;

    @FXML
    private Label locationLabel;

    @FXML
    private Label startLabel;

    @FXML
    private Label endLabel;

    public void initialize() {

    }

    @FXML
    void backToBootcampsList(ActionEvent event) {

        Navigation.getInstance().back();

    }

    public void loadBootcampDeatils(int bootcampID) {

        bootcampService = (BootcampService) ServiceRegistry.getInstance().getService("Bootcamp");

        bootcamp = bootcampService.getBootcamps().get(bootcampID);

        cadetsTable.setItems(FXCollections.observableList(new ArrayList<>(bootcamp.getCodeCadets())));

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        city.setCellValueFactory(new PropertyValueFactory<>("location"));
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));

        iDLabel.setText(Integer.toString(bootcamp.getId()));
        locationLabel.setText(bootcamp.getLocation());
        startLabel.setText(bootcamp.getStart().toString());
        endLabel.setText(bootcamp.getEnd().toString());

    }
}
