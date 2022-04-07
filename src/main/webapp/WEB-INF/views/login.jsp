<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>

<head>

<title>COTI Inform�tica - Projeto Agenda</title>

<!-- folhas de estilo CSS da p�gina -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">

</head>

<body class="bg-secondary">

	<div class="card mt-5 col-md-4 offset-md-4">
		<div class="card-body text-center">
		
			<img src="https://www.cotiinformatica.com.br/imagens/logo-coti-informatica.png" class="img-fluid"/>
						
			<h5 class="card-title">Agenda de tarefas</h5>
			<p class="card-text">Entre com suas credenciais de acesso.</p>
			
			<hr/>
			
			<!-- formul�rio para preenchimento dos dados do usu�rio (login) -->
			<form method="post" action="post-login">
			
				<label>Email de acesso:</label>
				<form:input path="model.email" 
					type="text" class="form-control" placeholder="Ex: joaopedro@gmail.com"/>
					<div class="text-danger">${erro_email}</div>
				<br/>
				
				<label>Senha de acesso:</label>
				<form:input path="model.senha" type="password" 
					class="form-control" placeholder="Digite aqui"/>
					<div class="text-danger">${erro_senha}</div>
				
				<div align="right">
					<a href="/projetoWeb01/forgot-password">Esqueci minha senha</a>
				</div>
				
				<br/>
				
				<div class="d-grid">
					<input type="submit" value="Acessar Sistema" class="btn btn-primary"/>
				</div>
			
			</form>
			
			<div class = "mt-3">
			
			<!-- <div class="text-success"><strong>${sucesso_login}</strong></div>-->
			
			<div class="text-danger"><strong>${erro_login}</strong></div>
			
			</div>
			
			<div class="d-grid">
				N�o possui uma conta? <a href="/projetoWeb01/register">
					Cadastre-se aqui!</a>
			</div>

		</div>
	</div>

	<!-- arquivos javascript da p�gina -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>

</body>

</html>



