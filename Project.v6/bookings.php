<?php
    session_start();
    if (!isset($_SESSION['username']))
            {
    header("location: index.php");
}
    
?>

<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">   
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">
        <title>ShaveCave - Bookings</title>
    </head>
    
    <body>
    <?php
        $conn = mysqli_connect("localhost","root","","project_db",3306);
        if (mysqli_connect_errno()){
            echo "Error: Could not connect to database. Please try again later";
            exit;
        }
    ?>
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" role="navigation">
    <div class="container">
        <a class="navbar-brand" href="#">ShaveCave</a>
        <button class="navbar-toggler border-0" type="button" data-toggle="collapse" data-target="#exCollapsingNavbar">
            &#9776;
        </button>
        <div class="collapse navbar-collapse" id="exCollapsingNavbar">
            <ul class="nav navbar-nav">
                <li class="nav-item"><a href="index.php" class="nav-link">Home</a></li>
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
            </ul>
            <?php
            if (isset($_SESSION['username']))
            {
                $user = $_SESSION['username'];
                echo "<button onclick='location.href = \"logout.php\"' type='button' id='dropdownMenu1'  class='btn btn-outline-secondary'> <strong>$user</strong> Log-Out</button>";
            }
            else{
            ?>
            <ul class="nav navbar-nav flex-row justify-content-between ml-auto">
                <li class="nav-item order-2 order-md-1"><a href="#" class="nav-link" title="settings"><i class="fa fa-cog fa-fw fa-lg"></i></a></li>
                <li class="dropdown order-1">
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
     <div class="jumbotron" style="background-color:#f8f9fa85;">
     <h1 class="display-4">Make a Booking</h1><br>   
    
    <form method="post" action="bookings.php">
  <div class="form-row">
    <div class="form-group col-md-6">
      <label>Opening Days: Tuesday - Saturday</label>
      <?php
      $dateNow = date("Y-m-d");
      echo "<input type='date' class='form-control' id='inputDate' placeholder='Date' name='date' min='$dateNow' required>";
          ?>
    </div>
    <div class="form-group col-md-6">
      <label>Opening Hours: 09:00 - 19:00</label>
      <input type="time" class="form-control" id="inputTime" placeholder="Time" name="time" min="09:00" max="19:00" required>
    </div>
    
  </div>
   
   
   
   <div class="form-row">
    <div class="form-group col-md-6">
      <select required class="form-control" id="exampleFormControlSelect1" name="barber_id" >
      <option value="">Barber</option>
       <?php
        $query = "SELECT * FROM barber";
        $result = mysqli_query($conn, $query) or die("Error in query: ". mysqli_error($conn));
        while ($row = mysqli_fetch_assoc($result)){
        echo "<option value='$row[barber_id]'>$row[name]</option>";
        } 
        ?>
      
        </select>
      
    </div>
    <div class="form-group col-md-6">
      <select required class="form-control" id="exampleFormControlSelect1" name="service_id" >
      <option value="">Service</option>
       <?php
        $query = "SELECT * FROM service";
        $result = mysqli_query($conn, $query) or die("Error in query: ". mysqli_error($conn));
        while ($row = mysqli_fetch_assoc($result)){
        echo "<option value='$row[service_id]'>$row[type]</option>";
        } 
        ?>
      
        </select>
      
    </div>
  </div>
  
  
        
  
  
  
   
   
   
  
  <button type="submit" class="btn btn-warning btn-lg btn-block" name="submit">Check Availability</button>
</form>
  
<?php
         
         if (isset($_POST['submit'])){
                 
                 $username = $_SESSION['username'];       
                 $date = $_POST["date"];               
                 $barber_id = $_POST["barber_id"];
                 $service_id = $_POST["service_id"];                   
                 $time = $_POST["time"];
                
                
                  
                 $checkDay  = date("D",strtotime("$date $time"));
                 settype($checkDay, "string");
                 
                 if ($checkDay=='Mon' || $checkDay=='Sun')
                 {
                     echo "<br><div class='alert alert-danger' role='alert'>Shop is closed on Monday and Sunday</div>";
                 }
             else{
                 
                 $get_duration = "SELECT type, price, Duration FROM service WHERE service_id = '$service_id';";
                 $result = mysqli_query($conn, $get_duration);
                 
                 if ($result)
                 {
                     $array = mysqli_fetch_assoc($result);
                     $duration = $array['Duration'];
                     $price = $array['price'];
                     $service_type = $array['type'];
                 }                 
                 
                $secs = strtotime($duration)-strtotime("00:00:00");
                
                $totalTime = date("H:i:s",strtotime($time)+$secs);
             
                $start_date = "$date $time";
                $end_date = "$date $totalTime";
                
             
                
                
              $check_time = "SELECT * FROM booking WHERE '$start_date' BETWEEN start_date AND end_date"; 
             
                 
               $result = mysqli_query($conn,$check_time) or die(mysqli_error($conn));

                if(mysqli_num_rows($result)==1)
                {
                    echo "<br><div class='alert alert-danger' role='alert'>Booking Not Available</div>";                
                }
                
                   
             
               
                              
                
                else
                {
                   
         echo "<br><div class='alert alert-success' role='alert'> Booking Available!</div>";           
         ?>
                    <hr class="my-4">
                      <h1 class="display-4">Confirm Booking</h1><br> 
                       

                    <?php
                    $get_user_id = "SELECT account_id FROM account WHERE username LIKE '$username'";
                    $result = mysqli_query($conn, $get_user_id);
                 
                 if ($result)
                 {
                     $array = mysqli_fetch_assoc($result);
                     $account_id = $array['account_id'];
                 }
                    
                $get_user_details = "SELECT client_id, name, surname, mobile FROM client WHERE account_id = $account_id";
                
                $result = mysqli_query($conn, $get_user_details);  
                if ($result)
                {
                     $array = mysqli_fetch_assoc($result);
                    $client_id= $array['client_id']; 
                    $name = $array['name'];
                     $surname = $array['surname'];
                     $mobile = $array['mobile'];
                     
                }
                
                $get_barber_details = "SELECT name, surname, mobile FROM barber WHERE barber_id = $barber_id";
                
                $result = mysqli_query($conn, $get_barber_details);  
                if ($result)
                {
                     $array = mysqli_fetch_assoc($result);
                     $nameB = $array['name'];
                     $surnameB = $array['surname'];
                     $mobileB = $array['mobile'];
                     
                }
                
                    
                    $string_date = date("l jS \of F Y h:i A",strtotime($start_date)); 
                    $string_duration = date('i \m\i\n\u\t\e\s',strtotime($duration));
                    
                    
                    ?>
                    <!-- Card -->
<div class="card card-image mb-3" style="background-image: url('images/card.jpg');">

    <!-- Content -->
    <div class="text-white text-center d-flex align-items-center rgba-black-strong py-5 px-4">
        <div>
            <h3 class="card-title pt-2"><strong>ShaveCave Booking</strong></h3>
           <?php
             $_SESSION['barberid'] = $barber_id;
             $_SESSION['clientid'] = $client_id;
             $_SESSION['startdate'] = $start_date;
             $_SESSION['enddate'] = $end_date;
             $_SESSION['serviceid'] = $service_id;
                    
             echo"<p><strong>Name and Surname</strong> $name $surname</p>";
             echo"<p><strong>Mobile</strong> $mobile</p>";
                 echo"<p><strong>Date and Time</strong> $string_date</p>";
                 echo"<p><strong>Duration</strong> $string_duration</p>";
                 echo"<p><strong>Service</strong> $service_type</p>";
                 echo"<p><strong>Price</strong> &euro;$price</p><hr>";
                 echo"<p><strong>Barber</strong> $nameB $surnameB</p>";
                 echo"<p><strong>Mobile</strong> $mobileB</p>";
            ?>
           <form method="post" action="bookings.php">
           <button type="submit" name="submit2" class="btn btn-outline-light">Confirm Appointment</button> 
           </form>
        </div>
    </div>
    <!-- Content -->
</div>
<!-- Card -->
                   
                   
                   
                    <?php
                }
                }
                }
             
             
           
                 
                
         
         if (isset($_POST['submit2'])){
               
             $barber_id = $_SESSION['barberid'];
             $client_id = $_SESSION['clientid'];
             $start_date = $_SESSION['startdate'];
             $end_date = $_SESSION['enddate'];
             $service_id = $_SESSION['serviceid'];
             $confirm_app = "INSERT INTO booking (booking_id, start_date, end_date, client_id, service_id, barber_id) VALUES (NULL, '$start_date', '$end_date', '$client_id', '$service_id', '$barber_id');";
                        mysqli_query($conn, $confirm_app);
                    echo "<br><div class='alert alert-success' role='alert'>Booking Complete!</div>";
                    header("location: viewbookings.php");
                    }
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