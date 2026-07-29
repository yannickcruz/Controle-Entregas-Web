package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import modelo.Cliente;

@Stateless
public class ClienteService extends GenericService<Cliente> {

    public ClienteService() {
        super(Cliente.class);
    }

    public List<Cliente> listarOrdenadoPorNome() {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Cliente> query = cb.createQuery(Cliente.class);
        Root<Cliente> root = query.from(Cliente.class);

        query.select(root);
        query.orderBy(cb.asc(root.get("nome")));

        return getEntityManager()
                .createQuery(query)
                .getResultList();
    }

    public boolean existeCpf(String cpf) {
        return existeCpf(cpf, null);
    }

    public boolean existeCpf(String cpf, Long idCliente) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> query = cb.createQuery(Long.class);
        Root<Cliente> root = query.from(Cliente.class);

        query.select(cb.count(root));

        if (idCliente == null) {
            query.where(cb.equal(root.get("cpf"), cpf));
        } else {
            query.where(cb.and(
                cb.equal(root.get("cpf"), cpf),
                cb.notEqual(root.get("id"), idCliente)
            ));
        }

        Long quantidade = getEntityManager()
                .createQuery(query)
                .getSingleResult();

        return quantidade > 0;
    }


    @Override
    public void create(Cliente cliente) {

        if (cliente.getCpf() == null || cliente.getCpf().trim().isEmpty()) {
            throw new RuntimeException("CPF é obrigatório");
        }

        if (existeCpf(cliente.getCpf())) {
            throw new RuntimeException("CPF já cadastrado");
        }

        super.create(cliente);
    }

    @Override
    public Cliente merge(Cliente cliente) {

        if (cliente.getId() == null) {
            throw new RuntimeException("Cliente inválido para atualização");
        }

        if (cliente.getCpf() == null || cliente.getCpf().trim().isEmpty()) {
            throw new RuntimeException("CPF é obrigatório");
        }

        if (existeCpf(cliente.getCpf(), cliente.getId())) {
            throw new RuntimeException("CPF já cadastrado para outro cliente");
        }

        return super.merge(cliente);
    }
    
    public List<Cliente> listAllOrdenado() {
        return listarOrdenadoPorNome();
    }
}