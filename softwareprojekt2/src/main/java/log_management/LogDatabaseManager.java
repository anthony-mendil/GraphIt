package log_management;

import actions.LogAction;
import actions.ObserverSyndrom;
import lombok.Data;

import java.sql.SQLException;

@Data
public class LogDatabaseManager implements ObserverSyndrom{

    private static int graphId;
    private static int logCounter;
    /**
     * is true if the user is in edit mode and the protocol gets recorded and stored in the database
     */
    private boolean shouldRecord;

    public static void main(String[] args) throws SQLException {
        throw new UnsupportedOperationException();
    }

    public static void addLogEntryToDatabase(LogAction logAction) {
        throw new UnsupportedOperationException();
    }

    public static void addGraphEntryToDatabase() {
        throw new UnsupportedOperationException();
    }

    public static int personalLogIncrementor() {
        throw new UnsupportedOperationException();
    }

    public static void setup() {
        throw new UnsupportedOperationException();
    }

    public static void printLogs() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void update() {
        // save gxl 
    }
}