<?php

$host = '193.111.0.203:3306'; // адрес сервера
$database = 'lendro'; // имя базы данных
$user = 'darklen'; // имя пользователя
$password = 'qwerty'; // пароль

$link = mysqli_connect($host, $user, $password, $database)
or die("Ошибка " . mysqli_error($link));
$href = $_POST['href'];

mysqli_query($link, "UPDATE `information` SET `read`= 1 WHERE `href`=\"$href\"") or die("Ошибка " . mysqli_error($link));
// закрываем подключение
mysqli_close($link);

?>

