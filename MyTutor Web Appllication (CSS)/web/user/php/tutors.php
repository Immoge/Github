<?php
include_once("dbconnect.php");
$sqltutor = "SELECT * FROM tbl_tutors";
$results_per_page = 10;
if (isset($_GET['pageno'])) {
    $pageno = (int)$_GET['pageno'];
    $page_first_result = ($pageno - 1) * $results_per_page;
} else {
    $pageno = 1;
    $page_first_result = 0;
}

$stmt = $conn->prepare($sqltutor);
$stmt->execute();
$number_of_result = $stmt->rowCount();
$number_of_page = ceil($number_of_result / $results_per_page);
$sqltutor = $sqltutor . " LIMIT $page_first_result , $results_per_page";
$stmt = $conn->prepare($sqltutor);
$stmt->execute();
$result = $stmt->setFetchMode(PDO::FETCH_ASSOC);
$rows = $stmt->fetchAll();
$conn = null;


function truncate($string, $length, $dots = "...")
{
    return (strlen($string) > $length) ? substr($string, 0, $length - strlen($dots)) . $dots : $string;
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
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="../js/menu.js" defer></script>
    <link rel="stylesheet" href="../css/style.css">
    <title>Tutor</title>
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

        #myBtn:hover {
            background-color: #555;
        }
        a {
            font-family: Lucida Sans Unicode;
            font-size: 25px;
        }

        b {
            font-family: cursive;
        }

        span {
            font-family: cursive;
        }
        p {
            font-family: cursive;
            font-size: 16px;
        }
    </style>
</head>

<body>
    <button onclick="topFunction()" id="myBtn" title="Go to top"> <i class="fa fa-arrow-circle-up"></i></button>
    <nav class="w3-sidebar w3-bar-block w3-card w3-top w3-xlarge w3-animate-left" style="display:none;z-index:2;width:40%;min-width:300px" id="mySidebar">
        <button onclick="w3_close()" class="w3-bar-item w3-button w3-large">Close &times;</button>
        <a href="index.php" class="w3-bar-item w3-button"><i class="fa fa-book"></i> Courses</a>
        <a href="tutors.php" class="w3-bar-item w3-button"><i class="fa fa-mortar-board"></i> Tutors</a>
        <a href="#" class="w3-bar-item w3-button"><i class="fa fa-tags"></i> Subscription</a>
        <a href="#" class="w3-bar-item w3-button"><i class="fa fa-id-card"></i> Profile</a>
    </nav>
    <!-- Top menu -->
    <div class="w3-top">
        <div class="w3-white w3-xlarge" style="margin:auto">
            <div class="w3-button w3-padding-16 w3-row w3-left" onclick="w3_open()">☰</div>
            <div class="w3-right w3-padding-16"></div>
            <a href="index.php" class="w3-bar-item w3-button w3-center w3-padding-16">MYTUTOR</a>
        </div>
    </div>

    <!-- Header -->
    <header class="w3-display-container w3-content w3-wide" style="max-width:1600px;">
        <img class="w3-image" src="../../res/backgound3.jpg" alt="Tutors" width="1600" height="500">
        <div class="w3-display-middle w3-margin-top w3-center">
            <h1 class="w3-jumbo w3-text-white"><span class="w3-padding w3-black "><b>TUTORS</b></h1>
        </div>
    </header>

    <div class="w3-main w3-content w3-padding" style="max-width:1800px;margin-top:100px">
        <div class="w3-grid-template">
            <?php
            $i = 0;
            foreach ($rows as $tutors) {
                $i++;
                $tutorid = $tutors['tutor_id'];
                $tutorname = truncate($tutors['tutor_name'], 25);
                $tutoremail = $tutors['tutor_email'];
                $tutorphone = $tutors['tutor_phone'];
                echo "<div class='w3-card-4 w3-round' style='margin:4px'>
                  <header class='w3-container w3-pale-green w3-center'><h5><b>$tutorname</b></h5></header>";
                echo "<a href='tutordetail.php?tutorid=$tutorid' style='text-decoration: none;'> <img class='w3-image' src=../../res/tutors/$tutorid.jpg" .
                    " onerror=this.onerror=null;this.src='../../res/avatar.png'"
                    . " style='width:100%;height:250px'></a><hr>";
                echo "<div class='w3-container'><p>Email: $tutoremail<br>Phone: $tutorphone<br></p></div>
                         </div>";
            }
            ?>
        </div>
    </div>
    <br>
    <?php
    $num = 1;
    if ($pageno == 1) {
        $num = 1;
    } else if ($pageno == 2) {
        $num = ($num) + 10;
    } else {
        $num = $pageno * 10 - 9;
    }
    echo "<div class='w3-container w3-row'>";
    echo "<center>";
    for ($page = 1; $page <= $number_of_page; $page++) {
        echo '<a href = "index.php?pageno=' . $page . '" style=
            "text-decoration: none">&nbsp&nbsp' . $page . ' </a>';
    }
    echo " ( " . $pageno . " )";
    echo "</center>";
    echo "</div>";
    ?>
    <br>

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

</html>