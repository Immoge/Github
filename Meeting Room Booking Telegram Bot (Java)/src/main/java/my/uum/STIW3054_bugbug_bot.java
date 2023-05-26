package my.uum;


import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is for manipulating Telegram Bot.
 * Give the command handling and response transmission for the bot.
 * Extends TelegramLongPollingBot provided by org.telegram.telegrambots.
 *
 * @author LIM JIA JUN 281231
 * @author CHONG QI JUN 279590
 * @author TEOH ERN SHENG 278985
 * @author LEONG QING YUN 277585
 * @author TAN JIA KEE 279437
 */
public class STIW3054_bugbug_bot extends TelegramLongPollingBot {

    /**
     * System Admin
     * loginStaffID - system Admins staff id
     * loginPassword - system Admins password
     * pinSA - system Admins PIN
     */
    private String loginStaffID, loginPassword, pinSA; //systemAdmin

    /**
     *
     */
    private String User_ID, ICNO,User_Name, Mobile_TelNo, Email,pin;
    private String Staff_ID,Staff_Name,Staff_Mobile_TelNo,Staff_Email,Staff_Password,Booking_ID,Room_ID,status;
    private String Room_Description,Maximum_Capacity,Room_Type,Status,School_ID;
    private String Purpose_of_booking, Booking_Date, Booking_Time;

    /**
     * This method is to get the Telegram Bot username.
     * Overrided method from TelegramLongPollingBot.
     */
    @Override
    public String getBotUsername() {
        // TODO
        return "STIW3054_bugbug_bot";
    }

    /**
     * This method is to get the Telegram Bot token.
     * Overrided method from TelegramLongPollingBot.
     */
    @Override
    public String getBotToken() {
        // TODO
        return "5837126857:AAH1cFR5CCBYfG2t_VKXbTL8ATw-6TQ21FM";
    }

    /**
     * This method receives commands and input entered from a user and displays the result.
     * Overrided method from TelegramLongPollingBot.
     * @param update The update that was received from Telegram's API and display the result.
     */
    @Override
    public void onUpdateReceived(Update update) {

        Message message;
        if (update.hasMessage()) {
            message = update.getMessage();
        } else
            return;

        String msg = "";

        /**
         * com[0] is using to get the data before " - ".
         * com[1] is using to get the data after " - ".
         */
        String[] com = message.getText().split(" - ");

        String chatId = message.getChatId().toString();
        String command = update.getMessage().getText();
        SendMessage sendMessage = new SendMessage();
        DatabaseManager app = new DatabaseManager();

        switch (com[0]) {
            case "/start":
            case "0":
                sendMessage.setText("Hello, " + update.getMessage().getFrom().getFirstName() + ". \ud83d\udc4b\n" +
                        "Welcome to Meeting Room Booking System!\n\n" +
                        "What is your position ? \n 1. /systemAdmin \n 2. /schooladmin \n 3. /user \n");
                sendMessage.setChatId(chatId);
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

                break;
                case "3":
                sendMessage.setText("Hello, " + update.getMessage().getFrom().getFirstName() + ". \ud83d\udc4b\n" +
                        "Welcome to Meeting Room Booking System!\n\n" +
                        " 3. /user \n");
                sendMessage.setChatId(chatId);
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }

                break;

            case "/systemAdmin":
                sendMessage.setText("Here is a list of available commands:\n" +
                        "/loginSystemAdmin - login to your account\n\n" +
                        "Reply 0: Back to main page");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "/loginSystemAdmin":
                sendMessage.setText("\ud83d\udc49 Please enter your Staff ID: " + "\n(Format: Login Staff ID - xxxxxx)");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "Login Staff ID":
                loginStaffID = com[1];
                sendMessage.setText("\ud83d\udc49 Please enter your Password: " + "\n(Format: Login Password - xxxxxx)");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "Login Password":
                loginPassword = com[1];
                String login = app.login(loginStaffID,loginPassword);
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText(login);
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "/approvedSchoolAdmin":
                sendMessage.setText("\ud83d\udc49 Please Enter the your PIN number if you want process to approve the school admin. \n" + "Format: PinSA - xxxxxxx");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "PinSA":
                pinSA = com[1];
                String checkAdminPin = app.checkAdminPin(loginStaffID,pinSA);
                sendMessage.setText(checkAdminPin);
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "Approve":
                Staff_ID = com[1];
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                app.updateStatusSchoolAdmin(Staff_ID);
                sendMessage.setText("Status of " + Staff_ID + " already been approved." +
                        "\n\n/displaySchoolAdmin - display the list of School Admin\n " + "Reply 0: Back to main page");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "/displaySchoolAdmin":
                String displayAdminApproved = app.displayAdminApproved();
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText("School Admin: \n" + displayAdminApproved);
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "/schooladmin":
                sendMessage.setText("School Administrator Rights\n\n" +
                        "/registerAdmin -provide admin details to do registration\n" +
                        "/createNewRoom -Admin create new Meeting Room\n" +
                        "/updateRoom -Update room\n" +
                        "/displayRoom -Checking the list of room\n");
                sendMessage.setChatId(chatId);
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "/user":
                sendMessage.setText("User Rights\n\n" +
                        "\uD83D\uDC49/received\n - Allows the user to receive a booking message and select a room\n" +
                        "\uD83D\uDC49/registerUser\n  - Allows the user to provide their details for booking a meeting room\n" +
                        "\uD83D\uDC49/checkingUser\n  - Allows the user to check their booking information by entering their UserID\n" +
                        "\uD83D\uDC49/updateInfo\n  - Allows the user to update their booking information by entering their UserID\n" +
                        "\uD83D\uDC49/checkingPin\n  - Allows the user to check their PIN by entering their UserID\n" +
                        "\uD83D\uDC49/updateBooking\n - Allows the user to update their booking by entering their UserID\n" +
                        "\uD83D\uDC49/cancelBooking\n - Allows the user to cancel their booking by entering their Pin\n" + "" +
                        "\uD83D\uDC49/displayUserList\n - Displays a list of users and their room and booking information\n" +
                        "\uD83D\uDC49/displayAvailableRooms\n - Allows the user to view available rooms by selecting \\n" +
                        "\n");
                sendMessage.setChatId(chatId);
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;



            /*

             */
 case "/registerAdmin":
                sendMessage.setText("Hi " + update.getMessage().getFrom().getFirstName() +
                        "\nPlease provide the personal details." +
                        "\n\n\t\ud83d\udc49 Please enter your Staff ID. (Format: StaffID - xxxxx) ");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "StaffID":
                Staff_ID = com[1];
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText("Your staff ID is " + Staff_ID + ". " +
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n\t\ud83d\udc49 Please enter your name. (Format: StaffName - xxxxxxxxxxx) ");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "StaffName":
                Staff_Name = com[1];
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText("Your name is " + Staff_Name + ". " +
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Staff ID: " + Staff_ID +
                        "\n  \ud83d\udccb Staff Name: " + Staff_Name +
                        "\n\t\ud83d\udc49  Please enter your phone number. (Format: Telephone - 60-xxxxxxx) ");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "Telephone":
                Staff_Mobile_TelNo = com[1];
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText("Your phone number is " + Staff_Mobile_TelNo + ". " +
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Staff ID: " + Staff_ID +
                        "\n  \ud83d\udccb Staff Name: " + Staff_Name +
                        "\n  \ud83d\udccb Phone Number: " + Staff_Mobile_TelNo +
                        "\n\t\ud83d\udc49 Please enter your staff email. (Format: StaffEmail - xxxxxx) ");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "StaffEmail":
                Staff_Email = com[1];
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText("Your staff email is " + Staff_Email + ". " +
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Staff ID: " + Staff_ID +
                        "\n  \ud83d\udccb Staff Name: " + Staff_Name +
                        "\n  \ud83d\udccb Phone Number: " + Staff_Mobile_TelNo +
                        "\n  \ud83d\udccb Email Address: " + Staff_Email +
                        "\n\t\ud83d\udc49 Please enter password. (Format: Password - xxxxxxxxxx) ");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "Password":
                Staff_Password = com[1];
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText("Your Password is " + Staff_Password + ". " +
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Staff ID: " + Staff_ID +
                        "\n  \ud83d\udccb Staff Name: " + Staff_Name +
                        "\n  \ud83d\udccb Phone Number: " + Staff_Mobile_TelNo +
                        "\n  \ud83d\udccb Phone Number: " + Staff_Email +
                        "\n  \ud83d\udccb Password: " + Staff_Password +
                        "\n\t\ud83d\udc49 Please enter school ID. (Format: SchoolID - xxxxxx) ");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "SchoolID":
                School_ID = com[1];
                pin = app.GeneratePin();
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText("Your School ID is " + School_ID + ". " +
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Staff ID: " + Staff_ID +
                        "\n  \ud83d\udccb Staff Name: " + Staff_Name +
                        "\n  \ud83d\udccb Phone Number: " + Staff_Mobile_TelNo +
                        "\n  \ud83d\udccb Phone Number: " + Staff_Email +
                        "\n  \ud83d\udccb Password: " + Staff_Password +
                        "\n  \ud83d\udccb Auto Generated Pin: " + pin +
                        "\n  \ud83d\udc4f Register Success");
                status = "pending";
                app.insertAdmin(Staff_ID, Staff_Name, Staff_Mobile_TelNo, Staff_Email, Staff_Password, School_ID, status, pin);
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "/createNewRoom":
                sendMessage.setText("Hi " + update.getMessage().getFrom().getFirstName() +
                        "\nPlease provide the information of the room" +
                        "\n\n\t\ud83d\udc49 Please enter the RoomID. (Format: RoomID1 - xxxxx) ");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "RoomID1":
                Room_ID = com[1];
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText("The room ID is " + Room_ID + ". " +
                        "\n\n Room Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Room ID: " + Room_ID +
                        "\n\t\ud83d\udc49 Please enter the room description. (Format: RoomDesc - xxxxxxxxxxx) ");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "RoomDesc":
                Room_Description = com[1];
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText("The room description is " + Room_Description + ". " +
                        "\n\n Room Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Room ID: " + Room_ID +
                        "\n  \ud83d\udccb Room Description: " + Room_Description +
                        "\n\t\ud83d\udc49  Please enter the maximum capacity of the room. (Format: MaxCap - xxx) ");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "MaxCap":
                Maximum_Capacity = com[1];
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText("The maximum capacity of the room is " + Maximum_Capacity + ". " +
                        "\n\n Room Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Room ID: " + Room_ID +
                        "\n  \ud83d\udccb Room Description: " + Room_Description +
                        "\n  \ud83d\udccb Maximum Capacity: " + Maximum_Capacity +
                        "\n\t\ud83d\udc49 Please enter the status of the room. (Format: RoomStatus - xxxxxx)(Reserved/Available) ");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "RoomStatus":
                Status = com[1];
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText("The room's status is " + Status + ". " +
                        "\n\n Room Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Room ID: " + Room_ID +
                        "\n  \ud83d\udccb Room Description: " + Room_Description +
                        "\n  \ud83d\udccb Maximum Capacity: " + Maximum_Capacity +
                        "\n  \ud83d\udccb Status" + Status +
                        "\n\t\ud83d\udc49 Please enter the type ID of the room. (Format: RoomTypeID - xxxxxxx) "+
                        "\n\n  Room Type Id \t\t Room Type" +
                        "\n  MR01 \t\t Meeting Room" +
                        "\n  TR01 \t\t Training Room" +
                        "\n  LAB01 \t\t Laboratory" +
                        "\n  LH01 \t\t Lecture Hall" +
                        "\n  LT01 \t\t Lecture Tutorial" +
                        "\n  SL01 \t\t Student Lounge");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "RoomTypeID":
                Room_Type = com[1];
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText("The room type is " + Room_Type + ". " +
                        "\n\n Room Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Room ID: " + Room_ID +
                        "\n  \ud83d\udccb Room Description: " + Room_Description +
                        "\n  \ud83d\udccb Maximum Capacity: " + Maximum_Capacity +
                        "\n  \ud83d\udccb Status" + Status +
                        "\n  \ud83d\udccb Room Type: " + Room_Type +
                        "\n\t\ud83d\udc49 Please enter school ID. (Format: SchoolID1 - xxxxxx)");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "SchoolID1":
                School_ID = com[1];
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText("The School ID is " + School_ID + ". " +
                        "\n\n Room Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb Room ID: " + Room_ID +
                        "\n  \ud83d\udccb Room Description: " + Room_Description +
                        "\n  \ud83d\udccb Maximum Capacity: " + Maximum_Capacity +
                        "\n  \ud83d\udccb Status" + Status +
                        "\n  \ud83d\udccb Room Type: " + Room_Type +
                        "\n  \ud83d\udccb School ID: " + School_ID +
                        "\n\n\ud83d\udc4f The room is added successfully");
                app.insertRoom(Room_ID, Room_Description, Maximum_Capacity, Status, Room_Type, School_ID);
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "/updateRoom":
                sendMessage.setText("Please enter the Room ID of the room you want to edit. Format: UpdateRoom - xxxxx");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "UpdateRoom":
                Room_ID = com[1];
                sendMessage.setText("What info do you want to edit ?" + "\n"+ app.getRoomInfo(Room_ID)+ "\n\nFormat for editing details: \nUpdateRoomDescription - xxx \nUpdateMaximumCapacity - xxx \nUpdateStatus - xxx \nUpdateRoomType - xxx \nUpdateSchoolID - xxx" );
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "UpdateRoomDescription":
                Room_Description= com[1];
                app.updateRoomDesc(Room_Description,Room_ID);
                sendMessage.setText("Room Description Successfully Updated"+ "\n" + "\n" + "Updated Room Details" + "\n" + app.getRoomInfo(Room_ID)+ "\n\nFormat for editing details: \nUpdateRoomDescription - xxx \nUpdateMaximumCapacity - xxx \nUpdateStatus - xxx \nUpdateRoomType - xxx \nUpdateSchoolID - xxx");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "UpdateMaximumCapacity":
                Maximum_Capacity= com[1];
                app.updateMaxCap(Maximum_Capacity,Room_ID);
                sendMessage.setText("Maximum Capacity Successfully Updated"+ "\n" + "\n" + "Updated Room Details" + "\n" + app.getRoomInfo(Room_ID)+ "\n\nFormat for editing details: \nUpdateRoomDescription - xxx \nUpdateMaximumCapacity - xxx \nUpdateStatus - xxx \nUpdateRoomType - xxx \nUpdateSchoolID - xxx");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "UpdateStatus":
                Status= com[1];
                app.updateRoomStatus(Status,Room_ID);
                sendMessage.setText("Status Successfully Updated"+ "\n" + "\n" + "Updated Room Details" + "\n" + app.getRoomInfo(Room_ID)+ "\n\nFormat for editing details: \nUpdateRoomDescription - xxx \nUpdateMaximumCapacity - xxx \nUpdateStatus - xxx \nUpdateRoomType - xxx \nUpdateSchoolID - xxx");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "UpdateRoomType":
                Room_Type= com[1];
                app.updateRoomType(Room_Type,Room_ID);
                sendMessage.setText("Room Type Successfully Updated"+ "\n" + "\n" + "Updated Room Details" + "\n" + app.getRoomInfo(Room_ID)+ "\n\nFormat for editing details: \nUpdateRoomDescription - xxx \nUpdateMaximumCapacity - xxx \nUpdateStatus - xxx \nUpdateRoomType - xxx \nUpdateSchoolID - xxx");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "UpdateSchoolID":
                School_ID= com[1];
                app.updateSchoolId(School_ID,Room_ID);
                sendMessage.setText("School ID Successfully Updated" + "\n" + "\n" + "Updated Room Details" + "\n" + app.getRoomInfo(Room_ID)+ "\n\nFormat for editing details: \nUpdateRoomDescription - xxx \nUpdateMaximumCapacity - xxx \nUpdateStatus - xxx \nUpdateRoomType - xxx \nUpdateSchoolID - xxx");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "/received":
                sendMessage.setText("\nThank you for choosing STIW3054_bugbug_bot service. We have received your reservation and are currently processing it. Once the reservation is confirmed, you will receive a confirmation message. To manage your reservation, " +
                        "please provide your information and use the following commands:\n\n"+

                                "\uD83D\uDC49/registerUser\n  - Allows the user to provide their details for booking a meeting room\n" +
                                "\uD83D\uDC49/checkingUser\n- Allows the user to check their booking information by entering their UserID\n" +
                                "\uD83D\uDC49/updateInfo\n- Allows the user to update their booking information by entering their UserID\n" +
                                "\uD83D\uDC49/checkingPin\n  - Allows the user to check their PIN by entering their UserID\n" +
                                "\uD83D\uDC49/updateBooking\n - Allows the user to update their booking by entering their Pin\n" +
                                "\uD83D\uDC49/cancelBooking\n - Allows the user to cancel their booking by entering their UserID\n" + "" +
                                "\uD83D\uDC49/displayUserList\n - Displays a list of users and their room and booking information\n" +
                                "\uD83D\uDC49/displayAvailableRooms\n   -Allows the user to view available rooms by selecting \\n" +
                                "\n");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "/registerUser":
                sendMessage.setText("Hi " + update.getMessage().getFrom().getFirstName() + ", let's proceed with booking-making! " +
                        "\nPlease provide the following information to book the meeting room." +
                        "\n\n\t\ud83d\udc49 Please enter your User ID. \nFormat:     \ud83d\udccb ID - 213578 ");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "ID":
                User_ID = com[1];
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());

                sendMessage.setText("Your staff ID is " + User_ID + ". " +
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb User_ID: " + User_ID +
                        "\n\nSo, we continue with the information." +
                        "\n\t\ud83d\udc49 Please enter your IC number. \nFormat:     \ud83d\udccb IC - 000101-01-1234 ");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "IC":
                ICNO = com[1];
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText("Your IC Number is " + ICNO + ". " +
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb User_ID: " + User_ID +
                        "\n  \ud83d\udccb IC Number: " + ICNO +
                        "\n\nSo, we continue with the information." +
                        "\n\t\ud83d\udc49 Please enter your name.  \nFormat:     \ud83d\udccb Name - Jackson_Tan_XXXX_XXXX ");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "Name":
                User_Name = com[1];
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText("Your name is " + User_Name + ". " +
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb User_ID: " + User_ID +
                        "\n  \ud83d\udccb IC Number: " + ICNO +
                        "\n  \ud83d\udccb Name: " + User_Name +
                        "\n\nSo, we continue with the information." +
                        "\n\t\ud83d\udc49  Please enter your phone number. (Format:\n \ud83d\udccb Phone - 601178944444 ");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;


            case "Phone":
                Mobile_TelNo = com[1];
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText("Your phone number is " + Mobile_TelNo + ". " +
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb User_ID: " + User_ID +
                        "\n  \ud83d\udccb IC Number: " + ICNO +
                        "\n  \ud83d\udccb Name: " + User_Name +
                        "\n  \ud83d\udccb Phone Number: " + Mobile_TelNo +
                        "\n\nSo, we continue with the information." +
                        "\n\t\ud83d\udc49 Please enter your email.  \nFormat:     \ud83d\udccb   Email - Jason@gmail.com ");
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "Email":
                Email = com[1];
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                sendMessage.setText("Your email is " + Email + ". " +
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb User_ID: " + User_ID +
                        "\n  \ud83d\udccb IC Number: " + ICNO +
                        "\n  \ud83d\udccb Name: " + User_Name +
                        "\n  \ud83d\udccb Phone Number: " + Mobile_TelNo +
                        "\n  \ud83d\udccb Email: " + Email +
                        "Your pin is " + pin + "  "+"\n\t\ud83d\udc49 /pin +"+
                        "\n\n" + "These are the room that is ready to booking: ");


                app.insertUser(User_ID, ICNO, User_Name, Mobile_TelNo, Email,pin);
                pin = app.checkUserPin(User_ID);


                sendMessage.setText("Your pin is " + pin + "  "+"\n\t\ud83d\udc49 /pin ");

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "/pin":
                app.checkPin(pin);
                sendMessage.setText(" Please provide the PIN for booking.? \nFormat:\nPin - 123456 ");
                sendMessage.setChatId(chatId);
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;


            case "Pin":
                pin= com[1];
                sendMessage.setText("Your booking room is " + pin+ ". "+
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb User_ID: " + User_ID +
                        "\n  \ud83d\udccb IC Number: " + ICNO +
                        "\n  \ud83d\udccb Name: " + User_Name +
                        "\n  \ud83d\udccb Phone Number: " + Mobile_TelNo +
                        "\n  \ud83d\udccb Email: " + Email +
                        "\n\n  Booking Details" +
                        "\n  =============" +
                        "\n  \ud83c\udfdb\ufe0f pin: " + pin +
                        "\n\n" + "These are the room that is ready to booking: "+
                        "\n\nSo, we continue with the information."+
                        "\n\n" + "These are the room that is ready to booking: "+
                     "\n" + app.displayAvailableRooms()+
                        "\n\t\ud83d\udc49 Which room you want to booking?  \nFormat:     \ud83d\udccb   Room - R101) ");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;



            case "Room":
                Room_ID= com[1];
                sendMessage.setText("Your booking room is " + Room_ID + ". "+
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb User_ID: " + User_ID +
                        "\n  \ud83d\udccb IC Number: " + ICNO +
                        "\n  \ud83d\udccb Name: " + User_Name +
                        "\n  \ud83d\udccb Phone Number: " + Mobile_TelNo +
                        "\n  \ud83d\udccb Email: " + Email +
                        "\n\n  Booking Details" +
                        "\n  =============" +
                        "\n  \ud83c\udfdb\ufe0f Room ID: " + Room_ID +
                        "\n\n" + "These are the room that is ready to booking: "+
                        "\n\nSo, we continue with the information."+
                        "\n\t\ud83d\udc49 Please enter the date you want to booking.  \nFormat:     \ud83d\udccb   Purpose - Tutorial) ");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            case "Purpose":
                Purpose_of_booking = com[1];
                sendMessage.setText("Your  booking purpose  is " + Purpose_of_booking + ". "+
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb User_ID: " + User_ID +
                        "\n  \ud83d\udccb IC Number: " + ICNO +
                        "\n  \ud83d\udccb Name: " + User_Name +
                        "\n  \ud83d\udccb Phone Number: " + Mobile_TelNo +
                        "\n  \ud83d\udccb Email: " + Email +
                        "\n\n  Booking Details" +
                        "\n  =============" +
                        "\n  \ud83c\udfdb\ufe0f Room ID: " + Room_ID +
                        "\n  \ud83c\udfdb\ufe0f Purpose: " + Purpose_of_booking +
                        "\n\nSo, we continue with the information."+
                        "\n\t\ud83d\udc49 Please enter the time you want to booking. \nFormat:     \ud83d\udccb   Date - 05-03-2023  ");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "Date":
                Booking_Date = com[1];
                sendMessage.setText("Your booking date is " + Booking_Date + ". "+
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb User_ID: " + User_ID +
                        "\n  \ud83d\udccb IC Number: " + ICNO +
                        "\n  \ud83d\udccb Name: " + User_Name +
                        "\n  \ud83d\udccb Phone Number: " + Mobile_TelNo +
                        "\n  \ud83d\udccb Email: " + Email +
                        "\n\n  Booking Details" +
                        "\n  =============" +
                        "\n  \ud83c\udfdb\ufe0f Room ID: " + Room_ID +
                        "\n  \ud83c\udfdb\ufe0f Purpose: " + Purpose_of_booking +
                        "\n  \ud83c\udfdb\ufe0f Booking Date: " + Booking_Date +
                        "\n\nSo, we continue with the information."+
                        "\n\t\ud83d\udc49 Please enter the time you want to booking. \nFormat:     \ud83d\udccb   Time - 10:00AM-01:30PM ");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "Time":
                Booking_Time = com[1];
                sendMessage.setText("Your booking time is " + Booking_Time + ". "+
                        "\n\n  Personal Information" +
                        "\n  =================" +
                        "\n  \ud83d\udccb User_ID: " + User_ID +
                        "\n  \ud83d\udccb IC Number: " + ICNO +
                        "\n  \ud83d\udccb Name: " + User_Name +
                        "\n  \ud83d\udccb Phone Number: " + Mobile_TelNo +
                        "\n  \ud83d\udccb Email: " + Email +
                        "\n\n  Booking Details" +
                        "\n  =============" +
                        "\n  \ud83c\udfdb\ufe0f Room ID: " + Room_ID +
                        "\n  \ud83c\udfdb\ufe0f Purpose: " + Purpose_of_booking +
                        "\n  \ud83c\udfdb\ufe0f Booking Date: " + Booking_Date +
                        "\n  \ud83c\udfdb\ufe0f Booking Time: " + Booking_Time +
                        "\n\n\ud83d\udc4f Congratulations!! You have successfully booking the meeting room!");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());

                app.insertBooking(Booking_ID, Room_ID, User_ID, Purpose_of_booking, Booking_Time, Booking_Date,pin);
                app.updateStatus(Room_ID);
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "/checkingUser":
                if (command.equals("/checkingUser")) {
                    sendMessage.setText("If you want to check the information of user. Please enter the User_ID to check about the information. " +
                            "\n \ud83d\udc49 Example format: /checkingUser - User_ID"+"\n \ud83d\udc49 Reply 3: Back to User page");

                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else {
                    User_ID = com[1];
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());

                    String checkingUser = app.checkingUser(User_ID);
                    sendMessage.setText(checkingUser);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    sendMessage.setText("/updateInfo");
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }

            case "/updateInfo":
                if (command.equals("/updateInfo")) {
                    sendMessage.setText("If you want to update the information of user. Please click /checkingUser to check User_ID " +
                            "\n  There are four options for the you choose to update" +
                            "\n \ud83d\udc49 /updateICNO " +
                            "\n \ud83d\udc49 /updateUserName" +
                            "\n \ud83d\udc49/updateMobile_TelNo "+
                            "\n \ud83d\udc49/updateEmail"+
                            "\n \ud83d\udc49 Reply 3: Back to User page");

                    //Reply 0: Back to main page
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();

                    }
                }

            case "/updateICNO":
                if (command.equals("/updateICNO")) {
                    sendMessage.setText("If you want to check the information of booking. Please enter the staff ID to check about the information. " +
                            "\n \ud83d\udc49 Example format: updateICNO - 010101-01-12345");
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }else {
                    ICNO = com[1];
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());


                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case "updateICNO":
                if (command.equals("updateICNO")) {

                    sendMessage.setText("Please provide your ICNO want to update\n" + "(Format: updateICNO - 010101-01-12345  ");
                    sendMessage.setChatId(chatId);
                    System.out.println("");
                    sendMessage.setChatId(update.getMessage().getChatId().toString());


                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else {
                    ICNO = com[1];

                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());

                    sendMessage.setText("Your ICNO has been successfully updated to " + ICNO + ". ");

                    app.updateUser(ICNO,User_ID);

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            case "/updateUserName":
                if (command.equals("/updateUserName")) {
                    sendMessage.setText("If you want to check the information of booking. Please enter the staff ID to check about the information. " +
                            "\n \ud83d\udc49 Example format: updateUserName - Jackson_Tan_XXXX_XXXX");
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }else {
                    ICNO = com[1];
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());


                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                break;
            //true
            case "updateUserName":
                if (command.equals("updateUserName")) {

                    sendMessage.setText("Please provide your ICNO want to update\n" + "Format: updateUserName - Jackson_Tan_XXXX_XXXX  ");
                    sendMessage.setChatId(chatId);

                    sendMessage.setChatId(update.getMessage().getChatId().toString());


                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else {
                    User_Name = com[1];

                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());

                    sendMessage.setText("Your User_Name has been successfully updated to" + User_Name + ". ");
                    app.UpdateProfileUser_Name(User_Name,User_ID);


                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            case "/updateMobile_TelNo":
                if (command.equals("/updateMobile_TelNo")) {
                    sendMessage.setText("If you want to check the information of User. Please enter the User_ID to check about the information. " +
                            "\n \ud83d\udc49 Example format: updateMobile_TelNo - 601178944444");
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }else {
                    Email = com[1];
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());


                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                break;
            //true
            case "updateMobile_TelNo":
                if (command.equals("updateMobile_TelNo")) {

                    sendMessage.setText("Please provide your updateEmail want to update\n" + "Format: updateMobile_TelNo - 601178944444\"  ");
                    sendMessage.setChatId(chatId);
                    System.out.println("");
                    sendMessage.setChatId(update.getMessage().getChatId().toString());


                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else {
                    Mobile_TelNo= com[1];

                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());

                    sendMessage.setText("Your Phone Number has been successfully updated to" +  Mobile_TelNo + ". ");
                    app.UpdateProfileMobile_TelNo(Mobile_TelNo,User_ID);



                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            case "/updateEmail":
                if (command.equals("/updateEmail")) {
                    sendMessage.setText("If you want to check the information of booking. Please enter the User_ID  to check about the information. " +
                            "\n \ud83d\udc49 Example format: updateEmail - Jason@gmail.com ");
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }else {
                    Email = com[1];
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());


                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                break;
            //true
            case "updateEmail":
                if (command.equals("updateEmail")) {

                    sendMessage.setText("Please provide your updateEmail want to update\n" + "Format: updateEmail - Jason@gmail.com  ");
                    sendMessage.setChatId(chatId);
                    System.out.println("");
                    sendMessage.setChatId(update.getMessage().getChatId().toString());


                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else {
                    Email= com[1];

                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());

                    sendMessage.setText("Your  Email has been successfully updated to" +  Email + ". ");

                    app.UpdateProfileEmail(Email,User_ID);


                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            case "/checkingPin":
                if (command.equals("/checkingPin")) {
                    sendMessage.setText("If you want to check the information of booking. Please enter the Pin to check about the information. " +
                            "\n \ud83d\udc49 Example format: /checkingPin- Pin"+"\nIf you want to check the pin Please click /checkingUser to check User_ID ");

                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else {
                    pin = com[1];
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());

                    String checkingUser = app.checkingUser(pin);
                    sendMessage.setText(checkingUser);
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                    sendMessage.setText("/updateBooking");

                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }

            case "/updateBooking":
                if (command.equals("/updateBooking")) {
                    sendMessage.setText("If you want to update the information of user. Please click \n\ud83d\udc49 /checkingPin to check pin" +
                            "\n There are four options for the you choose to update\n" +
                            "\n \ud83d\udc49/updateBookingPurpose_of_booking" +
                            "\n \ud83d\udc49 /updateBookingBooking_Date" +
                            "\n \ud83d\udc49 /updateBookingBooking_Time" +
                            "\n\ud83d\udc49 Reply 3: Back to User page");

                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();

                    }
                }
                break;

            case "/updateBookingPurpose_of_booking":
                if (command.equals("/updateBookingPurpose_of_booking")) {
                    sendMessage.setText("If you want to update the purpose of a booking, please enter the PIN and new purpose in the following format : " +
                            "\n \ud83d\udc49 Example Format: updateBookingPurpose_of_booking - Group Discussion");
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }else {
                    Purpose_of_booking = com[1];
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());


                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                break;
            //true
            case "updateBookingPurpose_of_booking":
                if (command.equals("updateBookingPurpose_of_booking")) {

                    sendMessage.setText("Please provide your Purpose_of_booking want to update\n" + "(Format: updateBookingPurpose_of_booking - Group Discussion)  ");
                    sendMessage.setChatId(chatId);

                    sendMessage.setChatId(update.getMessage().getChatId().toString());


                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else {
                    Purpose_of_booking = com[1];

                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());

                    sendMessage.setText("Your Purpose_of_booking is " + Purpose_of_booking + ". ");

                    app.updateBookingPurpose_of_booking(pin,Purpose_of_booking);

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
            case "/updateBookingBooking_Date":
                if (command.equals("/updateBookingBooking_Date")) {
                    sendMessage.setText("If you want to update the Booking_Date, please enter the PIN and new Booking_Date in the following format : " +
                            "\n \ud83d\udc49 Example Format: updateBookingBooking_Date - 05-03-2023");
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }else {
                    Booking_Date= com[1];
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());


                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                break;
            //true
            case "updateBookingBooking_Date":
                if (command.equals("updateBookingBooking_Date")) {

                    sendMessage.setText("Please provide your Booking_Date want to update\n" + "(Format: updateBookingBooking_Date - 05-03-2023)  ");
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else {
                    Booking_Date= com[1];

                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    sendMessage.setText("Your Booking_Date is " + Booking_Date + ". ");

                    app.updateBookingBooking_Date(pin,Booking_Date);

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                } case "/updateBookingBooking_Time":
                if (command.equals("/updateBookingBooking_Time")) {
                    sendMessage.setText("If you want to update the Booking_Time, please enter the PIN and new Booking_Time in the following format : " +
                            "\n \ud83d\udc49 Example format: updateBookingBooking_Time - 10:00AM-01:30PM");
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }else {
                    Booking_Date= com[1];
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());


                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                break;
            //true
            case "updateBookingBooking_Time":
                if (command.equals("updateBookingBooking_Time")) {

                    sendMessage.setText("Please provide your Booking_Time want to update\n" + "(Format: updateBookingBooking_Time - 10.30-1.00pm)  ");
                    sendMessage.setChatId(chatId);
                    System.out.println("");
                    sendMessage.setChatId(update.getMessage().getChatId().toString());


                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                } else {
                    Booking_Time= com[1];

                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());

                    sendMessage.setText("Your Booking_Time " + Booking_Time + ". ");

                    app.updateBookingBooking_Time(pin,Booking_Time);

                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }
                break;
            case "/cancelBooking":

                if(command.equals("/cancelBooking")){
                    sendMessage.setText("If you want to cancel booking, please enter the User_ID  to cancel it. "+
                            "\n \ud83d\udc49 Example format: /cancelBooking - User_ID"+
                    "\n \ud83d\udc49 Reply 3: Back to User page");

                    ////Reply 3: Back to User page
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }else{
                    User_ID = com[1];
                    sendMessage.setChatId(chatId);
                    sendMessage.setChatId(update.getMessage().getChatId().toString());
                    app.updateStatusDelete(User_ID);
                    app.deleteUsers(User_ID);
                    app.deleteBooking(User_ID);

                    sendMessage.setText("Booking of " + User_ID + " already been cancel."+ "\n\n");
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }
                break;

            case "/displayRoom":
                sendMessage.setText(app.displayAllRooms());
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;



            case "/displayAvailableRooms":
                sendMessage.setText(app.displayAvailableRooms());

                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;
            case "/displayUserList":
                sendMessage.setText(app.displayUserList(Room_ID, Room_Description, Maximum_Capacity , Booking_Date , Booking_Time));
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

            default:
                sendMessage.setText("Please enter command available with the correct format. \u2764\ufe0f");
                sendMessage.setChatId(chatId);
                sendMessage.setChatId(update.getMessage().getChatId().toString());
                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
                break;

        }



    }}

