package web.customer.tracker.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import web.customer.tracker.dao.CustomerDAO;
import web.customer.tracker.entity.Customer;
import web.customer.tracker.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {

	// We need to inject the DAO 
//	@Autowired
//	private CustomerDAO customerDAO;
	
	// inject the customer service instead
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/list") 
	public String listCustomer(Model model) {		
		//get the customers from the DAO
		List <Customer> theCustomers = customerService.getCustomers();
		
		// add the customers to the model
		model.addAttribute("customers", theCustomers);
				
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd") 
	public String showFormForAdd(Model model) {	
		// create a model to bind our form data
		Customer theCustomer = new Customer();
		
		model.addAttribute(theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer") //same as the form action name
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		// "theCustomer" is the same name as the model attribute in the form
		customerService.saveCustomer(theCustomer);
		return "redirect:/customer/list";
	}
	
	@GetMapping("/showFormForUpdate") 
	public String showFormForUpdate(
			@RequestParam("customerId") int theId,
			Model model) {	
		
		// get the customer from the service
		Customer theCustomer = customerService.getCustomer(theId);
		
		// set the customer as a model attribute to pre-populate the form
		model.addAttribute("customer", theCustomer);
		
		// send the user to our form
		return "customer-form";
	}
	@GetMapping("/delete") 
	public String deleteCsutomer(@RequestParam("customerId") int theId) {	
		
		// delete the customer from the service
		customerService.deleteCustomer(theId);
		
		// send the user to our form
		return "redirect:/customer/list";
	}
	@GetMapping("/search")
	public String searchCustomer(@RequestParam("name") String name,
			Model model) {
		System.out.println("inside controller");

		// search the customer from the service
		List<Customer> customersResult = customerService.searchCustomerByName(name);
		
		// add the result to the model to send it to the view
		model.addAttribute("customers", customersResult);
		
		return "list-customers"; 
	}
	
	
}










