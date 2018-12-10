package log_management;

import actions.LogAction;
import lombok.Data;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.SQLException;

@Data
public class LogDatabaseManager {

    private static int graphId;
    private static int logCounter;

    public static void main(String[] args) throws SQLException {
        throw new NotImplementedException();
    }

    public static void addLogEntryToDatabase(LogAction logAction) {
        throw new NotImplementedException();
    }

    public static void addGraphEntryToDatabase() {
        throw new NotImplementedException();
    }

    public static int personalLogIncrementor() {
        throw new NotImplementedException();
    }

    public static void setup() {
        throw new NotImplementedException();
    }

    public static void printLogs() {
        throw new NotImplementedException();
    }
}