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

@ViewScoped
@ManagedBean
public class EncomendaBean {
	
	@EJB
	private EncomendaService EncomendaService;
	private Encomenda encomenda = new Encomenda();
	private List<Encomenda> encomendas = new ArrayList<Encomenda>();
	private boolean edicao = false;
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
		edicao = true;
	}
	
	public void atualizarEncomenda() {
		EncomendaService.merge(encomenda);
		encomenda = new Encomenda();
		atualizarLista();
		FacesContext.getCurrentInstance()
						.addMessage("", new FacesMessage("Encomenda Atualizado!"));
		edicao = false;
	}
	
	public void gravarEncomenda() {
		EncomendaService.create(encomenda);
		encomenda = new Encomenda();
		atualizarLista();
		FacesContext.getCurrentInstance()
						.addMessage("", new FacesMessage("Encomenda Cadastrado!"));
	}

	
	@PostConstruct
	public void iniciar() {
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

	public boolean isEdicao() {
		return edicao;
	}

	public void setEdicao(boolean edicao) {
		this.edicao = edicao;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}
	
	
}
