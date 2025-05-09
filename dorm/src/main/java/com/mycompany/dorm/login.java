package com.mycompany.dorm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class login {
    JFrame f = new JFrame("---login---login---login---login---login---login---login----");
    ConnectionClass con = new ConnectionClass();
    JLabel lproctorId,lproctorpass,lloginform,illoginform;
    JTextField tfproctorid;
    JCheckBox cbshowpassword;
    JButton blogin,bcancel;
    JPasswordField pfpassword;
    PreparedStatement pstmt;
    ResultSet rs;

    login()
    {
        illoginform = new JLabel();
        lloginform = new JLabel("Login Form");
        cbshowpassword = new JCheckBox("Show Password");
        blogin = new JButton("Login");
        bcancel = new JButton("Cancel");
        lproctorId = new JLabel("User ID :");
        lproctorpass = new JLabel("Password :");
        tfproctorid = new JTextField();
        pfpassword = new JPasswordField();

        Font fontloginform = new Font(lloginform.getFont().getFontName(), Font.ITALIC, 35);
        Font font = new Font(lloginform.getFont().getFontName(), Font.ITALIC, 20);
        Font fon = new Font(lloginform.getFont().getFontName(), Font.ITALIC, 17);
        lloginform.setForeground(Color.BLACK);
        blogin.setBackground(Color.GREEN);
        bcancel.setBackground(Color.RED);
        lproctorId.setForeground(Color.BLACK);
        lproctorpass.setForeground(Color.BLACK);
        lloginform.setFont(fontloginform);
        lproctorId.setFont(font);
        lproctorpass.setFont(font);
        blogin.setFont(fon);
        bcancel.setFont(fon);

        lloginform.setBounds(160,70,230,50);
        lproctorId.setBounds(120,145,120,30);
        lproctorpass.setBounds(120,180,120,30);
        tfproctorid.setBounds(230,145,120,30);
        pfpassword.setBounds(230,180,120,30);
        cbshowpassword.setBounds(190,220,120,20);
        blogin.setBounds(150,250,100,40);
        bcancel.setBounds(260,250,100,40);
        illoginform.setBounds(0,0,500,400);

////////////////////////////////////////////////////////////////////////////////////////////////////////////
        try {
            ImageIcon img = new ImageIcon(getClass().getResource("login_background.png"));
            illoginform.setIcon(img);
        } catch (Exception e) {
            System.out.println("Image cannot be find");}

/////////////////////////////////////////////////////////////////////////////////////////////////////
        blogin.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e)
        {
            try{
                String un= tfproctorid.getText();
                String pa= pfpassword.getText();
                //String sql= "SELECT * FROM login WHERE proctor_id='"+un+"'and  proctor_password='"+pa+"'";//XAMPP
                String sql= "SELECT * FROM login WHERE id='"+un+"'and  pass='"+pa+"'";//ORACLE
                pstmt= con.openConnection().prepareStatement(sql);
                rs=pstmt.executeQuery();
                if (rs.next())
                {
                    f.dispose();//release resource in the dorm class
                    new checkin();
                }
                else
                {
                    tfproctorid.setText("");
                    pfpassword.setText("");
                    JOptionPane.showMessageDialog(null,"Invalid username or password", "Login Error", JOptionPane.ERROR_MESSAGE);
                }

            }catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null,"Here we go again");
            }
        }
    });
/////////////////////////////////////////////////////////////////////////////////////////////////////////
        bcancel.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            f.dispose();
        }
    });
////////////////////////////////////////////////////////////////////////////////////////////////////
        cbshowpassword.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (cbshowpassword.isSelected())
            {
                pfpassword.setEchoChar((char)0);
            }
            else
            {
                pfpassword.setEchoChar('*');
            }
        }
    });
        f.add(lloginform);  f.add(lproctorId);
        f.add(lproctorpass); f.add(tfproctorid);
        f.add(pfpassword); f.add(cbshowpassword);
        f.add(blogin); f.add(bcancel);
        f.setLayout(null);

        f.add(illoginform);
        f.setSize(500,400);
        f.setLayout(null);
        f.setVisible(true);

        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
    public static void main(String[] args) {
        new login();
    }
}