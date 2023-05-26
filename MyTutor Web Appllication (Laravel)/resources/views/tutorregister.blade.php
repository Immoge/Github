<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Tutor Register</title>
    <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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
        }
    </style>
</head>


<body>

    @if (session('save'))
    <script>
        alert("Success");
    </script>
    @endif
    @if (session('error'))
    <script>
        alert("Failed");
    </script>
    @endif

    <div class="w3-top">
        <div class="w3-bar w3-white w3-padding w3-card w3-large" style="letter-spacing:4px;">
            <a href="{{ url('landing') }}" class="w3-bar-item w3-button">MYTUTOR</a>
            <div class="w3-right">
                <a href="{{ url('login') }}" class="w3-bar-item w3-button">Login</a>
            </div>
        </div>
    </div>
    <div class="w3-padding-32">
        <div class="w3-row w3-padding-large w3-card-4 w3-border" id="register">
            <div class="w3-col m6 w3-padding-32">
                <img src="/images/mytutor.png" class="w3-round w3-image" alt="logo" width="600" height="750">
            </div>

            <div class="w3-col m6 w3-padding-large w3-left ">
                <h1 class="w3-center">Registration Form</h1><br>
                <form action=" {{route('register.post') }}" method="post">
                    {{csrf_field()}}
                    <label for="name">
                        <h4>Name</h4>
                    </label>
                    <p><input id="name" class="w3-input w3-round w3-border" type="name" name="name" placeholder="Name" required></p>
                    @if ($errors->has('name'))
                    <span class="text-danger">{{ $errors->first('name') }}</span>
                    @endif
                    <label for="email">
                        <h4>Email</h4>
                    </label>
                    <p><input id="email" class="w3-input w3-round w3-border" type="email" name="email" placeholder="Email Address" required></p>
                    @if ($errors->has('email'))
                    <span class="text-danger">{{ $errors->first('email') }}</span>
                    @endif
                    <label for="phone">
                        <h4>Phone Number</h4>
                    </label>
                    <p><input id="phone" class="w3-input w3-round w3-border" type="tel" name="phone" placeholder="Phone Number" required></p>
                    @if ($errors->has('phone'))
                    <span class="text-danger">{{ $errors->first('phone') }}</span>
                    @endif
                    <label for="address">
                        <h4>Mailing Address</h4>
                    </label>
                    <p><input id="address" class="w3-input w3-round w3-border" type="text" name="address" placeholder="Mailing Address" required></p>
                    @if ($errors->has('address'))
                    <span class="text-danger">{{ $errors->first('address') }}</span>
                    @endif
                    <label for="state">
                        <h4>State</h4>
                    </label>
                    <p><input id="state" class="w3-input w3-round w3-border" type="text" name="state" placeholder="State" required></p>
                    @if ($errors->has('state'))
                    <span class="text-danger">{{ $errors->first('state') }}</span>
                    @endif
                    <label for="pass">
                        <h4>Password</h4>
                    </label>
                    <p><input id="pass" class="w3-input w3-round w3-border" type="password" name="password" placeholder="Password" required></p>
                    @if ($errors->has('password'))
                    <span class="text-danger">{{ $errors->first('password') }}</span>
                    @endif
                    <button class="w3-button w3-round w3-border w3-cyan w3-block">
                        <h4>Register</h4>
                    </button>
                    <div class="container">
                        <a href="{{ url('login') }}" style="color:blue;">
                            <h5>I am already registered</h5>
                        </a>
                    </div>
                </form>
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