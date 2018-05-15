<?php
    session_start();
   
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
            else{
                echo "<li class='nav-item'><a href='viewbookings.php' class='nav-link'>My Appointments</a></li>";
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
     <h1 class="display-4">Upcoming Appointments</h1><br>   
    <table class="table table-hover table-dark">
    
  <thead>
    <tr>
      <th scope="col">Date and Time</th>
      <th scope="col">Service</th>
      <th scope="col">Duration</th>
      <th scope="col">Barber</th>
      <th scope="col">Price</th>
      <th scope="col"></th>
    </tr>
  </thead>
       <tbody>
        <?php
        $client_id = $_SESSION['clientid'];
        $currentDateTime = date('Y-m-d H:i:s');
        
        $get_future_bookings = "SELECT booking.booking_id, booking.start_date, barber.name, service.type ,service.price, service.Duration 
FROM booking INNER JOIN barber ON booking.barber_id = barber.barber_id INNER JOIN service ON booking.service_id = service.service_id
WHERE client_id = $client_id AND booking.start_date > '$currentDateTime'";
        
                
        $result = mysqli_query($conn, $get_future_bookings) or die("Error in query: ". mysqli_error($conn));
       while ($row = mysqli_fetch_assoc($result)){
            echo "<tr>
            <td>$row[start_date]</td>
            <td>$row[type]</td> 
            <td>$row[Duration]</td> 
            <td>$row[name]</td>
            <td>&euro;$row[price]<td/>
            <form method='post' action='viewbookings.php'>
            <input hidden type='text' name='booking_id' value='$row[booking_id]'>
            <button 
            
            name='submit'id=''type='submit'class='btn btn-danger btn-rounded btn-sm my-0'>Cancel</button></form>
            </tr>"; 
        
         
         }   
        ?>
  </tbody>
       
   </table>
    <h1 class="display-4">Past Appointments</h1><br>   
    <table class="table table-hover table-dark">
    
  <thead>
    <tr>
      <th scope="col">Date and Time</th>
      <th scope="col">Service</th>
      <th scope="col">Duration</th>
      <th scope="col">Barber</th>
      <th scope="col">Price</th>
      <th scope="col"></th>
    </tr>
  </thead>
       <tbody>
            <?php
        $client_id = $_SESSION['clientid'];
        $currentDateTime = date('Y-m-d H:i:s');
        
        $get_future_bookings = "SELECT booking.booking_id, booking.start_date, barber.name, service.type ,service.price, service.Duration 
FROM booking INNER JOIN barber ON booking.barber_id = barber.barber_id INNER JOIN service ON booking.service_id = service.service_id
WHERE client_id = $client_id AND booking.start_date < '$currentDateTime'";
        
                
        $result = mysqli_query($conn, $get_future_bookings) or die("Error in query: ". mysqli_error($conn));
       while ($row = mysqli_fetch_assoc($result)){
            echo "<tr>
            <td>$row[start_date]</td>
            <td>$row[type]</td> 
            <td>$row[Duration]</td> 
            <td>$row[name]</td>
            <td>&euro;$row[price]<td/>
            
            </tr>"; 
        
         
         }   
        ?>
        </tbody>
         </table>
   
   </div>
    </div>
    <?php
        if (isset($_POST['submit'])){
            $booking_id = $_POST['booking_id'];
            $sql_delete = "DELETE FROM `booking` WHERE booking_id = $booking_id";             
            mysqli_query($conn, $sql_delete);
            header('location: viewbookings.php');
        } 
        ?>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js" integrity="sha384-smHYKdLADwkXOn1EmN1qk/HfnUcbVRZyYmZ4qpPea6sjB/pTJ0euyQp0Mk8ck+5T" crossorigin="anonymous"></script>
    <link rel="stylesheet" type="text/css" href="css/style.css">
  </body>
</html>