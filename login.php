<?PHP 
    include_once("connection.php"); 
    defined("USERNAME" , "test");
    defined("PASSWORD" , "password");
    if( isset($_POST['txtUsername']) && isset($_POST['txtPassword']) ) { 
        $username = $_POST['txtUsername'];
        $password = $_POST['txtPassword'];
        
        $query = "SELECT username, password FROM tbl_joueur WHERE username = '$username' AND password = '$password'"; 
        
        $result = mysqli_query($conn, $query);
        
        if($result->num_rows > 0){
            if(isset($_POST['mobile']) && $_POST['mobile'] == "android"){ 
                echo "success"; 
                exit; 
            } 
        }else{ 
            echo "Login Failed <br/>"; 
        } 
    } 
?>

?>