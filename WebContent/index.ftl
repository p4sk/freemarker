<html>
	<head>
		<title>Freemarker Web Example</title>
		
		<!-- Latest compiled and minified CSS -->
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
		
		<!-- jQuery library -->
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
		
		<!-- Latest compiled JavaScript -->
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>

	</head>
	<body class="container">
	  <h1>Project</h1>
		 
		<table  class="table table-striped" border="1">
	        <thead>
		        <tr>
		            <th>Utente</th>  <!--<th>Lavoro</th>-->
		        </tr>
	        </thead>
        	<#list utenti as utente>
	            <tbody>
	      			<tr>
	      				<td>${utente.nome}</td>
		                <#--<td>${utente.nome}</td> <td>${utente.lavoro}</td>-->
		            </tr>
	            </tbody>
            </#list>
    	</table>
	</body>  
</html>
