 <?php
require_once 'dbconfig.php';

try {
    $conn = new PDO("mysql:host=$host;dbname=$dbname", $username, $password);
    // set the PDO error mode to exception
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    echo "Connected successfully";

    // begin the transaction
    $conn->beginTransaction();
    // our SQL statements
		$conn->exec("INSERT INTO channel (name, time_created)
    VALUES ('domestic', now())");
		$conn->exec("INSERT INTO channel (name, time_created)
    VALUES ('international', now())");
	    $conn->exec("INSERT INTO channel (name, time_created)
    VALUES ('business', now())");
	    $conn->exec("INSERT INTO channel (name, time_created)
    VALUES ('entertaiment', now())");
	    $conn->exec("INSERT INTO channel (name, time_created)
    VALUES ('sports', now())");
	    $conn->exec("INSERT INTO channel (name, time_created)
    VALUES ('science', now())");
	    $conn->exec("INSERT INTO channel (name, time_created)
    VALUES ('life', now())");
	    $conn->exec("INSERT INTO channel (name, time_created)
    VALUES ('region', now())");
		$conn->exec("INSERT INTO channel (name, time_created)
    VALUES ('megazine', now())");
	
    // commit the transaction
    $conn->commit();
	
}
catch(PDOException $e)
    {
    echo "Connection failed: " . $e->getMessage();
    }
	
$conn = null;
?> 