package com.harsh.fleetapp.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.harsh.fleetapp.models.Client;
import com.harsh.fleetapp.services.ClientService;
import com.harsh.fleetapp.services.CountryService;
import com.harsh.fleetapp.services.StateService;
 

@Controller
public class ClientController {

	@Autowired	private ClientService clientService;
	@Autowired	private CountryService countryService;
	@Autowired	private StateService stateService;


	@GetMapping("/clients")
	public String getClients(Model model) {		
		model.addAttribute("clients", clientService.getClients());	
		
		model.addAttribute("countries", countryService.getCountries());
		
		model.addAttribute("states", stateService.getStates());

		
		return "Client";
	}	
	
	@PostMapping("/clients/addNew")
	public String addNew(Client client) {
		clientService.save(client);
		return "redirect:/clients";
	}
	
	@RequestMapping("clients/findById")
	@ResponseBody
	public Optional<Client> findById(int id) {
	  return clientService.findById(id);	
	}	
	
	@RequestMapping(value="/clients/update", method= {RequestMethod.PUT, RequestMethod.GET})
	public String update(Client client) {
		clientService.save(client);
		return "redirect:/clients";
	}
	
	@RequestMapping(value="/clients/delete", method= {RequestMethod.DELETE, RequestMethod.GET})
	public String delete(Integer id) {
		clientService.delete(id);
		return "redirect:/clients";
	}
	
}