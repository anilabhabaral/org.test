package org.test.org.test;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

// Using session
public class Main {

	private static SessionFactory sessionFactory;

	public static void main(String[] args) {

		Main m = new Main();

		try {
			m.adddata("Mainak");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		m.getData();

	}

	// Add data in to DB
	public void adddata(String name) throws Exception {

		sessionFactory = (SessionFactory) new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(TestS.class).buildSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			TestS ts = new TestS();
			ts.setName(name);

			session.persist(ts);
			tx.commit();
			System.out.println("-----> data added successfully");

		}

		catch (Exception e) {
			// TODO: handle exception
			if (tx != null)
				tx.rollback();
			throw e;

		} finally {
			sessionFactory.close();
			session.close();

		}
	}

	// Fetch all the data from DB
	public void getData() {

		sessionFactory = (SessionFactory) new Configuration().configure("hibernate.cfg.xml")
				.addAnnotatedClass(TestS.class).buildSessionFactory();

		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try {
			tx = session.beginTransaction();

			List<TestS> listS = session.createQuery("FROM TestS").list();

			for (TestS tss : listS) {
				System.out.println(tss.getId() + ": " + tss.getName());
			}
			tx.commit();

		}

		catch (Exception e) {
			// TODO: handle exception
			if (tx != null)
				tx.rollback();
			throw e;

		} finally {
			sessionFactory.close();
			session.close();
		}

	}
}
