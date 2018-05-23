<?php
    session_start();
    ob_start();
   
?>

<!doctype html>
<html lang="en">
    <head>
       <script src='https://maps.googleapis.com/maps/api/js?v=3.exp&key=AIzaSyA8RQ_B8uMj4XzYUAt4SG1dHiU6I-CyhZg'></script> 
       <script type='text/javascript' src='https://embedmaps.com/google-maps-authorization/script.js?id=ade823447879c8bc6367294e6c6344050987ee6c'></script>
      <script type='text/javascript'>function init_map(){
            
            var myOptions = {disableDefaultUI: true,zoom:15,center:new google.maps.LatLng(35.861523710072674,14.489456676196255),mapTypeId: google.maps.MapTypeId.ROADMAP};map = new google.maps.Map(document.getElementById('gmap_canvas'), myOptions);marker = new google.maps.Marker({map: map,position: new google.maps.LatLng(35.861523710072674,14.489456676196255)});infowindow = new google.maps.InfoWindow({content:'<strong>ShaveCave</strong><br>9 Ta April<br> Luqa<br>'});google.maps.event.addListener(marker, 'click', function(){infowindow.open(map,marker);});infowindow.open(map,marker);}google.maps.event.addDomListener(window, 'load', init_map);</script>
        
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">   
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
        <title>ShaveCave - Contact</title>
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


<br><br><br><br>
    
    
    
    
    
    
    
   
    
    
    
    
    <div class="container">
    <?php
        if(!isset($_GET['sent']))
        {
           
        }
        else if($_GET['sent']==1){
         echo "<div class='alert alert-success' role='alert'>Sent</div>";
          }
        else if($_GET['sent']==0)
        {
           echo "<div class='alert alert-danger' role='alert'>Failed to Send</div>";  
        }
        
       
        ?>
     <div class="jumbotron" style="background-color:#f8f9fa85;">
     <h1 class="display-4">Contact Us</h1><br>   
     
    <div style='overflow:hidden;height:400px;width:100%;'><div id='gmap_canvas' style='height:400px;width:100%;'></div><style>#gmap_canvas img{max-width:none!important;background:none!important}</style></div> <a href='https://embedmaps.net'><br></a>   <br> 
    <form action="emailphp.php" method="post">
         <div class="form-row">
    <div class="form-group col-md-6">
      <input type='text' class='form-control' id='inputName' placeholder='Name' name='name' required>     
    </div>
    <div class="form-group col-md-6">
      <input type='text' class='form-control' id='inputSurame' placeholder='Surname' name='surname' required>       
    </div>
    
  </div>
   <div class="form-row">
     <div class="form-group col-md-6">
      
      <input type="email" class="form-control" id="inputEmail" placeholder="Email" name="email" required>
    </div>
    <div class="form-group col-md-6">
      
      <input type="text" pattern="\d*" class="form-control" id="inputMobile" placeholder="Mobile" name="mobile" required maxlength="8">
    </div>
    </div>
      <textarea class="form-control" name="message" rows="5" id="comment" placeholder="Message...." required></textarea>
      <br>
      <button type="submit" class="btn btn-primary btn-block" name="contactSubmit">Send</button>
     </form>
    
   
           
           
           
           
          
   
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