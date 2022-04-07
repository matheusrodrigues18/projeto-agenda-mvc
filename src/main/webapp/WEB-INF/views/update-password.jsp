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
<body>
<jsp:include page = "/WEB-INF/views/components/menu.jsp" />
	<div class="container mt-4">
			<h5>Atualizar minha senha.</h5>
			<p>Preencha Os Campos Para Ataulizar Sua Senha.</p>
			<hr>
			
			<form method="post" action="post-update">
			
				<div class="row">
				<div class="col-md-6">
						<label>Senha atual:</label>
						<form:input path="model.senhaAtual" type="password" class="form-control"
							placeholder="Digite aqui"/>
						<span class="text-danger">${erro_senhaAtual}</span>
						<span class="text-danger">${senha_vazia}</span>
					</div>
				<div class="col-md-6">
						<label>Nova senha:</label>
						<form:input path="model.novaSenha" type="password" class="form-control"
							placeholder="Digite aqui"/>
						<span class="text-danger">${nova_senha_vazia}</span>
					</div>
				<div class="col-md-6">
						<label>Confirme sua nova senha:</label>
						<form:input path="model.senhaConfirmacao" type="password" class="form-control"
							placeholder="Digite aqui"/>
						<span class="text-danger">${erro_senha_confirmacao}</span>
					</div>
				<div class="mt-3">
					<div class="col-md-12">
						<input type="submit" value="Realizar Atualização" class="btn btn-success"/>
						<a href="/projetoWeb01/schedule-consult" class="btn btn-light">Voltar</a>
					</div>
				</div>	
			</form>
			<div class="text-success"><strong>${mensagem_sucesso}</strong></div>
			<div class="text-danger"><strong>${erro_mensagem}</strong></div>	
<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>