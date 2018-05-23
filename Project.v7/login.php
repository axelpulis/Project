<?php
    session_start();

    if (isset($_SESSION['username']))
    {
       header("locaton:index.php");
    }
    else
    {
        if(isset($_POST['submit']))
        {
            $username = $_POST['username'];
            $password = $_POST['password'];
            
            
            if(empty($username) || empty($password))
            {
                header("location:index.php?error=1");
            }
            else
            {
                include ('dbcon.php');

                $sql = "SELECT * FROM account WHERE username='$username' AND password = '$password'";

                $result = mysqli_query($conn,$sql) or die(mysqli_error($conn));

                if(mysqli_num_rows($result)==1)
                {
                    $get_client_id = "SELECT client.client_id, account.username FROM client INNER JOIN account ON client.account_id = account.account_id WHERE username = '$username'";
                    $result = mysqli_query($conn, $get_client_id);
                    
                 if ($result)
                 {
                     $array = mysqli_fetch_assoc($result);
                     $_SESSION['clientid'] = $array['client_id'];                     
                 }  
                    
                    $_SESSION['username'] = $username;
                    
                    
                    
                    
                    header("location:index.php?login=1");                
                }
                else
                {
                    header("location:index.php?login=0");
                }

            }
        }
    }
?>

