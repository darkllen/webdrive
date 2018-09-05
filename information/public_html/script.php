<?php
$link = mysqli_connect("db4free.net:3306", "darklen", "0987654321", "lendro");
$href = $_POST['href'];

 $query1 = "UPDATE inf SET `read`=1 WHERE href=\"$href\"";
 			       mysqli_query($link, $query1);
  

?>