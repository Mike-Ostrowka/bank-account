<?php
    $accountNum = $_POST['accountNum'];
    $password = $_POST['password'];
    
    
    // check which of the above variables were set
    print_r($_POST);
    
    // Create connection
    $conn = new mysqli("localhost", "id931309_jcorls", "embers15", "id931309_bankaccountdb");
    
    // Check connection
    if ($conn->connect_error)
    {
        die("Connection failed: " . $conn->connect_error);
    }
    
    $table = "BankAccount";
    
    $sql = "UPDATE $table SET password='$password' WHERE accountNum=$accountNum";  
    
    if ($conn->query($sql) == TRUE)
    {
        echo "success";
    }
    else
    {
        echo "error " . $conn->error;
    }   

    $conn->close();
?>