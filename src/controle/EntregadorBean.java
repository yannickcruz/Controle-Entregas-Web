package controle;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import service.EntregadorService;
import service.EncomendaService;
import modelo.Encomenda;
import modelo.Entregador;

@ViewScoped
@ManagedBean
public class EntregadorBean {
	
	@EJB
	private EntregadorService EntregadorService;
	private Entregador entregador = new Entregador();
	private List<Entregador> entregadores = new ArrayList<Entregador>();
	private boolean edicao = false;
	private String texto;
	
	public void atualizarLista() {
		entregadores = EntregadorService.listAll();
	}
	
	public void pesquisarEntregador() {
		entregadores = EntregadorService.pesquisarEntregadorPorNome(texto);
	}
	
	public void excluirEntregador(Entregador e) {
		EntregadorService.remove(e);
		FacesContext.getCurrentInstance().addMessage("msg", new FacesMessage("Entregador Excluído!"));
		atualizarLista();
	}
	
	public void carregarEntregador(Entregador e) {
		entregador = e;
		edicao = true;
	}
	
	public void atualizarEntregador() {
		EntregadorService.merge(entregador);
		entregador = new Entregador();
		atualizarLista();
		FacesContext.getCurrentInstance()
						.addMessage("", new FacesMessage("Entregador Atualizado!"));
		edicao = false;
	}
	
	public void gravarEntregador() {
		EntregadorService.create(entregador);
		entregador = new Entregador();
		atualizarLista();
		FacesContext.getCurrentInstance()
						.addMessage("", new FacesMessage("Entregador Cadastrado!"));
	}

	
	@PostConstruct
	public void iniciar() {
		atualizarLista();
	}

	public EntregadorService getEntregadorService() {
		return EntregadorService;
	}

	public void setEntregadorService(EntregadorService entregadorService) {
		EntregadorService = entregadorService;
	}

	public Entregador getEntregador() {
		return entregador;
	}

	public void setEntregador(Entregador entregador) {
		this.entregador = entregador;
	}

	public List<Entregador> getEntregadores() {
		return entregadores;
	}

	public void setEntregadores(List<Entregador> entregadores) {
		this.entregadores = entregadores;
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
