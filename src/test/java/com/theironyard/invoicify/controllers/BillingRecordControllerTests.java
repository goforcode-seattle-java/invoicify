package com.theironyard.invoicify.controllers;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.*;
import org.springframework.web.servlet.ModelAndView;

import com.theironyard.invoicify.models.BillingRecord;
import com.theironyard.invoicify.repositories.BillingRecordRepository;
import com.theironyard.invoicify.repositories.CompanyRepository;

public class BillingRecordControllerTests {
	
	private BillingRecordController controller;
	private BillingRecordRepository recordRepository;
	private CompanyRepository companyRepository;
	
	@Before
	public void setup() {
		companyRepository = mock(CompanyRepository.class);
		recordRepository = mock(BillingRecordRepository.class);
		controller = new BillingRecordController(recordRepository, companyRepository);
	}

	@Test
	public void test_list() {
		List<BillingRecord> records = new ArrayList<BillingRecord>();
		when(recordRepository.findByLineItemIsNull()).thenReturn(records);
		
		ModelAndView actual = controller.list();
		
		verify(recordRepository).findByLineItemIsNull();
		assertThat(actual.getViewName()).isEqualTo("billing-records/list");
		assertThat(actual.getModel().get("records")).isSameAs(records);
	}
	
}
