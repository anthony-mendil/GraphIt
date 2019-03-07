package gui;

import com.sun.javafx.application.LauncherImpl; //NOSONAR
import log_management.dao.PersonalEntityManagerFactory;

/**
 * Starts the whole application.
 */
public class Main {


    /**
     * Starts the application.
     *
     * @param args The java command line arguments that is needed to start the application.
     */
    public static void main(String[] args) {
        LauncherImpl.launchApplication(MainApplication.class, AppPreloader.class, args);
        PersonalEntityManagerFactory.getInstance().close();
    }
}
