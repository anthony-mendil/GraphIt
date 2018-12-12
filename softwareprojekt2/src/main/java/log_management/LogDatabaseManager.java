package log_management;

import actions.LogAction;
import lombok.Data;

import java.sql.SQLException;

@Data
public class LogDatabaseManager {

    private static int graphId;
    private static int logCounter;

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
}