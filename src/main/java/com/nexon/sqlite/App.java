package com.nexon.sqlite;

/**
 * Created by chan8 on 2017-01-23.
 */
public class App {
    
    public static void main(String[] args) {
        App main = new App();
        main.initialize();
    }

    private void initialize() {
        DBConnector dbc = new DBConnector("C:\\sqlite\\test.db");
        dbc.initialize();
        
        dbc.connectDB();
        dbc.dropTable();
        
//        dbc.connectDB();
//        dbc.deleteAll();
        
        dbc.connectDB();
        dbc.createTable();
        
        dbc.connectDB();
        dbc.insertUsingPreparedStatement("SEUNG HWAN", 27, "shharn2");
        
        dbc.connectDB();
        dbc.insert();
        
        dbc.connectDB();
        dbc.insertUsingPreparedStatement();
        
        dbc.connectDB();
        dbc.selectAll();
        dbc.closeDB();
    }

}
