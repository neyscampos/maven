package persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import entity.Cliente;

public class ClienteDao {
	EntityManager manager;
	EntityTransaction transaction;

	public ClienteDao() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("maven_01");
		manager = factory.createEntityManager();
	}

	public void create(Cliente c) throws Exception {
		transaction = manager.getTransaction();

		transaction.begin();
		manager.persist(c);
		transaction.commit();
	}

	public List<Cliente> findAll() {
		return (List<Cliente>) manager.createQuery("select c from Cliente c").getResultList();
	}
	
	public void update(Cliente c)throws Exception{
		transaction = manager.getTransaction();

		transaction.begin();
		manager.merge(c);
		transaction.commit();
	}

	
	public void delete(Cliente c)throws Exception{
		transaction = manager.getTransaction();

		transaction.begin();
		manager.remove(c);
		transaction.commit();
	}
	
	public Cliente findByCode(Integer cod) {
		return (Cliente) manager.find(Cliente.class, cod);
	}
	
	
	public static void main(String[] args) {
		try {
			Cliente c = new Cliente(null, "orapoispois", "ora@gmail.com");
			new ClienteDao().create(c);
			System.out.println("Dados Gravados..." + c);
		} catch (Exception ex) {

		}
	}
}
