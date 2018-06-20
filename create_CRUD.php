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
		
		$s0 = addslashes($story_title);
		$s1 = addslashes($story_category);
		$s2 = addslashes($if_other_specify);
		$s3 = addslashes($author_id);
		$s4 = addslashes($story_description);
		$s5 = addslashes($story_events);
		$s6 = addslashes($orientation);
		$s7 = addslashes($complicated_action);
		$s8 = addslashes($evaluation);
		$s9 = addslashes($resolution);
		$s10 = addslashes($message);
		$s11 = addslashes($story_meta);
		$s12 = addslashes($stage_related);
		$s13 = addslashes($context_related);
		$s14 = addslashes($story_full);
		$s15 = addslashes($image_url);
		$s16 = addslashes($audience_stage);
		
		$dbAdapter->insert(array($s0,$s1,$s2,$s3,$s4,$s5,$s6,$s7,$s8,$s9,$s10,$s11,$s12,$s13,$s14,$s15,$s16));

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
		
		$s0 = addslashes($story_title);
		$s1 = addslashes($story_category);
		$s2 = addslashes($if_other_specify);
		$s3 = addslashes($author_id);
		$s4 = addslashes($story_description);
		$s5 = addslashes($story_events);
		$s6 = addslashes($orientation);
		$s7 = addslashes($complicated_action);
		$s8 = addslashes($evaluation);
		$s9 = addslashes($resolution);
		$s10 = addslashes($message);
		$s11 = addslashes($story_meta);
		$s12 = addslashes($stage_related);
		$s13 = addslashes($context_related);
		$s14 = addslashes($story_full);
		$s15 = addslashes($image_url);
		$s16 = addslashes($audience_stage);
		
		$dbAdapter->update($id,(array($s0,$s1,$s2,$s3,$s4,$s5,$s6,$s7,$s8,$s9,$s10,$s11,$s12,$s13,$s14,$s15,$s16)));
		
	}else if ($_POST['action']=="delete"){
		
		$dbAdapter = new DBadapter();
		
		$id=$_POST['story_id'];
			
		$dbAdapter->delete($id);
		
	} else if ($_POST['action']=="favourite"){
		
		$dbAdapter = new DBadapter();
		
		$storyId = $_POST['story_id'];
		$userId = $_POST['user_id'];
		
		$dbAdapter->favourite($storyId, $userId);
		
	} else if ($_POST['action']=="retrieveMyFavourites"){
		
		$dbAdapter = new DBadapter();
		
		$userId = $_POST['user_id'];
		
		$dbAdapter->retrieveMyFavourites($userId);
		
	} else if ($_POST['action']=="retrieveMyStories"){
		
		$dbAdapter = new DBadapter();
		
		$userId = $_POST['user_id'];
		
		$dbAdapter->retrieveMyStories($userId);
	}
	
} else {
	
	print(json_encode(array("Anecdot could not send the action request to the server.")));
	
}

?>