<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF8">
<title>Relatório de Compromissos</title>

<link rel="stylesheet"
	href="webjars/bootstrap/5.1.3/css/bootstrap.min.css">
</head>

<body class="bg-secondary">
	<div class="container mt-5">
		<div class="card">
			<div class="card-header">
				<h4>Relatório de Compromissos</h4>
			</div>

			<div class="card-body">
				<s:actionerror cssClass="alert alert-danger" />
				<s:form action="/gerarRelatorios.action">
					<div class="row mb-3">
						<div class="col-md-5">
							<label>Data Inicial:</label>

							<s:textfield name="dataInicial" type="date"
								cssClass="form-control" />
						</div>

						<div class="col-md-5">
							<label>Data Final:</label>

							<s:textfield name="dataFinal" type="date" cssClass="form-control" />
						</div>

						<div class="col-md-2 d-flex align-items-end">
							<button type="submit" class="btn btn-primary w-100">
								Gerar</button>
						</div>
					</div>
				</s:form>

				<s:if test="relatorios != null && !relatorios.isEmpty()">
					<table class="table table-light table-striped align-middle">
						<thead>
							<tr>
								<th>Código Funcionário</th>
								<th>Nome Funcionário</th>
								<th>Código Agenda</th>
								<th>Nome Agenda</th>
								<th>Data</th>
								<th>Hora</th>
							</tr>
						</thead>
						<tbody>

							<s:iterator value="relatorios">
								<tr>
									<td>${codigoFuncionario}</td>
									<td>${nomeFuncionario}</td>
									<td>${codigoAgenda}</td>
									<td>${nomeAgenda}</td>
									<td>${data}</td>
									<td>${hora}</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</s:if>
			</div>
		</div>
	</div>

	<script src="webjars/bootstrap/5.1.3/js/bootstrap.bundle.min.js"></script>

</body>
</html>