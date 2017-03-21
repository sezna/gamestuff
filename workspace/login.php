<?php
	session_start();

	if ($_SERVER['SERVER_NAME'] != "dias11.cs.trinity.edu") {
    	echo "<p>You must access this page from on campus through dias11.</p></body></html>";
    	die ();
	}
	//var_dump($_SERVER);


 	$conn = mysqli_connect("127.0.0.1", "gamestuff", "GetBackToWork", "gamestuff");
 	if ($conn->connect_errno) {
     	echo "Failed to connect to MySQL: (" . $conn->connect_errno . ") " . $conn->connect_error;
 	}

	echo $conn->host_info . "\n";

	function mysql_fix_string($string) {
		$string = stripslashes($string);
		return $conn->real_escape_string($string);
	}



	$user = "";
	$pass = "";
	if ($_SERVER["REQUEST_METHOD"] == "POST") {
 		$user =$_POST["username"];
		$pass =$_POST["password"];
	}
	echo $user;
	


	$query = "SELECT * From users where username = '" . $user . "' AND password = '" . $pass."'" ;

	//where username = $user AND password = $pass


	$result = mysqli_query($conn,$query);

	if ($result->num_rows > 0) {
    // output data of each row
	    while($row = mysqli_fetch_assoc($result)) {
	        //echo $row["id"]. " - Name: " . $row["username"]. " " . $row["password"]. "<br>";
	        $_SESSION["userName"] = $row["username"];
	        header('Location: game.html');
	    }
	} else {
    	header('Location: index.html');	
	}

?>