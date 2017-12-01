<%@include file="/WEB-INF/views/helper/template.jsp"%>
<script src="${pageContext.request.contextPath}/static/js/provaEscrita/provaEscritaController.js"></script>
<div id="contents" ng-controller="provaEscrita">
	 <div ng-if="!showNotas">
	    <table id="provas" class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
	        <thead>
	            <tr>
	                <th>Codigo Prova</th>
	                <th>Nome Prova</th>
	            </tr>
	        </thead>
	        <tbody>
	            <a href="#">
	                <tr ng-repeat="prova in provasEscritas.provasEscritas" ng-click="displayTabelasNotas(prova.codigo)">
	                    <td>{{prova.codigo}}</td>
	                    <td>{{prova.nome}}</td>
	                </tr>
	            </a>
	        </tbody>
	    </table>
	</div>
    <div ng-if="showNotas">
       <div class="row">
           <form class="col s12">
               <div class="input-field col s1">
                   <a ng-click="toggleTabelaNotas(showNotas)" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">Voltar</a>
               </div>
               <div class="mdl-textfield mdl-js-textfield">
                   <input class="mdl-textfield__input" id="conteudoFiltroNome" type="text" ng-model="filtroNome" ng-change="filtraNome()">
                   <label class="mdl-textfield__label" for="conteudoFiltroNome">Filtro</label>
               </div>
           </form>
       </div>
       <table id="notasProvaSel" class="mdl-data-table mdl-js-data-table mdl-shadow--2dp">
           <thead>
               <tr>
                   <th class="mdl-data-table__cell--non-numeric">Nome Aluno</th>
                   <th ng-repeat="nota in notasProvaSel[0].pesosNotas track by $index">Nota questão: {{$index + 1}}</th>
                   <th>Nota final</th>
               </tr>
           </thead>
           <tbody>
               <tr class="entradaAluno" ng-repeat="notaAluno in notasProvaSel" data-id-aluno={{notaAluno.idAluno}} data-peso-total={{notaAluno.pesoTotal}}>
                   <td>{{notaAluno.nomeAluno}}</td>
                   <td ng-repeat="peso in notaAluno.pesosNotas track by $index">
                       <input type="number" class="nota" data-peso-questao={{peso}} ng-model=notas ng-change="alteracaoNota({{notaAluno.idAluno}})">
                   </td>
                   <td>
                       <input class="notaFinal" id={{notaAluno.idAluno}} type="number" maxlength="3" readonly>
                   </td>
                   <td>
                   	<a  style="margin: 20px" ng-click="grabNotaAluno(notaAluno)" class="mdl-button mdl-js-button mdl-button--raised mdl-button--colored">Salvar</a>
                   </td>
               </tr>
           </tbody>
       </table>
       
   </div>
</div>
