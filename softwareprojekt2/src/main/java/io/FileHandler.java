package io;

import org.apache.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;

class FileHandler {

    /**
     * The Logger of the FileHandler. It's used to log Exceptions caused by errors at reading and writing files.
     */
    private static Logger logger = Logger.getLogger(FileHandler.class);

    /**
     * The Filehandler constructor which should not be used.
     */
    private FileHandler() {
        throw new IllegalStateException("utility class");
    }

    /**
     * Writes the content of a given String to the given File.
     *
     * @param pString The String which contains the data that gets written.
     * @param pFile   The File that gets written on.
     */
    static void stringToFile(String pString, File pFile) {
        try (OutputStreamWriter writer =
                     new OutputStreamWriter(new FileOutputStream(pFile), StandardCharsets.UTF_8)) {
            writer.write(pString);
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    /**
     * Reads the given File and returns its content as String.
     *
     * @param pFile The given File which contains the data that gets returned.
     * @return The content-String.
     */
    static String fileToString(File pFile) {
        StringBuilder oof = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(pFile), StandardCharsets.UTF_8))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                oof.append(line);
            }
        } catch (IOException e) {
            logger.error(e.toString());
        }
        return oof.toString();
    }
}
