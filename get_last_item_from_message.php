<?php
 
 if($_SERVER['REQUEST_METHOD']=='POST'){
 	$source_name = $_POST['source_name'];
	$channel = $_POST['channel'];
 
  	$mysqli = new mysqli("japannewstech.ipagemysql.com", "rssfeed", "Hongying@2017!", "rssfeed");
 	
	 /* check connection */
	if (mysqli_connect_errno()) {
    	printf("Connect failed: %s\n", mysqli_connect_error());
    	exit();
	}
	if (!$mysqli->set_charset("utf8")) {
            printf("Error loading character set utf8: %s\n", $mysqli->error);
            exit();
       } else {
 	$sql = "SELECT title FROM message where source_name='$source_name' and channel='$channel' ORDER BY id DESC
LIMIT 1";
 	
 	//executing query
 	$result = mysqli_query($mysqli,$sql);
 
 	//fetching result
 	$last_item = mysqli_query($mysqli,$sql)->fetch_object()->title;
 
 	//if we got some result 
 	if(!$mysqli->error){
 		echo $last_item;
 	}else{
 		echo "Error Could not get last item";
 	}
        }
 	mysqli_close($mysqli);
 
  }else{
	echo 'error';
}

?>