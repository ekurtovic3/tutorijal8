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
import java.util.List;

public class Controller {

    public ListView listDriver;
    public ListView listBus;
    public TextField nameDriver;
    public TextField surnameDriver;
    public TextField JMBGDriver;
    public DatePicker BirthdayDateDriver;
    public DatePicker EmploymentDateDriver;
    public TextField MakerBus;
    public TextField SeriesBus;
    public TextField SeatNumberBus;

    private TransportDAO dao;



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
                try {
                    dao.UpdateDriver(oldDriver);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

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
                try {
                    dao.UpdateBus(oldBus);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

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
    public void addBus(ActionEvent actionEvent) {
        listBus.getItems().add(new Bus(" "," ",0));
        dao.addBus  (new Bus(" "," ",0));
    }

    public void deleteBus(ActionEvent actionEvent) {
        Bus bus = (Bus) listBus.getSelectionModel().getSelectedItem();
        listBus.getItems().remove(bus);
        dao.deleteBus(bus);

    }
}
