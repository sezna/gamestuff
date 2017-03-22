<?php
	

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
		$pass =$_POST["password1"];
		$pass2 = $_POST["password2"];
	}
	//echo $user;
	
	if(strcmp($pass,$pass2) == 0){
		$query = "INSERT into users Values ('".$user."', '".$pass."', 0,0,0)" ;
		//echo $query;
		$result = mysqli_query($conn,$query);
		if($result == TRUE){
			header('Location: index.html');	
		} else {
			header('Location: newUser.php');
		}
		

	}else{
		//echo "<html><script> window.alert('Passwords don't match); </script></html>";
		header('Location: newUser.php');	
	}

	//echo "<html><script> window.alert('Passwords don't match); </script></html>";
   	//header('Location: newUser.php');	

?>