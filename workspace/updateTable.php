<?php
	session_start();
	//echo "Pageviews = ". $_SESSION['views'] . "<br>";
	//var_dump($_SESSION);
	//var_dump($_SESSION)
	$id = $_SESSION["userName"];
	$conn = mysqli_connect("127.0.0.1", "gamestuff", "GetBackToWork", "gamestuff");
 	if ($conn->connect_errno) {
     	//echo "Failed to connect to MySQL: (" . $conn->connect_errno . ") " . $conn->connect_error;
 	}

 	$score = $_POST["score"];

	$query = "SELECT * From users where username = '$id'";

	$result = mysqli_query($conn,$query);

	if ($result->num_rows > 0) {
	    $row = mysqli_fetch_assoc($result);
	    if($score > $row["highScore1"]){

	    	$query3 = "UPDATE users set highScore3 = highScore2 where username = '$id'";
	    	mysqli_query($conn,$query3);

	    	$query2 = "UPDATE users set highScore2 = highScore1 where username = '$id'";
	    	mysqli_query($conn,$query2);

	    	$query1 = "UPDATE users set highScore1 = $score where username = '$id'";
	    	mysqli_query($conn,$query1);
	    } else if($score > $row["highScore2"]){
	    	$query3 = "UPDATE users set highScore3 = highScore2 where username = '$id'";
	    	mysqli_query($conn,$query3);
	    	
	    	$query2 = "UPDATE users set highScore2 = $score where username = '$id'";
	    	mysqli_query($conn,$query2);
	    } else if($score > $row["highScore3"]){
	    	$query3 = "UPDATE users set highScore3 = $score where username = '$id'";
	    	mysqli_query($conn,$query3);
	    }
	}


?>