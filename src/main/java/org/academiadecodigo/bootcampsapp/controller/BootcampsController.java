package org.academiadecodigo.bootcampsapp.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import org.academiadecodigo.bootcampsapp.Navigation;
import org.academiadecodigo.bootcampsapp.model.Bootcamp;
import org.academiadecodigo.bootcampsapp.service.BootcampService;
import org.academiadecodigo.bootcampsapp.service.ServiceRegistry;
import org.academiadecodigo.bootcampsapp.service.UserService;

import java.util.List;


/**
 * Created by codecadet on 10/11/17.
 */
public class BootcampsController implements Controller {

    private BootcampService bootcampService;
    @FXML
    private Button logoutButton;
    @FXML
    private TableView<Bootcamp> bootcampsTable;

    @FXML
    private TableColumn<Bootcamp, Integer> iD;

    @FXML
    private TableColumn<Bootcamp, String> city;

    @FXML
    private TableColumn<Bootcamp, String> start;

    @FXML
    private TableColumn<Bootcamp, String> end;


    public void initialize() {

        bootcampService = (BootcampService) ServiceRegistry.getInstance().getService("Bootcamp");

        populateTable(bootcampService.getBootcamps());

        iD.setCellValueFactory(new PropertyValueFactory<>("id"));
        city.setCellValueFactory(new PropertyValueFactory<>("location"));
        start.setCellValueFactory(new PropertyValueFactory<>("start"));
        end.setCellValueFactory(new PropertyValueFactory<>("end"));


    }


    @FXML
    void goToBootcamp(MouseEvent event) {

        Navigation.getInstance().loadScreen("bootcampsdetails");

        int bootcampSelected = bootcampsTable.getSelectionModel().getSelectedItem().getId();

        ((BootcampDetailsController) Navigation.getInstance().getController("bootcampsdetails")).loadBootcampDeatils(bootcampSelected);

    }


    @FXML
    void logout() {
        Navigation.getInstance().back();

    }

    public void setUserService(UserService userService) {
        this.bootcampService = bootcampService;
    }

    public void populateTable(List<Bootcamp> bootcamps) {

        bootcampsTable.setItems(FXCollections.observableList(bootcamps));
    }
}
