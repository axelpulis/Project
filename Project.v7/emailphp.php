

   <?php
    session_start();

     require('phpmailer/PHPMailerAutoload.php'); #load the library required for phpmailer
    
    
if (isset($_POST['contactSubmit'])) {

        $email = $_POST["email"];
        $name = $_POST["name"];
        $surname = $_POST["surname"];
        $mobile = $_POST["mobile"];
        
        
        $username = 'axel.pulis.a100872@mcast.edu.mt';
        $pwd = 'Mcast6110';
        $message =  $_POST["message"];
        $emailTo = 'axel.pulis.a100872@mcast.edu.mt';
        $mail = new PHPMailer(); #create a new instance
        $mail->isSMTP(); #using SMTP
        $mail->isHtml(true);
        $mail->Host = 'smtp.office365.com';
        #$mail->SMTPDebug = 2; #include client and server messges
        $mail->Port = 587;
        $mail->SMTPSecure = 'tls';
        $mail->SMTPAuth = true;
        $mail->Username = $username;
        $mail->Password = $pwd;
       $mail->Body = "From: $email <br> To: $username <hr> Client: $name $surname <br> Mobile: $mobile <br> Message: $message";
        $mail->Subject = "ShaveCave Query - $name $surname ";
        $mail->From = $username; #sender
        $mail->AddAddress($emailTo); #recepient
        $mail->SMTPOptions = array(
    'ssl' => array(
        'verify_peer' => false,
        'verify_peer_name' => false,
        'allow_self_signed' => true
    )
);
        if (!$mail->Send()) #sending the email
        {
            
            echo "Mailer Error: ". $mail->ErrorInfo;
            header("location: contactus.php?sent=0");
        }
        else
            
            header("location: contactus.php?sent=1");
    }
    
    ?>