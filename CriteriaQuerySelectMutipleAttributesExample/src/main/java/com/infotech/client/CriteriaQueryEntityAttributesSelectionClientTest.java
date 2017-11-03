package com.infotech.client;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import com.infotech.entities.Employee;
import com.infotech.util.HibernateUtil;

public class CriteriaQueryEntityAttributesSelectionClientTest {

	public static void main(String[] args) {

		try (Session session = HibernateUtil.getSessionFactory().openSession()) {
			CriteriaBuilder builder = session.getCriteriaBuilder();
		   CriteriaQuery<Object[]> criteriaQuery = builder.createQuery(Object[].class);
			Root<Employee> root = criteriaQuery.from(Employee.class);
			
			Path<Object> employeeNamePath = root.get("employeeName");
			Path<Object> emailPath = root.get("email");
			Path<Object> salaryPath = root.get("salary");
			
			criteriaQuery.select(builder.array(employeeNamePath,emailPath,salaryPath));
			
			Query<Object[]> query = session.createQuery(criteriaQuery);
			List<Object[]> list = query.list();
			for (Object[] objects : list) {
				System.out.println("Employee Name:"+(String)objects[0]);
				System.out.println("Email:"+(String)objects[1]);
				System.out.println("Salary:"+(Double)objects[2]);
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
	
	}

}
