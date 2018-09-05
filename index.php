 <!DOCTYPE html>
 <html lang="en">
 <head>
 	<meta charset="UTF-8">
 	<title>Document</title>


 	<style type="text/css">

 	body, html{height:100%}
	.btn_bash{
		width: 100%;
		height: 15%;
		background-color: GreenYellow;
		font-size: 20px;
	}
	.btn_habr{
		width: 100%;
		height: 15%;
		background-color: Plum;
		font-size: 20px;
	}
	.btn_del{
		width: 20%;
		height: 15%;
	}

 	</style>


 </head>
 <body  style="margin: 0">
 		
		<?php  



		$link = mysqli_connect("db4free.net:3306", "darklen", "0987654321", "lendro");


				

		$query ="SELECT * FROM inf ORDER BY date DESC";

		$result = mysqli_query($link, $query) or die("Ошибка " . mysqli_error($link)); 
		$rows_num = mysqli_num_rows($result);

 	for ($i = 0; $i < $rows_num; $i++) {
 			$row = mysqli_fetch_row($result);

	

 		if ($row[1] == "bash") {
 			echo "<button  class=\"btn_bash\" onclick = \"showDiv(event)\" id = \"$row[2]\" >bash</button
 			>";
 		} else{
 			
 			echo "<button  class=\"btn_habr\" onclick = \"showDiv(event)\" id = \"$row[2]\" >$row[3]</button
 			>";
 		}
 		

 

 			
	}
 ?>
	</div>

  

 </body>
 </html>
 
 <script>
 	function showDiv(e) {
 		location.href = e.target.id;
 		
 		}
 </script>