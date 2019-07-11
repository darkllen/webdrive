 <!DOCTYPE html>
 <html lang="en">
 <head>
 	<meta charset="UTF-8">
 	<title>Document</title>
     <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.0/jquery.min.js"></script>
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
		$query ="SELECT * FROM information where `read` = 0 ORDER BY popularity DESC";
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
        e.target.parentNode.removeChild(e.target);
        $.ajax({
            type: "POST",
            url: 'read.php',
            data: 'href='+e.target.id,
            success: function(data) {
                location = e.target.id;
            }
    });

 	}
 </script>