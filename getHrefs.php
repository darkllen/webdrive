<?php

	$link = mysqli_connect("193.111.0.203:3306", "darklen", "qwerty", "lendro");
		$query ="SELECT * FROM information where `read` = 0 ORDER BY popularity DESC";
		$result = mysqli_query($link, $query) or die("Ошибка " . mysqli_error($link));

$rows_num = mysqli_num_rows($result);

for ($i = 0; $i < $rows_num; $i++) {
    $row = mysqli_fetch_row($result);

   echo $row[1];
   echo "\n";
   echo $row[2];
    echo "\n";
   echo $row[3];
    echo "\n";
}
?>