<?php

require_once dirname(__FILE__).'/create_DBadapter.php';

if (isset($_POST['action'])){

	if($_POST['action']=="save"){
		
		$dbAdapter = new DBadapter();
		
		$story_title = $_POST['story_title'];
		$story_category = $_POST['story_category'];
		$if_other_specify = $_POST['if_other_specify'];
		$author_id = $_POST['author_id'];
		$story_description = $_POST['story_description'];
		$story_events = $_POST['story_events'];
		$orientation = $_POST['orientation'];
		$complicated_action = $_POST['complicated_action'];
		$evaluation = $_POST['evaluation'];
		$resolution = $_POST['resolution'];
		$message = $_POST['message'];
		$story_meta = $_POST['story_meta'];
		$stage_related = $_POST['stage_related'];
		$context_related = $_POST['context_related'];
		$story_full = $_POST['story_full'];
		$image_url = $_POST['image_url'];
		$audience_stage = $_POST['audience_stage'];
		
		$dbAdapter->insert(array($story_title,$story_category,$if_other_specify,$author_id,$story_description,$story_events,$orientation,$complicated_action,$evaluation,$resolution,$message,$story_meta,$stage_related,$context_related,$story_full,$image_url,$audience_stage));

		} else if ($_POST['action']=="update"){
		
		$dbAdapter = new DBadapter();
		
		$id = $_POST['story_id'];
		$story_title = $_POST['story_title'];
		$story_category = $_POST['story_category'];
		$if_other_specify = $_POST['if_other_specify'];
		$author_id = $_POST['author_id'];
		$story_description = $_POST['story_description'];
		$story_events = $_POST['story_events'];
		$orientation = $_POST['orientation'];
		$complicated_action = $_POST['complicated_action'];
		$evaluation = $_POST['evaluation'];
		$resolution = $_POST['resolution'];
		$message = $_POST['message'];
		$story_meta = $_POST['story_meta'];
		$stage_related = $_POST['stage_related'];
		$context_related = $_POST['context_related'];
		$story_full = $_POST['story_full'];
		$image_url = $_POST['image_url'];
		$audience_stage = $_POST['audience_stage'];
		
		$dbAdapter->update($id,(array($story_title,$story_category,$if_other_specify,$author_id,$story_description,$story_events,$orientation,$complicated_action,$evaluation,$resolution,$message,$story_meta,$stage_related,$context_related,$story_full,$image_url,$audience_stage)));
		
	}else if ($_POST['action']=="delete"){
		
		$dbAdapter = new DBadapter();
		
		$id=$_POST['story_id'];
			
		$dbAdapter->delete($id);
		
	} else if ($_POST['action']=="favourite"){
		
		$dbAdapter = new DBadapter();
		
		$storyId = $_POST['story_id'];
		$userId = $_POST['user_id'];
		
		$dbAdapter->favourite($storyId, $userId);
	}
	
} else {
	
	print(json_encode(array("Anecdot could not send the action request to the server.")));
	
}

?>