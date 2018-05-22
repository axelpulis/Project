<?php
    session_start();
if (isset($_SESSION['username']))
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

    <title>Hello, world!</title>
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
                <li class="nav-item"><a href="register.php" class="nav-link">Register</a></li>
                
            </ul>
            <?php
            if (isset($_SESSION['username']))
            {
                $user = $_SESSION['username'];
                echo "<button onclick='location.href = \"logout.php\"' type='button' id='dropdownMenu1'  class='btn btn-outline-secondary' style=\"float: right;\"> <strong>$user</strong> Log-Out</button>";
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
     <h1 class="display-4">Register</h1><br>   
    
    <form method="post" action="register.php">
  <div class="form-row">
    <div class="form-group col-md-6">
      
      <input type="text" class="form-control" id="inputName" placeholder="Name" name="name" required>
    </div>
    <div class="form-group col-md-6">
      
      <input type="text" class="form-control" id="inputSurname" placeholder="Surname" name="surname" required>
    </div>
    
  </div>
   
   <div class="form-group">
      
      <input type="email" class="form-control" id="inputEmail" placeholder="Email" name="email" required>
    </div>
   
   <div class="form-row">
    <div class="form-group col-md-6">
      
      <input type="text" class="form-control" id="inputUsername" placeholder="Username" name="username" required>
    </div>
    <div class="form-group col-md-6">
      
      <input type="password" class="form-control" id="inputPassword" placeholder="Password" name="password" required>
    </div>
  </div>
  
  
  
   
   
   <div class="form-row">
      <div class="form-group col-md-6">
    
    <select required class="form-control" id="exampleFormControlSelect1" name="city" >
      <option value="">City</option>
      <?php

    $query = "SELECT * FROM town";
    
$result = mysqli_query($conn, $query)
or die("Error in query: ". mysqli_error($conn));

  
while ($row = mysqli_fetch_assoc($result)){
echo "<option value='$row[town_id]'>$row[name]</option>";
}
  ?>
      
    </select>
  </div>
    
    <div class="form-group col-md-6">
      
      <input type="text" pattern="\d*" class="form-control" id="inputMobile" placeholder="Mobile" name="mobile" required maxlength="8">
    </div>
  </div>
  
  <button type="submit" class="btn btn-primary" name="submit">Register</button>
</form>
  
<?php
         
         if (isset($_POST['submit'])){
             
             $link = mysqli_connect("localhost","root","","project_db",3306);
             $username = $_POST["username"];
             $sql = ("SELECT * FROM account WHERE username = '$username';");
             $result = mysqli_query($link,$sql) or die(mysqli_error($link));
             
             if(mysqli_num_rows($result)==1){
                 echo "<br><div class='alert alert-danger' role='alert'>Registration Failed - Username Already Taken!</div>"; 
             }
             else{                 
                 $username = $_POST["username"];
                 $password = $_POST["password"];
                 $email = $_POST["email"];
                 $create_acc = "INSERT INTO account (account_id, username, password, admin, email) VALUES (NULL,'$username','$password', 0, '$email');";
                 mysqli_query($conn, $create_acc);
                 $get_acc_id = "SELECT * FROM account WHERE username LIKE '$username';";
                 $result = mysqli_query($conn, $get_acc_id);
                 
                 if ($result)
                 {
                     $array = mysqli_fetch_assoc($result);
                     $account_id = $array['account_id'];
                 }                 
                 $name = $_POST["name"];
                 $surname = $_POST["surname"];
                 $city = $_POST["city"];
                 $mobile = $_POST["mobile"];
                 $create_cli = "INSERT INTO client (client_id, name, surname, mobile, town_id, account_id) VALUES (NULL, '$name', '$surname', $mobile, $city, $account_id);";
                 mysqli_query($conn, $create_cli); 
                 echo " <br><div class='alert alert-success' role='alert'> Registration Successful!</div>";
                 
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
     <link rel="stylesheet" type="text/css" href="css/style.css">
  </body>
</html>