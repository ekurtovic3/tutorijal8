package ba.unsa.etf.rs.tutorijal8;

public class Bus {
    String Maker, Series;
    int SeatNumber;
    Driver DriverOne, DriverTwo;

    public Bus(String maker, String series, int seatNumber) {
        Maker = maker;
        Series = series;
        SeatNumber = seatNumber;
    }

    public Bus(String maker, String series, int seatNumber, Driver driverOne, Driver driverTwo) {
        Maker = maker;
        Series = series;
        SeatNumber = seatNumber;
        DriverOne = driverOne;
        DriverTwo = driverTwo;
    }

    public String getMaker() {
        return Maker;
    }
    public void setMaker(String maker) {
        Maker = maker;
    }
    public String getSeries() {
        return Series;
    }
    public void setSeries(String series) {
        Series = series;
    }
    public int getSeatNumber() {
        return SeatNumber;
    }
    public void setSeatNumber(int seatNumber) {
        SeatNumber = seatNumber;
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
    public void setDriverTwo(Driver driverTwo) {
        DriverTwo = driverTwo;
    }
    @Override
    public String toString(){
        String s= Maker+" "+ Series+" ( seats: "+ SeatNumber+ " )";
        if(DriverOne!= null) s+=" - (" + DriverOne.toString() + ")";
        if(DriverOne!= null) s+=" - (" + DriverOne.toString() + ")";
        return s;
    }
}