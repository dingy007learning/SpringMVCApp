package com.spring.userApp.controllers;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.spring.userApp.domain.Dependent;
import com.spring.userApp.service.DependentService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class DependentAppController {
	
	@Autowired
	private DependentService dependentService;
	
	private List<Dependent> currentDependents;
	

	@Qualifier(value="dependentService")
	public void setDependentService(DependentService dependentService) {
		this.dependentService = dependentService;
	}
	
	private List<Dependent> getCurrentDependents() {
		if (currentDependents == null) {
			currentDependents = new ArrayList<Dependent>();
		}
		return currentDependents;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(DependentAppController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.FULL, locale);//.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate );
		return "index";
	}
	
/*	@RequestMapping("/addDependentForm")
	public String showContactForm(Model model) {
		model.addAttribute("dependent", new Dependent());
		return "AddNewDependentForm";
	}
	*/
	
	@RequestMapping("/addDependentForm")
	public String showContactForm(Model model) {
		model.addAttribute("dependent", new Dependent());
		return "AddNewDependentForm";
	}

	@RequestMapping(value="add", method=RequestMethod.POST)
	@ResponseBody
	public String addNewDependent(@Valid Dependent dependent, BindingResult result) {
		System.out.println("Data recieved from Form: " + dependent);
		String response = "";
		if (result.hasErrors()) {
			dependent.setError(result.getFieldErrors().toString());
			List<FieldError> fieldErrors = result.getFieldErrors();
			for (FieldError fieldError : fieldErrors) {
				response= response + fieldError.getDefaultMessage().toString();

			}
			// send an errormessage back
		}
		else {
			dependentService.addDependent(dependent);
			System.out.println("--> Trying JSON: ");
			ObjectMapper objectMapper = new ObjectMapper();
			//getCurrentDependents().clear();
			getCurrentDependents().add(dependent);
			try {
				response = objectMapper.writeValueAsString(getCurrentDependents());
				System.out.println("Response JSON: " + response);
			}
			catch (Exception e) {
				response = e.getMessage();
			}
		}
		return response;
	}
	
	/*
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addNewDependent(@ModelAttribute("dependent") @Valid Dependent dependent, BindingResult result) {
		ModelAndView mav = new ModelAndView();
		if(result.hasErrors()) {
			mav.setViewName("AddNewDependentForm");
		}
		else {
			mav.setViewName("redirect:/listAllDependents");
			getCurrentDependents().add(dependent);
			dependentService.addDependent(dependent);
		}
		return mav;
	}
	*/
	@RequestMapping(value="/listAllDependents", method=RequestMethod.GET)
	public String displayAllDependents(Model model) {
		int countOfDependents = 0;
		List<Dependent> allDependents = (ArrayList<Dependent>) dependentService.getAllDependents();
		System.out.println("Listing Dependents: " + allDependents);
		
		if (allDependents != null) {
			countOfDependents = allDependents.size();
		}
		model.addAttribute("countOfDependents", countOfDependents);
		model.addAttribute("allDependents", allDependents);
		return "DisplayAllDependents";
		
	}
	
	@RequestMapping("/delete/{dependentId}")
	public ModelAndView deleteDependent(@PathVariable(value="dependentId") Integer dependentId) {
		ModelAndView mav = new ModelAndView();
		System.out.println("Deleting ID: " + dependentId);
		dependentService.deleteDependent(dependentId);
		mav.setViewName("redirect:/listAllDependents");
		return mav;
	}
	
	@RequestMapping(value="/jsonlistAllDependents", method=RequestMethod.GET, produces="application/json")
	@ResponseBody
	public List<Dependent> jsonListOfAllDependents(Model model) {
		
		Dependent dep1 = new Dependent();
		Dependent dep2 = new Dependent();
		dep1.setName("TempDep1");
		dep1.setDob("DateDep1");
		dep1.setGender("GenderDep1");
		dep2.setName("TempDep2");
		dep2.setDob("DateDep2");
		dep2.setGender("GenderDep2");
		if (currentDependents == null) {
			getCurrentDependents().add(dep1);
			//getCurrentDependents().add(dep2);
		}
		
//		List<Dependent> allDependents = (ArrayList<Dependent>) dependentService.getAllDependents();
		List<Dependent> allDependents = getCurrentDependents();
		
		
		System.out.println("Listing Dependents: " + allDependents);
		return allDependents;
	}
	
	
}
