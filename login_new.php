<?php
    $con = mysqli_connect("localhost", "id2842189_jbapplab", "tim!!357", "id2842189_anekdot");
    
    $username = $_POST["username"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM user_new WHERE username = ? AND password = ?");
    mysqli_stmt_bind_param($statement, "ss", $username, $password);
    mysqli_stmt_execute($statement);
	
    mysqli_stmt_store_result($statement);
    /*The order of the statement fields needs to be in the same order as the columns in the table*/
    mysqli_stmt_bind_result($statement, $colUserID, $colFirstName, $colLastName, $colUsername, $colPassword, $colEmail);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
                $response["success"] = true;  
                $response["firstName"] = $colFirstName;
		        $response["lastName"] = $colLastName;
                $response["username"] = $colUsername;
                $response["password"] = $colPassword;
		        $response["email"] = $colEmail;
    }
    
    echo json_encode($response);
?>