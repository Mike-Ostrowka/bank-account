<?php
   $accountNum = $_POST['accountNum'];
   $password = $_POST['password'];
  
  if(!empty($_POST)) 
{
 


  $con = new mysqli("localhost", "id931309_jcorls", "embers15", "id931309_bankaccountdb");

  if ($con->connect_error) 
  {
    die("Connection failed: " . $con->connect_error);
  }

  $table = "BankAccount";
  $stmt = $con->prepare("SELECT * FROM $table WHERE accountNum = ? AND password = ?");
  $stmt->bind_param("is", $accountNum, $password);
  
  if(!$stmt->execute())
 {
	 die('ERROR: ' .$con->error);
 }
 else
 {
	 $result = $stmt->get_result();
	 $row = $result->fetch_assoc();
	 
	 if($row != null)
         {
	   echo "Login Successful";
         }
         else
	 {
	   echo "Login Failed";
	 }
 }
}
 	else 
	{
		echo "Nothing submited";
	}
	$stmt->close();
 	$con->close();
?>