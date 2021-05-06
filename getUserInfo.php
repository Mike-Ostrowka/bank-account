<?php
$accountNum = $_POST['accountNum'];
 $conn = new mysqli("localhost", "id931309_jcorls", "embers15", "id931309_bankaccountdb");

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
} 

$table = "BankAccount";
$sql = "SELECT * FROM $table WHERE accountNum='$accountNum'";
$result = $conn->query($sql);


$row = mysqli_fetch_assoc($result);
  print (json_encode($row));

$conn->close();
?>