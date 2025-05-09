package com.mycompany.dorm;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class template{

    public static void main(String[] args) {
        // Database connection parameters
        String driver="oracle.jdbc.driver.OracleDriver";
        String jdbcUrl="jdbc:oracle:thin:@//AtsewKaleb:1521/xe";
        String username="dorm";
        String password="dorm";

        try {
            // Load the Oracle JDBC driver
            Class.forName(driver);

            // Create a connection to the database
            Connection connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Prepare the SQL statement
            String sql = "SELECT IMAGE_DATA FROM IMAGE_TABLE WHERE IMAGE_ID = 's1'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            // Set the parameter (replace 1 with the actual ID)
            //preparedStatement.setInt(1, 1);

            // Execute the query
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                // Retrieve image data from the result set
                byte[] imageData = resultSet.getBytes("IMAGE_DATA");

                // Display the image in a JFrame
                displayImageInFrame(imageData);
            }

            // Close the result set, statement, and connection
            resultSet.close();
            preparedStatement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    private static void displayImageInFrame(byte[] imageData) {
        JFrame frame = new JFrame("Image Display");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        try {
            // Convert byte array to Image
            ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
            Image image = ImageIO.read(bis);

            // Display Image in JLabel
            JLabel label = new JLabel(new ImageIcon(image));
            frame.getContentPane().add(label, BorderLayout.CENTER);

            frame.pack();
            frame.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
