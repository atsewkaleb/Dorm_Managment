package com.mycompany.dorm;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class insertImage {

    public static void main(String[] args) {
        // Database connection parameters
        String driver="oracle.jdbc.driver.OracleDriver";
        String jdbcUrl="jdbc:oracle:thin:@//AtsewKaleb:1521/xe";
        String username="dorm";
        String password="dorm";

        // Image file path
        String imagePath = "C:\\students image\\kaleb.png";

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            byte[] imageData = readImage(imagePath);
            insertImage(connection, imageData);
            System.out.println("Image inserted successfully.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static byte[] readImage(String imagePath) throws IOException {
        File imageFile = new File(imagePath);
        byte[] imageData = new byte[(int) imageFile.length()];
        try (FileInputStream fis = new FileInputStream(imageFile)) {
            fis.read(imageData);
        }

        return imageData;
    }

    private static void insertImage(Connection connection, byte[] imageData) throws SQLException {
        String insertQuery = "INSERT INTO IMAGE_TABLE (IMAGE_ID,IMAGE_DATA) VALUES ('s1',?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setBytes(1, imageData);
            preparedStatement.executeUpdate();
        }
    }

}
