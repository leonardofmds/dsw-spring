package br.unirio.dsw.selecaoppgi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import br.unirio.dsw.selecaoppgi.service.dao.EditalDAO;
import br.unirio.dsw.selecaoppgi.service.dao.UsuarioDAO;

@Controller
public class ProvaEscritaController
{
	@Autowired
	private UsuarioDAO userDAO; 

	@Autowired
	private EditalDAO editalDAO; 
	
	//@Secured("ROLE_PROFESSOR")
	@ResponseBody
	@RequestMapping(value = "/testeAula", method = RequestMethod.GET)
	public ModelAndView mostraPaginaLista()
	{
		return new ModelAndView("provaEscrita/provaEscrita");
	
	}
//	/edital/escrita/presenca
//	/edital/escrita/notas
//	/edital/escrita/encerramento
}
