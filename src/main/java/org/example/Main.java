package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Main {

    public static void main(String[] args) {
        String url = "jdbc:postgresql://localhost:5432/projectJ";
        String user = "postgres";
        String password = "33633599";

        try {
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Conectado com sucesso!");
            Statement stm = conn.createStatement();
            consultaDados(stm);

            conn.close();
        } catch (SQLException e) {
            System.out.println("Erro na conexão:");
            e.printStackTrace();

        }
    }

static void consultaDados(Statement stm){
        String sql = "select id, nome, email from usuario";
        try {
            ResultSet result = stm.executeQuery(sql);
            while(result.next()){
                System.out.printf("id: %s \n nome : %s \n email: %s \n", result.getInt("id"), result.getString("nome"), result.getString("email"));
            }

        } catch (SQLException e){
            e.printStackTrace();
        }


}






}