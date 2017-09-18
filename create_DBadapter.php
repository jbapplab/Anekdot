<?php

require_once dirname(__FILE__).'/create_constants.php';

class DBadapter{
	
	//1. CONNECT TO DB
	//2. RETURN CONNECTION OBJECT
	public function connect(){
		$con = mysqli_connect(constants::$DB_SERVER, constants::$DB_USERNAME, constants::$DB_PASSWORD, constants::$DB_NAME);
		if(mysqli_connect_error(!$con)){
			echo "Unable to connect";
			return null;
		} else {
			return $con;
		}
	}
	
	//1. INSERT STORY INTO DATABASE
	public function insert($s){
		//INSERT
		$con = $this->connect();
		
		if ($con != null){
			$sql = "INSERT INTO story(story_title, story_category, if_other_specify, author_id, story_description, story_events, orientation, complicated_action, evaluation, resolution, message, story_meta, stage_related, context_related, story_full, image_url, audience_stage) VALUES('$s[0]','$s[1]','$s[2]','$s[3]','$s[4]','$s[5]','$s[6]','$s[7]','$s[8]','$s[9]','$s[10]','$s[11]','$s[12]','$s[13]','$s[14]','$s[15]','$s[16]')";
			
			try{
				$result = mysqli_query($con,$sql);
				
				if($result){
					print(json_encode(array("Success")));
				} else {
					print(json_encode(array("Unsuccessful")));
				}
			} catch (Exception $e){
				print(json_encode(array("PHP EXCEPTION: Can't save to MySQL Database. "+$e->getMessage())));
			}
		} else {
			print(json_encode(array("PHP EXCEPTION: Can't connect to MySQL Database. Null connection.")));
		}
		
		mysqli_close($con);
	}
	
	//1. SELECT FROM DATABASE
	public function select(){
		$con=$this->connect();
		
		if($con != null){
			$retrieved=mysqli_query($con,constants::$SQL_SELECT_ALL);
			if($retrieved){
				while($row=mysqli_fetch_array($retrieved)){
					
					//echo $row["story_title"]," \t | ",$row["description"],"</br>"
					$stories[]=$row;
				}
				print(json_encode($stories));
			} else {
				print(json_encode(array("PHP EXCEPTION: Can't retrieve data from the MySQL database.")));
			}
		} else {
			print(json_encode(array("PHP EXCEPTION: Can't connect to MySQL Database. Null connection.")));
		}
		
		mysqli_close($con);
	}
	
	

	
	//public function update()
	
	//public function delete()
	
}

?>