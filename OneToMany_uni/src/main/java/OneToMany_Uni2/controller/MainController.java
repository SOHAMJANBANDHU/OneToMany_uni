package OneToMany_Uni2.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import OneToMany_Uni2.dao.CompanyDao;
import OneToMany_Uni2.dto.Company;
import OneToMany_Uni2.dto.Employee;

public class MainController {

	public static void main(String[] args) {
		
//		Employee e1 = new Employee();
//		
//		e1.setId(4);
//		e1.setName("mahesh");
//		e1.setAddress("dhule");
//		e1.setPhone(9112198814l);
//		
//		Employee e2 = new Employee();
//		
//		e2.setId(5);
//		e2.setName("sam");
//		e2.setAddress("chandrapur");
//		e2.setPhone(9112198814l);
//		
//		Employee e3 = new Employee();
//		
//		e3.setId(6);
//		e3.setName("sneha");
//		e3.setAddress("delhi");
//		e3.setPhone(9112198814l);
//		
//		List<Employee> employees=new ArrayList<Employee>();
//		employees.add(e1);
//		employees.add(e2);
//		employees.add(e3);
//		
//		Company company = new Company();
//		
//		company.setId(101);
//		company.setName("TATA");
//		company.setGst("TATA123");
//		company.setEmployees(employees);
//		
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("soham");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
//		EntityTransaction entityTransaction = entityManager.getTransaction();
//		
//		entityTransaction.begin();
//		entityManager.persist(e1);
//		entityManager.persist(e2);
//		entityManager.persist(e3);
//		entityManager.persist(company);
//		entityTransaction.commit();
//		
		CompanyDao dao = new CompanyDao();
//		
//		dao.saveCompany(company);
//		
//		dao.findCompany(101);
//		
//		dao.deleteCompany(102);
//		
//		dao.upadateCompany(101, company);
//		dao.updateBoth(101, company);
//		dao.removeEmployee(101, 3);
		
		Scanner scan = new Scanner(System.in);
		System.out.println("Select option for the task \n1.save company details "
				+ "\n2. find company \n3.fetch all company \n4.update company "
				+ "\n5. update both \n6. delete company");
		int option = scan.nextInt();
		
		switch (option) {
		case 1:
		{
			System.out.println("how many person do you want to add in company");
			int a = scan.nextInt();
			
			
			List<Employee> employees=new ArrayList<Employee>();
			
			for (int i = 0; i < a; i++) {
				Employee employee = new Employee();
				
				System.out.println("enter person id");
				employee.setId(scan.nextInt());
				
				System.out.println("enter name");
				employee.setName(scan.next());
				
				System.out.println("enter address");
				employee.setAddress(scan.next());
				
				System.out.println("enter phone");
				employee.setPhone(scan.nextLong());
				
				employees.add(employee);
			}
			Company company = new Company();
			
			System.out.println("enter company id");
			company.setId(scan.nextInt());
			
			System.out.println("enter company name");
			company.setName(scan.next());
			
			System.out.println("enter gst");
			company.setGst(scan.next());
			
			company.setEmployees(employees);
			
			dao.saveCompany(company);
			
			break;
		}
		case 2:
		{
			System.out.println("Enter company id");
			dao.findCompany(scan.nextInt());
			break;
		}
		case 3:
		{
			System.out.println("Here are the all details");
			dao.findAllCompany();
			break;
		}
		case 4:
		{
			Company company = new Company();
			
			System.out.println("enter company id");
//			company.setId(scan.nextInt());
			int a = scan.nextInt();
			
			System.out.println("enter company name");
			company.setName(scan.next());
			
			System.out.println("enter gst");
			company.setGst(scan.next());
			
			dao.upadateCompany(a, company);
			break;
		}
		case 5:
		{
//			Pending work
//			int id = scan.nextInt();
			
			break;
		}
		case 6:
		{
			System.out.println("Enter company ID");
			 dao.deleteCompany(scan.nextInt());
			break;
		}
		default:
		{
			System.out.println("Enter a valid choice");
			break;
		}
			
		}
	}
	
}
