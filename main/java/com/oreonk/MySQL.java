package com.oreonk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    private String host = "localhost";
    private String port = "3306";
    private String database = "donate";
    private String ssl = "false";
    private String username = "root";
    private String password = "k8mhEfxjZvkmj3nmJM";

    private Connection connection;

    public  boolean isConnected(){
        return connection != null;
    }

    public void connect() throws ClassNotFoundException, SQLException{
        if (!isConnected()) {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?allowPublicKeyRetrieval=true&" + "useSSL=" + ssl + "&autoReconnect=true", username, password);
        }
    }

    public void  disconnect(){
        if (isConnected()){
            try{
                connection.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
