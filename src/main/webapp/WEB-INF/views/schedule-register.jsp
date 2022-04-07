<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>

	<head>

		<title>COTI Informática - Projeto Agenda</title>

		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
			rel="stylesheet">

	</head>

	<body>
	
		<jsp:include page = "/WEB-INF/views/components/menu.jsp" />
		
		<div class="container mt-4">
			<h5>Seja Bem Vindo Ao Cadastro De Tarefas</h5>
			<p>Preencha Os Campos Para Registrar Uma Tarefa</p>
			<hr>
			
			<form method="post" action="post-register-schedule">
			
				<div class="row">
					<div class="col-md-6">
						<label>Nome Da Tarefa:</label>
						<form:input path="model.nome" type="text" class="form-control" placeholder="Digite aqui"/>
						<span class="text-danger">${erro_nome}</span>
					</div>
					<div class="col-md-2">
						<label>Data:</label>
						<form:input path="model.data" type="date" class="form-control"/>
						<span class="text-danger">${erro_data}</span>
					</div>
					<div class="col-md-2">
						<label>Hora:</label>
						<form:input path="model.hora" type="time" class="form-control"/>
						<span class="text-danger">${erro_hora}</span>
					</div>
					
					<div class="col-md-2">
						<label>Prioridade:</label>
							<form:select path="model.prioridade" class="form-select">
								<option value="">Selecione</option>
								<form:options items="${prioridades}"/>
							</form:select>
							<span class="text-danger">${erro_prioridade}</span>
				</div>	

				</div>
				
				<div class="row mt-3">
					<div class="col-md-12">
						<label>Descrição Da Tarefa:</label>
						<form:textarea path="model.descricao" class="form-control"></form:textarea>
						<span class="text-danger">${erro_descricao}</span>
					</div>
				</div>
				
				<div class="mt-3">
					<div class="col-md-12">
						<input type="submit" value="Realizar Cadastro" class="btn btn-success"/>
						<a href="/projetoWeb01/schedule-consult" class="btn btn-light">Voltar</a>
					</div>
				</div>
			
			</form>
			
			<div class="text-success"><strong>${mensagem_sucesso}</strong></div>
			<div class="text-danger"><strong>${erro_cadastro}</strong></div>	
			
		</div>
		
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
			
	</body>

</html>