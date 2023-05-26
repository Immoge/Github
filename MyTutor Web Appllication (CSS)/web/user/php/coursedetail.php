<?php
include_once("dbconnect.php");

if (isset($_SESSION['sessionid'])) {
    $cust_email = $_SESSION['email'];
} else {
    $cust_email = "guest@immoge.com";
}

if (isset($_POST['submit'])) {
    $subid = $_POST['subid'];
    if ($cust_email == "guest@immoge.com") {
        echo "<script>alert('Please register an account first.');</script>";
        echo "<script> window.location.replace('registration.php')</script>";
    } else {
        echo "<script> window.location.replace('coursedetail.php?subid=$subid')</script>";
        echo "<script>alert('OK.');</script>";
    }
}
if (isset($_GET['subid'])) {
    $subid = $_GET['subid'];
    $sqlsubject = "SELECT * FROM tbl_subjects INNER JOIN tbl_tutors ON tbl_subjects.tutor_id = tbl_tutors.tutor_id WHERE subject_id = '$subid'";
    $stmt = $conn->prepare($sqlsubject);
    $stmt->execute();
    $number_of_result = $stmt->rowCount();
    if ($number_of_result > 0) {
        $result = $stmt->setFetchMode(PDO::FETCH_ASSOC);
        $rows = $stmt->fetchAll();
    } else {
        echo "<script>alert('Course not found.');</script>";
        echo "<script> window.location.replace('courses.php')</script>";
    }
} else {
    echo "<script>alert('Page Error.');</script>";
    echo "<script> window.location.replace('courses.php')</script>";
}

?>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <script src="../js/menu.js" defer></script>
    <link rel="stylesheet" href="../css/style.css">
    <title>Course Detail</title>
    <style>
        #myBtn {
            display: none;
            position: fixed;
            bottom: 20px;
            right: 30px;
            z-index: 99;
            font-size: 18px;
            border: none;
            outline: none;
            background-color: darkblue;
            color: white;
            cursor: pointer;
            padding: 15px;
            border-radius: 4px;
        }

        h2 {
            font-family: cursive;
            font-size: 40px;
        }

        p {
            font-family: cursive;
            font-size: 20px;
        }
    </style>
</head>

<body>
    <button onclick="topFunction()" id="myBtn" title="Go to top"> <i class="fa fa-arrow-circle-up"></i></button>
    <nav class="w3-sidebar w3-bar-block w3-card w3-top w3-xlarge w3-animate-left" style="display:none;z-index:2;width:40%;min-width:300px" id="mySidebar">
        <button onclick="w3_close()" class="w3-bar-item w3-button w3-large">Close &times;</button>
        <a href="courses.php" class="w3-bar-item w3-button"><i class="fa fa-book"></i> Courses</a>
        <a href="tutors.php" class="w3-bar-item w3-button"><i class="fa fa-mortar-board"></i> Tutors</a>
        <a href="#" class="w3-bar-item w3-button"><i class="fa fa-tags"></i> Subscription</a>
        <a href="#" class="w3-bar-item w3-button"><i class="fa fa-id-card"></i> Profile</a>
    </nav>
    <!-- Top menu -->
    <div class="w3-top">
        <div class="w3-white w3-xlarge" style="margin:auto">
            <div class="w3-button w3-padding-16 w3-row w3-left" onclick="w3_open()">☰</div>
            <a href="courses.php" class="w3-bar-item w3-button w3-right w3-padding-16 w3-border">BACK</a>
            <div class="w3-right w3-padding-16"></div>
            <a href="index.php" class="w3-bar-item w3-button w3-center w3-padding-16">MYTUTOR</a>
        </div>
    </div>

    <div class="w3-container w3-padding-32 w3-margin">
        <div class="w3-round w3-card-4 w3-border w3-padding w3-white" style="max-width:1000px;margin:0 auto;">
            <?php
            foreach ($rows as $subjects) {
                $subid = $subjects['subject_id'];
                $subname = ($subjects['subject_name']);
                $subdescription = $subjects['subject_description'];
                $subprice = number_format((float)$subjects['subject_price'], 2, '.', '');
                $subsession = $subjects['subject_sessions'];
                $subrating = number_format((float)$subjects['subject_rating'], 2, '.', '');
                $tutorid = $subjects['tutor_id'];
                $tutoremail = $subjects['tutor_email'];
                $tutorname = $subjects['tutor_name'];
            }

            echo "<div class='w3-padding w3-card w3-center'><img class='w3-image resimg' src=../../res/courses/$subid.png" .
                " onerror=this.onerror=null;this.src='../../res/avatar.png'"
                . " ></div><hr>";
            echo "<div class='w3-container w3-cyan w3-round w3-padding-large'><h2>$subname</h2>";
            echo " <div><p><b>Description</b><br>$subdescription</p><p><b>Price: </b>RM$subprice</p><p><b>Tutor:</b> $tutorid</p><p><b>Session:</b> $subsession</p><p><b>Rating: </b> $subrating</p>
        </div></div>";

            echo "<div class='w3-padding w3-card w3-center'><b><h2>Tutor</h2></b>";
            echo "<div class='w3-row'><div class='w3-quarter w3-container'>
              <div><a href='tutordetail.php?tutorid=$tutorid' style='text-decoration: none;'><img class='w3-image w3-circle resimg' src='../../res/tutors/$tutorid.jpg'" .
                " onerror=this.onerror=null;this.src='../res/avatar.png'"
                . " style='width:100%'></div></div></a><br>";

            echo "<div class='w3-threequarter w3-container'>
        <p><b>Name: </b>$tutorname<b></p><p>Email: </b>$tutoremail</p>
        </div></div></div><div>";
      
      ?>
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
    <script>
        var mybutton = document.getElementById("myBtn");
        window.onscroll = function() {
            scrollFunction()
        };

        function scrollFunction() {
            if (document.body.scrollTop > 20 || document.documentElement.scrollTop > 20) {
                mybutton.style.display = "block";
            } else {
                mybutton.style.display = "none";
            }
        }

        function topFunction() {
            document.body.scrollTop = 0;
            document.documentElement.scrollTop = 0;
        }
    </script>
</body>
</body>

</html>