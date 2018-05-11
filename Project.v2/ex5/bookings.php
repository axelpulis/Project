<?php
    session_start();
?>
<!doctype html>
<html lang="en">
  <head>
    
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">   
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css" integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB" crossorigin="anonymous">

    <title>Hello, world!</title>
  </head>
  <body>
   <body>
    <?php
    $conn = mysqli_connect("localhost","root","","project_db",3306);
if (mysqli_connect_errno()){
echo "Error: Could not connect to database. Please try again
later";
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
                 echo "<li class='nav-item'><a href='booking.php' class='nav-link'>View Bookings</a></li>";
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
     <div class="jumbotron">
     <h1 class="display-4">Make a Booking</h1><br>   
    
    <form method="post" action="bookings.php">
  <div class="form-row">
    <div class="form-group col-md-6">
      <label>Opening Days: Tuesday - Saturday</label>
      <input type="date" class="form-control" id="inputDate" placeholder="Date" name="date" required>
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
  
  <div class="input-group mb-3">
  <div class="input-group-prepend">
    <span class="input-group-text" id="inputGroup-sizing-default">Price</span>
  </div>
  <input disabled type="text" class="form-control" aria-label="Default" aria-describedby="inputGroup-sizing-default">
</div>
        
  
  
  
   
   
   
  
  <button type="submit" class="btn btn-primary" name="submit">Check Availability</button>
</form>
  
<?php
         
         if (isset($_POST['submit'])){
             
                        
                 $date = $_POST["date"];               
                 $barber_id = $_POST["barber_id"];
                 $service_id = $_POST["service_id"];                   
                 $time = $_POST["time"];
                  
                 
                 $get_duration = "SELECT Duration FROM service WHERE service_id = '$service_id';";
                 $result = mysqli_query($conn, $get_duration);
                 
                 if ($result)
                 {
                     $array = mysqli_fetch_assoc($result);
                     $duration = $array['Duration'];
                 }                 
                 
                $secs = strtotime($duration)-strtotime("00:00:00");
                
                $totalTime = date("H:i:s",strtotime($time)+$secs);
             
                $start_date = "$date $time";
                $end_date = "$date $totalTime";
                
             echo "$start_date";
                 echo "$end_date";
                
                
             $check_time = "SELECT * FROM booking WHERE start_date BETWEEN '$start_date' AND '$end_date'"; 
             
                 
               $result = mysqli_query($conn,$check_time) or die(mysqli_error($conn));

                if(mysqli_num_rows($result)==1)
                {
                    echo "<br><div class='alert alert-danger' role='alert'>Booking Not Available</div>";                
                }
                else
                {
                   $check_time = "SELECT * FROM booking WHERE end_date BETWEEN '$start_date' AND '$end_date'"; 
             
                 
               $result = mysqli_query($conn,$check_time) or die(mysqli_error($conn));

                if(mysqli_num_rows($result)==1)
                {
                    echo "<br><div class='alert alert-danger' role='alert'>Booking Not Available</div>";                
                }
                else
                {
                   echo " <br><div class='alert alert-success' role='alert'> Booking Available!</div>";
                }
                }
             
             
           
                 
                }
         
         
?>
   </div>
    </div>

    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
  </body>
</html>