BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Driver" (
	"name"	TEXT,
	"surname"	TEXT,
	"jmb"	TEXT PRIMARY KEY ,
	"Brithday"	date,
	"EmployedDate"	date
);
CREATE TABLE IF NOT EXISTS "bus" (
	"BusID"	INTEGER PRIMARY KEY AUTOINCREMENT,
	"Maker"	TEXT,
	"Series"	TEXT,
	"SeatNumber"	INTEGER,
	"DriverOne"	TEXT,
	"DriverTwo"	TEXT
);
DELETE FROM "bus";
DELETE FROM "Driver";
COMMIT;
