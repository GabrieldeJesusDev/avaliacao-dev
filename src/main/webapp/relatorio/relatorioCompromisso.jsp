<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF8">
<title><s:text name="label.titulo.pagina" /></title>

<link rel="stylesheet"
	href="webjars/bootstrap/5.1.3/css/bootstrap.min.css">
</head>

<body class="bg-secondary">
	<div class="container mt-5">
		<div class="card">
			<div class="card-header">
				<h4>
					<s:text name="label.titulo.pagina" />
				</h4>
			</div>

			<div class="card-body">
				<s:actionerror cssClass="alert alert-danger" />
				<s:form action="/gerarRelatorios.action">
					<div class="row mb-3">
						<div class="col-md-5">
							<label><s:text name="label.data.inicial" />:</label>
							<s:textfield name="dataInicial" type="date"
								cssClass="form-control" />
						</div>
						<div class="col-md-5">
							<label><s:text name="label.data.final" /> :</label>
							<s:textfield name="dataFinal" type="date" cssClass="form-control" />
						</div>
						<div class="col-md-2 d-flex flex-column justify-content-end">
							<button type="submit" class="btn btn-primary w-100">
								<s:text name="label.gerar" />
							</button>
							<button type="submit" formaction="excelRelatorios.action"
								class="btn btn-success w-100 mt-2">
								<s:text name="label.exportar.excel" />
							</button>
						</div>
					</div>
				</s:form>

				<s:if test="relatorios != null && !relatorios.isEmpty()">
					<table class="table table-light table-striped align-middle">
						<thead>
							<tr>
								<th><s:text name="label.codigo.funcionario" /></th>
								<th><s:text name="label.nome.funcionario" /></th>
								<th><s:text name="label.codigo.agenda" /></th>
								<th><s:text name="label.nome.agenda" /></th>
								<th><s:text name="label.data" /></th>
								<th><s:text name="label.hora" /></th>
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