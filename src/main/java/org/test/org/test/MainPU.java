package org.test.org.test;

import java.util.List;

import org.hibernate.Version;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

// Using Persistence Unit
public class MainPU {
	private EntityManagerFactory entityManagerFactory;

	public static void main(String[] args) {
		MainPU mainp = new MainPU();
		
		System.out.println(Version.getVersionString());

		mainp.getDataP();

	}

	public void getDataP() {
		entityManagerFactory = Persistence.createEntityManagerFactory("testPU");

		EntityManager entityManager = entityManagerFactory.createEntityManager();

		entityManager.getTransaction().begin();
//		Query query = entityManager.createNativeQuery("select id, name from TestS", TestS.class);
//
//		for (int i = 0; i < query.getResultList().size(); i++) {
//			TestS m = (TestS) query.getResultList().get(i);
//			System.out.println("--------> " + m.getId() + ": " + m.getName());
//		}
		
		@SuppressWarnings("unchecked")
		List<TestS> ts=entityManager.createQuery("FROM TestS").getResultList();
		
		for (TestS t:ts) {
			System.out.println("--------> " + t.getId() + ": " + t.getName());

		}

		entityManager.getTransaction().commit();
		entityManager.close();

	}

}
