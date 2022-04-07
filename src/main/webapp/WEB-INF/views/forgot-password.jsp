<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>COTI Informática - Projeto Agenda</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-secondary">
	<div class="card mt-5 col-md-4 offset-md-4">
		<div class="card-body text-center">
		
			<img src="https://www.cotiinformatica.com.br/imagens/logo-coti-informatica.png" class="img-fluid"/>
			
			<h5 class="card-title">Esqueci minha senha</h5>
			<p class="card-text">Entre com seu email para recuperar a senha de acesso.</p>
			
			<hr/>
			
			<form method="post" action="post-resolution">
			
				<label>Email de acesso:</label>
				<form:input path="model.email"
						type="text" class="form-control" placeholder="Ex: joaopedro@gmail.com"/>
				<div class="text-danger">${erro_email}</div>			
				<br/>
				
				<div class="d-grid">
					<input type="submit" value="Recuperar Senha" class="btn btn-success"/>
				</div>
			
			</form>
			
			<div class="mt-3 mb-3">
				
				<div class="text-success">
					<strong>${mensagem_sucesso}</strong>
				</div>
				
				<div class="text-danger">
					<strong>${mensagem_erro}</strong>
				</div>
				
			</div>
			
			<div class="d-grid">
				<a href="/projetoWeb01/" class="btn btn-outline-primary">Voltar para a página de login.</a>
			</div>

		</div>
		</div>

	<!-- arquivos javascript da página -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>