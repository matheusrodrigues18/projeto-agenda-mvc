<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
			<h5>Seja Bem Vindo À Agenda de tarefas</h5>
			<hr>
		<form method="post" action="post-consult">
			<div class="row">
				<div class="col-md-3">
					<label>Data de Início:</label>
					<form:input path="model.dataMin" type="date" class="form-control"/>
					<span class="text-danger">${erro_dataMin}</span>
				</div>
				<div class="col-md-3">
					<label>Data de Término:</label>
					<form:input path="model.dataMax" type="date" class="form-control"/>
					<span class="text-danger">${erro_dataMax}</span>
				</div>
				<div class="col-md-6">
					<input type="submit" value="Pesquisar Tarefas" class="btn btn-success mt-4">
				</div>
			</div>
		</form>
		
			<div class="text-success mt-2"><strong>${mensagem_sucesso}</strong></div>
			<div class="text-danger mt-2"><strong>${mensagem_erro}</strong></div>		
		
			<c:if test="${tarefas.size() > 0}">
			
			<div class="row">
				<div class="col-md-3">
				
					<div class="row mb-3">
						<div class="col-md-12">
							<div class="card bg-success">
								<div class="card-body">
									<div class="row">
										<div class="col-md-8">
											<div class="text-white">Prioridade Baixa</div>
										</div>
										<div class="col-md-4 text-center">
											<strong class="text-white">${qtdBaixa}</strong>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row mb-3">
						<div class="col-md-12">
							<div class="card bg-warning">
								<div class="card-body">
									<div class="row">
										<div class="col-md-8">
											<div class="text-white">Prioridade Média</div>
										</div>
										<div class="col-md-4 text-center">
											<strong class="text-white">${qtdMedia}</strong>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
					<div class="row mb-3">
						<div class="col-md-12">
							<div class="card bg-danger">
								<div class="card-body">
									<div class="row">
										<div class="col-md-8">
											<div class="text-white">Prioridade Alta</div>
										</div>
										<div class="col-md-4 text-center">
											<strong class="text-white">${qtdAlta}</strong>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					
				</div>
				<div class="col-md-9">
					<div id="grafico"></div>
				</div>
			</div>

			
			<table class="table table-sm table-hover mt-3">
			<thead>
				<tr>
					<th>Nome Da Tarefa</th>
					<th>Data</th>
					<th>Hora</th>
					<th>Descrição</th>
					<th>Prioridade</th>
					<th>Operações</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${tarefas}" var="t">
				<tr>
					<td>${t.nome}</td>
					<td>${t.data}</td>
					<td>${t.hora}</td>
					<td>${t.descricao}</td>
					<td>
					<c:if test="${t.prioridade == 'Baixa' }">
							<span class="badge bg-success">${t.prioridade}</span>
					</c:if>
					<c:if test="${t.prioridade == 'Media' }">
							<span class="badge bg-warning">${t.prioridade}</span>
					</c:if>
					<c:if test="${t.prioridade == 'Alta' }">
							<span class="badge bg-danger">${t.prioridade}</span>
					</c:if>
					</td>
					<td>
					<!-- Query String -->
						<a href="/projetoWeb01/schedule-edit?id=${t.idTarefa}" class="btn btn-primary btn-sm">
							Alterar</a>
						<a href="/projetoWeb01/schedule-delete?id=${t.idTarefa}" class="btn btn-danger 
							btn-sm" onclick="return confirm('Deseja Realmente Excluir a tarefa ${t.nome}?')">
								Excluir</a>
					</td>
				</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
				<td colspan = "6">Quantidade de tarefas: ${tarefas.size()}</td>
				</tr>
			</tfoot>
		</table>
		</c:if>
		</div>
		<script
			src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
			
		<!-- Arquivos javascript do HighCharts -->
		<script src="https://code.highcharts.com/highcharts.js"></script>
		<script src="https://code.highcharts.com/highcharts-3d.js"></script>
		<script src="https://code.highcharts.com/modules/exporting.js"></script>
		<script src="https://code.highcharts.com/modules/export-data.js"></script>
	
		<script>
		
		var array = [];
		
		//dados do gráfico
		array.push(['Prioridade Alta', ${qtdAlta}]);
		array.push(['Prioridade Media', ${qtdMedia}]);
		array.push(['Prioridade Baixa', ${qtdBaixa}]);
		
		//desenhando o gráfico
		new Highcharts.Chart({
			chart: {
				type : 'pie',
				renderTo: 'grafico',
				height: 240
			},
			plotOptions: {
				pie : {
					innerSize: '60%',
					dataLabels: { enable: true }
				}
			},
			title: {
				text: 'Tarefas por prioridade'
			},
			series: [
				{ data: array }
			],
			colors: ['#d9534f', '#f0ad4e', '#5cb85c']
		});
		
		</script>
			
			
	</body>

</html>