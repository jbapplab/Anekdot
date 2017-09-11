<?php
	require("password.php");
	
    $connect = mysqli_connect("localhost", "id2842189_jbapplab", "tim!!357", "id2842189_anekdot");
    
    $first_name = $_POST["firstName"];
	$last_name = $_POST["lastName"];
	$username = $_POST["username"];    
    $password = $_POST["password"];
	$email = $_POST["email"];
	
    $statement = mysqli_prepare($connect, "INSERT INTO user_new (first_name, last_name, username, password, email) VALUES (?, ?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "sssss", $first_name, $last_name, $username, $password, $email);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true;  
    
    echo json_encode($response);
?>
?>