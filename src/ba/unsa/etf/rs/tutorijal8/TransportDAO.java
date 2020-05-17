package ba.unsa.etf.rs.tutorijal8;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Scanner;

public class TransportDAO {
    private static TransportDAO instance;
    private Connection conn;
    private ArrayList<Driver> Drivers = new ArrayList<>();
    private ArrayList<Bus> Busses = new ArrayList<>();


    public static TransportDAO getInstance() {
        if (instance == null) instance = new TransportDAO();
        return instance;
    }

    private TransportDAO() {
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:baza.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void resetDatabase() {
        try {
            Scanner ulaz = new Scanner(new FileInputStream("baza.db.sql"));
            String sqlupit = "";
            while (ulaz.hasNext()) {
                sqlupit += ulaz.nextLine();
                if (sqlupit.charAt(sqlupit.length() - 1) == ';') {
                    try {
                        Statement s = conn.createStatement();
                        s.execute(sqlupit);
                        sqlupit = "";
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
            ulaz.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Driver> getDrivers() {
        Drivers = new ArrayList<>();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet rezultat = statement.executeQuery("SELECT *FROM Driver");
            while (rezultat.next()) {
                Drivers.add(new Driver(rezultat.getString(1), rezultat.getString(2),
                        rezultat.getString(3),
                        LocalDate.parse(rezultat.getDate(4).toString()),
                        LocalDate.parse(rezultat.getDate(5).toString())));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Drivers;
    }

    public ObservableList<Driver> getDriversMVC() {
        ObservableList<Driver> vozaci = FXCollections.observableArrayList();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet rezultat = statement.executeQuery("SELECT *FROM Driver");
            while (rezultat.next()) {
                vozaci.add(new Driver(rezultat.getString(1), rezultat.getString(2),
                        rezultat.getString(3),
                        LocalDate.parse(rezultat.getDate(4).toString()),
                        LocalDate.parse(rezultat.getDate(5).toString())));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vozaci;
    }

    public void addDriver(Driver driver) {
        getDrivers();
        boolean i = false;
        for (Driver d : Drivers)
            if (d.getJmb().equals(driver.getJmb())) i = true;
        if (i == false) {
            Drivers.add(driver);
            try {
                PreparedStatement addDriver = conn.prepareStatement(" insert into Driver values (?,?,?,?,?)");
                addDriver.setString(1, driver.getName());
                addDriver.setString(2, driver.getSurname());
                addDriver.setString(3, driver.getJmb());
                addDriver.setDate(4, Date.valueOf(driver.getBirthday()));
                addDriver.setDate(5, Date.valueOf(driver.getEmployeadDate()));
                addDriver.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else throw new IllegalArgumentException("Taj vozač već postoji!");

    }

    public void addBus(Bus bus) {
        Busses.add(bus);
        try {
            PreparedStatement addDriver = conn.prepareStatement(" insert into bus values (?,?,?,?,?,?)");
         //   ResultSet id;
           // PreparedStatement ID = conn.prepareStatement(" SELECT max(BusID)+1 From bus");
            //                id=ID.executeQuery();

          //  addDriver.setInt(1,id.next() );
            addDriver.setString(2, bus.getMaker());
            addDriver.setString(3, bus.getSeries());
            addDriver.setInt(4, bus.getSeatNumber());
            addDriver.setString(5, null);
            addDriver.setString(6, null);
            addDriver.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public ArrayList<Bus> getBusses() {
        Busses = new ArrayList<>();
        try {
            Statement statement = conn.createStatement();
            ResultSet rezultat = statement.executeQuery("SELECT * FROM bus");
            while (rezultat.next()) {
                Driver prvi = null;
                Driver drugi = null;
                if (rezultat.getString(5) != null) {
                    for (Driver d : Drivers) {
                        if (d.getJmb().equals(rezultat.getString(5))) prvi = d;
                    }
                }
                if (rezultat.getString(6) != null) {
                    for (Driver d : Drivers) {
                        if (d.getJmb().equals(rezultat.getString(5))) drugi = d;
                    }
                }
                Busses.add(new Bus(rezultat.getString(2), rezultat.getString(3),
                        rezultat.getInt(4), prvi, drugi));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Busses;
    }


    public ObservableList<Bus> getBussesMVC() {
        ObservableList<Bus> busevi = FXCollections.observableArrayList();
        try {
            Statement statement = conn.createStatement();
            ResultSet rezultat = statement.executeQuery("SELECT * FROM bus");
            while (rezultat.next()) {
                Driver prvi = null;
                Driver drugi = null;
                if (rezultat.getString(5) != null) {
                    for (Driver d : Drivers) {
                        if (d.getJmb().equals(rezultat.getString(5))) prvi = d;
                    }
                }
                if (rezultat.getString(6) != null) {
                    for (Driver d : Drivers) {
                        if (d.getJmb().equals(rezultat.getString(5))) drugi = d;
                    }
                }
                busevi.add(new Bus(rezultat.getString(2), rezultat.getString(3),
                        rezultat.getInt(4), prvi, drugi));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return busevi;
    }


    public void deleteBus(Bus bus) {
        Busses.remove(bus);
        try {
            PreparedStatement deleteBus = conn.prepareStatement(
                    "DELETE FROM bus where Maker=? and Series=? and SeatNumber=?");
            deleteBus.setString(1, bus.getMaker());
            deleteBus.setString(2, bus.getSeries());
            deleteBus.setInt(3, bus.getSeatNumber());
            deleteBus.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteDriver(Driver driver) {
        Drivers.remove(driver);
        try {
            PreparedStatement addDriver = conn.prepareStatement("DELETE FROM Driver where jmb=?");
            addDriver.setString(1, driver.getJmb());
            addDriver.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dodijeliVozacuAutobus(Driver driver, Bus bus, int which) {
        try {
            PreparedStatement updateBus;
            if (which == 1) {
                updateBus = conn.prepareStatement(
                        "UPDATE bus SET DriverOne=? WHERE Maker=? and Series=? and SeatNumber=?");
                bus.setDriverOne(driver);
            } else {
                updateBus = conn.prepareStatement(
                        "UPDATE bus SET DriverTwo=? WHERE Maker=? and Series=? and SeatNumber=?");
                bus.setDriverTwo(driver);
            }
            updateBus.setString(1, driver.getJmb());
            updateBus.setString(2, bus.getMaker());
            updateBus.setString(3, bus.getSeries());
            updateBus.setInt(4, bus.getSeatNumber());
            updateBus.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void UpdateDriver(Driver driver) throws SQLException {
        PreparedStatement updateDriverST;
        updateDriverST = conn.prepareStatement("UPDATE Driver SET name = ?, surname = ? WHERE jmb = ? ");
        updateDriverST.setString(1, driver.getName());
        updateDriverST.setString(2, driver.getSurname());
        updateDriverST.setString(3, driver.getJmb());
        updateDriverST.executeUpdate();

    }

    public void UpdateBus(Bus bus) throws SQLException {
        PreparedStatement updateBus;
        updateBus = conn.prepareStatement("UPDATE bus SET Maker = ?, Series=? WHERE SeatNumber=?");
        updateBus.setString(1, bus.getMaker());
        updateBus.setString(2, bus.getSeries());
        updateBus.setInt(3, bus.getSeatNumber());
        updateBus.executeUpdate();

    }

}