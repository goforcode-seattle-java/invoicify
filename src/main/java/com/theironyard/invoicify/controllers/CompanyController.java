package com.theironyard.invoicify.controllers;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.theironyard.invoicify.models.Company;
import com.theironyard.invoicify.repositories.CompanyRepository;

@Controller
@RequestMapping("/admin/companies")
public class CompanyController {
	
	private CompanyRepository companyRepository;

	public CompanyController(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	@GetMapping("")
	public ModelAndView list() {
		List<Company> companies = companyRepository.findAll(new Sort("name"));
		ModelAndView mv = new ModelAndView("companies/list");
		mv.addObject("companies", companies);
		mv.addObject("hasCompanies", companies.size() > 0);
		return mv;
	}
	
	@PostMapping("")
	public String create(Company company) {
		companyRepository.save(company);
		return "redirect:/admin/companies";
	}
	
}
