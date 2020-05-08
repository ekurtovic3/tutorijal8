CREATE TABLE DriverBus (
	driverId	INTEGER,
	busId	INTEGER,
	driverbus_id	INTEGER,
	PRIMARY KEY(driverbus_id),
	FOREIGN KEY(busId) REFERENCES Bus(bus_id),
	FOREIGN KEY(driverId) REFERENCES Vozac(vozac_id)
);
CREATE TABLE Driver (
	driver_id	INTEGER,
	name	TEXT,
	surname	TEXT,
	JMBG	TEXT UNIQUE ,
	birthday_date	DATE,
	employment_date	DATE,
	PRIMARY KEY(driver_id)
);
CREATE TABLE Bus (
	bus_id	INTEGER,
	maker	TEXT,
	series	TEXT,
	seat_number	INTEGER,
	driver_number	INTEGER,
	PRIMARY KEY(bus_id)
);