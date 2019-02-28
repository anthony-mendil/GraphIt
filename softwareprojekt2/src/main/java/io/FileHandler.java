package io;

import org.apache.log4j.Logger;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileHandler {
    private static Logger logger = Logger.getLogger(FileHandler.class);

    static void StringToFile(String pString, File pFile){
        try (OutputStreamWriter writer =
                     new OutputStreamWriter(new FileOutputStream(pFile), StandardCharsets.UTF_8)) {
            writer.write(pString);
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }
    public static String FileToString(File pFile){
        StringBuilder oof = new StringBuilder();
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(
                new FileInputStream(pFile), StandardCharsets.UTF_8))){
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
