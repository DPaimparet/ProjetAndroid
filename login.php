<?php
include_once("connection.php"); 

if( !isset($_POST['txtUsername']) || !isset($_POST['txtPassword']) || empty($_POST['txtUsername']) || empty($_POST['txtPassword'])  ) { 
	echo "Problème de POST<br/>";
	exit;
}

/***********************************************************************/

$username = $_POST['txtUsername'];
$password = $_POST['txtPassword'];

$query = "SELECT username, password FROM tbl_joueur WHERE username = '$username' AND password = '$password'"; 

$result = mysqli_query($conn, $query);

if($result->num_rows > 0){ // avec DB
//if("test" == $username && "password" == $password){ //Sans DB
    echo "success"; 
    exit; 
}else{ 
    echo "Login Failed <br/>"; 
} 

?>