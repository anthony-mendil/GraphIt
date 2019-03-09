package gui;

import com.sun.javafx.application.LauncherImpl;
import log_management.dao.PersonalEntityManagerFactory;

/**
 * Starts the whole application.
 */
public class Main {


    /**
     * Starts the application. At first, it starts the preloader. Afterwards the main application
     * gets started and the preloader closes. The Database gets closed when the application stops.
     *
     * @param args The java command line arguments that is needed to start the application.
     */
    public static void main(String[] args) {
        LauncherImpl.launchApplication(MainApplication.class, AppPreloader.class, args);
        PersonalEntityManagerFactory.getInstance().close();
    }
}
