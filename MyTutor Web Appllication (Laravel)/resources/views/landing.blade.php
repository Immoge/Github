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
    <title>My Tutor Landing Page</title>
</head>
<style>
    body {
        font-family: "Times New Roman", Georgia, Serif;
    }

    h1,
    h2,
    h3,
    h4,
    h5,
    h6 {
        font-family: "Playfair Display";
        letter-spacing: 5px;
    }

</style>

<body>
    <div class="w3-top">
        <div class="w3-bar w3-white w3-padding w3-card w3-large" style="letter-spacing:4px;">
            <a href="{{ url('landing') }}" class="w3-bar-item w3-button">MYTUTOR</a>
            <div class="w3-right">
                <a href="{{ url('registration') }}" class="w3-bar-item w3-button">Register</a>
                <a href="{{ url('login') }}" class="w3-bar-item w3-button">Login</a>
            </div>
        </div>
    </div>
    <!-- Header -->
    <header class="w3-display-container w3-content w3-wide" style="max-width:1600px;min-width:500px" id="home">
        <img class="w3-image" src="/images/mytutorlandingpage.png" alt="landing page" width="1600" height="800">
    </header>
    <hr>
    <div class="w3-row w3-padding-large" id="about">
        <div class="w3-col m6 w3-padding-32">
            <img src="/images/mytutor.png" class="w3-round w3-image" alt="logo" width="600" height="750">
        </div>

        <div class="w3-col m6 w3-padding-large w3-center ">
            <h1 class="w3-center">About MYTUTOR</h1><br>
            <h5 class="w3-center">Founded in 2010</h5>
            <p class="w3-large">At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.</p>
        </div>
    </div>
    <hr>

    <div class="w3-row w3-padding-64 w3-center" id="contact us">
        <div class="w3-col l6 w3-padding-large">
            <h1 class="w3-center">Contact Us</h1><br>
            <h5 class="w3-center">Phone Number: 016-4926695</h5>
            <p class="w3-large">At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere possimus, omnis voluptas assumenda est, omnis dolor repellendus. Temporibus autem quibusdam et aut officiis debitis aut rerum necessitatibus saepe eveniet ut et voluptates repudiandae sint et molestiae non recusandae. Itaque earum rerum hic tenetur a sapiente delectus, ut aut reiciendis voluptatibus maiores alias consequatur aut perferendis doloribus asperiores repellat.</p>
        </div>

        <div class="w3-col l6 w3-padding-large">
            <img src="/images/uum.jpeg" class="w3-round w3-image" alt="contact" style="width:100%">
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

</html>