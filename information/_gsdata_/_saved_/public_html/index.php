 <?php echo "Привет, мир!"; 
 	$dir = '/storage/ssd1/075/6554075/public_html/files/';
 	$files1 = scandir($dir);
 	print_r($files1[0]);

 	for ($i = 0; $i < count($files1); $i++) {
    $fp = fopen($files1[$i], 'r');
    if ($fp) {
		while (!feof($fp)) {
			$mytext = fgets($fp, 999);
			echo $mytext."<br />";
		}
	}
	else echo "Ошибка при открытии файла";
	fclose($fp);
	}
 ?>
 