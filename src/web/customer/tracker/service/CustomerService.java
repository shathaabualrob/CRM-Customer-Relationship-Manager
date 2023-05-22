package web.customer.tracker.service;

import java.util.List;

import web.customer.tracker.entity.Customer;

public interface CustomerService {
	public List<Customer> getCustomers();

	public void saveCustomer(Customer theCustomer);

	public Customer getCustomer(int theId);

	public void deleteCustomer(int theId);

	public List<Customer> searchCustomerByName(String name);

	public List<Customer> getSortedCustomers(int theSortField);
}
