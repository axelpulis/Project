<?php
    session_start();
$_SESSION['bookingS']=0;

?>
<!doctype html>
<html lang="en">
  <head>
    <link rel="stylesheet" type="text/css" href="css/style.css">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">   
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
    
    <title>ShaveCave - Home</title>
  </head>
  <body>
    <?php
   
      
     include ('dbcon.php');
?>
    
    
    
    
    
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" role="navigation">
    <div class="container">
        <a class="navbar-brand" href="index.php"><img src="images/logo.png" height="50px"></a>
        <button class="navbar-toggler border-0" type="button" data-toggle="collapse" data-target="#exCollapsingNavbar">
            &#9776;
        </button>
        <div class="collapse navbar-collapse" id="exCollapsingNavbar">
            <ul class="nav navbar-nav">
                
            <?php
               if (!isset($_SESSION['username'])){    
                echo "<li class='nav-item'><a href='register.php' class='nav-link'>Register</a></li>";
               }
                else
                {
                 echo "<li class='nav-item'><a href='viewbookings.php' class='nav-link'>My Appointments</a></li>";
                echo "<li class='nav-item'><a href='bookings.php' class='nav-link'>Make Appointment</a></li>";
                }
                ?>
                <li class="nav-item"><a href="aboutus.php" class="nav-link">About</a></li>
                <li class="nav-item"><a href="gallery.php" class="nav-link">Gallery</a></li>
                <li class="nav-item"><a href="contactus.php" class="nav-link">Contact</a></li>
            </ul>
            <ul class="nav navbar-nav flex-row justify-content-between ml-auto">
                <li class="nav-item order-2 order-md-1"><a href="#" class="nav-link" title="settings"><i class="fa fa-cog fa-fw fa-lg"></i></a></li>
                <li class="dropdown order-1">
            <?php
            if (isset($_SESSION['username']))
            {
                $user = $_SESSION['username'];
                echo "<button onclick='location.href = \"logout.php\"' type='button' id='dropdownMenu1'  class='btn btn-outline-secondary'> <strong>$user</strong> Log-Out</button>";
            }
            else{
            ?>
            
                    <button type="button" id="dropdownMenu1" data-toggle="dropdown" class="btn btn-outline-secondary dropdown-toggle">Login <span class="caret"></span></button>
                    <ul class="dropdown-menu dropdown-menu-right mt-2">
                       <li class="px-3 py-2">
                           <form class="form" role="form" action="login.php" method="post">
                                <div class="form-group">
                                    <input id="emailInput" placeholder="Username" name="username" class="form-control form-control-sm" type="text" required="">
                                </div>
                                <div class="form-group">
                                    <input id="passwordInput" placeholder="Password" name="password" class="form-control form-control-sm" type="password" required="">
                                </div>
                                <div class="form-group">
                                    <button type="submit"  name ="submit" value="login" class="btn btn-primary btn-block">Login</button>
                                </div>
                                <div class="form-group text-center">
                                    <small><a href="#" data-toggle="modal" data-target="#modalPassword">Forgot password?</a></small>
                                </div>
                            </form>
                        </li>
                    </ul>
                </li>
            </ul>





<div id="modalPassword" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h3>Forgot password</h3>
                <button type="button" class="close font-weight-light" data-dismiss="modal" aria-hidden="true">×</button>
            </div>
            <div class="modal-body">
                <p>Reset your password..</p>
            </div>
            <div class="modal-footer">
                <button class="btn" data-dismiss="modal" aria-hidden="true">Close</button>
                <button class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>

            <?php
            }
            ?>
               
        </div>
    </div>
</nav>


            
             
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
   
    
    
    
    
    <div class="container">
    
     
     
     <br><br><br><br>
     <center><img height="300px" src="images/logoc.png"></center>
     <br>
     <div class="jumbotron" style="background-color:#f8f9fa85;">
     <h1 class="display-4">Welcome to ShaveCave</h1><br>   
    <p class='lead'>At ShaveCave, our only objective is to make sure that you get the best grooming experience you have ever had. We invite men and boys of all ages to come and enjoy the lost art of the barber shop - the conversation, the periodicals, the great service and the advice of a real professional barber. Each guest may select a complimentary beverage, a shampoo and his choice of clipper or scissor cut. Facial shaves, head shaves, facials and hair coloring are also available. We also offer a complete line of grooming products and gift cards for special holidays and events. We look forward to seeing you at the shop.</p>
       <?php
        
           if (isset($_SESSION['username']))
            {
               
               echo "<a id='buttonstyle' href='bookings.php'><button  type='button' class='btn btn-primary btn-lg btn-block'>Book Now</button></a>";
           }
         else
         {     echo "<a id='buttonstyle' href='register.php'><button  type='button' class='btn btn-primary btn-lg btn-block'>Register</button></a>";}
         ?>
   
   </div>
    </div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="css/style.css">
  </body>
</html>