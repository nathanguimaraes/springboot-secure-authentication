package com.projectfinal.entitycrud.controle;



import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
	@RequestMapping("/") 
	public String index(Model model) {
		model.addAttribute("msnBemVindo", "Exemplo básico ");			//essa msg é atribuida na index.html como msnBemVindo
		return "publica-index";
	}
	@RequestMapping("/login") 										
	public String login() {	
		return "login";												//retornando pagina login
	}	

	

}
