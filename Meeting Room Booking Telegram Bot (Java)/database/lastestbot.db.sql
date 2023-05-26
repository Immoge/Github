BEGIN TRANSACTION;
CREATE TABLE IF NOT EXISTS "Room_Type" (
	"Room_TypeID"	INTEGER,
	"Room_Name"	TEXT,
	PRIMARY KEY("Room_TypeID")
);
CREATE TABLE IF NOT EXISTS "User" (
	"User_ID"	TEXT,
	"ICNO"	TEXT,
	"User_Name"	TEXT,
	"Mobile_TelNo"	TEXT,
	"Email"	TEXT,
	"Booking_ID"	TEXT,
	PRIMARY KEY("User_ID"),
	CONSTRAINT "User_Booking__fk" FOREIGN KEY("Booking_ID") REFERENCES "Booking"
);
CREATE TABLE IF NOT EXISTS "School" (
	"School_ID"	INTEGER,
	"School_Name"	TEXT,
	"Office_TelNo"	TEXT,
	"Building_LocationID"	TEXT,
	PRIMARY KEY("School_ID"),
	FOREIGN KEY("Building_LocationID") REFERENCES "Building_Location"("Building_LocationID")
);
CREATE TABLE IF NOT EXISTS "Building_Location" (
	"Building_LocationID"	TEXT,
	"Building_LocationName"	TEXT,
	PRIMARY KEY("Building_LocationID")
);
CREATE TABLE IF NOT EXISTS "Room" (
	"Room_ID"	TEXT,
	"Room_Description"	TEXT,
	"Maximum_Capacity"	TEXT,
	"Status"	TEXT,
	"Room_Type"	TEXT,
	"School_ID"	TEXT,
	PRIMARY KEY("Room_ID"),
	FOREIGN KEY("School_ID") REFERENCES "School"("School_Name"),
	FOREIGN KEY("Room_Type") REFERENCES "Room_Type"("Room_TypeID")
);
CREATE TABLE IF NOT EXISTS "Booking" (
	"Booking_ID"	INTEGER,
	"Room_ID"	TEXT,
	"Purpose_of_booking"	TEXT,
	"Booking_Date"	TEXT,
	"Booking_Time"	TEXT,
	"TimeStamp"	TEXT,
	"User_ID"	TEXT,
	PRIMARY KEY("Booking_ID" AUTOINCREMENT),
	FOREIGN KEY("Room_ID") REFERENCES "Room"("Room_ID"),
	FOREIGN KEY("User_ID") REFERENCES "User"("User_ID")
);
CREATE TABLE IF NOT EXISTS "System_Admin" (
	"Staff_ID"	TEXT,
	"Email"	TEXT,
	"Password"	TEXT,
	FOREIGN KEY("Staff_ID") REFERENCES "School_Admin"("Staff_ID")
);
CREATE TABLE IF NOT EXISTS "School_Admin" (
	"School_Admin_ID"	INTEGER,
	"Staff_ID"	TEXT,
	"Staff_Name"	TEXT,
	"Staff_Mobile_TelNo"	TEXT,
	"Staff_Email"	TEXT,
	"password"	TEXT,
	"School_ID"	TEXT,
	"Room_ID"	TEXT,
	"status"	TEXT,
	PRIMARY KEY("School_Admin_ID"),
	FOREIGN KEY("School_ID") REFERENCES "School"("School_ID"),
	FOREIGN KEY("Room_ID") REFERENCES "Room"("Room_ID")
);
INSERT INTO "User" VALUES ('212341','123456555555',NULL,NULL,'jason@gmail.com','1');
INSERT INTO "User" VALUES ('221231','44',NULL,NULL,'kelvin@gmail.com','2');
INSERT INTO "User" VALUES ('xxxxx',NULL,NULL,NULL,NULL,NULL);
INSERT INTO "User" VALUES ('44','44',NULL,NULL,NULL,NULL);
INSERT INTO "Room" VALUES ('DKG 1','Lecture Hall of DKG 1/1','100','Available','Lecture Hall','SAPSP');
INSERT INTO "Room" VALUES ('DKG 2','Lecture Hall of DKG 2/1','100','Available','Lecture Hall','SOA');
INSERT INTO "Room" VALUES ('DKG 3','Lecture Hall of DKG 3/1','50','Available','Lecture Hall','SEFB');
INSERT INTO "Room" VALUES ('DKG 4','Lecture Hall of DKG 4/1','70','Available','Lecture Hall','SOC');
INSERT INTO "Booking" VALUES (1,'DKG 1','Revison','1-1-2023','10AM',NULL,'212341');
INSERT INTO "School_Admin" VALUES (1,'A123456','Ali','60-123456789','ali@gmail.com',NULL,NULL,'DKG1',NULL);
INSERT INTO "School_Admin" VALUES (2,'B987456','Ahmad','60-423568795','ahmad@gmail.com',NULL,NULL,'DKG2',NULL);
COMMIT;
