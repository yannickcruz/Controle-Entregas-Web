package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import modelo.Cliente;
import modelo.Encomenda;
import modelo.Entregador;
import service.EncomendaService;
import service.EntregadorService;

@ViewScoped
@ManagedBean
public class EncomendaBean {
	
	@EJB
	private EncomendaService EncomendaService;
	@EJB
	private EntregadorService EntregadorService;
	/*
	 * A ser implementado
	@EJB
	private ClienteService ClienteService;
	*/
	
	private Encomenda encomenda = new Encomenda();
	private List<Encomenda> encomendas = new ArrayList<Encomenda>();
	private List<Cliente> clientes = new ArrayList<Cliente>();
	private List<Entregador> entregadores = new ArrayList<Entregador>();
	private Long idCliente = 0L;
	private Long idEntregador = 0L;
	private String texto;
	
	public void atualizarLista() {
		encomendas = EncomendaService.listAll();
	}
	
	public void pesquisarEncomenda() {
		encomendas = EncomendaService.pesquisarEncomendaPorCodigo(texto);
	}
	
	public void excluirEncomenda(Encomenda e) {
		EncomendaService.remove(e);
		FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage("Encomenda Excluído!"));
		atualizarLista();
	}
	
	public void carregarEncomenda(Encomenda e) {
		encomenda = e;
		idCliente = e.getCliente().getId();
		idEntregador = e.getEntregador().getId();
	}
	
	public void gravar() {
		// Relacionando cliente e entregador à encomenda
		//Cliente cli = ClienteService.obtemPorId(idCliente);
		Entregador ent = EntregadorService.obtemPorId(idEntregador);
		//encomenda.setCliente(cli);
		encomenda.setEntregador(ent);

		String msg;
		if (encomenda.getId() == null) {
			EncomendaService.create(encomenda);
			msg = "Encomenda Gravada!";
		} else {
			EncomendaService.merge(encomenda);
			msg = "Encomenda Atualizada!";
		}
		encomenda = new Encomenda();
		idCliente = 0L;
		idEntregador = 0L;
		FacesContext.getCurrentInstance().addMessage(
				"msg", new FacesMessage(msg));
		atualizarLista();
	}

	
	@PostConstruct
	public void iniciar() {
		//A ser implementado
		//clientes = clienteService.listAll();
		entregadores = EntregadorService.listAll();
		atualizarLista();
	}

	public EncomendaService getEncomendaService() {
		return EncomendaService;
	}

	public void setEncomendaService(EncomendaService encomendaService) {
		EncomendaService = encomendaService;
	}

	public Encomenda getEncomenda() {
		return encomenda;
	}

	public void setEncomenda(Encomenda encomenda) {
		this.encomenda = encomenda;
	}

	public List<Encomenda> getEncomendas() {
		return encomendas;
	}

	public void setEncomendas(List<Encomenda> encomendas) {
		this.encomendas = encomendas;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public EntregadorService getEntregadorService() {
		return EntregadorService;
	}

	public void setEntregadorService(EntregadorService entregadorService) {
		EntregadorService = entregadorService;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Entregador> getEntregadores() {
		return entregadores;
	}

	public void setEntregadores(List<Entregador> entregadores) {
		this.entregadores = entregadores;
	}

	public Long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(Long idCliente) {
		this.idCliente = idCliente;
	}

	public Long getIdEntregador() {
		return idEntregador;
	}

	public void setIdEntregador(Long idEntregador) {
		this.idEntregador = idEntregador;
	}
	
	
}
