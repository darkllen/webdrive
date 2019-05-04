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
		$link = mysqli_connect("193.111.0.203:3306", "darklen", "qwerty", "lendro");
		$query ="SELECT * FROM information where read = 0 ORDER BY popularity DESC";
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
 		this.parentNode.removeChild(this);
 		location.href = e.target.id;
 		<?php
 			$link = mysqli_connect("193.111.0.203:3306", "darklen", "qwerty", "lendro");
 			$query ="update information Set `read` = 1 where href ='" + e.target.id+"'";
 			mysqli_query($link, $query) or die("Ошибка " . mysqli_error($link));
 		?>
 		}
 </script>