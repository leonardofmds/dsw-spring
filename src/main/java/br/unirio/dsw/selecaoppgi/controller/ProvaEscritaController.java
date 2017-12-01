package br.unirio.dsw.selecaoppgi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import br.unirio.dsw.selecaoppgi.model.edital.Edital;
import br.unirio.dsw.selecaoppgi.model.edital.ProvaEscrita;
import br.unirio.dsw.selecaoppgi.model.inscricao.InscricaoEdital;
import br.unirio.dsw.selecaoppgi.service.dao.EditalDAO;
import br.unirio.dsw.selecaoppgi.service.dao.InscricaoDAO;
import br.unirio.dsw.selecaoppgi.service.dao.UsuarioDAO;
import br.unirio.dsw.selecaoppgi.service.json.JsonEditalReader;
import br.unirio.dsw.selecaoppgi.service.json.JsonEditalWriter;

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
	@RequestMapping(value = "/gravaNotaAluno/{info}", method = RequestMethod.GET)
	public boolean registraNotaProva(@PathVariable("info") String  info) {
		System.out.println(info);
		return false;
	}

	@ResponseBody
	@RequestMapping(value = "/getAlunos", method = RequestMethod.GET)
	public String grabAlunosData() {
		return "{\"notas\":[{\"idAluno\":18,\"nomeAluno\":\"Luiz\",\"nomeProva\":\"FSI\",\"notaFinal\":0},{\"idAluno\":18,\"nomeAluno\":\"Luiz\",\"nomeProva\":\"ING\",\"notaFinal\":0},{\"idAluno\":19,\"nomeAluno\":\"XYZ\",\"nomeProva\":\"FSI\",\"notaFinal\":0},{\"idAluno\":19,\"nomeAluno\":\"XYZ\",\"nomeProva\":\"ING\",\"notaFinal\":0},{\"idAluno\":20,\"nomeAluno\":\"Fulano 0003\",\"nomeProva\":\"FSI\",\"notaFinal\":0},{\"idAluno\":20,\"nomeAluno\":\"Fulano 0003\",\"nomeProva\":\"ING\",\"notaFinal\":0},{\"idAluno\":21,\"nomeAluno\":\"Fulano 0004\",\"nomeProva\":\"FSI\",\"notaFinal\":0},{\"idAluno\":21,\"nomeAluno\":\"Fulano 0004\",\"nomeProva\":\"EDG\",\"notaFinal\":12},{\"idAluno\":22,\"nomeAluno\":\"Fulano 0005\",\"nomeProva\":\"FSI\",\"notaFinal\":0},{\"idAluno\":22,\"nomeAluno\":\"Fulano 0005\",\"nomeProva\":\"ING\",\"notaFinal\":0}]}";
	}

	
	// Joga na tela as provas somente
	@ResponseBody
	@RequestMapping(value = "/inscricao/provas", method = RequestMethod.GET)
	public String getProvasEdital(HttpServletRequest request) {
		// Generalizar isso no pacote de servicos
		Edital edital = (Edital) request.getSession().getAttribute("edital");

		// if ((edital == null || edital.getId() != usuario.getIdEdital()) &&
		// usuario.getIdEdital() > 0) {
		// edital = editalDAO.carregaEditalId(usuario.getIdEdital(), userDAO);
		// request.getSession().setAttribute("edital", edital);
		// }
		JsonEditalWriter jw = new JsonEditalWriter();

		return jw.execute(edital).toString();

	}

	@ResponseBody
	@RequestMapping(value = "/inscricao/prova", method = RequestMethod.GET)
	public String getAlunosProva(HttpServletRequest request, @ModelAttribute String codigo) {

		// Generalizar isso no pacote de servicos
		// Edital edital = (Edital) request.getSession().getAttribute("edital");

		// if ((edital == null || edital.getId() != usuario.getIdEdital()) &&
		// usuario.getIdEdital() > 0) {
		// edital = editalDAO.carregaEditalId(usuario.getIdEdital(), userDAO);
		// request.getSession().setAttribute("edital", edital);
		// }

		// List<InscricaoEdital> inscricoes =
		// inscricaoEditalDAO.carregaPresencaProvaEscrita(edital.getId(), codigo);
		Gson gson = new Gson();

		// PEGAR O SERVICE JSON DO GRUPO 1 PRA N ENVIAR UM JSON RETARDADO
		// return gson.toJson(inscricoes).toString();
		return "Luiz";
	}

	// /edital/escrita/presenca
	// /edital/escrita/notas
	// /edital/escrita/encerramento
}
