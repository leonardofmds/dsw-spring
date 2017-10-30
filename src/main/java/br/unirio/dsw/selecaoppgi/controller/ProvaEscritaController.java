package br.unirio.dsw.selecaoppgi.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import br.unirio.dsw.selecaoppgi.model.edital.Edital;
import br.unirio.dsw.selecaoppgi.model.edital.ProvaEscrita;
import br.unirio.dsw.selecaoppgi.model.inscricao.InscricaoEdital;
import br.unirio.dsw.selecaoppgi.service.dao.EditalDAO;
import br.unirio.dsw.selecaoppgi.service.dao.InscricaoDAO;
import br.unirio.dsw.selecaoppgi.service.dao.UsuarioDAO;

@Controller
public class ProvaEscritaController {
	@Autowired
	private UsuarioDAO userDAO;

	@Autowired
	private EditalDAO editalDAO;

	@Autowired
	private InscricaoDAO inscricaoEditalDAO;
	
	// @Secured("ROLE_PROFESSOR")
	@ResponseBody
	@RequestMapping(value = "/testeAula", method = RequestMethod.GET)
	public ModelAndView mostraPaginaLista() {
		return new ModelAndView("provaEscrita/provaEscrita");

	}

	@ResponseBody
	@RequestMapping(value = "/alunos/notas", method = RequestMethod.GET)
	public String getAlunosProvasNotas(int idEdital) {
		Edital editalSelecionado = editalDAO.carregaEditalId(idEdital, userDAO);
		List<List<InscricaoEdital>> candidatosPorProvas = new ArrayList<>();
		String jsonProvas = "{ idEdital: 1, provasEscritas: [ @ ]}";
		String content = "";
		List<String> codigosProvasEscritas = new ArrayList<>();
		for(ProvaEscrita provaEscrita : editalSelecionado.getProvasEscritas()) {
			int pesoTotal = 0;
			String pesos = "[ @ ]";
			String pesosContent = "";
			for(int peso : provaEscrita.getPesosQuestoes()) {
				pesoTotal += peso;
				pesosContent += peso;
			}
			pesos.replace("@", pesosContent);
			content += "{codigo: " + provaEscrita.getCodigo() + 
						", nome: " + provaEscrita.getNome() + 
						", dispensavel: " + provaEscrita.isDispensavel() + 
						", notaMinimaAprovacao: " + provaEscrita.getNotaMinimaAprovacao() + 
						", pesoTotal: " + pesoTotal +
						", pesos : " + pesos + "},";
			
			candidatosPorProvas.add(inscricaoEditalDAO.carregaAvaliacaoProvaEscrita(idEdital, provaEscrita.getNome()));
		}
		jsonProvas.replace("@", content);
		
		
		
		return "";
		
	}
	// /edital/escrita/presenca
	// /edital/escrita/notas
	// /edital/escrita/encerramento
}
