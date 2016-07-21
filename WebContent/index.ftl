<html>
<head><title>Freemarker Web Example</title>
<body>
	  <h1>Hello</h1>
		 
		<table border="1">
	        <tr>
	            <th>Utente</th>  <th>Lavoro</th>
	        </tr>
        	<#list utenti as utente>
	            <tr>
	                <td>${utente.nome}</td> <td>${utente.lavoro}</td>
	            </tr>
            </#list>
    	</table>
	</body>  
</html>
