package my.uum;

import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

/**
 * This class is to handle all database interactions.
 * This uses JDBC-SQLite
 *
 * @author LIM JIA JUN 281231
 * @author CHONG QI JUN 279590
 * @author TEOH ERN SHENG 278985
 * @author LEONG QING YUN 277585
 * @author TAN JIA KEE 279437
 */

public class DatabaseManager {

    PreparedStatement pstmt = null;
    ResultSet rs = null;

    /**
     * The base constructor.
     * Making connection.
     */
    private Connection connect() {
//        String url = "jdbc:sqlite:C:\\Users\\qijun\\OneDrive - Allkindsoftware\\Desktop\\Source Code\\A221\\Real Time Programming\\group-project-bugbug\\database\\bot.db";
        String url = "jdbc:sqlite:C:\\Users\\qijun\\OneDrive - Allkindsoftware\\Desktop\\Source Code\\Github\\Meeting Room Booking Telegram Bot (Java)\\database\\bot.db";
        //String url = "jdbc:sqlite:C:\\Users\\JUN\\IdeaProjects\\group-project-bugbug\\database\\bot.db";

        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;

    }

    /**
     * The system admin need to provide loginStaffID and loginPassword to log in the system.
     *
     * @param loginStaffID System Admin need to fill in his/her staff if for login.
     * @param loginPassword System Admin need to fill in his/her password for login.
     *
     * @return Display the message.
     */
    public String login(String loginStaffID, String loginPassword) {
        String respond = " ";
        String sql = "SELECT * FROM System_Admin WHERE Staff_ID = ? AND Password = ?";

        try{
            Connection conn = this.connect();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, loginStaffID);
            pstmt.setString(2, loginPassword);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                respond += "Login successful! Welcome, " + loginStaffID + "." +
                        "\n\n" + "Here are some additional commands that are now available to you:\n" +
                        "/approvedSchoolAdmin - approved the request of register School Admin\n" +
                        "/displaySchoolAdmin - display the list of School Admin\n\n" +
                        "Reply 0: Back to main page";
            } else {
                respond += "Sorry, the Staff ID or password is incorrect. Please try again.\n\n" +
                        "Reply 0: Back to main page";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            if(rs != null){
                try{
                    rs.close();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
            if(pstmt != null){
                try{
                    pstmt.close();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return respond;
    }

    /**
     * Get the details of the school admin that the status is "pending" and waiting to approve.
     *
     * @return Display the list of school admin which the status is "pending".
     */
    public String displayApprovedAdmin() {
        String respond = " ";

        try{
            Connection conn = this.connect();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery("SELECT Staff_ID, Staff_Name FROM School_Admin WHERE status = 'pending'");

            while (rs.next()) {
                respond += "\ud83d\udccb " + rs.getString("Staff_ID") + " " + rs.getString("Staff_Name") + "\n";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            if(rs != null){
                try{
                    rs.close();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
            if(pstmt != null){
                try{
                    pstmt.close();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return respond;
    }

    /**
     * The System Admin need to provide his/her specific PIN continue to approve the school admin.
     *
     * @param loginStaffID The staff id is taken to detect before approve the school admin.
     * @param pin The PIN is taken to detect before approve the school admin.
     * @return Display the result.
     */
    public String checkAdminPin(String loginStaffID, String pin) {
        String respond = " ";
        String sql = "SELECT * FROM System_Admin WHERE Staff_ID = ? AND pin = ?";

        try{
            Connection conn = this.connect();
            pstmt = conn.prepareStatement(sql);

            pstmt.setString(1, loginStaffID);
            pstmt.setString(2, pin);

            rs = pstmt.executeQuery();
            if (rs.next()) {
                respond += "Correct Pin! " + "\n\n" +
                        "The School Admin that need to approved: \n\n" +
                        displayApprovedAdmin() + "\n" +
                        "Please Enter the Staff Id that wanted to approved. \n" + "Format: Approve - xxxxxxx";
            } else {
                respond += "Sorry, this pin is incorrect. So you cannot continue to process approve the school admin. Please try again.\n\n" +
                        "Reply 0: Back to main page";
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            if(rs != null){
                try{
                    rs.close();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
            if(pstmt != null){
                try{
                    pstmt.close();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return respond;
    }

    /**
     * Update the status of the school admin that already be approved.
     *
     * @param schoolStaffID The staff if of school admin that the system admin choose to change the status to approve.
     */
    public void updateStatusSchoolAdmin(String schoolStaffID) {
        String sql = "UPDATE School_Admin SET status = 'approved' WHERE Staff_ID = ?";

        try{
            Connection conn = this.connect();
            pstmt = conn.prepareStatement(sql);

            // set the corresponding param
            pstmt.setString(1, schoolStaffID);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            if(rs != null){
                try{
                    rs.close();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
            if(pstmt != null){
                try{
                    pstmt.close();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Get the details of the school admin that the status is "approved" and waiting to approve.
     *
     * @return Display the list of school admin which the status is "approved".
     */
    public String displayAdminApproved() {
        String respond = " ";

        try{
            Connection conn = this.connect();
            Statement statement = conn.createStatement();
            rs = statement.executeQuery("SELECT Staff_ID, Staff_Name FROM School_Admin WHERE status = 'approved'");

            if (rs.next()) {
                respond += "\ud83d\udccb " + rs.getString("Staff_ID") + " " + rs.getString("Staff_Name") + "\n\n" + "Reply 0: Back to main page";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }finally {
            if(rs != null){
                try{
                    rs.close();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
            if(pstmt != null){
                try{
                    pstmt.close();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
        return respond;
    }

    /**
     * Insert the user information entered by the user and save it into the database.
     *
     * @param User_ID Users need to fill in their user id for their information.
     * @param ICNO Users need to fill in their IC number for their information.
     * @param User_Name Users need to fill in their name for their information.
     * @param Mobile_TelNo Users need to fill in their phone number for their information.
     * @param Email Users need to fill in their email for their information.
     * @param pin PIN will be generate after the user register.
     * @return Display the result.
     */
    public String[] insertUser(String User_ID, String ICNO, String User_Name, String Mobile_TelNo, String Email, String pin) {
        String respond = "";
        int min = 0;
        int max = 999999;
        int random = (int) (Math.random() * (max - min + 1) + min);
        pin = String.format("%06d", random);

        String sql2 = "INSERT INTO User(User_ID,ICNO,User_Name,Mobile_TelNo,Email,pin) VALUES(?,?,?,?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql2)) {
            pstmt.setString(1, User_ID);
            pstmt.setString(2, ICNO);
            pstmt.setString(3, User_Name);
            pstmt.setString(4, Mobile_TelNo);
            pstmt.setString(5, Email);
            pstmt.setString(6, pin);
            pstmt.executeUpdate();
            System.out.println("Pin" + pin);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new String[0];
    }

    /**
     * Checking the user who is entering the correct PIN.
     *
     * @param User_ID The user ID that want to continue booking the system.
     * @return
     */
    public String checkUserPin(String User_ID) {
        String respond = "";
        String sql = "SELECT pin FROM User WHERE User_ID = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, User_ID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("pin");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return respond;
    }


    /**
     * Checking the user who is entering the correct PIN.
     *
     * @param pin The PIN entered by the user.
     * @return
     */
    public boolean checkPin(String pin) {
        String sql = "SELECT pin FROM User WHERE pin = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, pin);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                System.out.println("Pin="+pin);
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    /**
     * Insert the booking details entered by the user and save them into the database.
     *
     * @param Booking_ID
     * @param Room_ID Users need to choose the room id that they want to book.
     * @param User_ID Users need to give their user id to know who is booking the room.
     * @param Purpose_of_booking Users need to give the purpose for booking the room.
     * @param Booking_Time Users need to give time to book the room.
     * @param Booking_Date Users need to give a date to book the room.
     * @param pin Users need to enter the PIN to continue booking.
     */
    public void insertBooking( String Booking_ID,String Room_ID, String User_ID, String Purpose_of_booking, String Booking_Time, String Booking_Date,String pin) {
        if(checkPin(pin)) {
            String sql = "INSERT INTO Booking(Booking_ID, Room_ID, User_ID, Purpose_of_booking, Booking_Time, Booking_Date,timeStamp,pin) VALUES(?,?,?,?,?,?,?,?)";
            try (Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, pin);
                pstmt.setString(1, Booking_ID);
                pstmt.setString(2, Room_ID);
                pstmt.setString(3, User_ID);
                pstmt.setString(4, Purpose_of_booking);
                pstmt.setString(5, Booking_Time);
                pstmt.setString(6, Booking_Date);

                ZonedDateTime zdt = ZonedDateTime.now(ZoneId.of("Asia/Kuala_Lumpur"));
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String timeStamp = zdt.format(formatter);
                System.out.println(timeStamp);


                pstmt.setString(7, timeStamp);
                pstmt.setString(8, pin);

                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }else {
            System.out.println("The pin you entered is not exist in the User table, please insert the User First.");
        }
    }

    /**
     * Display the meeting room that can be booked which the status = 'Ready'.
     *
     * @return Display the result.
     */
    public String displayRoomID() {
        String respond = " ";

        try (Connection conn = this.connect();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT Room_ID FROM Room WHERE Status = 'Available'")) {

            while (resultSet.next()) {
                respond += "Room ID: " + resultSet.getString("Room_ID") + "\n";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return respond;
    }

    /**
     * Update the meeting room status to 'Reserved' when the user successfully books the meeting room.
     *
     * @param Room_ID The room id that the users choose to change the status.
     */
    public void updateStatus(String Room_ID) {
        String sql = "UPDATE Room SET status = 'Reserved' WHERE Room_ID = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, Room_ID);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Checking the User_ID is found or not.
     *
     * @param User_ID Users need to give their user id to know who is booking the room.
     * @return Display the result.
     */
    public String checkingUser(String User_ID) {
        String respond = "";
        String selectSql = "SELECT * FROM User WHERE User_ID = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(selectSql)) {
            pstmt.setString(1, User_ID);

            // Execute the select statement and get the result set
            ResultSet resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                respond += "Congratulations! Record " + User_ID + " already been search." + "\n\n";
                respond += "These the User_ID  about " + User_ID + ": " + "\n\n";

                respond += " User Details" + "\n";
                respond += " ~~~~~~~~~~" + "\n";
                respond += " \u21aa\ufe0f Pin: " + resultSet.getString("pin") + "\n";
                respond += " \u21aa\ufe0f User_ID: " + resultSet.getString("User_ID") + "\n";
                respond += " \u21aa\ufe0f ICNO: " + resultSet.getString("ICNO") + "\n";
                respond += " \u21aa\ufe0f User_Name: " + resultSet.getString("User_Name") + "\n\n";
                respond += " \u21aa\ufe0f Mobile_TelNo: " + resultSet.getString("Mobile_TelNo") + "\n";
                respond += " \u21aa\ufe0f Email: " + resultSet.getString("Email") + "\n";

            }
            if (respond.equals(" ")) {
                respond += "Sorry, there is no record of " + User_ID + ". Please try another ID.";
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        return respond;
    }

    /**
     *  Update the user details when the user click to change their information.
     *
     * @param ICNO Users need to fill in their IC number for updating their information.
     * @param User_ID Users need to fill in their User_ID for updating their information.
     * @return
     */
    public String updateUser(String ICNO, String User_ID) {
        String updateSql = "UPDATE User SET ICNO =? WHERE  User_ID = ?";


        // Check if the user ID exists in the database
        try (Connection conn = this.connect();

             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
            System.out.println("yes");
            // The user ID is valid, so update the user's profile in the users table

            updateStmt.setString(1, ICNO);
            updateStmt.setString(2, User_ID);

            updateStmt.executeUpdate();
            System.out.println("not");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return updateSql;
    }

    /**
     * Update the user details when the user click to change their information by providing their User_ID.
     *
     * @param User_Name The users need to give the new name that they want to update.
     * @param User_ID The users need to give the User_ID to choose the user's information that they want to update.
     */
    public void UpdateProfileUser_Name(String User_Name, String User_ID) {

        String updateSql = "UPDATE User SET User_Name =? WHERE  User_ID = ?";

        try (Connection conn = this.connect();

             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            // The user ID is valid, so update the user's profile in the users table
            updateStmt.setString(1, User_Name);

            updateStmt.setString(2, User_ID);
            updateStmt.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exceptions
            System.out.println(e.getMessage());

        }
    }

    /**
     * Update the user details when the user click to change their information by providing their User_ID.
     *
     * @param Mobile_TelNo The users need to give the new phone number that they want to update.
     * @param User_ID The users need to give the User_ID to choose the user's information that they want to update.
     */
    public void UpdateProfileMobile_TelNo(String Mobile_TelNo, String User_ID) {

        String updateSql = "UPDATE User SET Mobile_TelNo =? WHERE  User_ID = ?";

        try (Connection conn = this.connect();

             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            // The user ID is valid, so update the user's profile in the users table
            updateStmt.setString(1, Mobile_TelNo);

            updateStmt.setString(2, User_ID);
            updateStmt.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exceptions
            System.out.println(e.getMessage());

        }
    }

    /**
     * Update the user details when the user click to change their information by providing their User_ID.
     *
     * @param User_ID The users need to give the User_ID to choose the user's information that they want to update.
     * @param Email The users need to give the new email that they want to update.
     */
    public void UpdateProfileEmail(String User_ID, String Email) {

        String updateSql = "UPDATE User SET Email =? WHERE  User_ID = ?";

        try (Connection conn = this.connect();

             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            // The user ID is valid, so update the user's profile in the users table
            updateStmt.setString(1, Email);

            updateStmt.setString(2, User_ID);
            updateStmt.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exceptions
            System.out.println(e.getMessage());

        }
    }

    /**
     * Delete the user details from the database.
     *
     * @param User_ID The users need to give the User_ID to delete.
     */
    public void deleteUsers(String User_ID) {
        String sql = "DELETE FROM User WHERE User_ID = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, User_ID);
            // execute the delete statement
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    // pin,Booking_ID, Room_ID, User_ID, Purpose_of_booking, Booking_Time, Booking_Date

    /**
     * Update the booking details when the user click to change the booking information by providing their PIN.
     *
     * @param pin The users need to give the PIN to choose the booking's information that they want to update.
     * @param Purpose_of_booking The users need to give the new purpose that they want to update.
     */
    public void updateBookingPurpose_of_booking(String pin, String Purpose_of_booking) {

        String updateSql = "UPDATE Booking SET Purpose_of_booking =? WHERE  pin= ?";

        try (Connection conn = this.connect();

             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            // The user ID is valid, so update the user's profile in the users table
            updateStmt.setString(1, Purpose_of_booking);

            updateStmt.setString(2, pin);
            updateStmt.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exceptions
            System.out.println(e.getMessage());

        }
    }

    /**
     * Update the booking details when the user click to change the booking information by providing their PIN.
     *
     * @param pin The users need to give the PIN to choose the booking's information that they want to update.
     * @param Booking_Date The users need to give the new booking date that they want to update.
     */
    public void updateBookingBooking_Date(String pin, String Booking_Date) {

        String updateSql = "UPDATE Booking  SET Booking_Date =? WHERE  pin= ?";

        try (Connection conn = this.connect();

             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            // The user ID is valid, so update the user's profile in the users table
            updateStmt.setString(1, Booking_Date);

            updateStmt.setString(2, pin);
            updateStmt.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exceptions
            System.out.println(e.getMessage());

        }
    }

    /**
     * Update the booking details when the user click to change the booking information by providing their PIN.
     *
     * @param pin The users need to give the PIN to choose the booking's information that they want to update.
     * @param Booking_Time The users need to give the new booking time that they want to update.
     */
    public void updateBookingBooking_Time(String pin, String Booking_Time) {

        String updateSql = "UPDATE Booking  SET Booking_Time =? WHERE  pin= ?";

        try (Connection conn = this.connect();

             PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {

            // The user ID is valid, so update the user's profile in the users table
            updateStmt.setString(1, Booking_Time);

            updateStmt.setString(2, pin);
            updateStmt.executeUpdate();

        } catch (SQLException e) {
            // Handle SQL exceptions
            System.out.println(e.getMessage());

        }
    }

    /**
     * Update the status of the meeting room to 'Ready' when the user cancels booking the meeting room.
     *
     * @param User_ID The user id that the user enters to cancel the booking and the room ID taken by the user will be detected.
     */
    public void updateStatusDelete(String User_ID) {
        String sql = "UPDATE Room SET status = 'Available' WHERE room_id =(SELECT Room.Room_ID from Room INNER JOIN Booking on Booking.Room_ID = Room.Room_ID WHERE Booking.User_ID = ?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, User_ID);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Delete the booking details from the database.
     *
     * @param User_ID The user id is taken to get the booking details.
     */
    public void deleteBooking(String User_ID) {
        String sql = "DELETE FROM Booking WHERE User_ID= ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // set the corresponding param
            pstmt.setString(1, User_ID);
            // execute the delete statement
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
     /**
     * Get the details of all rooms in the system.
     *
     * @return Display the details of all the rooms in the system.
     */
    public String displayAllRooms() {
        String respond = " ";

        try (Connection conn = this.connect();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM Room")) {

            while (resultSet.next()) {
                 respond += "Room ID:  " + resultSet.getString("Room_ID") + "\n" + "Room Description: " + resultSet.getString("Room_Description") + "\n" + "Maximum Capacity: " + resultSet.getString("Maximum_Capacity") + "\n" + "Room Type: " + resultSet.getString("Room_TypeID") + "\n" + "Room Status: " + resultSet.getString("Status") + "\n" + "School ID: " + resultSet.getString("School_ID") + "\n" + "\n";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return respond;
    }
    
    /**
     * Update the Room Description of a room.
     *
     * @param Room_Description School Admin need to enter the new Room Description of the room.
     * @param Room_ID School Admin need to enter the RoomID of the room to edit.
     */
    public void updateRoomDesc(String Room_Description, String Room_ID) {
        String sql = "UPDATE ROOM SET Room_Description = ? WHERE Room_ID = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, Room_Description);
            pstmt.setString(2, Room_ID);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Update the Maximum Capacity of a room.
     *
     * @param Maximum_Capacity School Admin need to enter the new Maximum Capacity of the room.
     * @param Room_ID School Admin need to enter the RoomID of the room to edit.
     */
    public void updateMaxCap(String Maximum_Capacity, String Room_ID) {
        String sql = "UPDATE ROOM SET Maximum_Capacity = ? WHERE Room_ID = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, Maximum_Capacity);
            pstmt.setString(2, Room_ID);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
     /**
     * Update the Room Type of room.
     *
     * @param Room_Type School Admin need to enter the new Room Type of the room.
     * @param Room_ID School Admin need to enter the RoomID of the room to edit.
     */
    public void updateRoomType(String Room_Type, String Room_ID) {
        String sql = "UPDATE ROOM SET Room_TypeID = ? WHERE Room_ID = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, Room_Type);
            pstmt.setString(2, Room_ID);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Update the Status of a room.
     *
     * @param Status School Admin need to enter the new Status of the room.
     * @param Room_ID School Admin need to enter the RoomID of the room to edit.
     */
    public void updateRoomStatus(String Status, String Room_ID) {
        String sql = "UPDATE ROOM SET Status = ? WHERE Room_ID = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, Status);
            pstmt.setString(2, Room_ID);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Update the School ID of a room.
     *
     * @param School_ID School Admin need to enter the new School ID of the room.
     * @param Room_ID School Admin need to enter the RoomID of the room to edit.
     */
    public void updateSchoolId(String School_ID, String Room_ID) {
        String sql = "UPDATE ROOM SET School_ID = ? WHERE Room_ID = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setString(1, School_ID);
            pstmt.setString(2, Room_ID);
            // update
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    /**
     * Get the details of a selected room from the system.
     *
     * @param Room_ID School Admin need to enter the RoomID of the room to edit.
     * @return Display the details of the room.
     */
    public String getRoomInfo(String Room_ID) {
        String respond = "";
        String sql = "SELECT * FROM Room WHERE Room_ID = ?";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, Room_ID);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                respond += "Room Description: " + rs.getString("Room_Description") + "\n" + "Maximum Capacity: " + rs.getString("Maximum_Capacity") + "\n" + "Status: " + rs.getString("Status") + "\n" + "Room Type: " + rs.getString("Room_TypeID") + "\n" + "School ID: " + rs.getString("School_ID");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return respond;
    }

    /**
     * Insert the information of School Admin into table School_Admin
     *
     * @param Staff_ID Staff ID.
     * @param Staff_Name The name of the staff.
     * @param Staff_Mobile_TelNo The phone number of the staff.
     * @param Staff_Email The email address of the staff.
     * @param password Password.
     * @param School_ID The school ID  of the staff.
     * @param status The status of the account (pending/apporve).
     * @param pin The auto generated access pin.
     * @return The array of the School Admin information.
     */
    public String[] insertAdmin(String Staff_ID, String Staff_Name, String Staff_Mobile_TelNo, String Staff_Email, String password, String School_ID, String status, String pin) {
        String sql2 = "INSERT INTO School_Admin(Staff_ID,Staff_Name,Staff_Mobile_TelNo,Staff_Email,password,School_ID,status,pin) VALUES(?,?,?,?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql2)) {
            pstmt.setString(1, Staff_ID);
            pstmt.setString(2, Staff_Name);
            pstmt.setString(3, Staff_Mobile_TelNo);
            pstmt.setString(4, Staff_Email);
            pstmt.setString(5, password);
            pstmt.setString(6, School_ID);
            pstmt.setString(7, status);
            pstmt.setString(8, pin);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new String[0];
    }

    /**
     * Add a new room information
     *
     * @param Room_ID Room_ID.
     * @param Room_Description The description of the Room.
     * @param Maximum_Capacity The maximum capacity of the Room.
     * @param Status The status of the room (Reserved/Available).
     * @param Room_TypeID The Room Type ID of the room.
     * @param School_ID  The School ID of the room.
     * @return The array of the room information.
     */
    public String[] insertRoom(String Room_ID, String Room_Description, String Maximum_Capacity, String Status, String Room_TypeID, String School_ID) {
        String sql2 = "INSERT INTO Room(Room_ID,Room_Description,Maximum_Capacity,Status,Room_TypeID,School_ID) VALUES(?,?,?,?,?,?)";
        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql2)) {
            pstmt.setString(1, Room_ID);
            pstmt.setString(2, Room_Description);
            pstmt.setString(3, Maximum_Capacity);
            pstmt.setString(4, Status);
            pstmt.setString(5, Room_TypeID);
            pstmt.setString(6, School_ID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return new String[0];
    }

    /**
     * Generate 6 digit of pin
     *
     * @return 6 digit pin number.
     */
    public String GeneratePin(){
        int min = 0;
        int max = 999999;
        int random = (int) (Math.random() * (max - min + 1) + min);
        String pin = String.format("%06d", random);
        return pin;
    }

    /**
     * Get the list of users and the details of the room that had been booking.
     *
     * @param Room_ID Room ID is shown in the list after booking the room.
     * @param Room_Description Room description is shown in the list after booking the room.
     * @param Maximum_Capacity Maximum capacity of the room is shown in the list after booking the room.
     * @param Booking_Date Booking date is shown in the list after booking the room.
     * @param Booking_Time Booking time is shown in the list after booking the room.
     *
     * @return Display list of users and their room information that had been booking.
     */
    public String displayUserList(String Room_ID,String Room_Description,String Maximum_Capacity , String Booking_Date ,String Booking_Time) {
        String respond = " ";

        try (Connection conn = this.connect();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT Booking.Room_ID, Room.Room_Description, Room.Maximum_Capacity, Booking.Booking_Date, Booking.Booking_Time FROM Booking JOIN Room ON Booking.Room_ID = Room.Room_ID")) {

            while (resultSet.next()) {
                respond +="Room ID: " + resultSet.getString("Room_ID") + "\n" + "Room Description: " + resultSet.getString("Room_Description") + "\n" + "Maximum Capacity: " + resultSet.getString("Maximum_Capacity") + "\n" + "Booking Date: " + resultSet.getString("Booking_Date") + "\n" + "Booking Time: " + resultSet.getString("Booking_Time") + "\n" + "\n";            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return respond;
    }

    /**
     * Get the details of available booking room from the system.
     *
     * @return Display the list of room and the room's details that available to booking.
     */
    public String displayAvailableRooms() {
        String respond = " ";

        try (Connection conn = this.connect()) {
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM Room WHERE Status = 'Available' ");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                respond += "Room ID:  " + resultSet.getString("Room_ID") + "\n" + "Room Description: " + resultSet.getString("Room_Description") + "\n" + "Maximum Capacity: " + resultSet.getString("Maximum_Capacity") + "\n" + "Room Type: " + resultSet.getString("Room_TypeID") + "\n" + "Room Status: " + resultSet.getString("Status") + "\n" + "School ID: " + resultSet.getString("School_ID") + "\n" + "\n";
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return respond;
    }

    /**
     * Delete the booking details from the database while thW data is more than a week.
     */
    public void deleteOldBookings() {
        // Delete data that is more than a week old
        String sql = "DELETE FROM Booking WHERE timeStamp <= DATEADD(week, -1, GETDATE())";
        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}




