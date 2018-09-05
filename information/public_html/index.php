 <!DOCTYPE html>
 <html lang="en">
 <head>
 	<meta charset="UTF-8">
 	<title>Document</title>
			<script
				src="https://code.jquery.com/jquery-1.12.3.min.js"
				integrity="sha256-aaODHAgvwQW1bFOGXMeX+pC4PZIPsvn2h1sArYOhgXQ="
				crossorigin="anonymous">
					
			</script>

 	<style type="text/css">

 	body, html{height:100%}
	.btn_bash{
		width: 100%;
		height: 15%;
		background-color: GreenYellow;
		font-size: 20px;
			border-radius: 0px;
			-webkit-appearance: none;
	}
		.btn_bash_read{
		width: 100%;
		height: 15%;
		background-color: LightCoral;
		font-size: 20px;
			border-radius: 0px;
			-webkit-appearance: none;
	}
		.btn_bash2{
		width: 100%;
		height: 100%;
		background-color: GreenYellow;
		font-size: 20px;
			border-radius: 0px;
			-webkit-appearance: none;
	}
			.btn_bash2_read{
		width: 100%;
		height: 100%;
		background-color: LightCoral;
		font-size: 20px;
			border-radius: 0px;
			-webkit-appearance: none;
	}
	.btn_habr{
		width: 100%;
		height: 15%;
		background-color: Plum;
		font-size: 20px;
				border-radius: 0px;
			-webkit-appearance: none;
	}
		.btn_habr_read{
		width: 100%;
		height: 15%;
		background-color: LightCoral;
		font-size: 20px;
			border-radius: 0px;
			-webkit-appearance: none;
	}
		.btn_habr2{
		width: 100%;
		height: 100%;
		background-color: Plum;
		font-size: 20px;
			border-radius: 0px;
			-webkit-appearance: none;
	}
			.btn_habr2_read{
		width: 100%;
		height: 100%;
		background-color: LightCoral;
		font-size: 20px;
				border-radius: 0px;
			-webkit-appearance: none;
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

	

 		if ($row[1] == "bash" AND $row[5]==0) {
 			echo "<form class = \"btn_bash\" method=\"post\" action=\"\">
		<input class=\"btn_bash2\" type=\"button\" id=\"$row[2]\" value=\"bash\">
	</form>";
 		} elseif ($row[1] == "bash" AND $row[5]==1) {
 			echo "<form class = \"btn_bash_read\" method=\"post\" action=\"\">
		<input class=\"btn_bash2_read\" type=\"button\" id=\"$row[2]\" value=\"bash\">
	</form>";
 		} else if ($row[1] == "habr" AND $row[5]==0) {
 			echo "<form class = \"btn_habr\" method=\"post\" action=\"\">
		<input class=\"btn_habr2\" type=\"button\" id=\"$row[2]\" value=\"$row[3]\">
	</form>";
 		} else if ($row[1] == "habr" AND $row[5]==1) {
 			echo "<form class = \"btn_habr_read\" method=\"post\" action=\"\">
		<input class=\"btn_habr2_read\" type=\"button\" id=\"$row[2]\" value=\"$row[3]\">
	</form>";
 		}
 

 			
	}
 ?>
	</div>

  

 </body>
 </html>
 
 <script>



$("input").click(function(){
		console.log(this.id);

		$(this).css("background-color", "LightCoral");
     
        $.ajax({
          type: 'POST',
          url:'script.php',
          data:'href='+this.id,
          success:function(){
            console.log("sda");
          }
        });
         location.reload();

        location.href = this.id;

      
    });

 </script>