package com.mycompany.dorm;

import oracle.jdbc.proxy.annotation.Pre;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Date;

public class checkin {

    JFrame fr = new JFrame("Home");
    ResultSetMetaData rsmd,rsmd1;
    DefaultTableModel dtm,dtm1;
    JLabel il1, l1, l2, l3,lstudentinfo,
            lfirstName,lfatherName,lbirthPlace,lbirthDate,lphone, lemergencyContact,
            lemergencyNumber,lemergencyAdres,lgender,lstudentType,lstudentType2,lyear ,
            lstream,ldepartment,ldormnumber,lemrgencyrelation, labelimage,labelimage1;
    JTextField tfstudentid, tfstudentname,tffirstName,tffatherName,tfbirthPlace
            ,tfbirthDate1,tfbirthDate2,tfbirthDate3,tfphone, tfEmergencyContact,tfEmergencyNumber,
            tfEmergencyAdres,tfyear,tfdepartment,tfdormnumber;
    JComboBox chkstudentType,chkstudentType2,chkstream,chkemrgencyrelation;
    JRadioButton rbmale,rbfemale;
    JPanel  pchoice,pcheckstudent,pregistor,pwithdraw,pupdate,penrolledstud,pwithdrewstud,pabsentstud;
    JButton bhome,blogout,bexit,bregistor1,bregistor2, bwithdraw1,
            bwithdraw2, bupdate1,bupdate2, benrolledstudent, bwithdrawstudent, babsentstudent, battendance, bcheckstudent,
            bcheckstudent1,bcheckstudent2,  bstudentnotfound,battendance2,
            bwelcome,bcheckavailability,bsubmit,bsubmit1,bsubmit2,bclose;

    JTable tavailable,tavailable1,tavailable2;
    JScrollPane spavailable,spavailable1,spavailable2;
    ConnectionClass con = new ConnectionClass();
    PreparedStatement pstmt,pstmt1, pstmt2,pstmt3;
    ResultSet rs,rs1,rs2,rs3;
    ImageIcon icon;

    private static int isStringInComboBox(String targetString, JComboBox<String> comboBox) {
        for (int i = 0; i < comboBox.getItemCount(); i++) {
            String currentItem = comboBox.getItemAt(i);
            if (currentItem.equals(targetString)) {
                return i;
            }
        }
        return 0;
    }
    checkin() {

        labelimage = new JLabel();                                     labelimage1 = new JLabel();
        ButtonGroup bgg=new ButtonGroup();                             pchoice = new JPanel();
        pcheckstudent = new JPanel();                                   pregistor = new JPanel();
        pwithdraw = new JPanel();                                       pupdate = new JPanel();
        penrolledstud = new JPanel();                                   pwithdrewstud = new JPanel();
        pabsentstud = new JPanel();                                     tfstudentid = new JTextField();
        tfstudentname = new JTextField();                               bhome = new JButton("HOME");
        blogout = new JButton("LOGOUT");                           bexit = new JButton("Exit");
        bregistor1 = new JButton("Register");                      bwithdraw1 = new JButton("Withdraw");
        bupdate2 = new JButton("Update");                          bupdate1 = new JButton("Update");
        benrolledstudent = new JButton("Enrolled Student");        bwithdrawstudent = new JButton("Withdrew Student");
        babsentstudent = new JButton("Absent Student");            battendance = new JButton("Attendence");
        il1 = new JLabel();                                             l1 = new JLabel("Check Student");
        l2 = new JLabel("Student Id: ");                            l3 = new JLabel("Student Name: ");
        bcheckstudent = new JButton("Check Student");               bregistor2 = new JButton("Registor");
        battendance2=new JButton("Take attendance");
        bwithdraw2 = new JButton ("Withdraw");                      bstudentnotfound = new JButton("Student not Found");
        bwelcome = new JButton();                                          bcheckstudent1 = new JButton("Check Student:");
        bcheckstudent2= new JButton("Check Student::");

        String[] stream ={"Natural Science","Social Science"};
        String[] studentYtpe = {"Regular ","Summer "};
        String[] relation={"Father","Mother","Brother","Sister","Uncle","Aunt","Sibiling","Other"};
        String[] studentYtpe2 = {"Placement","Transfer","Scholarship"};
        lstudentinfo = new JLabel("Student Information");
        lfirstName = new JLabel("First Name: ");                      tffirstName = new JTextField();
        lfatherName = new JLabel("Last Name: ");                      tffatherName = new JTextField();
        lyear = new JLabel("Year: ");                                 tfyear = new JTextField();
        lgender = new JLabel("Gender: ");          rbmale = new JRadioButton("Male"); rbfemale = new JRadioButton("Female");
        lbirthPlace = new JLabel("Birth Place: ");                    tfbirthPlace = new JTextField();
        lbirthDate = new JLabel("Birth Date: ");  tfbirthDate1 = new JTextField();tfbirthDate2 = new JTextField();tfbirthDate3 = new JTextField();
        lphone = new JLabel("Phone number: ");                        tfphone = new JTextField();
        lemergencyContact = new JLabel("Emergency Contact: ");        tfEmergencyContact = new JTextField();
        lemergencyNumber = new JLabel("Emergency Number: ");          tfEmergencyNumber = new JTextField();
        lemergencyAdres = new JLabel("Emergency Address: ");          tfEmergencyAdres = new JTextField();
        lstudentType = new JLabel("Student type: ");                  chkstudentType = new JComboBox(studentYtpe);
        lstudentType2 = new JLabel("Student type2: ");                chkstudentType2 =new JComboBox(studentYtpe2);
        ldepartment = new JLabel("Department: ");                       ldormnumber = new JLabel("Dorm no: ");
        tfdepartment = new JTextField();                                     tfdormnumber = new JTextField();
        lstream = new JLabel("Stream: ");                                 chkstream = new JComboBox(stream);
        bcheckavailability = new JButton("Check");                      chkemrgencyrelation=new JComboBox(relation);
        lemrgencyrelation=new JLabel("Emergency Relation: ");           bsubmit = new JButton("Submit and Add Student");
        bclose =new JButton("Close");                                    bsubmit1 = new JButton("Submit and Delete Student");
        bsubmit2 = new JButton("Submit and Update Student");


        il1.setBounds(0, 0, 1382, 735);
        lstudentinfo.setBounds(150,0,300,40);
        lfirstName.setBounds(0,50,150,40);
        tffirstName.setBounds(160,50,150,40);
        lfatherName.setBounds(0,100,150,40);
        tffatherName.setBounds(160,100,150,40);
        lstream.setBounds(400,50,100,40);
        chkstream.setBounds(510,50,150,40);
        ldepartment.setBounds(0,600,150,40);
        tfdepartment.setBounds(160,600,150,40);
        bcheckavailability.setBounds(580,210,100,40);
        lyear.setBounds(0,150,150,40);
        tfyear.setBounds(160,150,150,40);
        lgender.setBounds(0,200,150,40);
        rbmale.setBounds(160,200,70,40);
        rbfemale.setBounds(230,200,70,40);
        lbirthPlace.setBounds(0,250,150,40);
        tfbirthPlace.setBounds(160,250,150,40);
        lbirthDate.setBounds(0,300,150,40);
        tfbirthDate1.setBounds(160,300,40,40);
        tfbirthDate2.setBounds(205,300,40,40);
        tfbirthDate3.setBounds(250,300,65,40);
        lphone.setBounds(0,350,150,40);
        tfphone.setBounds(160,350,150,40);
        lemergencyContact.setBounds(0,400,150,40);
        tfEmergencyContact.setBounds(160,400,150,40);
        lemergencyNumber.setBounds(0,450,150,40);
        tfEmergencyNumber.setBounds(160,450,150,40);
        lemergencyAdres.setBounds(0,500,150,40);
        tfEmergencyAdres.setBounds(160,500,150,40);
        lstudentType.setBounds(400,110,100,40);
        chkstudentType.setBounds(510,110,150,40);
        ldormnumber.setBounds(400,210,100,40);
        tfdormnumber.setBounds(510,210,50,40);
        lstudentType2.setBounds(400,160,100,40);
        chkstudentType2.setBounds(510,160,100,40);
        lemrgencyrelation.setBounds(0,550,150,40);
        chkemrgencyrelation.setBounds(160,550,150,40);
        bsubmit.setBounds(350,550,390,90);
        bsubmit1.setBounds(350,550,390,90);
        bsubmit2.setBounds(350,550,390,90);
        bclose.setBounds(770,550,220,90);

        lemrgencyrelation.setHorizontalAlignment(SwingConstants.RIGHT);
        lfirstName.setHorizontalAlignment(SwingConstants.RIGHT);
        lfatherName.setHorizontalAlignment(SwingConstants.RIGHT);
        lemergencyNumber.setHorizontalAlignment(SwingConstants.RIGHT);
        lemergencyContact.setHorizontalAlignment(SwingConstants.RIGHT);
        lemergencyAdres.setHorizontalAlignment(SwingConstants.RIGHT);
        lbirthPlace.setHorizontalAlignment(SwingConstants.RIGHT);
        lstudentType.setHorizontalAlignment(SwingConstants.RIGHT);
        lbirthDate.setHorizontalAlignment(SwingConstants.RIGHT);
        lphone.setHorizontalAlignment(SwingConstants.RIGHT);
        lstream.setHorizontalAlignment(SwingConstants.RIGHT);
        ldormnumber.setHorizontalAlignment(SwingConstants.RIGHT);
        ldepartment.setHorizontalAlignment(SwingConstants.RIGHT);
        lstudentType2.setHorizontalAlignment(SwingConstants.RIGHT);
        lgender.setHorizontalAlignment(SwingConstants.RIGHT);
        lyear.setHorizontalAlignment(SwingConstants.RIGHT);
        l1.setHorizontalAlignment(SwingConstants.CENTER);
        l2.setHorizontalAlignment(SwingConstants.CENTER);
        l3.setHorizontalAlignment(SwingConstants.CENTER);

        l1.setBounds(60,20,450,60);
        l2.setBounds(10,90,220,60);
        l3.setBounds(10,160,220,60);
        bhome.setBounds(850,0,164,60);
        blogout.setBounds(1030,0,164,60);
        bexit.setBounds(1205,0,164,60);
        tfstudentid.setBounds(240,90,190,50);
        tfstudentname.setBounds(240,160,190,50);
        bcheckstudent.setBounds(40,290,170,55);
        bcheckstudent1.setBounds(40,290,170,55);
        bcheckstudent2.setBounds(40,290,200,55);
        bregistor2.setBounds(250,290,170,55);
        battendance2.setBounds(250,290,230,55);
        bwithdraw2.setBounds(250,290,170,55);
        bupdate2.setBounds(250,290,170,55);
        bstudentnotfound.setBounds(120,225,250,55);
        bwelcome.setBounds(120,225,250,55);
        pcheckstudent.setBounds(500,250,1000,400);
        penrolledstud.setBounds(329,60,1050,648);
        pwithdrewstud.setBounds(329,60,1050,648);
        pabsentstud.setBounds(329,60,1050,648);

        pchoice.setBounds(0,60,329,648);
        pregistor.setBounds(329,60,1050,648);
        pwithdraw.setBounds(329,60,1050,648);
        pupdate.setBounds(329,60,1050,648);


        l1.setForeground(Color.BLACK);
        l2.setForeground(Color.BLACK);
        l3.setForeground(Color.BLACK);
        bstudentnotfound.setBackground(Color.RED);
        bwelcome.setBackground(Color.GREEN);
        bhome.setBackground(Color.GREEN);
        blogout.setBackground(Color.yellow);
        bexit.setBackground(Color.red);
        bsubmit.setBackground(Color.GREEN);
        bsubmit1.setBackground(Color.GREEN);
        bsubmit2.setBackground(Color.GREEN);
        bclose.setBackground(Color.RED);


        bregistor1.setPreferredSize(new Dimension(200, 68));
        bwithdraw1.setPreferredSize(new Dimension(200, 68));
        bupdate1.setPreferredSize(new Dimension(200, 68));
        benrolledstudent.setPreferredSize(new Dimension(200, 68));
        bwithdrawstudent.setPreferredSize(new Dimension(200, 68));
        babsentstudent.setPreferredSize(new Dimension(200, 68));
        battendance.setPreferredSize(new Dimension(200, 68));
        Font fontpchoice = bregistor1.getFont().deriveFont(Font.ITALIC, 15);
        Font bruw = bregistor1.getFont().deriveFont(Font.ITALIC, 20);
        bsubmit.setFont(bruw);
        bsubmit1.setFont(bruw);
        bsubmit2.setFont(bruw);
        bclose.setFont(bruw);
        bhome.setFont(fontpchoice);
        blogout.setFont(fontpchoice);
        bexit.setFont(fontpchoice);
        bregistor1.setFont(fontpchoice);
        bwithdraw1.setFont(fontpchoice);
        bupdate1.setFont(fontpchoice);
        benrolledstudent.setFont(fontpchoice);
        bwithdrawstudent.setFont(fontpchoice);
        babsentstudent.setFont(fontpchoice);
        battendance.setFont(fontpchoice);

        Font fontlabel = bcheckstudent.getFont().deriveFont(Font.HANGING_BASELINE, 20);
        Font fontcheck = bcheckstudent.getFont().deriveFont(Font.BOLD, 49);
        bcheckstudent.setFont(fontlabel);
        bcheckstudent1.setFont(fontlabel);
        bcheckstudent2.setFont(fontlabel);
        bregistor2.setFont(fontlabel);
        battendance2.setFont(fontlabel);
        bwithdraw2.setFont(fontlabel);
        bupdate2.setFont(fontlabel);
        bwelcome.setFont(fontlabel);
        bstudentnotfound.setFont(fontlabel);
        l1.setFont(fontcheck);
        l2.setFont(fontlabel);
        l3.setFont(fontlabel);

        pcheckstudent.add(l1);                   pcheckstudent.add(l2);
        pcheckstudent.add(l3);                   pcheckstudent.add(bwithdraw2);
        pcheckstudent.add(tfstudentid);          pcheckstudent.add(tfstudentname);
        pcheckstudent.add(bregistor2);           pcheckstudent.add(battendance2);
        pcheckstudent.add(bwelcome);             pcheckstudent.add(bstudentnotfound);
        pcheckstudent.add(bupdate2);             pcheckstudent.add(labelimage1);

        pchoice.add(bregistor1);                 pchoice.add(bwithdraw1);
        pchoice.add(bupdate1);                   pchoice.add(benrolledstudent);
        pchoice.add(bwithdrawstudent);                         pchoice.add(babsentstudent);
        pchoice.add(battendance);

        bwithdraw2.setEnabled(false);           bregistor2.setEnabled(false);
        bstudentnotfound.setEnabled(false);     bwelcome.setEnabled(false);
        bupdate2.setEnabled(false);             battendance2.setEnabled(false);

        bwelcome.setVisible(false);             bstudentnotfound.setVisible(false);
        bregistor2.setVisible(false);           bwithdraw2.setVisible(false);
        bupdate2.setVisible(false);             battendance2.setVisible(false);


        rbmale.setSelected(true);    bgg.add(rbmale);
        bgg.add(rbfemale);
////////////////////////////////////////////////////////////////////////////////////////////////////////////
        try {
            ImageIcon image = new ImageIcon(getClass().getResource("home background.png"));
            ImageIcon i0 = new ImageIcon(getClass().getResource("logout.png"));
            ImageIcon i1 = new ImageIcon(getClass().getResource("login.png"));
            ImageIcon i2 = new ImageIcon(getClass().getResource("Leaved.png"));
            ImageIcon i3 = new ImageIcon(getClass().getResource("Update.png"));
            ImageIcon i4 = new ImageIcon(getClass().getResource("all student living.png"));
            ImageIcon i5 = new ImageIcon(getClass().getResource("Leaved.png"));
            ImageIcon i6 = new ImageIcon(getClass().getResource("absent.png"));
            ImageIcon i7 = new ImageIcon(getClass().getResource("list.png"));
            ImageIcon i9 = new ImageIcon(getClass().getResource("new student.png"));
            ImageIcon i10 = new ImageIcon(getClass().getResource("Close.png"));
            ImageIcon i11 = new ImageIcon(getClass().getResource("home.png"));
            ImageIcon i12 = new ImageIcon(getClass().getResource("delete.png"));
            il1.setIcon(image);
            bregistor1.setIcon(i1);          bwithdraw1.setIcon(i2);
            bupdate1.setIcon(i3);            benrolledstudent.setIcon(i4);
            bwithdrawstudent.setIcon(i5);    babsentstudent.setIcon(i6);
            battendance.setIcon(i7);                  bregistor2.setIcon(i9);
            blogout.setIcon(i0);             bwithdraw2.setIcon(i2);
            bupdate2.setIcon(i3);            bexit.setIcon(i10);
            bhome.setIcon(i11);              bsubmit.setIcon(i9);
            bclose.setIcon(i10);             bsubmit1.setIcon(i12);
        } catch (Exception e) {System.out.println("Image cannot be find");}
/////////////////////////////////////////////////////////////////////////////////////////////////
        bhome.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr.dispose();
                new checkin();
                pcheckstudent.setVisible(false);
                pwithdraw.setVisible(false);
                pregistor.setVisible(false);
                pabsentstud.setVisible(false);
                pupdate.setVisible(false);
                penrolledstud.setVisible(false);
                pwithdrewstud.setVisible(false);
                pabsentstud.setVisible(false);
                pupdate.setVisible(false);
            }
        });
//////////////////////////////////////////////////////////////////////////////////////////////
        blogout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr.dispose();
               login login= new login();
               login.f.dispose();
                new login();
            }
        });
//////////////////////////////////////////////////////////////////////////////////////////////
        bexit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fr.dispose();
            }
        });
/////////////////////////////////////////////////////////////////////////////////////////////
        bclose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                fr.dispose();
                new checkin();
                pcheckstudent.setVisible(true);
            }
        });
/////////////////////////////////////////////////////////////////////////////////////////////
        bcheckstudent.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try
                {
                    String student_id=tfstudentid.getText();
                    String student_name=(tfstudentname.getText()).toLowerCase();
                    String sql = "SELECT * FROM check_list WHERE student_id='"+student_id+"'and  student_name='"+student_name+"'";
                    pstmt = con.openConnection().prepareStatement(sql);
                    rs=pstmt.executeQuery();
                    if (rs.next())
                    {
                        String dorm_no=rs.getString("STUDENT_DORM_NO");
                        bregistor2.setEnabled(true);
                        bwelcome.setVisible(true);
                        bwelcome.setText("Welcome : "+student_name);
                        bstudentnotfound.setVisible(false);
                        ///table
                        
                       tavailable=new JTable();
                        spavailable=new JScrollPane(tavailable);
                        spavailable.setBounds(320,260,730,120);
                        String sql1="Select * from REGISTOR_STUDENT where STU_DORM_NO='"+dorm_no+"'";
                        pstmt1=con.openConnection().prepareStatement(sql1);
                        rs1=pstmt1.executeQuery();
                        rsmd=rs1.getMetaData();
                        dtm=(DefaultTableModel)tavailable.getModel();
                        int cols=rsmd.getColumnCount();
                        String[] colName=new String[cols];

                        for(int i=0;i<cols;i++)
                        {
                            colName[i]=rsmd.getColumnName(i+1);
                            dtm.setColumnIdentifiers(colName);
                            String c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17;
                            while(rs1.next())
                            {
                                c1=rs1.getString(1);
                                c2=rs1.getString(2);
                                c3=rs1.getString(3);
                                c4=rs1.getString(4);
                                c5=rs1.getString(5);
                                c6=rs1.getString(6);
                                c7=rs1.getString(7);
                                c8=rs1.getString(8);
                                c9=rs1.getString(9);
                                c10=rs1.getString(10);
                                c11=rs1.getString(11);
                                c12=rs1.getString(12);
                                c13=rs1.getString(13);
                                c14=rs1.getString(14);
                                c15=rs1.getString(15);
                                c16=rs1.getString(16);
                                c17=rs1.getString(17);
                                String[] row={c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17};
                                dtm.addRow(row);
                            }
                        }
                        //table
                        tavailable2=new JTable();
                        spavailable2=new JScrollPane(tavailable2);
                        spavailable2.setBounds(320,400,700,120);
                        String sql2="Select * from CHECK_LIST where STUDENT_DORM_NO='"+dorm_no+"'";
                        pstmt2=con.openConnection().prepareStatement(sql2);
                        rs2= pstmt2.executeQuery();
                        rsmd1=rs2.getMetaData();
                        dtm1=(DefaultTableModel)tavailable2.getModel();
                        int colus=rsmd1.getColumnCount();
                        String[] coluName=new String[colus];

                        for(int i=0;i<colus;i++)
                        {
                            coluName[i]=rsmd1.getColumnName(i+1);
                            dtm1.setColumnIdentifiers(coluName);
                            String id,name,dep,dorm,gender,father;
                            while(rs2.next())
                            {
                                id=rs2.getString(1);
                                name=rs2.getString(2);
                                dep=rs2.getString(3);
                                dorm=rs2.getString(4);
                                gender=rs2.getString(5);
                                father=rs2.getString(6);
                                String[] row={id,name,dep,dorm,gender,father};
                                dtm1.addRow(row);
                            }

                        }
                        rs2.close();
                        //image
                        try {
                            labelimage.setIcon(null);
                        }catch(Exception eima)
                        {
                            eima.getMessage();
                        }
                        String imagesql = "SELECT IMAGE_DATA FROM IMAGE_TABLE WHERE IMAGE_ID ='"+student_id+"'";
                        PreparedStatement preparedStatement = con.openConnection().prepareStatement(imagesql);
                        ResultSet resultSet = preparedStatement.executeQuery();

                        if (resultSet.next()) {
                            byte[] imageData = resultSet.getBytes("IMAGE_DATA");
                            ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                            Image image = ImageIO.read(bis);
                            icon = new ImageIcon(image);
                            labelimage.setIcon(icon);
                        }
                        else {
                            try {
                                ImageIcon icon = new ImageIcon(getClass().getResource("noimage.png"));
                                labelimage.setIcon(icon);
                            }catch(Exception ei){
                                System.out.println("image not found not found");
                            }
                        }
                        labelimage.setBounds(735,0,300,260);
                    //end
                    }
                    else
                    {
                        bregistor2.setEnabled(false);
                        bwithdraw2.setEnabled(false);
                        bupdate2.setEnabled(false);
                        bstudentnotfound.setVisible(true);
                        bwelcome.setVisible(false);
                    }
                    con.close();
                }catch (SQLException ex) {
                    System.out.println(ex);
                }
                catch (IOException eee)
                {
                    eee.printStackTrace();

                }
            }
        });
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        bcheckstudent1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {

                try
                {
                    String student_id=tfstudentid.getText();
                    String student_name=(tfstudentname.getText()).toLowerCase();
                    String sql = "Select * from REGISTOR_STUDENT where STU_ID ='"+student_id+"'  and  STU_FIRST_NAME='"+student_name+"'";
                    pstmt = con.openConnection().prepareStatement(sql);
                    rs=pstmt.executeQuery();
                    if (rs.next())
                    {
                        String dorm_no=rs.getString("STU_DORM_NO");
                        bwithdraw2.setEnabled(true);
                        bupdate2.setEnabled(true);
                        bwelcome.setVisible(true);
                        bwelcome.setText("Welcome : "+student_name);
                        bstudentnotfound.setVisible(false);
                        //table
                        tavailable=new JTable();
                        spavailable=new JScrollPane(tavailable);
                        spavailable.setBounds(320,260,730,120);
                        String sql1="Select * from REGISTOR_STUDENT where STU_DORM_NO='"+dorm_no+"'";
                        pstmt1=con.openConnection().prepareStatement(sql1);
                        rs1=pstmt1.executeQuery();
                        rsmd=rs1.getMetaData();
                        dtm=(DefaultTableModel)tavailable.getModel();
                        int cols=rsmd.getColumnCount();
                        String[] colName=new String[cols];

                        for(int i=0;i<cols;i++)
                        {
                            colName[i]=rsmd.getColumnName(i+1);
                            dtm.setColumnIdentifiers(colName);
                            String c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17;
                            while(rs1.next())
                            {
                                c1=rs1.getString(1);
                                c2=rs1.getString(2);
                                c3=rs1.getString(3);
                                c4=rs1.getString(4);
                                c5=rs1.getString(5);
                                c6=rs1.getString(6);
                                c7=rs1.getString(7);
                                c8=rs1.getString(8);
                                c9=rs1.getString(9);
                                c10=rs1.getString(10);
                                c11=rs1.getString(11);
                                c12=rs1.getString(12);
                                c13=rs1.getString(13);
                                c14=rs1.getString(14);
                                c15=rs1.getString(15);
                                c16=rs1.getString(16);
                                c17=rs1.getString(17);
                                String[] row={c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17};
                                dtm.addRow(row);
                            }
                        }
                        //table
                        tavailable2=new JTable();
                        spavailable2=new JScrollPane(tavailable2);
                        spavailable2.setBounds(320,400,700,120);
                        String sql2="Select * from CHECK_LIST where STUDENT_DORM_NO='"+dorm_no+"'";
                        pstmt2=con.openConnection().prepareStatement(sql2);
                        rs2= pstmt2.executeQuery();
                        rsmd1=rs2.getMetaData();
                        dtm1=(DefaultTableModel)tavailable2.getModel();
                        int colus=rsmd1.getColumnCount();
                        String[] coluName=new String[colus];

                        for(int i=0;i<colus;i++)
                        {
                            coluName[i]=rsmd1.getColumnName(i+1);
                            dtm1.setColumnIdentifiers(coluName);
                            String id,name,dep,dorm,gender,father;
                            while(rs2.next())
                            {
                                id=rs2.getString(1);
                                name=rs2.getString(2);
                                dep=rs2.getString(3);
                                dorm=rs2.getString(4);
                                gender=rs2.getString(5);
                                father=rs2.getString(6);
                                String[] row={id,name,dep,dorm,gender,father};
                                dtm1.addRow(row);
                            }

                        }
                        rs2.close();
                        //image

                        String imagesql = "SELECT IMAGE_DATA FROM IMAGE_TABLE WHERE IMAGE_ID ='"+student_id+"'";
                        PreparedStatement preparedStatement = con.openConnection().prepareStatement(imagesql);
                        ResultSet resultSet = preparedStatement.executeQuery();

                        if (resultSet.next()) {
                            byte[] imageData = resultSet.getBytes("IMAGE_DATA");
                            ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                            Image image = ImageIO.read(bis);
                            icon = new ImageIcon(image);
                            labelimage.setIcon(icon);
                        }
                        else {
                            try {
                                ImageIcon icon = new ImageIcon(getClass().getResource("noimage.png"));
                                labelimage.setIcon(icon);
                            }catch(Exception ei){
                                System.out.println("image not found not found");
                            }
                        }
                        labelimage.setBounds(735,0,300,260);
                    //end
                    }
                    else
                    {
                        bregistor2.setEnabled(false);
                        bwithdraw2.setEnabled(false);
                        bupdate2.setEnabled(false);
                        bstudentnotfound.setVisible(true);
                        bwelcome.setVisible(false);
                    }
                    con.close();
                }catch (SQLException ex) {
                    System.out.println(ex);
                }
                catch (IOException eee)
                {
                    eee.printStackTrace();
                }
            }
        });
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        bregistor1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pcheckstudent.setVisible(true);
                bwithdraw2.setVisible(false);
                bupdate2.setVisible(false);
                bregistor2.setVisible(true);
                pwithdraw.setVisible(false);
                pregistor.setVisible(false);
                pabsentstud.setVisible(false);
                pupdate.setVisible(false);
                penrolledstud.setVisible(false);
                pabsentstud.setVisible(false);
                pwithdrewstud.setVisible(false);
                labelimage1.setVisible(false);
                battendance2.setVisible(false);
                pcheckstudent.add(bcheckstudent);
                pcheckstudent.remove(bcheckstudent1);
                pcheckstudent.remove(bcheckstudent2);
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        bregistor2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ec) {
                try{

                    pregistor.add(lstudentinfo);            pregistor.add(lfirstName);
                    pregistor.add(lfatherName);             pregistor.add(lyear);
                    pregistor.add(lgender);                 pregistor.add(lbirthPlace);
                    pregistor.add(lbirthDate);              pregistor.add(lphone);
                    pregistor.add(lemergencyAdres);         pregistor.add(lemergencyContact);
                    pregistor.add(lemergencyNumber);        pregistor.add(lstudentType);
                    pregistor.add(lstudentType2);           pregistor.add(chkstudentType2);
                    pregistor.add(tffirstName);             pregistor.add(tffatherName);
                    pregistor.add(tfyear);                  pregistor.add(rbmale);                   pregistor.add(rbfemale);
                    pregistor.add(tfbirthDate1);            pregistor.add(tfbirthDate2);             pregistor.add(tfbirthDate3);
                    pregistor.add(tfbirthPlace);            pregistor.add(tfphone);
                    pregistor.add(tfEmergencyAdres);        pregistor.add(tfEmergencyContact);
                    pregistor.add(tfEmergencyNumber);       pregistor.add(chkstudentType);
                    pregistor.add(ldepartment);             pregistor.add(ldormnumber);
                    pregistor.add(tfdepartment);            pregistor.add(tfdormnumber);
                    pregistor.add(lstream);                 pregistor.add(chkstream);
                    pregistor.add(bcheckavailability);      pregistor.add(lemrgencyrelation);
                    pregistor.add(chkemrgencyrelation);     pregistor.add(bsubmit);
                    pregistor.add(bclose);                  pregistor.add(labelimage);
                    pregistor.add(spavailable);             pregistor.add(spavailable2);
                    String student_id=tfstudentid.getText();
                    String registorselect ="Select STUDENT_NAME,STUDENT_DEPARTMENT,STUDENT_DORM_NO, STUDENT_GENDER,STUDENT_FATHER_NAME from CHECK_LIST where STUDENT_ID = '"+student_id+"'";
                    pstmt=con.openConnection().prepareStatement(registorselect);
                    rs =pstmt.executeQuery();
                    while(rs.next()) {
                        String firstname = rs.getString("STUDENT_NAME");
                        String department = rs.getString("STUDENT_DEPARTMENT");
                        String dormnumber = rs.getString("STUDENT_DORM_NO");
                        String gender = rs.getString("STUDENT_GENDER");
                        if (gender=="female")
                        {
                            rbfemale.setSelected(true);
                        }
                        else
                        {
                            rbmale.setSelected(true);
                        }
                        String father = rs.getString("STUDENT_FATHER_NAME");
                        tffatherName.setText(father);
                        tffirstName.setText(firstname);
                        tfdepartment.setText(department);
                        tfdormnumber.setText(dormnumber);
                    }
                    con.close();
                }catch(SQLException ee)
                {
                    System.out.println(ee);
                    }

                pcheckstudent.setVisible(false);
                pregistor.setVisible(true);
                bregistor2.setEnabled(false);
                bwithdraw2.setEnabled(false);
                bupdate2.setEnabled(false);
            }
        });
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        bsubmit.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String instudent_id = null;
        String infirstname1 = null;
        String infathername = null;
        int inyear = 0;
        String ingender = "male";
        String inbirthplace = null;
        int inphonenumber = 0;
        String inbirthdate = null;
        int inemergencyphone = 0;
        String inemergency = null;
        String inemeregncyaddress = null;
        String inemergencyrelation = null;
        String instudenttype = null;
        String instudenttype2 = null;
        String instream = null;
        String indepartment = null;
        int indormno = 0;

        int tf = 0;
            instudent_id = tfstudentid.getText();
            infirstname1 = tffirstName.getText();
            infathername = tffatherName.getText();
            inbirthplace = tfbirthPlace.getText();
            inemergency = tfEmergencyContact.getText();
            inemeregncyaddress = tfEmergencyAdres.getText();
            inemergencyrelation = String.valueOf(chkemrgencyrelation.getSelectedItem());
            instudenttype = String.valueOf(chkstudentType.getSelectedItem());
            instudenttype2 = String.valueOf(chkstudentType2.getSelectedItem());
            instream = String.valueOf(chkstream.getSelectedItem());
            indepartment = tfdepartment.getText();
            indormno = Integer.parseInt(tfdormnumber.getText());
            if (infirstname1.length()==0)
            {
            JOptionPane.showMessageDialog(null, "First Name cannot be empty", "Name", JOptionPane.ERROR_MESSAGE);
            tf++;
            }
        if (infathername.length()==0)
        {
            JOptionPane.showMessageDialog(null, "Father Name cannot be empty", "Name", JOptionPane.ERROR_MESSAGE);
            tf++;
        }
        if (inbirthplace.length()==0)
        {
            JOptionPane.showMessageDialog(null, "Birth Place cannot be empty", "Birth Place", JOptionPane.ERROR_MESSAGE);
            tf++;
        }
        if (inemergency.length()==0)
        {
            JOptionPane.showMessageDialog(null, "Emergency cannot be empty", "Emergency", JOptionPane.ERROR_MESSAGE);
            tf++;
        }
        if (inemeregncyaddress.length()==0)
        {
            JOptionPane.showMessageDialog(null, "Emergency address cannot be empty", "Emergency", JOptionPane.ERROR_MESSAGE);
            tf++;
        }
        if (indepartment.length()==0)
        {
            JOptionPane.showMessageDialog(null, "Department cannot be empty", "Department", JOptionPane.ERROR_MESSAGE);
            tf++;
        }

            if (rbfemale.isSelected()) {
                JOptionPane.showMessageDialog(null, "You cannot be female this is males dorm");
                rbmale.setSelected(true);
                tf++;
            }

            try {
                inyear = Integer.valueOf(tfyear.getText());
                if (inyear == 0) {
                    System.out.println("Heellloooo");
                    JOptionPane.showMessageDialog(null, "Enter the year", "Year", JOptionPane.ERROR_MESSAGE);
                    tf++;
                } else {
                    inyear = Integer.parseInt(tfyear.getText());

                }
                int day = Integer.parseInt(tfbirthDate1.getText());
                int month = Integer.parseInt(tfbirthDate2.getText());
                int year = Integer.parseInt(tfbirthDate3.getText());
                if (day > 30 || (tfbirthDate1.getText()).length() > 2) {
                    JOptionPane.showMessageDialog(null, "Incorrect day", "Birth Date Error", JOptionPane.ERROR_MESSAGE);
                    tf++;
                } else if (month > 12 || (tfbirthDate2.getText()).length() > 2) {
                    JOptionPane.showMessageDialog(null, "Incorrect month", "Birth Date Error", JOptionPane.ERROR_MESSAGE);
                    tf++;
                } else if (year < 1916 || year > 2006 || (tfbirthDate3.getText()).length() > 4) {
                    JOptionPane.showMessageDialog(null, "Incorrect year", "Birth Date Error", JOptionPane.ERROR_MESSAGE);
                    tf++;
                } else {
                    inbirthdate = day + "-" + month + "-" + year;
                     
                }
            } catch (NumberFormatException ex) {
                System.out.println("Number format exception occured on birth date");
                JOptionPane.showMessageDialog(null, "Enter only number", "Birth Date Error", JOptionPane.ERROR_MESSAGE);
                tf++;
            }
            try {
                String inp = tfphone.getText();
                String inp2 = tfEmergencyNumber.getText();
                if (inp.startsWith("2519") || inp.startsWith("2517") || inp2.startsWith("2519") || inp2.startsWith("2517")) {
                    if (inp.length() == 12 || inp2.length() == 12) {
                        inphonenumber = Integer.parseInt(tfphone.getText());
                        inemergencyphone = Integer.valueOf(tfEmergencyNumber.getText());
                         
                    } else {
                        JOptionPane.showMessageDialog(null, "The Length Of Phone Number Should Be 12", "Phone Number Error", JOptionPane.ERROR_MESSAGE);
                        tf++;
                    }
                } else if (inp.startsWith("09") || (inp.startsWith("07")) || inp2.startsWith("09") || (inp2.startsWith("07"))) {
                    if (inp.length() == 10 || inp2.length() == 10) {
                        inphonenumber = Integer.parseInt(tfphone.getText());
                        inemergencyphone = Integer.valueOf(tfEmergencyNumber.getText());
                         
                    } else {
                        JOptionPane.showMessageDialog(null, "The Length Of Phone Number Should Be 10", "Phone Number Error", JOptionPane.ERROR_MESSAGE);
                        tf++;
                    }
                } else if (inp.startsWith("9") || inp.startsWith("7") || inp2.startsWith("9") || inp2.startsWith("7")) {
                    if (inp.length() == 9 || inp2.length() == 9) {
                        inphonenumber = Integer.parseInt(tfphone.getText());
                        inemergencyphone = Integer.valueOf(tfEmergencyNumber.getText());
                         
                    } else {
                        JOptionPane.showMessageDialog(null, "The Length Of Phone Number Should Be 9", "Phone Number Error", JOptionPane.ERROR_MESSAGE);
                        tf++;
                    }
                } else if (inp.startsWith("+2519") || inp.startsWith("+2517") || inp2.startsWith("+2519") || inp2.startsWith("+2517")) {
                    if (inp.length() == 13 || inp2.length() == 13) {
                        inphonenumber = Integer.parseInt(tfphone.getText());
                        inemergencyphone = Integer.valueOf(tfEmergencyNumber.getText());
                         
                    } else {
                        JOptionPane.showMessageDialog(null, "Enter correct phone number", "Phone Number Error", JOptionPane.ERROR_MESSAGE);
                        tf++;
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Enter Phone Number correctly ", "Phone Number Error", JOptionPane.ERROR_MESSAGE);
                    tf++;
                }
            } catch (NumberFormatException excc) {
                System.out.println("Number format exception occured on the phone number");
                JOptionPane.showMessageDialog(null, "Enter only number", "Error", JOptionPane.ERROR_MESSAGE);
                tf++;
            }

            if (tf==0) {
                try {
                    String insertsql = "INSERT INTO REGISTOR_STUDENT (    STU_ID,         STU_FIRST_NAME,       STU_FATHER_NAME,          STU_YEAR,       STU_GENDER, " +
                            "STU_BIRTHDATE,     STU_BIRTHPLACE,    STU_PHONE_NUMBER,            STU_EMERGENCY,          STU_EMERGENCY_PHONE, " +
                            "STU_EMERGENCY_RELATION,                STU_TYPE,               STU_TYPE2,              STU_DEPARTMENT, " +
                            "STU_DORM_NO,                              STU_EMEREGENCY_ADDRESS,STU_STREAM) " +
                            "VALUES (?, ?, ?,    ?, ?, ?,   ?, ?, ?,    ?, ?, ?,     ?, ?, ?,   ?,?)";
                    pstmt1 = con.openConnection().prepareStatement(insertsql);
                    pstmt1.setString(1, instudent_id);
                    pstmt1.setString(2, infirstname1);
                    pstmt1.setString(3, infathername);
                    pstmt1.setInt(4, inyear);
                    pstmt1.setString(5, ingender);
                    pstmt1.setString(6, inbirthdate);
                    pstmt1.setString(7, inbirthplace);
                    pstmt1.setInt(8, inphonenumber);
                    pstmt1.setString(9, inemergency);
                    pstmt1.setInt(10, inemergencyphone);
                    pstmt1.setString(11, inemergencyrelation);
                    pstmt1.setString(12, instudenttype);
                    pstmt1.setString(13, instudenttype2);
                    pstmt1.setString(14, indepartment);
                    pstmt1.setInt(15, indormno);
                    pstmt1.setString(16, inemeregncyaddress);
                    pstmt1.setString(17, instream);
                    int raf = pstmt1.executeUpdate();
                    if (raf > 0) {
                        System.out.println("Data inserted successfully!");
                        JOptionPane.showMessageDialog(null, "Student registered Successfully");
                        String insertatt = "INSERT INTO ATTENDANCE (STUDENT_ID, STUDENT_NAME) VALUES (?,?)";
                        pstmt2 = con.openConnection().prepareStatement(insertatt);
                        pstmt2.setString(1, instudent_id);
                        pstmt2.setString(2, infirstname1);
                        int ij=pstmt2.executeUpdate();
                        if (ij>0)
                        {
                            System.out.println("Attendance student added");
                        }
                        String sql = "Delete from CHECK_LIST Where STUDENT_ID='"+instudent_id+"'";
                        pstmt = con.openConnection().prepareStatement(sql);
                        int rs12= pstmt.executeUpdate();
                        if (rs12>0)
                        {
                            System.out.println("Student deleted from checklist");
                        }
                        pregistor.setVisible(false);
                        pcheckstudent.setVisible(false);
                    } else {
                        System.out.println("Failed to insert data.");
                    }
                } catch (SQLException e) {
                    System.out.println(e);
                }
            }else {
                System.out.println("Data insertion failed!");
                JOptionPane.showMessageDialog(null, "Student insertion Unsuccessfull");
            }
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        bsubmit1.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try{
            String student_id = tfstudentid.getText();
                String registorselect ="Select * from REGISTOR_STUDENT where STU_ID = '"+student_id+"'";
                pstmt=con.openConnection().prepareStatement(registorselect);
                rs =pstmt.executeQuery();
                if (rs.next()) {
                    String firstname = rs.getString("STU_FIRST_NAME");
                    String fathername = rs.getString("STU_FATHER_NAME");
                    String gender = rs.getString("STU_GENDER");
                    String department = rs.getString("STU_DEPARTMENT");
                    int dormnumber = rs.getInt("STU_DORM_NO");
                    String insertsql = "INSERT INTO WITHDRAW (STUDENT_ID,   STUDENT_NAME,STUDENT_DEPARTMENT,    STUDENT_DORM_NO,    STUDENT_GENDER," +
                            "STUDENT_FATHER_NAME) VALUES (?, ?, ?,    ?, ?, ?)";
                    pstmt1 = con.openConnection().prepareStatement(insertsql);
                    pstmt1.setString(1, student_id);
                    pstmt1.setString(2, firstname);
                    pstmt1.setString(3, department);
                    pstmt1.setInt(4, dormnumber);
                    pstmt1.setString(5, gender);
                    pstmt1.setString(6, fathername);
                    int rsi=pstmt1.executeUpdate();
                    if(rsi>0)
                    {
                        System.out.println("Student added to withdraw");
                        String sql = "Delete from REGISTOR_STUDENT Where STU_ID='"+student_id+"'";
                        pstmt1 = con.openConnection().prepareStatement(sql);
                        int rs12= pstmt1.executeUpdate();
                        if (rs12>0)
                        {
                            JOptionPane.showMessageDialog(null,"Student Deleted","Withdraw",JOptionPane.WARNING_MESSAGE);
                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null,"Student not found","Withdraw",JOptionPane.WARNING_MESSAGE);

                        }
                    }
                }else {
                    System.out.print("No data");
                }
                con.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        pcheckstudent.setVisible(true);
        pwithdraw.setVisible(false);
    }
});
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        bsubmit2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String instudent_id = null;
                String infirstname1 = null;
                String infathername = null;
                int inyear = 0;
                String ingender = "male";
                String inbirthplace = null;
                int inphonenumber = 0;
                String inbirthdate = null;
                int inemergencyphone = 0;
                String inemergency = null;
                String inemeregncyaddress = null;
                String inemergencyrelation = null;
                String instudenttype = null;
                String instudenttype2 = null;
                String instream = null;
                String indepartment = null;
                int indormno = 0;

                int tf=0;
                instudent_id = tfstudentid.getText();
                infirstname1 = tffirstName.getText();
                infathername = tffatherName.getText();
                inbirthplace = tfbirthPlace.getText();
                inemergency = tfEmergencyContact.getText();
                inemeregncyaddress = tfEmergencyAdres.getText();
                inemergencyrelation = String.valueOf(chkemrgencyrelation.getSelectedItem());
                instudenttype = String.valueOf(chkstudentType.getSelectedItem());
                instudenttype2 = String.valueOf(chkstudentType2.getSelectedItem());
                instream = String.valueOf(chkstream.getSelectedItem());
                indepartment = tfdepartment.getText();
                indormno = Integer.parseInt(tfdormnumber.getText());
                if (infirstname1.length()==0)
                {
                    JOptionPane.showMessageDialog(null, "First Name cannot be empty", "Name", JOptionPane.ERROR_MESSAGE);
                    tf++;
                }
                if (infathername.length()==0)
                {
                    JOptionPane.showMessageDialog(null, "Father Name cannot be empty", "Name", JOptionPane.ERROR_MESSAGE);
                    tf++;
                }
                if (inbirthplace.length()==0)
                {
                    JOptionPane.showMessageDialog(null, "Birth Place cannot be empty", "Birth Place", JOptionPane.ERROR_MESSAGE);
                    tf++;
                }
                if (inemergency.length()==0)
                {
                    JOptionPane.showMessageDialog(null, "Emergency cannot be empty", "Emergency", JOptionPane.ERROR_MESSAGE);
                    tf++;
                }
                if (inemeregncyaddress.length()==0)
                {
                    JOptionPane.showMessageDialog(null, "Emergency address cannot be empty", "Emergency", JOptionPane.ERROR_MESSAGE);
                    tf++;
                }
                if (indepartment.length()==0)
                {
                    JOptionPane.showMessageDialog(null, "Department cannot be empty", "Department", JOptionPane.ERROR_MESSAGE);
                    tf++;
                }
                if (rbfemale.isSelected()) {
                    JOptionPane.showMessageDialog(null, "You cannot be female this is males dorm");
                    rbmale.setSelected(true);
                    tf++;
                }

                try {
                    inyear = Integer.valueOf(tfyear.getText());
                    if (inyear == 0) {
                        JOptionPane.showMessageDialog(null, "Enter the year", "Year", JOptionPane.ERROR_MESSAGE);
                        tf++;
                    }
                    int day = Integer.parseInt(tfbirthDate1.getText());
                    int month = Integer.parseInt(tfbirthDate2.getText());
                    int year = Integer.parseInt(tfbirthDate3.getText());
                    if (day > 30 || (tfbirthDate1.getText()).length() > 2) {
                        JOptionPane.showMessageDialog(null, "Incorrect day", "Birth Date Error", JOptionPane.ERROR_MESSAGE);
                        tf++;
                    } else if (month > 12 || (tfbirthDate2.getText()).length() > 2) {
                        JOptionPane.showMessageDialog(null, "Incorrect month", "Birth Date Error", JOptionPane.ERROR_MESSAGE);
                        tf++;
                    } else if (year < 1916 || year > 2006 || (tfbirthDate3.getText()).length() > 4) {
                        JOptionPane.showMessageDialog(null, "Incorrect year", "Birth Date Error", JOptionPane.ERROR_MESSAGE);
                        tf++;
                    } else {
                        inbirthdate = day + "-" + month + "-" + year;

                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Number format exception occured on birth date");
                    JOptionPane.showMessageDialog(null, "Enter only number", "Birth Date Error", JOptionPane.ERROR_MESSAGE);
                    tf++;
                }
                try {
                    String inp = tfphone.getText();
                    String inp2 = tfEmergencyNumber.getText();
                    if (inp.startsWith("2519") || inp.startsWith("2517") || inp2.startsWith("2519") || inp2.startsWith("2517")) {
                            if (inp.length() == 12 || inp2.length() == 12) {
                                inphonenumber = Integer.parseInt(tfphone.getText());
                                inemergencyphone = Integer.valueOf(tfEmergencyNumber.getText());

                            } else {
                                JOptionPane.showMessageDialog(null, "The Length Of Phone Number Should Be 12", "Phone Number Error", JOptionPane.ERROR_MESSAGE);
                                tf++;
                            }
                     } else if (inp.startsWith("09") || (inp.startsWith("07")) || inp2.startsWith("09") || (inp2.startsWith("07"))) {
                            if (inp.length() == 10 || inp2.length() == 10) {
                                inphonenumber = Integer.parseInt(tfphone.getText());
                                inemergencyphone = Integer.valueOf(tfEmergencyNumber.getText());

                            } else {
                                JOptionPane.showMessageDialog(null, "The Length Of Phone Number Should Be 10", "Phone Number Error", JOptionPane.ERROR_MESSAGE);
                                tf++;
                            }
                     } else if (inp.startsWith("9") || inp.startsWith("7") || inp2.startsWith("9") || inp2.startsWith("7")) {
                            if (inp.length() == 9 || inp2.length() == 9) {
                                inphonenumber = Integer.parseInt(tfphone.getText());
                                inemergencyphone = Integer.valueOf(tfEmergencyNumber.getText());

                            } else {
                                JOptionPane.showMessageDialog(null, "The Length Of Phone Number Should Be 9", "Phone Number Error", JOptionPane.ERROR_MESSAGE);
                                tf++;
                            }
                     } else if (inp.startsWith("+2519") || inp.startsWith("+2517") || inp2.startsWith("+2519") || inp2.startsWith("+2517")) {
                            if (inp.length() == 13 || inp2.length() == 13) {
                                inphonenumber = Integer.parseInt(tfphone.getText());
                                inemergencyphone = Integer.valueOf(tfEmergencyNumber.getText());

                            } else {
                                JOptionPane.showMessageDialog(null, "Enter correct phone number", "Phone Number Error", JOptionPane.ERROR_MESSAGE);
                                tf++;
                            }
                    } else {
                        JOptionPane.showMessageDialog(null, "Enter Phone Number correctly ", "Phone Number Error", JOptionPane.ERROR_MESSAGE);
                        tf++;
                    }
                } catch (NumberFormatException excc) {
                    System.out.println("Number format exception occured on the phone number");
                    JOptionPane.showMessageDialog(null, "Enter only number", "Phone Number Error", JOptionPane.ERROR_MESSAGE);
                    tf++;
                }

                if (tf==0) {
                    try {
                        String updatesql = "UPDATE REGISTOR_STUDENT SET STU_FIRST_NAME = ?, STU_FATHER_NAME = ?, STU_YEAR = ?," +
                                " STU_GENDER = ?, STU_BIRTHDATE = ?, STU_BIRTHPLACE = ?, STU_PHONE_NUMBER = ?, STU_EMERGENCY = ?," +
                                " STU_EMERGENCY_PHONE = ?, STU_EMERGENCY_RELATION = ?, STU_TYPE = ?, STU_TYPE2 = ?, STU_DEPARTMENT = ?," +
                                " STU_DORM_NO = ?, STU_EMEREGENCY_ADDRESS = ?, STU_STREAM = ? WHERE STU_ID = ? ";
                        pstmt1 = con.openConnection().prepareStatement(updatesql);

                        pstmt1.setString(1, infirstname1);
                        pstmt1.setString(2, infathername);
                        pstmt1.setInt(3, inyear);
                        pstmt1.setString(4, ingender);
                        pstmt1.setString(5, inbirthdate);
                        pstmt1.setString(6, inbirthplace);
                        pstmt1.setInt(7, inphonenumber);
                        pstmt1.setString(8, inemergency);
                        pstmt1.setInt(9, inemergencyphone);
                        pstmt1.setString(10, inemergencyrelation);
                        pstmt1.setString(11, instudenttype);
                        pstmt1.setString(12, instudenttype2);
                        pstmt1.setString(13, indepartment);
                        pstmt1.setInt(14, indormno);
                        pstmt1.setString(15, inemeregncyaddress);
                        pstmt1.setString(16,instream);
                        pstmt1.setString(17, instudent_id);
                        int raf = pstmt1.executeUpdate();
                        if (raf > 0) {
                            System.out.println("Data updated successfully!");
                            JOptionPane.showMessageDialog(null, "Student updated Successfully");
                            pupdate.setVisible(false);
                            pcheckstudent.setVisible(false);
                        } else {
                            System.out.println("Failed to insert data.");
                        }
                        pstmt1.close();
                        con.close();
                    } catch (SQLException e) {
                        System.out.println(e);
                        e.printStackTrace();
                    }
                }
                else {
                    System.out.println("Data updation failed!");
                    JOptionPane.showMessageDialog(null, "Student updated Unsuccessfully");
                }
            }
        });
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        bwithdraw1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bwithdraw2.setVisible(true);
                bregistor2.setVisible(false);
                battendance2.setVisible(false);
                bupdate2.setVisible(false);
                pcheckstudent.setVisible(true);
                pwithdraw.setVisible(false);
                pregistor.setVisible(false);
                pabsentstud.setVisible(false);
                pupdate.setVisible(false);
                penrolledstud.setVisible(false);
                pwithdrewstud.setVisible(false);
                pabsentstud.setVisible(false);
                pupdate.setVisible(false);
                labelimage1.setVisible(false);
                pcheckstudent.add(bcheckstudent1);
                pcheckstudent.remove(bcheckstudent);
                pcheckstudent.remove(bcheckstudent2);
                tfstudentid.setText("");
                tfstudentname.setText("");
            }

        });
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        bwithdraw2.addActionListener(new ActionListener() {
                                  @Override
              public void actionPerformed(ActionEvent e) {
                  pwithdraw.add(lstudentinfo);            pwithdraw.add(lfirstName);
                  pwithdraw.add(lfatherName);             pwithdraw.add(lyear);
                  pwithdraw.add(lgender);                 pwithdraw.add(lbirthPlace);
                  pwithdraw.add(lbirthDate);              pwithdraw.add(lphone);
                  pwithdraw.add(lemergencyAdres);         pwithdraw.add(lemergencyContact);
                  pwithdraw.add(lemergencyNumber);        pwithdraw.add(lstudentType);
                  pwithdraw.add(lstudentType2);           pwithdraw.add(chkstudentType2);
                  pwithdraw.add(tffirstName);             pwithdraw.add(tffatherName);
                  pwithdraw.add(tfyear);                  pwithdraw.add(rbmale);                   pwithdraw.add(rbfemale);
                  pwithdraw.add(tfbirthDate1);            pwithdraw.add(tfbirthDate2);             pwithdraw.add(tfbirthDate3);
                  pwithdraw.add(tfbirthPlace);            pwithdraw.add(tfphone);
                  pwithdraw.add(tfEmergencyAdres);        pwithdraw.add(tfEmergencyContact);
                  pwithdraw.add(tfEmergencyNumber);       pwithdraw.add(chkstudentType);
                  pwithdraw.add(ldepartment);             pwithdraw.add(ldormnumber);
                  pwithdraw.add(tfdepartment);            pwithdraw.add(tfdormnumber);
                  pwithdraw.add(lstream);                 pwithdraw.add(chkstream);
                  pwithdraw.add(bcheckavailability);      pwithdraw.add(lemrgencyrelation);
                  pwithdraw.add(chkemrgencyrelation);     pwithdraw.add(bsubmit1);
                  pwithdraw.add(bclose);                  pwithdraw.add(labelimage);
                  pwithdraw.add(spavailable);             pwithdraw.add(spavailable2);
                  pcheckstudent.setVisible(false);
                  bwithdraw2.setEnabled(false);
                  bregistor2.setEnabled(false);
                  pwithdraw.setVisible(true);
                  bupdate2.setEnabled(false);

                  try{

                      String student_id=tfstudentid.getText();
                      String registorselect ="Select * from REGISTOR_STUDENT where STU_ID = '"+student_id+"'";
                      pstmt=con.openConnection().prepareStatement(registorselect);
                      rs =pstmt.executeQuery();
                      if (rs.next()) {
                              String firstname = rs.getString("STU_FIRST_NAME");
                              String fathername = rs.getString("STU_FATHER_NAME");
                              int year = rs.getInt("STU_YEAR");
                              String gender = rs.getString("STU_GENDER");
                              String birthdate = rs.getString("STU_BIRTHDATE");
                              String birthplace = rs.getString("STU_BIRTHPLACE");
                              int phone = rs.getInt("STU_PHONE_NUMBER");
                              String emergency = rs.getString("STU_EMERGENCY");
                              int emrgencyphone = rs.getInt("STU_EMERGENCY_PHONE");
                              String emergencyrelation = rs.getString("STU_EMERGENCY_RELATION");
                              String studenttype = rs.getString("STU_TYPE");
                              String studenttype2 = rs.getString("STU_TYPE2");
                              String department = rs.getString("STU_DEPARTMENT");
                              int dormnumber = rs.getInt("STU_DORM_NO");
                              String emergencyaddrss = rs.getString("STU_EMEREGENCY_ADDRESS");
                              String stream = rs.getString("STU_STREAM");

                              tffirstName.setText(firstname);
                              tffatherName.setText(fathername);
                              tfyear.setText(String.valueOf(year));
                              if (gender == "female")
                              {
                                  rbfemale.setSelected(true);
                              } else
                              {
                                  rbmale.setSelected(true);
                              }
                              String[] bd = birthdate.split("-");
                              tfbirthDate1.setText(bd[0]);
                              tfbirthDate2.setText(bd[1]);
                              tfbirthDate3.setText(bd[2]);
                              tfbirthPlace.setText(birthplace);
                              tfphone.setText(String.valueOf(phone));
                              tfEmergencyContact.setText(emergency);
                              tfEmergencyNumber.setText(String.valueOf(emrgencyphone));
                              int er = isStringInComboBox(emergencyrelation, chkemrgencyrelation);
                              chkemrgencyrelation.setSelectedIndex(er);
                              int st = isStringInComboBox(studenttype, chkstudentType);
                              chkstudentType.setSelectedIndex(st);
                              int st2 = isStringInComboBox(studenttype2, chkstudentType2);
                              chkstudentType2.setSelectedIndex(st2);
                              int str = isStringInComboBox(stream, chkstream);
                              chkstream.setSelectedIndex(str);
                              tfEmergencyAdres.setText(emergencyaddrss);
                              tfdormnumber.setText(String.valueOf(dormnumber));
                              tfdepartment.setText(department);
                      }else {
                          System.out.print("No data");
                      }
                      con.close();
                  }catch(SQLException ee)
                  {
                      System.out.println(ee);
                  }
              }

          });
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        bupdate1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                bupdate2.setVisible(true);
                bwithdraw2.setVisible(false);
                bregistor2.setVisible(false);
                battendance2.setVisible(false);
                pcheckstudent.setVisible(true);
                pwithdraw.setVisible(false);
                pregistor.setVisible(false);
                pabsentstud.setVisible(false);
                pupdate.setVisible(false);
                penrolledstud.setVisible(false);
                pwithdrewstud.setVisible(false);
                pabsentstud.setVisible(false);
                pupdate.setVisible(false);
                labelimage1.setVisible(false);
                pcheckstudent.add(bcheckstudent1);
                pcheckstudent.remove(bcheckstudent2);
                pcheckstudent.remove(bcheckstudent);
                tfstudentid.setText("");
                tfstudentname.setText("");
            }
            });
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        bupdate2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                pupdate.add(lstudentinfo);            pupdate.add(lfirstName);
                pupdate.add(lfatherName);             pupdate.add(lyear);
                pupdate.add(lgender);                 pupdate.add(lbirthPlace);
                pupdate.add(lbirthDate);              pupdate.add(lphone);
                pupdate.add(lemergencyAdres);         pupdate.add(lemergencyContact);
                pupdate.add(lemergencyNumber);        pupdate.add(lstudentType);
                pupdate.add(lstudentType2);           pupdate.add(chkstudentType2);
                pupdate.add(tffirstName);             pupdate.add(tffatherName);
                pupdate.add(tfyear);                  pupdate.add(rbmale);                   pupdate.add(rbfemale);
                pupdate.add(tfbirthDate1);            pupdate.add(tfbirthDate2);             pupdate.add(tfbirthDate3);
                pupdate.add(tfbirthPlace);            pupdate.add(tfphone);
                pupdate.add(tfEmergencyAdres);        pupdate.add(tfEmergencyContact);
                pupdate.add(tfEmergencyNumber);       pupdate.add(chkstudentType);
                pupdate.add(ldepartment);             pupdate.add(ldormnumber);
                pupdate.add(tfdepartment);            pupdate.add(tfdormnumber);
                pupdate.add(lstream);                 pupdate.add(chkstream);
                pupdate.add(bcheckavailability);      pupdate.add(lemrgencyrelation);
                pupdate.add(chkemrgencyrelation);     pupdate.add(bsubmit2);
                pupdate.add(bclose);                  pupdate.add(labelimage);
                pupdate.add(spavailable);             pupdate.add(spavailable2);
                pcheckstudent.setVisible(false);
                pupdate.setVisible(true);
                bupdate2.setEnabled(false);
                bwithdraw2.setEnabled(false);
                bregistor2.setEnabled(false);
                try{
                    String student_id=tfstudentid.getText();
                    String registorselect ="Select * from REGISTOR_STUDENT where STU_ID = '"+student_id+"'";
                    pstmt=con.openConnection().prepareStatement(registorselect);
                    rs =pstmt.executeQuery();
                    if(rs.next()) {
                        String firstname = rs.getString("STU_FIRST_NAME");
                        String fathername = rs.getString("STU_FATHER_NAME");
                        int year = rs.getInt("STU_YEAR");
                        String gender = rs.getString("STU_GENDER");
                        String birthdate = rs.getString("STU_BIRTHDATE");
                        String birthplace = rs.getString("STU_BIRTHPLACE");
                        int phone = rs.getInt("STU_PHONE_NUMBER");
                        String emergency = rs.getString("STU_EMERGENCY");
                        int emrgencyphone = rs.getInt("STU_EMERGENCY_PHONE");
                        String emergencyrelation =rs.getString("STU_EMERGENCY_RELATION");
                        String studenttype = rs.getString("STU_TYPE");
                        String studenttype2 = rs.getString("STU_TYPE2");
                        String department = rs.getString("STU_DEPARTMENT");
                        int dormnumber = rs.getInt("STU_DORM_NO");
                        String emergencyaddrss = rs.getString("STU_EMEREGENCY_ADDRESS");
                        String stream = rs.getString("STU_STREAM");

                        tffirstName.setText(firstname);
                        tffatherName.setText(fathername);
                        tfyear.setText(String.valueOf(year));
                        if (gender=="female") {rbfemale.setSelected(true);}
                        else {rbmale.setSelected(true);}
                        String[] bd =birthdate.split("-");
                        tfbirthDate1.setText(bd[0]);
                        tfbirthDate2.setText(bd[1]);
                        tfbirthDate3.setText(bd[2]);
                        tfbirthPlace.setText(birthplace);
                        tfphone.setText(String.valueOf(phone));
                        tfEmergencyContact.setText(emergency);
                        tfEmergencyNumber.setText(String.valueOf(emrgencyphone));
                        int er=isStringInComboBox(emergencyrelation, chkemrgencyrelation);
                        chkemrgencyrelation.setSelectedIndex(er);
                        int st=isStringInComboBox(studenttype, chkstudentType);
                        chkstudentType.setSelectedIndex(st);
                        int st2=isStringInComboBox(studenttype2, chkstudentType2);
                        chkstudentType2.setSelectedIndex(st2);
                        int str=isStringInComboBox(stream, chkstream);
                        chkstream.setSelectedIndex(str);
                        tfEmergencyAdres.setText(emergencyaddrss);
                        tfdormnumber.setText(String.valueOf(dormnumber));
                        tfdepartment.setText(department);
                    }
                }catch(SQLException ee)
                {
                    System.out.println(ee);
                }
            }
        });
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        benrolledstudent.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        bupdate2.setVisible(false);
        bwithdraw2.setVisible(false);
        bregistor2.setVisible(false);
        pcheckstudent.setVisible(false);
        pwithdraw.setVisible(false);
        pregistor.setVisible(false);
        pabsentstud.setVisible(false);
        pupdate.setVisible(false);
        labelimage1.setVisible(false);
        penrolledstud.setVisible(true);
        pupdate.setVisible(false);
        try
        {


//////////////////table
                tavailable1=new JTable();
                spavailable1=new JScrollPane(tavailable1);
                spavailable1.setBounds(0,0,1050,648);
                String sql1="Select * from REGISTOR_STUDENT ";
                pstmt3=con.openConnection().prepareStatement(sql1);
                rs1=pstmt3.executeQuery();
                rsmd=rs1.getMetaData();
                dtm=(DefaultTableModel)tavailable1.getModel();
                int cols=rsmd.getColumnCount();
                String[] colName=new String[cols];

                for(int i=0;i<cols;i++)
                {
                    colName[i]=rsmd.getColumnName(i+1);
                    dtm.setColumnIdentifiers(colName);
                    String c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17;
                    while(rs1.next())
                    {
                        c1=rs1.getString(1);
                        c2=rs1.getString(2);
                        c3=rs1.getString(3);
                        c4=rs1.getString(4);
                        c5=rs1.getString(5);
                        c6=rs1.getString(6);
                        c7=rs1.getString(7);
                        c8=rs1.getString(8);
                        c9=rs1.getString(9);
                        c10=rs1.getString(10);
                        c11=rs1.getString(11);
                        c12=rs1.getString(12);
                        c13=rs1.getString(13);
                        c14=rs1.getString(14);
                        c15=rs1.getString(15);
                        c16=rs1.getString(16);
                        c17=rs1.getString(17);
                        String[] row={c1,c2,c3,c4,c5,c6,c7,c8,c9,c10,c11,c12,c13,c14,c15,c16,c17};
                        dtm.addRow(row);
                    }
                }

                } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
        penrolledstud.add(spavailable1);
    }
    });
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        babsentstudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bupdate2.setVisible(false);
                bwithdraw2.setVisible(false);
                bregistor2.setVisible(false);
                pcheckstudent.setVisible(false);
                pwithdraw.setVisible(false);
                pregistor.setVisible(false);
                labelimage1.setVisible(false);
                pabsentstud.setVisible(false);
                pupdate.setVisible(false);
                penrolledstud.setVisible(false);
                pwithdrewstud.setVisible(false);
                pabsentstud.setVisible(true);
                pupdate.setVisible(false);
                try
                {
//////////////////table
                    tavailable1=new JTable();
                    spavailable1=new JScrollPane(tavailable1);
                    spavailable1.setBounds(0,0,1050,648);
                    String sql1="Select * from CHECK_LIST ";
                    pstmt3=con.openConnection().prepareStatement(sql1);
                    rs3=pstmt3.executeQuery();
                    rsmd=rs3.getMetaData();
                    dtm=(DefaultTableModel)tavailable1.getModel();
                    int cols=rsmd.getColumnCount();
                    String[] colName=new String[cols];

                    for(int i=0;i<cols;i++)
                    {
                        colName[i]=rsmd.getColumnName(i+1);
                        dtm.setColumnIdentifiers(colName);
                        String c1,c2,c3,c4,c5,c6;
                        while(rs3.next())
                        {
                            c1=rs3.getString(1);
                            c2=rs3.getString(2);
                            c3=rs3.getString(3);
                            c4=rs3.getString(4);
                            c5=rs3.getString(5);
                            c6=rs3.getString(6);
                            String[] row={c1,c2,c3,c4,c5,c6};
                            dtm.addRow(row);
                        }
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                pabsentstud.add(spavailable1);
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        bwithdrawstudent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                bupdate2.setVisible(false);
                bwithdraw2.setVisible(false);
                bregistor2.setVisible(false);
                pcheckstudent.setVisible(false);
                pwithdraw.setVisible(false);
                pregistor.setVisible(false);
                pabsentstud.setVisible(false);
                pupdate.setVisible(false);
                labelimage1.setVisible(false);
                penrolledstud.setVisible(false);
                pabsentstud.setVisible(false);
                pwithdrewstud.setVisible(true);
                pupdate.setVisible(false);
                try
                {
//////////////////table
                    tavailable1=new JTable();
                    spavailable1=new JScrollPane(tavailable1);
                    spavailable1.setBounds(0,0,1050,648);
                    String sql1="Select * from WITHDRAW ";
                    pstmt3=con.openConnection().prepareStatement(sql1);
                    rs1=pstmt3.executeQuery();
                    rsmd=rs1.getMetaData();
                    dtm=(DefaultTableModel)tavailable1.getModel();
                    int cols=rsmd.getColumnCount();
                    String[] colName=new String[cols];

                    for(int i=0;i<cols;i++)
                    {
                        colName[i]=rsmd.getColumnName(i+1);
                        dtm.setColumnIdentifiers(colName);
                        String c1,c2,c3,c4,c5,c6;
                        while(rs1.next())
                        {
                            c1=rs1.getString(1);
                            c2=rs1.getString(2);
                            c3=rs1.getString(3);
                            c4=rs1.getString(4);
                            c5=rs1.getString(5);
                            c6=rs1.getString(6);
                            String[] row={c1,c2,c3,c4,c5,c6};
                            dtm.addRow(row);
                        }
                    }

                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                pwithdrewstud.add(spavailable1);
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        bcheckstudent2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String student_id = tfstudentid.getText();
                String student_name = (tfstudentname.getText()).toLowerCase();
                try {
                    String sqlaten = "Select * from ATTENDANCE WHERE STUDENT_ID='" + student_id + "' and STUDENT_NAME='" + student_name + "'";
                    PreparedStatement ps = con.openConnection().prepareStatement(sqlaten);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        bwelcome.setVisible(true);
                        bwelcome.setText("Welcome : " + student_name);
                        bstudentnotfound.setVisible(false);
                        battendance2.setEnabled(true);


                        labelimage1.setVisible(true);
                        //image
                        try {
                            labelimage1.setIcon(null);
                        } catch (Exception eima) {
                            eima.getMessage();
                        }
                        String imagesql = "SELECT IMAGE_DATA FROM IMAGE_TABLE WHERE IMAGE_ID ='" + student_id + "'";
                        PreparedStatement preparedStatement = con.openConnection().prepareStatement(imagesql);
                        ResultSet resultSet = preparedStatement.executeQuery();
                        if (resultSet.next()) {
                            System.out.println("image part");
                            byte[] imageData = resultSet.getBytes("IMAGE_DATA");
                            ByteArrayInputStream bis = new ByteArrayInputStream(imageData);
                            Image image = ImageIO.read(bis);
                            ImageIcon icon1 = new ImageIcon(image);
                            labelimage1.setIcon(icon1);
                        } else {
                            try {
                                ImageIcon icon = new ImageIcon(getClass().getResource("noimage.png"));
                                labelimage1.setIcon(icon);
                            } catch (Exception ei) {
                                System.out.println("image not found not found");
                            }
                        }
                        labelimage1.setBounds(500, 10, 300, 260);

                        //end
                    } else {
                        bstudentnotfound.setVisible(true);
                        battendance2.setEnabled(false);
                        bwelcome.setVisible(false);
                    }
                } catch (SQLException ex) {
                    System.out.println(ex);
                    System.out.println("SQLException has occurred");
                } catch (IOException exx) {
                    exx.getMessage();
                    System.out.println("IOException has occurred");
                }
            }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        battendance2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Date thistime = new Date();
                String date = String.valueOf(thistime);
                String[] day = date.split(" ");
                String today = day[0] + "_" + day[1] + "_" + day[2] + "_" + day[5];
                today=today.toUpperCase();
                String student_id = tfstudentid.getText();
                String student_name = (tfstudentname.getText()).toLowerCase();
                String present = "present";
                String s=null;
        try{
                    String sqlaten = "Select * from ATTENDANCE WHERE STUDENT_ID='"+student_id+"' and STUDENT_NAME='"+student_name+"'";
                    PreparedStatement ps = con.openConnection().prepareStatement(sqlaten);
                    ResultSet rs = ps.executeQuery();
                    rsmd1 = rs.getMetaData();
                    int colus = rsmd1.getColumnCount();
                    String coluName = rsmd1.getColumnName(colus);
                            int x=today.length();
                            int y=coluName.length();
                            System.out.println(today+" "+x);
                            System.out.println(coluName+" "+y);
                    if(rs.next()) {
                        s = rs.getString(coluName);
                    }
                    else {
                        System.out.println("Error while select rs.next()");
                    }
                 if (!today.equals(coluName)) {
                        try {
                            String sqlalter = "ALTER TABLE ATTENDANCE ADD " + today + " VARCHAR2(100) DEFAULT 'absent'";
                            PreparedStatement pralter = con.openConnection().prepareStatement(sqlalter);
                            int alt = pralter.executeUpdate();
                            if (alt > 0) {
                                System.out.println("Attendance tabe altered");
                            }
                            } catch (Exception exc) {
                            System.out.println("Exception has occured on the alter");
                                 }
                            }
        } catch (Exception exc) {
            System.out.println("Exception has occured on the select");
        }
                if (!s.equals(present )) {
                    try {
                        String sqlupdate = "UPDATE  ATTENDANCE  SET " + today + "  = ? WHERE STUDENT_ID = ? AND STUDENT_NAME = ?";
                        PreparedStatement prupdate = con.openConnection().prepareStatement(sqlupdate);
                        prupdate.setString(1, present);
                        prupdate.setString(2, student_id);
                        prupdate.setString(3, student_name);
                        int upd = prupdate.executeUpdate();
                        if (upd > 0) {
                            System.out.println("attendance taken successfully");
                            JOptionPane.showMessageDialog(null, "Attendance Taken successfully");
                            battendance2.setEnabled(false);
                        }
                    } catch (Exception ex) {
                        System.out.println("Exception in the update part");
                    }
                }
                else {
                    JOptionPane.showMessageDialog(null, "Attendance already taken");
                }
                }
        });
///////////////////////////////////////////////////////////////////////////////////////////////////////////////
        battendance.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                pcheckstudent.setVisible(true);
                bwithdraw2.setVisible(false);
                bupdate2.setVisible(false);
                bregistor2.setVisible(true);
                pwithdraw.setVisible(false);
                pregistor.setVisible(false);
                pabsentstud.setVisible(false);
                pupdate.setVisible(false);
                penrolledstud.setVisible(false);
                pabsentstud.setVisible(false);
                pwithdrewstud.setVisible(false);
                bregistor2.setVisible(false);
                bwithdraw2.setVisible(false);
                bupdate2.setVisible(false);
                battendance2.setVisible(true);
                labelimage1.setVisible(false);
                pcheckstudent.remove(bcheckstudent1);
                pcheckstudent.remove(bcheckstudent);
                pcheckstudent.add(bcheckstudent2);
            }
        });


        tfdormnumber.setEditable(false);
        fr.add(bhome);                      fr.add(blogout);
        fr.add(pchoice);                    fr.add(bexit);
        fr.add(pcheckstudent);
        fr.add(pregistor);                   pregistor.setVisible(false);
        fr.add(pwithdraw);                   pwithdraw.setVisible(false);
        fr.add(pabsentstud);                 pabsentstud.setVisible(false);
        fr.add(penrolledstud);               penrolledstud.setVisible(false);
        fr.add(pwithdrewstud);               pwithdrewstud.setVisible(false);
        fr.add(pupdate);                     pupdate.setVisible(false);
        fr.add(il1);

        pregistor.setLayout(null);
        pupdate.setLayout(null);
        pwithdraw.setLayout(null);
        penrolledstud.setLayout(null);
        pwithdrewstud.setLayout(null);
        pabsentstud.setLayout(null);
        pcheckstudent.setOpaque(false);
        pcheckstudent.setLayout(null);
        pchoice.setOpaque(false);
        pchoice.setLayout(new FlowLayout(FlowLayout.LEFT,60,20));

        fr.setExtendedState(JFrame.MAXIMIZED_BOTH);
        fr.setLayout(null);
        fr.setVisible(true);
        pcheckstudent.setVisible(false);
        bregistor2.setEnabled(false);
        bwithdraw2.setEnabled(false);
        bupdate2.setEnabled(false);
        fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fr.setResizable(false);

    }

    public static void main(String[] args) {
        new checkin();
    }}
