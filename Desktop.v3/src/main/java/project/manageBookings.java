/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;


import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

/**
 *
 * @author Axel
 */
public class manageBookings extends javax.swing.JFrame {

    public void deleteBooking()
    {
       if(isNumeric(txtId.getText()))
       {
        try {               
            
            if (bookingIdCheck(Integer.parseInt(txtId.getText()))){
            int id = Integer.parseInt(txtId.getText());
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/project_db", "root", "");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("DELETE FROM booking WHERE booking_id = "+id+"");            
            con.close();
            txtError.setText("Record successfully deleted");
            
            }
            else
            {
                txtError.setText("Error: Booking Not Found");
            }
        } catch (Exception e) {
            System.out.println("Query Failed" +e);
        }
       }
       else
       {
           txtError.setText("Error: Invalid ID input");
       }

        
    }
    
    
    public boolean bookingIdCheck(int id)
    {
         try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "");
            Statement stmt = con.createStatement();
            
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM `booking` WHERE `booking_id` = "+id+"");
            int rows = 0;
            while (rs.next()) {
                rows = rs.getInt(1);         
                     
            }
            if (rows==0)
               {
                   System.out.println("Found");               
                   con.close();
                   return false;
               }
            else
            {
                 System.out.println("not found");
                con.close();
                return true;
            }
            
       }
       catch(Exception e){
           System.out.println(e);
       return true;
       }
    }
           
    
    
    public boolean bookingExists(String date1)
    {
       try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "");
            Statement stmt = con.createStatement();
            
            
            ResultSet rs = stmt.executeQuery("SELECT * FROM booking WHERE '"+date1+"' BETWEEN start_date AND end_date");
            int rows = 0;
            while (rs.next()) {
                rows = rs.getInt(1);         
                     
            }
            if (rows==0)
               {
                   txtError.setText("Booking Available");                
                   con.close();
                   return true;
               }
            else
            {
                txtError.setText("Booking Unavailable"); 
                con.close();
                return false;
            }
            
       }
       catch(Exception e){
           System.out.println(e);
       return false;
       }
    }
    
    public boolean userExists(int clientId)
    {
    try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "");
            Statement stmt = con.createStatement();
            
           
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM client WHERE client_id = "+clientId+"");
            int rows = 0;
            while (rs.next()) {
             rows = rs.getInt(1);
             
                     
            }
            if (rows==0)
               {
                   txtError.setText("Error: User ID does not exist");
                    con.close(); 
                    
                   return false;
                  
               }
            else
            {
                
               
                con.close(); 
                 return true;
            }
            
            
             
             }
    
        catch (Exception e) {
            System.out.println("Query Failed");
       return true;
                } 
        
    }
    
    
    public void testInsert(String date1, String date2,String id,int serviceId,int barberId)    {
         try {               
            if (bookingExists(date1))
            {
             Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/project_db", "root", "");           
            
                Statement stmt2= con.createStatement();
                stmt2.executeUpdate("INSERT INTO booking (booking_id, start_date, end_date, client_id, service_id, barber_id) VALUES ("+id+", '"+date1+"', '"+date2+"', "+txtClient.getText()+", "+serviceId+", "+barberId+");");
            con.close();
            txtError.setText("Booking Successfull");
            txtId.setText("");
            txtClient.setText("");
            cBoxService.setSelectedIndex(0);
            cBoxBarber.setSelectedIndex(0);
            
            }
            else
            {
                txtError.setText("Error: Booking Spot Not Available");
            }
           
        } catch (Exception e) {
            System.out.println("Query Failed"+e);
        }
    }

   
    
    public void createBooking(boolean check,String fulldate)
    {
        int clientId = Integer.parseInt(txtClient.getText());
        int serviceId = cBoxService.getSelectedIndex();
        int barberId = cBoxBarber.getSelectedIndex();
        String id ="NULL";
        try{
        if (check == true){id = txtId.getText();}        
        
        
          Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "");
          Statement stmt = con.createStatement();      
          
                System.out.println(serviceId);
           ResultSet rs = stmt.executeQuery("SELECT Duration FROM service WHERE service_id = '"+serviceId+"'");
        
        while (rs.next()) {
             SimpleDateFormat timeF = new SimpleDateFormat("HH:mm");     
             
            String Stime = fulldate+" "+timeF.format(txtTime.getValue());
            String Mtime = fulldate+" "+rs.getTime(1).toString();
            String minus = fulldate+" "+"00:00:00";
            SimpleDateFormat plusDate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            SimpleDateFormat middleDate=new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date sDate = plusDate.parse(Mtime);
            Date mDate = middleDate.parse(Stime);
            Date minusDate = plusDate.parse(minus);
            
            long sum = sDate.getTime() + mDate.getTime() - minusDate.getTime();

            Date endDate = new Date(sum);
            
            System.out.println(Stime+" "+middleDate.format(endDate));
            
            testInsert(Stime,middleDate.format(endDate),id,serviceId,barberId);
            
         }
            
           
            con.close();
        
            
        
        }
        catch(Exception e){System.out.print("query failed");}
    }
    
    
    
    public static boolean isNumeric(String str)  
{  
  try  
  {  
    double d = Double.parseDouble(str);  
  }  
  catch(NumberFormatException nfe)  
  {  
    return false;  
  }  
  return true;  
}
    
    public void insertQuery(boolean check,String fulldate)
    { 
        int bookingId = 0;
        if (check==false){
        bookingId = Integer.parseInt(txtId.getText());
        
            try{
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "");
            Statement stmt = con.createStatement();
            
           
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM booking WHERE booking_id = "+bookingId+"");
            int rows = 0;
            while (rs.next()) {
             rows = rs.getInt(1);
             System.out.print(rows);
                     
            }
            if (rows==0)
               {
                   System.out.println("Inserting with id ");
                   createBooking(true,fulldate);
               }
            else
            {
                
                txtError.setText("Error: Booking ID Already Exists");
            }
            
            
             con.close(); 
             }
        catch (Exception e) {
            System.out.println("Query Failed");
       
                } 
            
        }
        
        else
        {
            //automaticali insert
            System.out.println("Inserting without id ");
            createBooking(false,fulldate);
        }
    }
    
    
    public void insertBooking(String fulldate,int  num)
    {
       
        if (num==1)
        {
            if (txtId.getText().equals(""))
            {
                System.out.println("go ahead");
                insertQuery(true,fulldate);
            }
            else
            {
                 
                 insertQuery(false,fulldate);
                 
            }
            
        }
        else
        {
            System.out.println("Invalid Id");
        }
                
                
       
 
        
    }
    
    public void serviceBarberLoad()
   {
       try {  
                      
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "");
            Statement stmt = con.createStatement();
            
           
            ResultSet rs = stmt.executeQuery("SELECT `type` FROM `service`");
            while (rs.next()) {
             cBoxService.addItem(rs.getString(1));          
             
                     
            }
            rs = stmt.executeQuery("SELECT `name` FROM `barber`");
            while (rs.next()) {
             cBoxBarber.addItem(rs.getString(1));          
             
                     
            }
             
            con.close();}
           
        
        catch (Exception e) {
            System.out.println("Query Failed");
       
        }  
   }
    
    
    public void checkValid(){
       try{
       SimpleDateFormat dateF = new SimpleDateFormat("yyyy-MM-dd");
       SimpleDateFormat timeF = new SimpleDateFormat("HH:mm");       
       SimpleDateFormat dayF = new SimpleDateFormat ("EEE");
       
       String hour =  timeF.format(txtTime.getValue());
       hour = hour.substring(0,2);
       int hours = Integer.parseInt(hour);
       if (dayF.format(txtDate.getDate()).equals("Sun") || dayF.format(txtDate.getDate()).equals("Mon") || hours<9 || hours>18 )
       {
           txtError.setText("Error: Check Date and Time");
           
       }
       
       else if(cBoxBarber.getSelectedIndex()==0 || cBoxService.getSelectedIndex()==0 || txtClient.getText().equals("") ){
       
        txtError.setText("Error: Please Input All Fields");
       
       
       }
       else if(isNumeric(txtClient.getText()))
       {
           
           String sqlDate = dateF.format(txtDate.getDate());
           if (txtId.getText().equals("")||isNumeric(txtId.getText()))
           {
           insertBooking(sqlDate,1);
           }
           else
           {
           insertBooking(sqlDate,0);    
           }
       }
       else
       {
           System.out.println("not numbers");
       }
       
       
       }
       
        
        catch(NullPointerException ex){
           txtError.setText("Error: Invalid Date Format");
       }
   
  
   }
   
    public manageBookings() {
        initComponents();
         getContentPane().setBackground(new Color(52,58,64));
        serviceBarberLoad();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        txtDate = new com.toedter.calendar.JDateChooser();
        Date date = new Date();
        SpinnerDateModel sm =
        new SpinnerDateModel(date, null, null, Calendar.HOUR_OF_DAY);
        txtTime = new javax.swing.JSpinner(sm);
        lblDate = new javax.swing.JLabel();
        lblTime = new javax.swing.JLabel();
        txtClient = new javax.swing.JTextField();
        cBoxService = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        cBoxBarber = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        txtError = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);
        addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                formComponentAdded(evt);
            }
        });

        jPanel2.setBackground(new java.awt.Color(52, 58, 64));
        jPanel2.setForeground(new java.awt.Color(255, 255, 255));

        txtDate.getJCalendar().setMinSelectableDate(new Date());
        txtDate.setDateFormatString("yyyy-MM-dd");
        txtDate.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        JSpinner.DateEditor de = new JSpinner.DateEditor(txtTime, "HH:mm");
        txtTime.setEditor(de);
        txtTime.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        lblDate.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        lblDate.setForeground(new java.awt.Color(255, 255, 255));
        lblDate.setText("Opening Hours: 09:00 - 19:00");

        lblTime.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        lblTime.setForeground(new java.awt.Color(255, 255, 255));
        lblTime.setText("Opening Days: Tuesday - Saturday");

        txtClient.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N

        cBoxService.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        cBoxService.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SERVICE" }));
        cBoxService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cBoxServiceActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("APPOINTMENT ID");

        jLabel3.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("DATE & TIME");

        txtId.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("CLIENT ID");

        cBoxBarber.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        cBoxBarber.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "BARBER" }));

        jButton1.setBackground(new java.awt.Color(160, 122, 65));
        jButton1.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/remove.png"))); // NOI18N
        jButton1.setText("DELETE");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtError.setFont(new java.awt.Font("Trebuchet MS", 0, 14)); // NOI18N
        txtError.setForeground(new java.awt.Color(255, 255, 255));
        txtError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mini.png"))); // NOI18N

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/home.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(160, 122, 65));
        jButton3.setFont(new java.awt.Font("Trebuchet MS", 0, 18)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/add.png"))); // NOI18N
        jButton3.setText("ADD");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTime)
                                    .addComponent(lblDate)))
                            .addComponent(txtError, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cBoxBarber, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtClient, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(cBoxService, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(64, 64, 64)
                                .addComponent(jLabel1))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(27, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtTime, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDate, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lblTime)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDate)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cBoxService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cBoxBarber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(txtError, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(65, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cBoxServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cBoxServiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cBoxServiceActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

    private void formComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_formComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_formComponentAdded

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        
        deleteBooking();             
        
        
        
       
        
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dashboard frame = new dashboard();
             frame.setVisible(true);
             this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
       try{
            if(userExists(Integer.parseInt(txtClient.getText())))
            {
                checkValid();

            }

        }
        catch(NumberFormatException ex){
            txtError.setText("Error: Check Inputs For Invalid Format");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(manageBookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(manageBookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(manageBookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(manageBookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new manageBookings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cBoxBarber;
    private javax.swing.JComboBox<String> cBoxService;
    public static javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblTime;
    private javax.swing.JTextField txtClient;
    private com.toedter.calendar.JDateChooser txtDate;
    private javax.swing.JLabel txtError;
    public static javax.swing.JTextField txtId;
    private javax.swing.JSpinner txtTime;
    // End of variables declaration//GEN-END:variables
}
