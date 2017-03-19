 <?php
require_once 'dbconfig.php';

try {
    $conn = new PDO("mysql:host=$host;dbname=$dbname", $username, $password);
    // set the PDO error mode to exception
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    echo "Connected successfully";

	$statement=$conn->prepare("SELECT * FROM message");
	$statement->execute();
	$results=$statement->fetchAll(PDO::FETCH_ASSOC);
	$json=json_encode($results);
	print_r($json);
}
catch(PDOException $e)
    {
    echo "Connection failed: " . $e->getMessage();
    }
	
$conn = null;
?> 