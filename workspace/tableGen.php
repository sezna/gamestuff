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


	$query = "SELECT * From users where username = '$id'";

	$result = mysqli_query($conn,$query);

	if ($result->num_rows > 0) {
		print("<table>");
	    $row = mysqli_fetch_assoc($result);
	    	print("<tr>");
	        print("<td>Your High Scores </td>");
	        print("</tr>");

	        print("<tr>");
	        print("<td>".$row["highScore1"]."</td>");
	        print("</tr>");
	        
	        print("<tr>");
	        print("<td>".$row["highScore2"]."</td>");
	        print("</tr>");
	        print("<tr>");
	        print("<td>".$row["highScore3"]."</td>");
	        print("</tr>");
	    print("</table>");
	}


?>