<?php
    $accountNum = $_POST['accountNum'];
    $balance = $_POST['balance'];
    $firstName = $_POST['firstName'];
    $lastName = $_POST['lastName'];
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
    
    $sql = "INSERT INTO $table (accountNum, balance, firstName, lastName, password) VALUES ($accountNum, $balance, '$firstName', '$lastName', '$password')";



    if ($conn->query($sql) == TRUE)
    {
        echo "created";
    }
    else
    {
        echo "error " . $conn->error;
    }
    
    $conn->close();
?>