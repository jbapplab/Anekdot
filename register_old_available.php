<?php
    $con = mysqli_connect("localhost", "id2842189_jbapplab", "tim!!357", "id2842189_anekdot");
    
    $name = $_POST["name"];
    $age = $_POST["age"];
    $username = $_POST["username"];
    $password = $_POST["password"];
	$available = false;
	
	$statementAvailable = mysqli_prepare($connect, "SELECT * FROM user WHERE username = ?"); 
    mysqli_stmt_bind_param($statementAvailable, "s", $username);
    mysqli_stmt_execute($statementAvailable);
    mysqli_stmt_store_result($statementAvailable);
        $count = mysqli_stmt_num_rows($statementAvailable);
        mysqli_stmt_close($statementAvailable); 
        if ($count < 1){
            $available = true; 
        }else {
            $available = false; 
        }
	
	if ($available == true){
    $statement = mysqli_prepare($con, "INSERT INTO user (name, username, age, password) VALUES (?, ?, ?, ?)");
    mysqli_stmt_bind_param($statement, "ssis", $name, $username, $age, $password);
    mysqli_stmt_execute($statement);
    
    $response = array();
    $response["success"] = true; 
	}else{
	$response["success"] = false;
	}		
    
    echo json_encode($response);
?>