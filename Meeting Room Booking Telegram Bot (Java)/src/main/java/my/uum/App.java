package my.uum;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.time.ZonedDateTime;

/**
 * This class is the main class of the system.
 *
 * @author LIM JIA JUN 281231
 * @author CHONG QI JUN 279590
 * @author TEOH ERN SHENG 278985
 * @author LEONG QING YUN 277585
 * @author TAN JIA KEE 279437
 */
/**
 * Main method that creates an instance of the DatabaseManager class and starts a daemon thread
 * that runs every day at 12:01 AM. The thread calls the deleteOldBookings method of the
 * DatabaseManager object and then sleeps for 1 minute before checking the time again.
 * Also creates an instance of the TelegramBotsApi class and registers a bot called STIW3054_bugbug_bot.
 *
 * @param args command line arguments
 */
public class App {
    
    public static void main(String[] args) {
        // Create a new instance of the DatabaseManager class
        DatabaseManager db = new DatabaseManager();

        // Create a daemon thread that runs every day at 12:01 AM
        Thread daemonThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    // Get current date and time
                    ZonedDateTime now = ZonedDateTime.now();
                    // Check if it is currently 12:01 AM
                    if (now.getHour() == 0 && now.getMinute() == 1) {
                        // Call deleteOldBookings method
                        db.deleteOldBookings();
                    }
                    try {
                        Thread.sleep(60000); // Sleep for 1 minute
                    } catch (InterruptedException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        });
        daemonThread.setDaemon(true);
        daemonThread.start();

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new STIW3054_bugbug_bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
/*
    public static void main(String[] args) {
        try {
          new DatabaseManager();
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new STIW3054_bugbug_bot());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
*/
