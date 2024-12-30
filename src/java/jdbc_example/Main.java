package jdbc_example;

import java.sql.*; // 1. import packages

public class Main {

    public static void main(String[] args) throws Exception {

        try {
            Connection connpostgres = PostgresConnection.getConexao();

            Statement stmt = connpostgres.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM cars");

            while (rs.next()) {
                String userData = "Marca:" + rs.getString(1) + " Modelo:" + rs.getString(2) + " Ano: " + rs.getInt(3);
                
                System.out.println(userData);
            }

            stmt.close();
            rs.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            PostgresConnection.close();
        }
    }
}
