
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Axel
 */
public class dailyBookings extends javax.swing.JFrame {

    /**
     * Creates new form dailyBookings
     */
    
    public String[] getAllInfo(int id)
    {
        try {  
                      
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "");
            Statement stmt = con.createStatement();
            
            String[] a = new String[10];
            ResultSet rs = stmt.executeQuery("SELECT client.name, client.surname, account.email, client.mobile, town.name, booking.booking_id, service.type, service.Duration, service.price , barber.name FROM booking INNER JOIN client ON booking.client_id = client.client_id INNER JOIN town ON client.town_id = town.town_id INNER JOIN barber ON booking.barber_id = barber.barber_id INNER JOIN service ON booking.service_id = service.service_id INNER JOIN account ON client.account_id = account.account_id where booking.booking_id = "+id+"");
            while (rs.next()) {
             a[0] = rs.getString(1);
             a[1] = rs.getString(2);
            a[2] = rs.getString(3);
             a[3] = Integer.toString(rs.getInt(4));
             a[4] = rs.getString(5);
            a[5] = Integer.toString(rs.getInt(6));
            a[6] = rs.getString(7);
            a[7] = rs.getTime(8).toString();
             a[8] = Double.toString(rs.getDouble(9));
             a[9] = rs.getString(10);
             
             
             
                     
            }
             
            con.close();
             return a;} 
        
        catch (Exception e) {
            System.out.println("Query Failed");
        return null;
        }   
        
        
        
    }
    
    
    
    public void deleteAllRows( DefaultTableModel model )
{
for( int i = model.getRowCount() - 1; i >= 0; i-- )
{
model.removeRow(i);
}
}
    public void refreshBookings(){
    try {
            
            LocalDate localDate = LocalDate.now();
            String date = (DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate));
             
            
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/project_db", "root", "");
            Statement stmt = con.createStatement();
            
            
            
            ResultSet rs = stmt.executeQuery("SELECT booking.start_date, client.name,client.surname,client.mobile ,service.type,service.price,barber.name,booking.booking_id FROM booking INNER JOIN client ON booking.client_id = client.client_id INNER JOIN service ON booking.service_id = service.service_id INNER JOIN barber on booking.barber_id = barber.barber_id where booking.start_date BETWEEN \""+date+"\" AND \""+date+" 23:00:00\" ORDER BY start_date ASC");
            DefaultTableModel m = (DefaultTableModel)dailyBookings.getModel();
            deleteAllRows(m);
            while (rs.next()) {              
                m.addRow(new Object[]{rs.getTime(1),rs.getString(2)+" "+rs.getString(3),rs.getInt(4),rs.getString(5),rs.getDouble(6),rs.getString(7),rs.getInt(8)}); }
            con.close();
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,"Query Failed");
}
    }
    
    
    
    public dailyBookings() {
        initComponents();
        refreshBookings();
        
        
        
        

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        dailyBookings = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        dailyBookings.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Time", "Client", "Mobile", "Service", "Price", "Barber", "ID"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(dailyBookings);
        if (dailyBookings.getColumnModel().getColumnCount() > 0) {
            dailyBookings.getColumnModel().getColumn(0).setResizable(false);
            dailyBookings.getColumnModel().getColumn(1).setResizable(false);
            dailyBookings.getColumnModel().getColumn(2).setResizable(false);
            dailyBookings.getColumnModel().getColumn(3).setResizable(false);
            dailyBookings.getColumnModel().getColumn(4).setResizable(false);
            dailyBookings.getColumnModel().getColumn(5).setResizable(false);
            dailyBookings.getColumnModel().getColumn(6).setResizable(false);
        }

        jButton1.setText("Update Daily Bookings");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Dashboard");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Print Invoice");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(261, 261, 261)
                        .addComponent(jButton3)))
                .addContainerGap(101, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jButton2)
                .addGap(28, 28, 28)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jButton3)
                .addContainerGap(56, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        refreshBookings();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
              dashboard frame = new dashboard();
             frame.setVisible(true);
             this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
            try{int selectedRow = dailyBookings.getSelectedRow();
             selectedRow = dailyBookings.convertRowIndexToModel(selectedRow);
             int bookingId = Integer.parseInt(dailyBookings.getModel().getValueAt(selectedRow, 6).toString());
                     
            
            try {
             
            
            
            printPdf.getPdf(Integer.toString(bookingId),"axel","pulis",getAllInfo(bookingId));
        } catch (IOException ex) {
            Logger.getLogger(dailyBookings.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            
            
            
            }
            catch (ArrayIndexOutOfBoundsException ex)
            {
                System.out.println(ex);
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
            java.util.logging.Logger.getLogger(dailyBookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(dailyBookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(dailyBookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(dailyBookings.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new dailyBookings().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable dailyBookings;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
