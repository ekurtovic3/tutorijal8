package ba.unsa.etf.rs.tutorijal8;

import com.sun.jdi.connect.Transport;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;

import java.util.List;

public class Controller {

    public ListView listDriver;
    public ListView listBus;
    public TextField nameDriver;
    public TextField surnameDriver;
    public TextField JMBGDriver;
    public TextField BirthdayDateDriver;
    public TextField EmploymentDateDriver;
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
                BirthdayDateDriver.textProperty().unbindBidirectional(oldDriver.birthdayProperty());
                EmploymentDateDriver.textProperty().unbindBidirectional(oldDriver.employeadDateProperty());

            }
            if (newKorisnik == null) {
                nameDriver.setText("");
                surnameDriver.setText("");
                JMBGDriver.setText("");
                BirthdayDateDriver.setText("");
                EmploymentDateDriver.setText("");
            }
            else {
                nameDriver.textProperty().bindBidirectional(newDriver.nameProperty());
                surnameDriver.textProperty().bindBidirectional(newDriver.surnameProperty());
                JMBGDriver.textProperty().bindBidirectional(newDriver.jmbProperty());
                BirthdayDateDriver.textProperty().bindBidirectional(newDriver.birthdayProperty());
                EmploymentDateDriver.textProperty().bindBidirectional(newDriver.employeadDateProperty());
            }
        });
        listBus.getSelectionModel().selectedItemProperty().addListener((obs, oldKorisnik, newKorisnik) -> {
            Bus oldBus= (Bus) oldKorisnik;
            Bus newBus= (Bus) newKorisnik;
            if (oldKorisnik != null) {
                MakerBus.textProperty().unbindBidirectional(oldBus.makerProperty());
                SeriesBus.textProperty().unbindBidirectional(oldBus.seriesProperty());
               // SeatNumberBus.integerProperty().unbindBidirectional(oldBus.seatNumberProperty());


            }
            if (newKorisnik == null) {
                MakerBus.setText("");
                SeriesBus.setText("");
                SeatNumberBus.setText("");
            }
            else {
                MakerBus.textProperty().bindBidirectional(newBus.makerProperty());
                SeriesBus.textProperty().bindBidirectional(newBus.seriesProperty());
             //   SeatNumberBus.integerProperty().bindBidirectional(newBus.seatNumberProperty());



            }
        });


    }


    public void exit(ActionEvent actionEvent) {
        System.exit(0);
    }


}
