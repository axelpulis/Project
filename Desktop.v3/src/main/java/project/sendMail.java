package project;

        
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.swing.table.DefaultTableModel;
  
public class sendMail {  
  public static void mail(int id) {      
  try {  
                      
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_db", "root", "");
            Statement stmt = con.createStatement();        
            
            ResultSet rs = stmt.executeQuery("SELECT client.name,client.surname, booking.start_date FROM booking INNER JOIN client ON booking.client_id = client.client_id where booking.booking_id = "+id+"");
            String name="";
            String time= "";
            while (rs.next()) {
              name = rs.getString(1)+" "+rs.getString(2);
              time = rs.getTime(3).toString();
            }          
            
            
           
           
                    
                    String host="smtp.office365.com";  
                    final String user="axel.pulis.a100872@mcast.edu.mt";//change accordingly  
                    final String password="Mcast6110";//change accordingly  

                    String to="axel.pulis.a100872@mcast.edu.mt";//change accordingly  

                     //Get the session object  
                     Properties props = new Properties();  

                      props.put("mail.smtp.auth", "true");
                      props.put("mail.smtp.host", host);
                      props.put("mail.smtp.port", "587");
                      props.put("mail.smtp.starttsl.enable","true");
                      props.put("mail.smtp.auth", "true"); 

                     Session session = Session.getDefaultInstance(props,  
                      new javax.mail.Authenticator() {  
                        protected PasswordAuthentication getPasswordAuthentication() {  
                      return new PasswordAuthentication(user,password);  
                        }  
                      });  

                     //Compose the message  
                      try {  
                       MimeMessage message = new MimeMessage(session);  
                       message.setFrom(new InternetAddress(user));  
                       message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
                       message.setSubject("Shave Cave - Reminder");  
                       message.setText("Hi "+name+" see you soon at "+time+" at ShaveCave");  

                      //send the message  
                       Transport.send(message);  

                       System.out.println("message sent successfully...");  

                       } catch (MessagingException e) {e.printStackTrace();}  }

    catch (Exception e) {
            System.out.println("Query Failed");}  
 }  
}  