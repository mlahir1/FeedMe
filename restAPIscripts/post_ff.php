<?php

date_default_timezone_set('US/Eastern');
$ff_name = ($_REQUEST["name"])? $_REQUEST["name"] : NULL;
$ff_location = ($_REQUEST["location"])? $_REQUEST["location"] : NULL;
$start_time = isset($_GET["start_time"])? date('Y-m-d H:i:s', strtotime($_GET["start_time"])) : date('Y-m-d H:i:s');
$end_time = isset($_GET["end_time"])? date('Y-m-d H:i:s', strtotime($_GET["end_time"])) : date('Y-m-d H:i:s', strtotime('+3 hours'));

$mysqli = new mysqli("localhost","root","","fedmedatabase");

if($mysqli->errno)
{
	echo "error connecting to the database";
	echo $mysqli->errno;	
}

$sub_query= "select count(*) as count from free_food where name = '".$ff_name."' AND location = '".$ff_location."' and start_time = '".$start_time."' and end_time = '".$end_time."'";
$res = $mysqli->query($sub_query);
$row =  $res->fetch_assoc();



$currentTime =   date('Y-m-d H:i:s');
if($row['count'] == 0){
	$query = "Insert into free_food(name, location, start_time, end_time,ins_ts) values('".$ff_name."', '".$ff_location."', '".$start_time."', '".$end_time."' , '$currentTime')";

$result =  $mysqli->query($query);
if(!$result)
{	
	echo "error in reading table";
	echo $mysqli->error;
	
}
else{
	echo "Insert Successfull";
}
}
$mysqli->close();

?>