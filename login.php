<?php
    require("password.php");
    $con = mysqli_connect("localhost", "id2842189_jbapplab", "tim!!357", "id2842189_anekdot");
    
    $username = $_POST["username"];
    $password = $_POST["password"];
    
    $statement = mysqli_prepare($con, "SELECT * FROM user WHERE username = ?");
    mysqli_stmt_bind_param($statement, "s", $username);
    mysqli_stmt_execute($statement);
    mysqli_stmt_store_result($statement);
	/*The order of the statement fields needs to be in the same order as the columns in the table*/
    mysqli_stmt_bind_result($statement, $colUserID, $colName, $colUsername, $colAge, $colPassword);
    
    $response = array();
    $response["success"] = false;  
    
    while(mysqli_stmt_fetch($statement)){
        if (password_verify($password, $colPassword)) {
                $response["success"] = true;  
                $response["name"] = $colName;
                $response["username"] = $colUsername;
                $response["age"] = $colAge;
                $response["password"] = $colPassword;
        }
    }
    
    echo json_encode($response);
?>