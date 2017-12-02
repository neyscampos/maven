package manager;

import java.util.List;


import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import entity.Cliente;
import persistence.ClienteDao;

@ManagedBean(name = "mb")
@RequestScoped
public class ManagerBean {

	private Cliente cliente;
	private List<Cliente> clientes;

	public ManagerBean() {
		cliente = new Cliente();
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public void gravar() {

		FacesContext fc = FacesContext.getCurrentInstance();
		try {
			ClienteDao dao = new ClienteDao();
			dao.create(cliente);
			fc.addMessage(null, new FacesMessage("Dados Gravados..."));
		} catch (Exception ex) {
			fc.addMessage(null, new FacesMessage("Error:" + ex.getMessage()));
		}
	}

	public List<Cliente> getClientes() {
		try {
			ClienteDao dao = new ClienteDao();
			clientes = dao.findAll();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
}
