    <?php
    //Start session
    session_start();
     
    //Include database connection details
    require_once('connection.php');
     
    //Array to store validation errors
    $errmsg_arr = array();
     
    //Validation error flag
    $errflag = false;
     
    //Value Assignment
	$eventid=$_POST["EventID"];
	$eventname=$_POST["EventName"];
	$name=$_POST["Name"];
	$college=$_POST["College"];
	$course=$_POST["Course"];
	$dept=$_POST["Dept"];
	$email=$_POST["Email"];
	$mob=$_POST["Mob"];
	$year=$_POST["Year"];
	
	//Check if Member variables are set, if so, add them to the Variables
	if($_POST["TeamEvent"]=="true")
	{
	$teamname=$_POST["TeamName"];
    $member1=$_POST["Member1"];
	if(isset($_POST["Member2"])){$member2=$_POST["Member2"];}
	if(isset($_POST["Member3"])){$member3=$_POST["Member3"];}
	if(isset($_POST["Member4"])){$member4=$_POST["Member4"];}
	}	
	
	//Defualt Response	
	$response["success"] = 0; 
	
	//Actual Query Execution 
	if (!empty($_POST)) 
	{ 
	mysql_query("INSERT INTO asthra(name,event,eventid,college,course,dept,email,mob,year) VALUES('$eventid', '$eventname', '$name','$college', '$course', '$dept','$email', '$mob','$year')") or die(mysql_error());
	
	//If Team Event
	if($_POST["TeamEvent"]=="true")
	{
	//Add Mandatory Team Name and Member
	mysql_query("UPDATE asthra SET member1='$member1', team='$teamname' WHERE name='$name' and event='$eventname' and college='$college'  and mob='$mob' ") or die(mysql_error());
	//Add Optional Members	
	if(isset($member2)){mysql_query("UPDATE asthra SET member2='$member2' WHERE name='$name' and event='$eventname' and college='$college'  and mob='$mob' ") or die(mysql_error());}
	if(isset($member3)){mysql_query("UPDATE asthra SET member3='$member3' WHERE name='$name' and event='$eventname' and college='$college'  and mob='$mob' ") or die(mysql_error());}
	if(isset($member4)){mysql_query("UPDATE asthra SET member4='$member4' WHERE name='$name' and event='$eventname' and college='$college'  and mob='$mob' ") or die(mysql_error());}
	}
	$response["message"] = "Registration Successful!";
	$response["success"] = 1; 
	die(json_encode($response));
	}
	else
	{	 
	$response["message"] = "Registration Failed";
	die(json_encode($response)); 
	}
 
	?>