package controle;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import service.ClienteService;
import service.EncomendaService;
import service.EntregadorService;
import modelo.Encomenda;

@ViewScoped
@ManagedBean
public class RelatorioBean {

	@EJB
	private EncomendaService EncomendaService;
	
	private Long idEntregador = null;
	private String cidadeCliente = null;
	private Double precoEncomenda = null;
	
	private List<Encomenda> encomendas = new ArrayList<Encomenda>();
	
	public void filtrarEncomendas() {
		encomendas = EncomendaService.filtrosRelatorio(idEntregador, cidadeCliente, precoEncomenda);
	}

	public EncomendaService getEncomendaService() {
		return EncomendaService;
	}

	public void setEncomendaService(EncomendaService encomendaService) {
		EncomendaService = encomendaService;
	}

	public Long getIdEntregador() {
		return idEntregador;
	}

	public void setIdEntregador(Long idEntregador) {
		this.idEntregador = idEntregador;
	}

	public String getCidadeCliente() {
		return cidadeCliente;
	}

	public void setCidadeCliente(String cidadeCliente) {
		this.cidadeCliente = cidadeCliente;
	}

	public Double getPrecoEncomenda() {
		return precoEncomenda;
	}

	public void setPrecoEncomenda(Double precoEncomenda) {
		this.precoEncomenda = precoEncomenda;
	}

	public List<Encomenda> getEncomendas() {
		return encomendas;
	}

	public void setEncomendas(List<Encomenda> encomendas) {
		this.encomendas = encomendas;
	}
	
	
	
}
