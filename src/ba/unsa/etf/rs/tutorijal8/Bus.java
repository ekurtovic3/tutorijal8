package ba.unsa.etf.rs.tutorijal8;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Bus {
    SimpleStringProperty Maker, Series;
    SimpleIntegerProperty SeatNumber;
    Driver DriverOne, DriverTwo;

    public Bus(String maker, String series, int seatNumber) {
        Maker = new SimpleStringProperty(maker);
        Series = new SimpleStringProperty(series);
        SeatNumber =new SimpleIntegerProperty(seatNumber);
    }

    public Bus(String maker, String series, int seatNumber, Driver driverOne, Driver driverTwo) {
        Maker =new SimpleStringProperty(maker);
        Series =new SimpleStringProperty(series);
        SeatNumber =new SimpleIntegerProperty(seatNumber);
        DriverOne = driverOne;
        DriverTwo = driverTwo;
    }

    public String getMaker() {
        return Maker.get();
    }
    public void setMaker(String maker) {
        Maker.set(maker);
    }
    public String getSeries() {
        return Series.get();
    }
    public void setSeries(String series) {
        Series.set(series);
    }
    public int getSeatNumber() {
        return SeatNumber.get();
    }
    public void setSeatNumber(int seatNumber) {
        SeatNumber.set(seatNumber);
    }
    public Driver getDriverOne() {
        return DriverOne;
    }
    public void setDriverOne(Driver driverOne) {
        DriverOne = driverOne;
    }
    public Driver getDriverTwo() {
        return DriverTwo;
    }

    public SimpleStringProperty makerProperty() {
        return Maker;
    }

    public SimpleStringProperty seriesProperty() {
        return Series;
    }

    public SimpleIntegerProperty seatNumberProperty() {
        return SeatNumber;
    }

    public void setDriverTwo(Driver driverTwo) {
        DriverTwo = driverTwo;
    }

    @Override
    public String toString() {
        String s = this.getMaker() + " " + this.getSeries() + " ( seats: " + this.getSeatNumber() + " )";
        if (DriverOne != null) s += " - (" + DriverOne.toString() + ")";
        if (DriverOne != null) s += " - (" + DriverOne.toString() + ")";
        return s;


    }
}