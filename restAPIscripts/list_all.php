<?php


date_default_timezone_set('US/Eastern');

function isWeekend($date) {
    $weekDay = date('w', strtotime($date));
    return ($weekDay == 5 || $weekDay == 6);
}

$currentDate = time('w');

$isWeekend = isWeekend($currentDate);

//echo gettype(.date('H:i:s').")

$open_flag = isset($_REQUEST["open_flag"])? $_REQUEST['open_flag'] : NULL;

$mysqli = new mysqli("localhost","root","","fedmedatabase");
if($mysqli->errno)
{
	echo "error connecting to the database";
	echo $mysqli->errno;
	
}
if($isWeekend && strtolower($open_flag) == "y"){
	
	$query = "SELECT item_id, i.name as name, p.name as place_name, cost, location as place_location, we_start, we_end, wd_start, wd_end from places as p join items as i where i.place_id = p.place_id and CAST(we_start as time) < '13:00:00' and CAST(we_end as time) > '13:00:00'";
	
}
else if(!$isWeekend && strtolower($open_flag) == "y"){
	$query = "SELECT item_id, i.name as name, p.name as place_name, cost, location as place_location, wd_start, wd_end, we_start, we_end  from places as p join items as i where i.place_id = p.place_id and CAST(wd_start as time) < '".date('H:i:s')."' and CAST(wd_end as time) > '".date('H:i:s')."' ";
	}
	else{
		$query = "SELECT item_id, i.name as name, p.name as place_name, cost, location as place_location, wd_start, wd_end, we_start, we_end  from places as p join items as i where i.place_id = p.place_id";
	}
$result =  $mysqli->query($query);



if(!$result)
{	
	echo "error in reading table";
	echo $mysqli->error;
	
}

$return_string= array();

while($row = $result->fetch_assoc()) {
            #echo $row;
			//echo json_encode( $row );
            $return_string[] = $row;
           	#$return_string = $return_string.$flatitude.",".$flongitude.",".$fresname."/";
	
}
#var_dump($return_string);
header('Content-type: application/json');

echo (json_encode( $return_string ));


$mysqli->close();


?>