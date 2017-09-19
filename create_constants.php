<?php

class Constants{
	
	//DATABASE DETAILS
	static $DB_SERVER='localhost';
	static $DB_USERNAME='id2842189_jbapplab';
	static $DB_PASSWORD='tim!!357';
	static $DB_NAME='id2842189_anekdot';
	const TABLE_NAME = "story";
	
	//STATEMENTS
	static $SQL_SELECT_ALL = "SELECT * FROM story";
	static $SQL_SELECT_ART = "SELECT * FROM story WHERE story_category='Art'";
	static $SQL_SELECT_CAUSES = "SELECT * FROM story WHERE story_category='Causes'";
	static $SQL_SELECT_EDUCATION = "SELECT * FROM story WHERE story_category='Education'";
	static $SQL_SELECT_FOOD = "SELECT * FROM story WHERE story_category='Food'";
	static $SQL_SELECT_LIFESTYLE = "SELECT * FROM story WHERE story_category='Lifestyle'";
	static $SQL_SELECT_BUSINESS = "SELECT * FROM story WHERE story_category='Business'";
	static $SQL_SELECT_SPORTS = "SELECT * FROM story WHERE story_category='Sports'";
	static $SQL_SELECT_TRAVEL = "SELECT * FROM story WHERE story_category='Travel'";
	static $SQL_SELECT_SECURITY = "SELECT * FROM story WHERE story_category='Security'";
	static $SQL_SELECT_OTHER = "SELECT * FROM story WHERE story_category='Other'";
	
}

?>