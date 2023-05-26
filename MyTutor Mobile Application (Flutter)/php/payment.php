<?php
$email = $_GET['email'];
$mobile = $_GET['mobile']; 
$name = $_GET['name']; 
$amount = $_GET['amount']; 


$api_key = 'ff8c128f-73f5-446e-a1f8-5c46fdb5bdcb';
$collection_id = 'w7zlyl8b';
 
$host = 'https://www.billplz-sandbox.com/api/v3/bills';


$data = array(
          'collection_id' => $collection_id,
          'email' => $email,
          'mobile' => $mobile,
          'name' => $name,
          'amount' => ($amount + 1) * 100,
		  'description' => 'Payment for order by '.$name,
          'callback_url' => "https://yuelle.com/immoge/mytutor/mobile/php/return_url",
          'redirect_url' => "https://yuelle.com/immoge/mytutor/mobile/php/payment_update.php?email=$email &mobile=$mobile&amount=$amount&name=$name"
);


$process = curl_init($host );
curl_setopt($process, CURLOPT_HEADER, 0);
curl_setopt($process, CURLOPT_USERPWD, $api_key . ":");
curl_setopt($process, CURLOPT_TIMEOUT, 30);
curl_setopt($process, CURLOPT_RETURNTRANSFER, 1);
curl_setopt($process, CURLOPT_SSL_VERIFYHOST, 0);
curl_setopt($process, CURLOPT_SSL_VERIFYPEER, 0);
curl_setopt($process, CURLOPT_POSTFIELDS, http_build_query($data) ); 

$return = curl_exec($process);
curl_close($process);

$bill = json_decode($return, true);
header("Location: {$bill['url']}");
?>