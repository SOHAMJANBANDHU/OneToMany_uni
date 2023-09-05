package OneToMany_Uni2.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import OneToMany_Uni2.dto.Company;
import OneToMany_Uni2.dto.Employee;

public class CompanyDao {
	
	EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soham");
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	EntityTransaction entityTransaction = entityManager.getTransaction();

	public void saveCompany(Company company)
	{
		
		List<Employee> employees = company.getEmployees();
		
		entityTransaction.begin();
		for (Employee employee : employees) 
		{
			entityManager.persist(employee);
		}
		entityManager.persist(company);
		entityTransaction.commit();
		
	}
	
	public void findCompany(int id)
	{
		Company company = entityManager.find(Company.class, id);
		
		if(company != null) {
			System.out.println(company);
		} else {
			System.out.println("id not found");
		}
	}
	
	public void findAllCompany()
	{
		Query query = entityManager.createQuery("SELECT c FROM Company c");
		System.out.println(query.getResultList());
		
	}
	
	public void deleteCompany(int id)
	{
		Company company = entityManager.find(Company.class, id);
		if (company != null) {
			List<Employee> employees = company.getEmployees();
			
			entityTransaction.begin();
			for (Employee employee : employees) {
				entityManager.remove(employee);
			}
			entityManager.remove(company);
			entityTransaction.commit();
		} else {
			System.out.println("Id is not found");
		}
	}
	
	public void upadateCompany(int id, Company company)
	{
		Company dbCompany = entityManager.find(Company.class, id);
		if (dbCompany != null)
		{
			company.setId(id);
			company.setEmployees(dbCompany.getEmployees());
			
			entityTransaction.begin();
			entityManager.merge(company);
			entityTransaction.commit();
		}
		else {
			System.out.println("id is not found");
		}	
	}
	
	public void updateBoth(int id, Company company)
	{
		Company dbCompany = entityManager.find(Company.class, id);
		if (dbCompany != null)
		{
			company.setId(id);
			List<Employee> dbList = dbCompany.getEmployees();
			List<Employee> list = company.getEmployees();
			for(int i=0; i<dbList.size();i++)
			{
				list.get(i).setId(dbList.get(i).getId());
			}
			
			entityTransaction.begin();
			for (Employee employee : list) {
				entityManager.merge(employee);
			}
			entityManager.merge(company);
			entityTransaction.commit();
		}
		else {
			System.out.println("id is not found");
		}
	}
	
	public void removeEmployee(int Cid,int Eid)
	{
		Company company = entityManager.find(Company.class, Cid);
		Employee employee = entityManager.find(Employee.class, Eid);
		
		List<Employee> list = company.getEmployees();
		list.remove(employee);
		entityTransaction.begin();
		entityManager.remove(employee);
		entityManager.merge(company);
		entityTransaction.commit();
	}
	
	public void removeMulipleEmployee(int Cid,List<Integer> Eids) {
		
		Company company = entityManager.find(Company.class, Cid);
		List<Employee> list = new ArrayList<Employee>();
		
		for (Integer Eid : Eids) {
			Employee employee = entityManager.find(Employee.class, Eid);
			list.add(employee);
		}
		
		List<Employee> employees = company.getEmployees();
		employees.removeAll(list);
		
		entityTransaction.begin();
		for (Employee employee : list) {
			entityManager.remove(employee);
		}
		entityTransaction.commit();
		
	}
}
