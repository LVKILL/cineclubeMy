package br.com.cineclube.controller;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import br.com.cineclube.dao.FilmeRepository;
import br.com.cineclube.model.Category;
import br.com.cineclube.model.Filme;

@Controller
@RequestMapping("/filmes")
public class FilmeController {
	
	@Autowired
	FilmeRepository dao;
	
	@RequestMapping("/new")
	public String newForm(Model model) {
		Filme filme = new Filme();
		model.addAttribute("categories", Category.values());
		model.addAttribute("filme", filme);
		return "filme/manterFilme.html";
	}
	@GetMapping(value = "/delete/{id}")
	public String delete(@PathVariable Long id) {
		dao.deleteById(id);
		return "redirect:/filmes/list";
	}
	@GetMapping(value = "/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {
		Filme filme = dao.getOne(id);
		model.addAttribute("filme", filme);
		model.addAttribute("categories", Category.values());
		return "filme/manterFilme.html";
	}
	@RequestMapping("/list")
	public String list(Model model) {
		List<Filme> filmeList = dao.findAll();
		model.addAttribute("categories", Category.values());
		model.addAttribute("filmeList", filmeList);
		model.addAttribute("category", "Selecionar");
		return "filme/list.html";
	}
	@PostMapping("/save")
	public String save(@Valid Filme filme, BindingResult result, Model model) {
		if(result.hasErrors()) {
			model.addAttribute("categories", Category.values());
			return "filme/manterFilme.html";
		}
		dao.save(filme);
		return "redirect:/filmes/list";
	}
	@PostMapping(value = "/category")
	public String filtra(String cat, Model model) {
		List<Filme> filmes = dao.findByCategoria(cat);
		model.addAttribute("filmeList",filmes);
		model.addAttribute("category",cat);
		model.addAttribute("categories", Category.values());
		return "filme/list.html";
	}
}
