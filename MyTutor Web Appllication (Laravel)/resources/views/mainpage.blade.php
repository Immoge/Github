<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Main Page</title>
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

        #fixedbutton {
            position: fixed;
            bottom: 40px;
            right: 40px;
            font-size: 40px
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
            <a href="#home" class="w3-bar-item w3-button">MYTUTOR</a>
            <div class="w3-right">
                <a href="{{ url('logout') }}" class="w3-bar-item w3-button">Logout</a>
            </div>
        </div>
    </div>
    <header class="w3-display-container w3-content w3-wide" style="max-width:1600px;min-width:500px" id="main page">
    <img class="w3-image" src="/images/banner2.png" alt="Main Page" width="1600" height="800">
    </header>
    <div>
        <button class="w3-button w3-circle w3-cyan border-radius: 20px;" onclick="document.getElementById('newsubject').style.display= 'block';return false;" id="fixedbutton">+</button>
    </div>

        <div class="w3-padding" style= "margin:auto">
        <table class="w3-table w3-striped w3-bordered ">
            <thead class = "w3-cyan">
                <th><h5>No</h5></th>
                <th><h5>ID</h5></th>
                <th><h5>Title</h5></th>
                <th><h5>Description</h5></th>
                <th><h5>Price (RM)</h5></th>
                <th><h5>Hour</h5></th>
                <th><h5>Operations</h5></th>
            </thead>
            
            @foreach ($listSubjects as $listSubject)
            <tr>
                <td><h5>{{ $loop->iteration }}</h5></td>
                <td><h5>{{ $listSubject->id}}</h5></td>
                <td><h5>{{ $listSubject->subject_title}}</h5></td>
                <td><h5>{{ $listSubject->subject_description}}</h5></td>
                <td><h5>{{ $listSubject->subject_price}}</h5></td>
                <td><h5>{{ $listSubject->subject_hour}}</h5></td>
                <td>
                    <div class="w3-cell">
                        <form method="post" action="{{route('markDelete',$listSubject->id)}}" accept-charset="UTF-8" onsubmit="return confirm('Delete?');">
                            {{csrf_field()}}
                            <button class="w3-button w3-round w3-block" type="submit">
                                <i class="fa fa-trash"> </i></button>
                        </form>
                    </div>
                    <div class="w3-cell">
                        <button class="w3-button w3-round w3-block" onclick="document.getElementById('{{$loop->iteration}}').style.display='block';return false;"><i class="fa fa-pencil-square-o"> </i>
                        </button>
                    </div>
                    <div id="{{$loop->iteration}}" class="w3-modal w3-animate-opacity">
                        <div class="w3-modal-content w3-round" style="width:500px">
                            <header class="w3-row w3-cyan"> <span onclick="document.getElementById('{{$loop->iteration}}').style.display='none'" class="w3-button w3-display-topright w3-small">&times;</span>
                                <h4 class="w3-margin-left">Edit Subject Details</h4>
                            </header>
                            <div class="w3-padding">
                                <form method="post" action="{{route('markUpdate',$listSubject->id)}}">
                                    {{csrf_field()}}
                                    <p><input class="w3-input w3-round w3-border" type="text" name="subtitle" placeholder="Name" value ="{{ $listSubject->subject_title}}"></p>
                                    <p><input class="w3-input w3-round w3-border" type="text" name="subdesc" placeholder="Description" value ="{{ $listSubject->subject_description}}"></p>
                                    <p><input class="w3-input w3-round w3-border" type="number" name="subprice" placeholder="Price" step="any" value ="{{ $listSubject->subject_price}}"></p>
                                    <p><input class="w3-input w3-round w3-border" type="number" name="subhour" placeholder="Hour" value ="{{ $listSubject->subject_hour}}"></p>
                                    </textarea></p>
                                    <button class="w3-button w3-cyan w3-round" type="submit">Update</button>
                                </form>
                            </div>
                        </div>
                    </div>

                </td>
            </tr>
            @endforeach


        </table>
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
    
    <div id="newsubject" class="w3-modal w3-animate-opacity">
        <div class="w3-modal-content w3-round" style="width:500px">
            <header class="w3-row w3-cyan"> <span onclick="document.getElementById
     ('newsubejct').style.display='none'" class="w3-button w3-display-topright w3-small">&times;</span>
                <h4 class="w3-margin-left">New Subject</h4>
            </header>
            <div class="w3-padding">
                <form method="post" action="{{route('savesubject')}}">
                    {{csrf_field()}}
                    <p><input class="w3-input w3-round w3-border" type="text" name="subtitle" placeholder="Name"></p>
                                    <p><input class="w3-input w3-round w3-border" type="text" name="subdesc" placeholder="Description"></p>
                                    <p><input class="w3-input w3-round w3-border" type="number" name="subprice" placeholder="Price" step="any"></p>
                                    <p><input class="w3-input w3-round w3-border" type="number" name="subhour" placeholder="Hour" step="any"></p>
                                    </textarea></p>
                    <button class="w3-button w3-cyan w3-round" type="submit">Add</button>
                </form>
            </div>
        </div>
    </div>
</body>

</html>