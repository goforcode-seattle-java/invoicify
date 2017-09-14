package com.theironyard.invoicify.controllers;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Map;

import org.junit.*;
import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.ModelAndView;

import com.theironyard.invoicify.models.User;

public class InvoiceControllerTests {
	
	private InvoiceController controller;
	private Authentication authentication;
	private User user;
	
	@Before
	public void setup() {
		user = new User();
		authentication = mock(Authentication.class);
		when(authentication.getPrincipal()).thenReturn(user);
		controller = new InvoiceController();
	}

	@Test
	public void test_list() {
		ModelAndView mv = controller.list(authentication);
		
		assertThat(mv.getViewName()).isEqualTo("invoices/list");
		Map<String, Object> model = mv.getModel();
		assertThat(model.get("user")).isSameAs(user);
	}
	
}













