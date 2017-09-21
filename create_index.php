<?php

require_once dirname(__FILE__).'/create_DBadapter.php';

$dbAdapter = new DBadapter();

if (isset($_GET['category'])){

	if($_GET['category']=="Art"){
		$dbAdapter->select("Art");
	} else if ($_GET['category']=="Causes") {
		$dbAdapter->select("Causes");
	} else if ($_GET['category']=="Education") {
		$dbAdapter->select("Education");
	} else if ($_GET['category']=="Food") {
		$dbAdapter->select("Food");
	} else if ($_GET['category']=="Lifestyle") {
		$dbAdapter->select("Lifestyle");
	} else if ($_GET['category']=="Business") {
		$dbAdapter->select("Business");
	} else if ($_GET['category']=="Sports") {
		$dbAdapter->select("Sports");
	} else if ($_GET['category']=="Travel") {
		$dbAdapter->select("Travel");
	} else if ($_GET['category']=="Security") {
		$dbAdapter->select("Security");
	} else if ($_GET['category']=="Other") {
		$dbAdapter->select("Other");
	} else {
		$dbAdapter->select("All");
	}
	
} else {
	
	print(json_encode(array("Anecdot could not send the category selection to the server.")));
	
}
    
?>