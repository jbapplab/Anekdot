<?php
$db_host='localhost';
$db_username='id2842189_jbapplab';
$db_password='tim!!357';
$db='id2842189_anekdot';



$con = mysqli_connect($db_host, $db_username, $db_password, $db) or die('Unable to connect');

if(mysqli_connect_error($con))
{
	echo "Failed to connect to database ".mysqli_connect_error();
}

$sql="SELECT * FROM story";

$result=mysqli_query($con,$sql);
if($result)
{
	while($row=mysqli_fetch_array($result))
	{
		$flag[]=$row;
	}
	print(json_encode($flag));
}
mysqli_close($con);
    
?>