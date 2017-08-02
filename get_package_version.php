<?php
 
 if($_SERVER['REQUEST_METHOD']=='POST'){
        $program_name = $_POST['program_name'];

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
 	$sql = "SELECT version_name FROM package_version WHERE program_name = '$program_name'";
 	
  	//executing query
 	$result = mysqli_query($mysqli,$sql);

 	//fetching result
 	$version_name = mysqli_query($mysqli,$sql)->fetch_object()->version_name;
 
 	//if we got some result 
 	if(!$mysqli->error){
 		echo $version_name;
 	}else{
 		echo "Error Could not get version_name";
 	}
        }
 	mysqli_close($mysqli);
 
  }else{
	echo 'error';
}

?>