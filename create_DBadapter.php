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
	public function select($s){
		$con=$this->connect();
		
		$statementStoriesInCategory = mysqli_prepare($con, "SELECT * FROM story WHERE story_category =?");
		mysqli_stmt_bind_param($statementStoriesInCategory, "s", $s);
		mysqli_stmt_execute($statementStoriesInCategory);
		mysqli_stmt_store_result($statementStoriesInCategory);
		$count = mysqli_stmt_num_rows($statementStoriesInCategory);
		mysqli_stmt_close($statementStoriesInCategory);
		
		if ($count < 1){
            $storiesAvailable = false; 
        }else {
            $storiesAvailable = true; 
        }
		
		if($con != null){
			if ($storiesAvailable == true) {
				if ($s == "Art"){
					$retrieved=mysqli_query($con,constants::$SQL_SELECT_ART);
				} else if ($s == "Causes"){
					$retrieved=mysqli_query($con,constants::$SQL_SELECT_CAUSES);
				} else if ($s == "Education"){
					$retrieved=mysqli_query($con,constants::$SQL_SELECT_EDUCATION);
				} else if ($s == "Food"){
					$retrieved=mysqli_query($con,constants::$SQL_SELECT_FOOD);
				} else if ($s == "Lifestyle"){
					$retrieved=mysqli_query($con,constants::$SQL_SELECT_LIFESTYLE);
				} else if ($s == "Business"){
					$retrieved=mysqli_query($con,constants::$SQL_SELECT_BUSINESS);
				} else if ($s == "Sports"){
					$retrieved=mysqli_query($con,constants::$SQL_SELECT_SPORTS);
				} else if ($s == "Travel"){
					$retrieved=mysqli_query($con,constants::$SQL_SELECT_TRAVEL);
				} else if ($s == "Security"){
					$retrieved=mysqli_query($con,constants::$SQL_SELECT_SECURITY);
				} else if ($s == "Other"){
					$retrieved=mysqli_query($con,constants::$SQL_SELECT_OTHER);
				} else if ($s == "All"){
					$retrieved=mysqli_query($con,constants::$SQL_SELECT_ALL);
				}
				
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
	
				/*$retrieved=mysqli_query($con,constants::$SQL_SELECT_ALL);
				
				if($retrieved){
					while($row=mysqli_fetch_array($retrieved)){
						
						//echo $row["story_title"]," \t | ",$row["description"],"</br>"
						$allStories[]=$row;
					}
					print(json_encode($allStories));
				} else {
					print(json_encode(array("PHP EXCEPTION: Can't retrieve data from the MySQL database.")));
				}*/
				
				print(json_encode(array("There are no stories in this category at the moment. Please go back and select another.")));
			}	
			
		} else {
			
			print(json_encode(array("PHP EXCEPTION: Can't connect to MySQL Database. Null connection.")));
		
		}
		
		mysqli_close($con);
		
	}
	
	//1. UPDATE IN DATABASE
	public function update($id, $s){
		$con=$this->connect();
		
		if($con != null){
			$sql="UPDATE story SET story_title='$s[0]', story_category='$s[1]', if_other_specify='$s[2]', author_id='$s[3]', story_description='$s[4]', story_events='$s[5]', orientation='$s[6]', complicated_action='$s[7]', evaluation='$s[8]', resolution='$s[9]', message='$s[10]', story_meta='$s[11]', stage_related='$s[12]', context_related='$s[13]', story_full='$s[14]', image_url='$s[15]', audience_stage='$s[16]' WHERE story_id='$id'";
		
			try
            {
                $result=mysqli_query($con,$sql);
                if($result){
                    print(json_encode(array("Success")));
                } else {
                    print(json_encode(array("Update action failed")));
                }
            } catch (Exception $e){
                 print(json_encode(array("PHP EXCEPTION : Can't update entry in MySQL Database. "+$e->getMessage())));
            }
        } else {
            print(json_encode(array("PHP EXCEPTION : Can't connect to MySQL Database. Null connection.")));
        }
		
        mysqli_close($con);
	}
	
	//1. DELETE FROM DATABASE
	public function delete($id){
		$con=$this->connect();
		
		if($con != null){
			
			$sql="DELETE FROM story WHERE story_id='$id'";
		
		try{
            $result=mysqli_query($con,$sql);
                if($result) {
                    print(json_encode(array("Success")));
                } else {
                    print(json_encode(array("Delete action failed.")));
                }
            }catch (Exception $e){
                print(json_encode(array("PHP EXCEPTION : Can't delete entry in MySQL Database. "+$e->getMessage())));
            }
        } else {
            print(json_encode(array("PHP EXCEPTION : Can't connect to MySQL Database. Null connection.")));
        }
        mysqli_close($con);
		
	}
	
	//1. SAVE FAVOURITE INTO DATABASE
	public function favourite($storyId, $userId){
		//INSERT
		$con = $this->connect();
		
		$statementExists = mysqli_prepare($con, "SELECT * FROM saved_story WHERE story_id = ? AND user_id = ?");
		mysqli_stmt_bind_param($statementExists, "ss", $storyId, $userId);
		mysqli_stmt_execute($statementExists);
		mysqli_stmt_store_result($statementExists);
		$count = mysqli_stmt_num_rows($statementExists);
		mysqli_stmt_close($statementExists); 
        if ($count < 1){
            $notexist = true; 
        }else {
            $notexist = false; 
        }
		
		if ($con != null){
			if ($notexist == true){
				$sql = "INSERT INTO saved_story(story_id, user_id) VALUES('$storyId','$userId')";
				
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
				print(json_encode(array("Story is already saved in your favourites.")));
			}
		} else {
			print(json_encode(array("PHP EXCEPTION: Can't connect to MySQL Database. Null connection.")));
		}
		
		mysqli_close($con);
	}
	
	
	//1. SELECT MYFAVOURITES FROM DATABASE
	public function retrieveMyFavourites($userId){
		$con=$this->connect();
		
		$statementMyStories = mysqli_prepare($con, "SELECT * FROM story INNER JOIN saved_story ON story.story_id = saved_story.story_id AND saved_story.user_id = ?");
		mysqli_stmt_bind_param($statementMyStories, "s", $userId);
		mysqli_stmt_execute($statementMyStories);
		mysqli_stmt_store_result($statementMyStories);
		$count = mysqli_stmt_num_rows($statementMyStories);
		mysqli_stmt_close($statementMyStories);
		
		if ($count < 1){
            $storiesAvailable = false; 
        }else {
            $storiesAvailable = true; 
        }
		
		if($con != null){
			if ($storiesAvailable == true) {
				$retrieved=mysqli_query($con, "SELECT * FROM story INNER JOIN saved_story ON story.story_id = saved_story.story_id AND saved_story.user_id = $userId");
				
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
	
				/*$retrieved=mysqli_query($con,constants::$SQL_SELECT_ALL);
				
				if($retrieved){
					while($row=mysqli_fetch_array($retrieved)){
						
						//echo $row["story_title"]," \t | ",$row["description"],"</br>"
						$allStories[]=$row;
					}
					print(json_encode($allStories));
				} else {
					print(json_encode(array("PHP EXCEPTION: Can't retrieve data from the MySQL database.")));
				}*/
				
				print(json_encode(array("You have not yet created any stories. Create some and return again to this section.")));
			}	
			
		} else {
			
			print(json_encode(array("PHP EXCEPTION: Can't connect to MySQL Database. Null connection.")));
		
		}
		
		mysqli_close($con);
		
	}
	
	//1. SELECT MYSTORIES FROM DATABASE
	public function retrieveMyStories($userId){
		$con=$this->connect();
		
		$statementMyStories = mysqli_prepare($con, "SELECT * FROM story WHERE author_id =?");
		mysqli_stmt_bind_param($statementMyStories, "s", $userId);
		mysqli_stmt_execute($statementMyStories);
		mysqli_stmt_store_result($statementMyStories);
		$count = mysqli_stmt_num_rows($statementMyStories);
		mysqli_stmt_close($statementMyStories);
		
		if ($count < 1){
            $storiesAvailable = false; 
        }else {
            $storiesAvailable = true; 
        }
		
		if($con != null){
			if ($storiesAvailable == true) {
				$retrieved=mysqli_query($con, "SELECT * FROM story WHERE author_id = $userId");
				
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
	
				/*$retrieved=mysqli_query($con,constants::$SQL_SELECT_ALL);
				
				if($retrieved){
					while($row=mysqli_fetch_array($retrieved)){
						
						//echo $row["story_title"]," \t | ",$row["description"],"</br>"
						$allStories[]=$row;
					}
					print(json_encode($allStories));
				} else {
					print(json_encode(array("PHP EXCEPTION: Can't retrieve data from the MySQL database.")));
				}*/
				
				print(json_encode(array("You have not yet created any stories. Create some and return again to this section.")));
			}	
			
		} else {
			
			print(json_encode(array("PHP EXCEPTION: Can't connect to MySQL Database. Null connection.")));
		
		}
		
		mysqli_close($con);
		
	}
	
}

?>