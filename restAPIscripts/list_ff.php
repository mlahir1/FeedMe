<?php



$mysqli = new mysqli("localhost","root","","fedmedatabase");
if($mysqli->errno)
{
	echo "error connecting to the database";
	echo $mysqli->errno;
	
}

	$query = "SELECT DISTINCT ff_id, name, location, start_time, end_time, (select count(1) from upvotes where ff_id=f.ff_id) as up_count from free_food as f  order by up_count DESC, start_time ";

$result =  $mysqli->query($query);
if(!$result)
{	
	echo "error in reading table";
	echo $mysqli->error;
	
}

$return_string=[];
while($row = $result->fetch_assoc()) {
            #echo $row;
            array_push($return_string,$row);
           	#$return_string = $return_string.$flatitude.",".$flongitude.",".$fresname."/";
	
}
header('Content-type: application/json');
echo json_encode( $return_string );
$mysqli->close();

?>