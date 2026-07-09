package controle;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import modelo.Cliente;
import service.ClienteService;
import service.EncomendaService;

@ManagedBean
@ViewScoped
public class ClienteBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private ClienteService clienteService;

    @EJB
    private EncomendaService encomendaService;

    private Cliente cliente;

    private List<Cliente> listaClientes;

    private String filtroNome;

    @PostConstruct
    public void init() {
        novo();
        listar();
    }

    public void novo() {
        cliente = new Cliente();
    }

    public void listar() {
        listaClientes = clienteService.listAll();
    }

	public ClienteService getClienteService() {
		return clienteService;
	}


	public EncomendaService getEncomendaService() {
		return encomendaService;
	}


	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Cliente> getListaClientes() {
		return listaClientes;
	}

	public void setListaClientes(List<Cliente> listaClientes) {
		this.listaClientes = listaClientes;
	}	

	public String getFiltroNome() {
		return filtroNome;
	}

	public void setFiltroNome(String filtroNome) {
		this.filtroNome = filtroNome;
	}


    


}