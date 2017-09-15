<?php

require_once("/DBadapter.php")

if($_POST('action')=="save"){
	$dbAdapter = new DBadapter();
	$story_title = $_POST('story_title');
	$story_category = $_POST('story_category');
	$if_other_specify = $_POST('if_other_specify');
	$author_id = $_POST('author_id');
	$story_description = $_POST('story_description');
	$story_events = $_POST('story_events');
	$orientation = $_POST('orientation');
	$complicated_action = $_POST('complicated_action');
	$evaluation = $_POST('evaluation');
	$resolution = $_POST('resolution');
	$message = $_POST('message');
	$story_meta = $_POST('story_meta');
	$stage_related = $_POST('stage_related');
	$context_related = $_POST('context_related');
	$story_full = $_POST('story_full');
	$image_url = $_POST('image_url');
	$audience_stage = $_POST('audience_stage');
	
	$dbAdapter->insert(array($story_title,$story_category,$if_other_specify,$author_id,$story_description,$story_events,$orientation,$complicated_action,$evaluation,$resolution,$message,$story_meta,$stage_related,$context_related,$story_full,$image_url,$audience_stage));
}

?>