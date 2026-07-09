package service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import modelo.Entregador;

public class ClienteService extends GenericService<Cliente> {

    public ClienteService() {
        super(Cliente.class);
    }

    public List<Cliente> pesquisarClientePorNome(String texto) {
	    final CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
	    final CriteriaQuery<Cliente> cQuery = cBuilder.createQuery(Cliente.class);
	    final Root<Cliente> rootCliente = cQuery.from(Cliente.class);
	    cQuery.select(rootCliente);

	    final Expression<String> expNome = rootCliente.get("nome");

	    cQuery.where(cBuilder.like(expNome, "%"+texto+"%"));
	    cQuery.orderBy(cBuilder.asc(expNome));

	    List<Cliente> resultado = getEntityManager().createQuery(cQuery).getResultList();
	            
	    return resultado;
    }

    public boolean existeCpf(String cpf, Long idCliente) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Long> cq = cb.createQuery(Long.class);

        Root<Cliente> root = cq.from(Cliente.class);

        cq.select(cb.count(root));

        if (idCliente == null) {

            cq.where(
                cb.equal(root.get("cpf"), cpf)
            );

        } else {

            cq.where(
                cb.and(
                    cb.equal(root.get("cpf"), cpf),
                    cb.notEqual(root.get("id"), idCliente)
                )
            );
        }

        Long quantidade = getEntityManager()
                .createQuery(cq)
                .getSingleResult();

        return quantidade > 0;
    }

}
