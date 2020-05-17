package ba.unsa.etf.rs.tutorijal8;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Controller {

    public ListView<Driver> listDriver;
    public ListView<Bus> listBus;
    public TextField nameDriver;
    public TextField surnameDriver;
    public TextField JMBGDriver;
    public TextField MakerBus;
    public TextField SeriesBus;
    private TransportDAO dao;


    public Controller(TransportDAO t) {
        dao = t;
    }

    @FXML
    public void initialize() {
        dao=TransportDAO.getInstance();
        listDriver.setItems(dao.getDriversMVC());
        listBus.setItems(dao.getBussesMVC());
        listDriver.getSelectionModel().selectedItemProperty().addListener((obs, oldKorisnik, newKorisnik) -> {
            Driver oldDriver= (Driver) oldKorisnik;
            Driver newDriver= (Driver) newKorisnik;
            if (oldKorisnik != null) {
                nameDriver.textProperty().unbindBidirectional(oldDriver.nameProperty());
                surnameDriver.textProperty().unbindBidirectional(oldDriver.surnameProperty());
                JMBGDriver.textProperty().unbindBidirectional(oldDriver.jmbProperty());


            }
            if (newKorisnik == null) {
                nameDriver.setText("");
                surnameDriver.setText("");
                JMBGDriver.setText("");
            }
            else {
                nameDriver.textProperty().bindBidirectional(newDriver.nameProperty());
                surnameDriver.textProperty().bindBidirectional(newDriver.surnameProperty());
                JMBGDriver.textProperty().bindBidirectional(newDriver.jmbProperty());
            }
        });
        listBus.getSelectionModel().selectedItemProperty().addListener((obs, oldKorisnik, newKorisnik) -> {
            Bus oldBus= (Bus) oldKorisnik;
            Bus newBus= (Bus) newKorisnik;
            if (oldKorisnik != null) {
                MakerBus.textProperty().unbindBidirectional(oldBus.makerProperty());
                SeriesBus.textProperty().unbindBidirectional(oldBus.seriesProperty());


            }
            if (newKorisnik == null) {
                MakerBus.setText("");
                SeriesBus.setText("");
            }
            else {
                MakerBus.textProperty().bindBidirectional(newBus.makerProperty());
                SeriesBus.textProperty().bindBidirectional(newBus.seriesProperty());
            }
        });


    }


    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }


    public void addDriver(ActionEvent actionEvent) {
        listDriver.getItems().add(new Driver(" ",""," ", LocalDate.EPOCH,LocalDate.EPOCH));
        dao.addDriver(new Driver(" ",""," ", LocalDate.EPOCH,LocalDate.EPOCH));
    }

    public void deleteDriver(ActionEvent actionEvent) {
        Driver driver = (Driver) listDriver.getSelectionModel().getSelectedItem();
        listDriver.getItems().remove(driver);
        dao.deleteDriver(driver);

    }
    public void addBus(ActionEvent actionEvent) throws SQLException {

        listBus.getItems().add(new Bus(" "," ",0));
        dao.addBus  (new Bus(" "," ",0));

    }

    public void deleteBus(ActionEvent actionEvent) {
        Bus bus = (Bus) listBus.getSelectionModel().getSelectedItem();
        listBus.getItems().remove(bus);
        dao.deleteBus(bus);

    }


    public void btnUpdateBus(ActionEvent actionEvent) throws SQLException {
        for(Bus k: listBus.getItems())
            dao.UpdateBus(k);
    }

    public void btnUpdateDriver(ActionEvent actionEvent) throws SQLException {
        for(Driver k: listDriver.getItems())
            dao.UpdateDriver(k);
    }
}
