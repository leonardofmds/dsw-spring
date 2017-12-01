// ?Dataservice
App.controller("provaEscrita", function($scope){
	$scope.showNotas = false;
	/**
		var alunosNotas = {
	        notas: [
	            {
	                idAluno: 18,
	                nomeAluno: "Luiz",
	                nomeProva: "FSI",
	                notaFinal: 0
	            }, {
	                idAluno: 18,
	                nomeAluno: "Luiz",
	                nomeProva: "ING",
	                notaFinal: 0
	            }, {
	                idAluno: 19,
	                nomeAluno: "XYZ",
	                nomeProva: "FSI",
	                notaFinal: 0
	            }, {
	                idAluno: 19,
	                nomeAluno: "XYZ",
	                nomeProva: "ING",
	                notaFinal: 0
	            }, {
	                idAluno: 20,
	                nomeAluno: "Fulano 0003",
	                nomeProva: "FSI",
	                notaFinal: 0
	            }, {
	                idAluno: 20,
	                nomeAluno: "Fulano 0003",
	                nomeProva: "ING",
	                notaFinal: 0
	            }, {
	                idAluno: 21,
	                nomeAluno: "Fulano 0004",
	                nomeProva: "FSI",
	                notaFinal: 0
	            }, {
	                idAluno: 21,
	                nomeAluno: "Fulano 0004",
	                nomeProva: "EDG",
	                notaFinal: 12
	            }, {
	                idAluno: 22,
	                nomeAluno: "Fulano 0005",
	                nomeProva: "FSI",
	                notaFinal: 0
	            }, {
	                idAluno: 22,
	                nomeAluno: "Fulano 0005",
	                nomeProva: "ING",
	                notaFinal: 0
	            }
	        ]
	    };
	 **/
	
	 var requestAlunos = function(){
	    var xmlHttp = new XMLHttpRequest();
	    xmlHttp.open("GET", '/selecaoppgi/getAlunos', false);
	    xmlHttp.send( null );
	    console.log(xmlHttp.responseText);
	    return JSON.parse(xmlHttp.responseText);
	}
	
	var alunosNotas = requestAlunos();
	var provas = {
	        idEdital: 1,
	        provasEscritas: [
	            {
	                codigo: "FSI",
	                nome: "Fundamentos de Sistemas de Informação",
	                dispensavel: false,
	                notaMinimaAprovacao: 70,
	                pesosQuestoes: [10, 10, 10, 10, 10, 10],
	                pesoTotal: 60
	            },
	            {
	                codigo: "ING",
	                nome: "Inglês",
	                dispensave: true,
	                notaMinimaAprovacao: 70,
	                pesosQuestoes: [10, 10, 10, 10, 10, 10],
	                pesoTotal: 60
	            },
	            {
	                codigo: "EDG",
	                nome: "Estruturas de Dados e Grafos",
	                dispensavel: false,
	                notaMinimaAprovacao: 70,
	                pesosQuestoes: [],
	                pesoTotal: 0
	            },
	            {
	                codigo: "EDP",
	                nome: "Estrutura de Dados e Programação",
	                dispensavel: false,
	                notaMinimaAprovacao: 70,
	                pesosQuestoes: [],
	                pesoTotal: 0
	            },
	            {
	                codigo: "ENS",
	                nome: "Engenharia de Software",
	                dispensavel: false,
	                notaMinimaAprovacao: 70,
	                pesosQuestoes: [],
	                pesoTotal: 0
	            },
	            {
	                codigo: "RDP",
	                nome: "Redes",
	                dispensavel: false,
	                notaMinimaAprovacao: 70,
	                pesosQuestoes: [],
	                pesoTotal: 0
	            },
	            {
	                codigo: "WEB",
	                nome: "Desenvolvimento para Web",
	                dispensavel: false,
	                notaMinimaAprovacao: 70,
	                pesosQuestoes: [],
	                pesoTotal: 0
	            }
	        ]
	    };

	var getPesosProvas = function (nomeProva) {
        for (var i = 0; i < provas.provasEscritas.length; i++) {
            if (nomeProva == provas.provasEscritas[i].codigo)
                return provas.provasEscritas[i].pesosQuestoes
        }
    }
	
    var getPesoTotal = function (nomeProva) {
        for (var i = 0; i < provas.provasEscritas.length; i++) {
            if (nomeProva == provas.provasEscritas[i].codigo)
                return provas.provasEscritas[i].pesoTotal
        }
	}
   
	
	var request = function(dadosAluno){
	    var xmlHttp = new XMLHttpRequest();
	    xmlHttp.open("GET", '/selecaoppgi/gravaNotaAluno/' + dadosAluno, false);
	    xmlHttp.send( null );
	    return xmlHttp.responseText;
	}
	
	$scope.grabNotaAluno = function(notaAluno){
		var nota = document.getElementById(notaAluno.idAluno).value;
		notaAluno.notaFinal = nota
		var string = JSON.stringify(notaAluno);
		console.log(string)
		var result = request(string);
		
	}

    $scope.toggleTabelaNotas = function (param) {
		$scope.showNotas = !param
	}
	
	$scope.salvaNotas = function () {
		$scope.showNotas = !param
		 
    }
    
    $scope.filtraNome = function () {
        var texto = document.querySelector('#conteudoFiltroNome').value.toLowerCase()

        var alunosFiltrados = {
            notas: []
        }

        for (var i = 0; i < alunosNotas.notas.length; i++) {

            if (alunosNotas.notas[i].nomeAluno.toLowerCase().match(texto) && alunosNotas.notas[i].nomeProva == provaDaTela  ) {
                alunosFiltrados.notas.push(alunosNotas.notas[i])
            }
            
        }
        console.log(alunosFiltrados)
        $scope.notasProvaSel = alunosFiltrados.notas
    }
    
    $scope.alteracaoNota = function (idAluno) {
        var pesoTotal = 0;
        var linhas = document.getElementsByClassName('entradaAluno')


        for (var i = 0; i < linhas.length; i++) {
            var media = 0
            if (linhas[i].getAttribute('data-id-aluno') == idAluno) {

                pesoTotal = Number(linhas[i].getAttribute('data-peso-total'))
                var notas = linhas[i].getElementsByClassName('nota')
                for (var j = 0; j < notas.length; j++) {
                    var peso = Number(notas[j].getAttribute("data-peso-questao"))
                    var nota = Number(notas[j].value)
                    media += (peso * nota) / pesoTotal
                    media = Math.round(media * 100) / 100
                }
                if (media < 7) {
                    linhas[i].querySelector('.notaFinal').style.color = "red"
                } else {
                    linhas[i].querySelector('.notaFinal').style.color = "green"
                }
                linhas[i].querySelector('.notaFinal').value = media
            }

        }
    }
    
	$scope.provasEscritas = provas
	
	$scope.displayTabelasNotas = function (param) {
        var alunosDaProva = []
        provaDaTela = param
        for (var i = 0; i < alunosNotas.notas.length; i++) {
            var obj = alunosNotas.notas[i];

            if (obj.nomeProva == param) {
                obj.pesosNotas = getPesosProvas(param)
                obj.pesoTotal = getPesoTotal(param)
                alunosDaProva.push(obj)
            }
        }



        $scope.notasProvaSel = alunosDaProva
        $scope.showNotas = true
    }
})

