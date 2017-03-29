<?php
$mysqli = new mysqli("localhost", "root", "Hongying@2017!", "rssfeed");

/* check connection */
if (mysqli_connect_errno()) {
    printf("Connect failed: %s\n", mysqli_connect_error());
    exit();
}

header("Content-Type", "application/json");

$json = array();

//printf("Initial character set: %s\n", $mysqli->character_set_name());

/* change character set to utf8 */
if (!$mysqli->set_charset("utf8")) {
    printf("Error loading character set utf8: %s\n", $mysqli->error);
    exit();
} else {
    //printf("Current character set: %s\n", $mysqli->character_set_name());
	$stmt = $mysqli->query("SELECT id, source_name, channel, title, link, has_image, pub_date, image_url, image_width, image_height FROM message");
	
	while($rows = $stmt->fetch_object()){
		$json[] = $rows;
	}
	json_encode($json);	
	
	$stmt->free_result();
	
	//foreach ($json as $row) {
	//	foreach ($row as $element){
	//		echo $element;
	//	}
	//}

}

$mysqli->close();
