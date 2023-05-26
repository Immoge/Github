<?php
if (isset($_POST['submit'])) {
  include 'dbconnect.php';
  $email = $_POST['email'];
  $pass = sha1($_POST['password']);

  $sqllogin = "SELECT * FROM tbl_user WHERE user_email = '$email' AND user_password = '$pass'";

  $stmt = $conn->prepare($sqllogin);
  $stmt->execute();
  $number_of_rows = $stmt->fetchColumn();
  if ($number_of_rows  > 0) {
    session_start();
    $_SESSION["sessionid"] = session_id();
    $_SESSION["email"] = $email;
    echo "<script>alert('Login Success');</script>";
    echo "<script> window.location.replace('index.php')</script>";
  } else {
    echo "<script>alert('Login Failed');</script>";
    echo "<script> window.location.replace('login.php')</script>";
  }
}
?>

<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>User Login</title>
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <script src="../user/js/login.js" defer></script>
  <style>
    p {
      font-family: cursive;
      font-size: 16px;
    }

    label {
      font-family: cursive;
      font-size: 16px;
    }
    
  </style>
</head>


<body onload="loadCookies()">
  <div style=" display:flex; justify-content:center">
    <div class="w3-container w3-padding-32 w3-margin" style="width:600px;margin:auto;text-align:left;">

      <form name="loginForm" action="login.php" method="post" class="w3-round w3-card-4 w3-border w3-padding w3-white">
        <div class="w3-container w3-center">
          <img class="w3-image w3-margin" src="../../res/mytutor.png" style="height:200px;width:200px"><br>
        </div>
        <div class="w3-card w3-padding">
          <label><b>Email Address</b></label>
          <input class="w3-input w3-round" type="email" name="email" id="idemail" placeholder="Enter email" required>
        </div>
        <br>
        <div class="w3-round w3-card w3-padding">
          <label><b>Password</b></label>
          <input class="w3-input w3-round" type="password" name="password" id="idpassword" placeholder="Enter password" required>
        </div>
        <p>
          <input class="w3-check" name="rememberme" type="checkbox" id="idremember" onclick="rememberMe()">
          <label>Remember Me</label>
        </p>
        <div>
          <p>
            <input class="w3-button w3-round w3-border w3-cyan w3-block" type="submit" name="submit" id="idsubmit" value="Login">
          </p>
        </div>
        <div class="container">
          <a href="registeruser.php" style="color:blue;">Don't have an account? Register here</a>
        </div>
        <br>
      </form>
    </div>
  </div>
  <div id="cookieNotice" class="w3-right w3-block" style="display: none;">
    <div class="w3-red">
      <h4>Cookie Consent</h4>
      <p>This website uses cookies or similar technologies, to enhance your
        browsing experience and provide personalized recommendations.
        By continuing to use our website, you agree to our
        <a style="color:#115cfa;" href="/privacy-policy">Privacy Policy</a>
      </p>
      <div class="w3-button">
        <button onclick="acceptCookieConsent();">Accept</button>
      </div>
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

<script>
  let cookie_consent = getCookie("user_cookie_consent");
  if (cookie_consent != "") {
    document.getElementById("cookieNotice").style.display = "none";
  } else {
    document.getElementById("cookieNotice").style.display = "block";
  }
</script>

</html>