package web.customer.tracker.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import web.customer.tracker.entity.Customer;

@Repository
public class CustomerDAOImplementation implements CustomerDAO {

	 
	// I need to inject the session factory
	// so that my DAO can use it to connect to the DB
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List<Customer> getCustomers() {
		
		// get the current hibernayte session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// create the query
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by lastName", Customer.class);
		
		// execute the query and get result list
		List<Customer> customers = theQuery.getResultList();
		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		// get current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// save the customer
		currentSession.saveOrUpdate(theCustomer);
		// this method will look for the customer's id and
		// if it didn't find it
		// it will create a new one
		// if it was found
		// it will update it
	}

	@Override
	public Customer getCustomer(int theId) {
		// get the current hibernate sesison
		Session currentSession = sessionFactory.getCurrentSession();
		
		// retrieve/ read from the databse using the primary key
		return currentSession.get(Customer.class, theId);
	}

	@Override
	public void deleteCustomer(int theId) {
		// get the current hibernate sesison
		Session currentSession = sessionFactory.getCurrentSession();
		
		// delete the object with primary key
		@SuppressWarnings("rawtypes")
		Query theQuery = currentSession.createQuery("delete from Customer where id=:customerId");
		theQuery.setParameter("customerId", theId);
		theQuery.executeUpdate();
	}

	@Override
	public List<Customer> searchCustomerByName(String name) {
		System.out.println("inside DAO");
		// get the current hibernate sesison
		Session currentSession = sessionFactory.getCurrentSession();
		
		@SuppressWarnings("rawtypes")
		Query query = null;
		// search for firsname or last name case sensitive
					
		// only search by name if the search nam eis not empty
//		if(name != null && name.trim().length() > 0) {
//			// search for firsname or last name case sensitive
//			query = currentSession.createQuery("from Customer where lower(firstName)"
//					+ " like :name or lower(lastName) like :name", Customer.class);
//			query.setParameter("name", "%"+name.toLowerCase()+"%");
//		}else {
//			// the search name is empty so just get all the users
//			query = currentSession.createQuery("form Customer", Customer.class);
//		}
		
		query = currentSession.createQuery("from Customer where lower(firstName)"
				+ " like :name or lower(lastName) like :name", Customer.class);
		query.setParameter("name", "%"+name.toLowerCase()+"%");
		
		
		System.out.println("query inside DAO : "+ query);
		@SuppressWarnings("unchecked")
		List<Customer> customers = query.getResultList();
		
		return customers;
	}

}












