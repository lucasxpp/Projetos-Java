package repository;

import exceptions.DbException;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class CN {

    private static Connection conn = null;


   public static Connection getConnection(){ //metodo para se conectar o banco de dados...
       if(conn == null){
           try {
               Properties prop = loadProperties();
               String url = prop.getProperty("url");

               conn = DriverManager.getConnection(url, prop);

           } catch (SQLException e) {
               throw new DbException(e.getMessage());
           }
       }
       return conn;
   }

    public static Properties loadProperties() {

        try(FileInputStream fs = new FileInputStream("db.properties")) { //le os dados do banco
            Properties prop = new Properties();
            prop.load(fs);  //guarda as informações para login no banco de dados...

            return prop;

        }catch(Exception e) {
            throw new DbException(e.getMessage());

        }
    }

    //metodos de fechamento para cada uma das ferramentas...

    public static void closeConnection(){
       if(conn != null){
           try {
               conn.close();

           } catch (SQLException e) {
               throw new DbException(e.getMessage());
           }
       }
    }

    public static void closeStatement(Statement st){
       if(st != null){
           try {
               st.close();
           } catch (SQLException e) {
               throw new DbException(e.getMessage());
               }
       }

    }

    public static void closeResultSet(ResultSet rs){
       if(rs != null){
           try {
               rs.close();
           } catch (SQLException e) {
               throw new DbException(e.getMessage());
           }
       }

    }






















}
