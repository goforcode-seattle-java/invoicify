package com.theironyard.invoicify.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
public class UserAdditionAspect {

	@ModelAttribute
	public void addUserSettings(Model model, Authentication auth, HttpServletRequest request) {
		if (auth == null) {
			model.addAttribute("notUser", true);
		} else {
			boolean canSeeInvoices = request.isUserInRole("ADMIN") || request.isUserInRole("ACCOUNTANT");
			boolean canSeeBillingRecords = request.isUserInRole("ADMIN") || request.isUserInRole("CLERK");
			boolean isAdmin = request.isUserInRole("ADMIN");
			model.addAttribute("user", auth.getPrincipal());
			model.addAttribute("canSeeInvoices", canSeeInvoices);
			model.addAttribute("canSeeBillingRecords", canSeeBillingRecords);
			model.addAttribute("isAdmin", isAdmin);
		}
	}
	
}
