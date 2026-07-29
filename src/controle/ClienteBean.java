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
import service.ClienteService;

@ManagedBean
@ViewScoped
public class ClienteBean {

    private Cliente cliente = new Cliente();
    private List<Cliente> listaClientes = new ArrayList<>();
    private Boolean edicao = false;

    @EJB
    private ClienteService clienteService;

    @PostConstruct
    public void iniciar() {
        atualizarLista();
    }

    public void salvarCliente() {
        try {
            clienteService.create(cliente);
            cliente = new Cliente();
            atualizarLista();
            addMensagem("Cliente cadastrado com sucesso!");
        } catch (Exception e) {
            addMensagem(e.getMessage());
        }
    }

    public void editarCliente() {
        try {
            clienteService.merge(cliente);
            cliente = new Cliente();
            edicao = false;
            atualizarLista();
            addMensagem("Cliente atualizado com sucesso!");
        } catch (Exception e) {
            addMensagem(e.getMessage());
        }
    }

    public void excluirCliente(Cliente c) {
        clienteService.remove(c);
        atualizarLista();
        addMensagem("Cliente excluído!");
    }

    public void carregarCliente(Cliente c) {
        this.cliente = c;
        this.edicao = true;
    }

    public void novoCliente() {
        this.cliente = new Cliente();
        this.edicao = false;
    }

    private void atualizarLista() {
        listaClientes = clienteService.listarOrdenadoPorNome();
    }

    

    private void addMensagem(String msg) {
        FacesContext.getCurrentInstance()
            .addMessage(null, new FacesMessage(msg));
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

    public Boolean getEdicao() {
        return edicao;
    }

    public void setEdicao(Boolean edicao) {
        this.edicao = edicao;
    }
}