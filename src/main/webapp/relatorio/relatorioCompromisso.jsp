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
		<div class="row mt-4 mb-3">
			<div class="col-sm">
				<div class="btn-group w-100" role="group">

					<s:url action="todosFuncionarios" var="urlFuncionarios" />
					<a href="${urlFuncionarios}" class="btn btn-dark"> <s:text
							name="label.funcionarios" />
					</a>
					<s:url action="todosAgendas" var="urlAgendas" />
					<a href="${urlAgendas}" class="btn btn-dark"> <s:text
							name="label.agendas" />
					</a>
					<s:url action="todosCompromissos" var="urlCompromissos" />
					<a href="${urlCompromissos}" class="btn btn-dark"> <s:text
							name="label.compromissos" />
					</a>
					<s:url action="abrirRelatorios" var="urlRelatorios" />
					<a href="${urlRelatorios}" class="btn btn-dark"> <s:text
							name="label.relatorio.compromissos" />
					</a>
				</div>
			</div>
		</div>
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
									<td><s:property value="codigoFuncionario" /></td>
									<td><s:property value="nomeFuncionario" escapeHtml="true" /></td>
									<td><s:property value="codigoAgenda" /></td>
									<td><s:property value="nomeAgenda" escapeHtml="true" /></td>
									<td><s:property value="data" escapeHtml="true" /></td>
									<td><s:property value="hora" escapeHtml="true" /></td>
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