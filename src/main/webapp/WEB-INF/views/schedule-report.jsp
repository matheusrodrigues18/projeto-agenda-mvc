<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>

	<head>

		<title>COTI Inform�tica - Projeto Agenda</title>

		<link
			href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
			rel="stylesheet">

	</head>

	<body>
	
		<jsp:include page = "/WEB-INF/views/components/menu.jsp" />
		
		
	<div class="container mt-4">

		<h5>Relat�rio de Tarefas</h5>
		<p>Utilize os campos abaixo para gerar o relat�rio de tarefas.</p>
		<hr />

		<form method="post" action="post-report">

			<div class="row mb-3">
				<div class="col-md-3">
					<label>Data de in�cio:</label>
					<form:input path="model.dataMin" type="date" class="form-control" />
					<span class="text-danger">
						${erro_dataMin}
					</span>
				</div>
			</div>
			<div class="row mb-3">
				<div class="col-md-3">
					<label>Data de T�rmino:</label>
					<form:input path="model.dataMax" type="date" class="form-control" />
					<span class="text-danger">
						${erro_dataMax}
					</span>
				</div>
			</div>
			<div class="row mb-3">
				<div class="col-md-3">
					<input type="submit" value="Gerar Relat�rio"
						class="btn btn-success" />
				</div>
			</div>

		</form>

	</div>

		
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
			
	</body>

</html>