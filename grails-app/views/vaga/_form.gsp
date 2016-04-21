<%@ page import="exemplodaauladetestes.Vaga" %>



<div class="fieldcontain ${hasErrors(bean: vagaInstance, field: 'descricao', 'error')} required">
	<label for="descricao">
		<g:message code="vaga.descricao.label" default="Descricao" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descricao" required="" value="${vagaInstance?.descricao}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: vagaInstance, field: 'dataEntrada', 'error')} ">
	<label for="dataEntrada">
		<g:message code="vaga.dataEntrada.label" default="Data Entrada" />
		
	</label>
	<g:datePicker name="dataEntrada" precision="day"  value="${vagaInstance?.dataEntrada}" default="none" noSelection="['': '']" />

</div>

<div class="fieldcontain ${hasErrors(bean: vagaInstance, field: 'historicoDeReservas', 'error')} ">
	<label for="historicoDeReservas">
		<g:message code="vaga.historicoDeReservas.label" default="Historico De Reservas" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${vagaInstance?.historicoDeReservas?}" var="h">
    <li><g:link controller="reserva" action="show" id="${h.id}">${h?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="reserva" action="create" params="['vaga.id': vagaInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'reserva.label', default: 'Reserva')])}</g:link>
</li>
</ul>


</div>

<div class="fieldcontain ${hasErrors(bean: vagaInstance, field: 'ocupada', 'error')} ">
	<label for="ocupada">
		<g:message code="vaga.ocupada.label" default="Ocupada" />
		
	</label>
	<g:checkBox name="ocupada" value="${vagaInstance?.ocupada}" />

</div>

