package io;

public class JSONio {

    public JSONio(){

    }

    /**
     * Writes the JSON representation into the Database
     *
     * @param pJSON The protocol as JSON String that gets written into the Database
     */
    protected void JSONToDB(String pJSON) {
        throw new UnsupportedOperationException();
    }

    /**
     * Creates a JSON String of the current existing protocol from the Database
     *
     * @return The current protocol as JSON String
     */
    protected String createJSONFromDB(){
        throw new UnsupportedOperationException();
        //with DAO
    }
}
