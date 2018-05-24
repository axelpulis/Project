/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;


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
    
    public void insertBooking(String fulldate)
    {
       
        try{
        int bookingId = Integer.parseInt(txtId.getText());
        int clientId = Integer.parseInt(txtClient.getText());
        int serviceId = cBoxService.getSelectedIndex();
        int barberId = cBoxBarber.getSelectedIndex();
        System.out.println(fulldate);
        
        
         Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "");
            Statement stmt = con.createStatement();
            
           
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM booking WHERE booking_id = "+bookingId+"");
            int rows = 0;
            while (rs.next()) {
             rows = rs.getInt(1);
             
                     
            }
            if(rows==0)
            {
                System.out.println(serviceId);
            rs = stmt.executeQuery("SELECT Duration FROM service WHERE service_id = '"+serviceId+"'");
            
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
            
            }
            
            if(isNumeric(txtId.getText()))
            {
            }
            else
            {
              System.out.println("Booking id not valid");  
            }
            
            }
            else
            {
                System.out.println("Booking already exists");
                
            }
            
             
            con.close();}
           
        
        catch (Exception e) {
            System.out.println("Query Failed");
       
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
           lblDate.setText("Opening Days: Tuesday - Saturday");
           lblTime.setText("Opening Hours: 09:00 - 19:00");
       }
       
       else if(cBoxBarber.getSelectedIndex()==0 || cBoxService.getSelectedIndex()==0 || txtId.getText().equals("") ){
       
        System.out.println("Missing Values");
       
       
       }
       else if(isNumeric(txtClient.getText()))
       {
           System.out.println("numbers");
           String sqlDate = dateF.format(txtDate.getDate());
           insertBooking(sqlDate);
       }
       else
       {
           System.out.println("not numbers");
       }
       
       
       }
       
        
        catch(NullPointerException ex){
           System.out.println("error");
       }
   
  
   }
   
    public manageBookings() {
        initComponents();
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

        jPanel1 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
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
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cBoxBarber = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel5)
                .addContainerGap(538, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(167, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(112, 112, 112))
        );

        txtDate.getJCalendar().setMinSelectableDate(new Date());
        txtDate.setDateFormatString("yyyy-MM-dd");

        JSpinner.DateEditor de = new JSpinner.DateEditor(txtTime, "HH:mm");
        txtTime.setEditor(de);

        lblDate.setText("jLabel1");

        lblTime.setText("jLabel8");

        cBoxService.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Service" }));
        cBoxService.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cBoxServiceActionPerformed(evt);
            }
        });

        jLabel2.setText("Id");

        jLabel3.setText("Start date");

        txtId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdActionPerformed(evt);
            }
        });

        jLabel6.setText("client id ");

        jLabel4.setText("service type");

        jLabel7.setText("barber name");

        cBoxBarber.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Barber" }));

        jButton1.setText("jButton1");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblTime)
                .addGap(200, 200, 200))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addContainerGap()
                                    .addComponent(jLabel2)
                                    .addGap(26, 26, 26))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(4, 4, 4)
                                            .addComponent(jLabel3))
                                        .addGroup(layout.createSequentialGroup()
                                            .addContainerGap()
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel6))))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtClient, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cBoxService, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(jLabel7)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cBoxBarber, 0, 149, Short.MAX_VALUE))
                            .addGap(84, 84, 84)
                            .addComponent(lblDate))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cBoxService, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(lblTime)
                        .addGap(3, 3, 3)
                        .addComponent(lblDate))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(cBoxBarber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        checkValid();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cBoxServiceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cBoxServiceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cBoxServiceActionPerformed

    private void txtIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdActionPerformed

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
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblDate;
    private javax.swing.JLabel lblTime;
    private javax.swing.JTextField txtClient;
    private com.toedter.calendar.JDateChooser txtDate;
    private javax.swing.JTextField txtId;
    private javax.swing.JSpinner txtTime;
    // End of variables declaration//GEN-END:variables
}
