<html>
	<head>
		<title>Freemarker Web Example</title>
		
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		
		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		
		<!-- Latest compiled JavaScript -->
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
		
		<script src="./customFunctions.js"></script>

	</head>
	<body class="container">
	  <h1>Meteo</h1>
	  
	  <form action="meteo" method="post">
		<table class="table table-striped">
			<tr>
				<td>Citta'</td>
				<td><input name="city" /></td>
			</tr>
		</table>
		<input type="submit" />
	</form>
		 
	</body>  
</html>
