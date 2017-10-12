<?php

$ff_id = ($_REQUEST["ff_id"])? $_REQUEST["ff_id"] : NULL;
$user_id = ($_REQUEST["user_id"])? $_REQUEST["user_id"] : NULL;

$mysqli = new mysqli("localhost","root","","fedmedatabase");

if($mysqli->errno)
{
	echo "error connecting to the database";
	echo $mysqli->errno;	
}

$q_check = "Select * from upvotes where ff_id = '$ff_id' and user_id ='$user_id'";

$res = $mysqli->query($q_check);
$row = $res->fetch_assoc();

if(!$row){

$query = "Insert into upvotes(ff_id, user_id) values('".$ff_id."', '".$user_id."' )";

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