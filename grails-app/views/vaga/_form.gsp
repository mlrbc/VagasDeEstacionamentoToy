<%@ page import="exemplodaauladetestes.Vaga" %>



<div class="fieldcontain ${hasErrors(bean: vagaInstance, field: 'descricao', 'error')} ">
	<label for="descricao">
		<g:message code="vaga.descricao.label" default="Descricao" />
		
	</label>
	<g:textField name="descricao" value="${vagaInstance?.descricao}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: vagaInstance, field: 'ocupada', 'error')} ">
	<label for="ocupada">
		<g:message code="vaga.ocupada.label" default="Ocupada" />
		
	</label>
	<g:checkBox name="ocupada" value="${vagaInstance?.ocupada}" />
</div>

