<?php
if (isset($_POST['submit'])) {
  include_once("dbconnect.php");
  $userEmail = addslashes($_POST['useremail']);
  $userName = addslashes($_POST['username']);
  $userPassword = sha1($_POST['userpassword']);
  $userPhone = $_POST['userphone'];
  $userAddress = $_POST['useraddress'];
  $sqlinsertuser = "INSERT INTO `tbl_user`(`user_email`, `user_name`,`user_password`, 
    `user_phone`, `user_homeAddress`) VALUES 
    ('$userEmail','$userName','$userPassword',$userPhone,'$userAddress')";
  try {
    $conn->exec($sqlinsertuser);
    if (file_exists($_FILES["fileToUpload"]["tmp_name"]) || is_uploaded_file($_FILES["fileToUpload"]["tmp_name"])) {
      $last_id = $conn->lastInsertId();
      uploadImage($last_id);
      echo "<script>alert('Success')</script>";
      echo "<script>window.location.replace('login.php')</script>";
    }
  } catch (PDOException $e) {
    echo "<script>alert('Failed')</script>";
    echo "<script>window.location.replace('registeruser.php')</script>";
  }
}

function uploadImage($filename)
{
  $target_dir = "../../res/profile/";
  $target_file = $target_dir . $filename . ".png";
  move_uploaded_file($_FILES["fileToUpload"]["tmp_name"], $target_file);
}
?>


<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Register</title>
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="../js/script.js" defer></script>
</head>
<body>
  <div style="display:flex; justify-content:center">
    <div class="w3-container w3-padding-32 w3-margin" style="width:600px;margin:auto;text-align:left;">
      <form class="w3-round w3-card-4 w3-border w3-padding w3-white" action="registeruser.php" method="post" enctype="multipart/form-data" onsubmit="return confirm('Are you sure to register?')">
        <div class="w3-container w3-center">
          <img class="w3-image w3-margin" src="../../res/avatar.png" style="height:200px;width:200px"><br>
          <input type="file" name="fileToUpload" onchange="previewFile()">
        </div>
        <br>
        <div class="w3-card w3-padding">
          <label><b>Email Address</b></label>
          <input class="w3-input w3-round" name="useremail" type="email" required>
        </div>
        <br>
        <div class="w3-card w3-padding">
          <label><b>Username</b></label>
          <input class="w3-input w3-round" name="username" type="text" required>
        </div>
        <br>
        <div class="w3-card w3-padding">
          <label><b>Password</b></label>
          <input class="w3-input w3-round" name="userpassword" type="password" required>
        </div>
        <br>
        <div class="w3-card w3-padding">
          <label><b>Phone Number</b></label>
          <input class="w3-input w3-round" name="userphone" type="text" required>
        </div>
        <br>
        <div class="w3-card w3-padding">
          <label><b>Home Address</b></label>
          <input class="w3-input w3-round" name="useraddress" type="text" required>
        </div>
        <br>
        <div>
          <p>
            <input class="w3-button w3-round w3-border w3-cyan w3-block" type="submit" name="submit" id="idsubmit" value="Register">
          </p>
        </div>
        <div class="container">
          <a href="login.php" style="color:blue;">I am already member</a>
        </div>
        <br>
      </form>
    </div>
  </div>
  <footer class="w3-padding-24 w3-black w3-center w3-margin-top">
    <h2>Find Us On</h2>
    <div class="w3-xlarge w3-padding-16">
      <a href="http://facebook.com/" class="fa fa-facebook"></a>
      <a href="http://youtube.com/" class="fa fa-youtube"></a>
      <a href="http://instagram.com/" class="fa fa-instagram"></a>
    </div>
    <p>Copyright&copy Chong Qi Jun</a></p>
  </footer>
</body>
</html>