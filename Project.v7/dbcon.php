 <?php
    $conn = mysqli_connect("localhost","root","","project_db",3306);
        if (mysqli_connect_errno()){
            echo "Error: Could not connect to database. Please try again later";
            exit;
        }
?>