package com.oreonk;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQL {
    private String host = Prison.getPlugin(Prison.class).getConfig().getString("DB.host");
    private String port = Prison.getPlugin(Prison.class).getConfig().getString("DB.port");
    private String database = Prison.getPlugin(Prison.class).getConfig().getString("DB.database");
    private String ssl = Prison.getPlugin(Prison.class).getConfig().getString("DB.ssl");
    private String username = Prison.getPlugin(Prison.class).getConfig().getString("DB.username");
    private String password = Prison.getPlugin(Prison.class).getConfig().getString("DB.password");

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
