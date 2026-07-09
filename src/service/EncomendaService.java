package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import modelo.Cliente;
import modelo.Encomenda;

@Stateless
public class EncomendaService extends GenericService<Encomenda> {

    public EncomendaService() {
        super(Encomenda.class);
    }

    public boolean clientePossuiEncomendas(Cliente cliente) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Encomenda> cq = cb.createQuery(Encomenda.class);

        Root<Encomenda> root = cq.from(Encomenda.class);

        cq.select(root);

        cq.where(cb.equal(root.get("cliente"), cliente));

        List<Encomenda> resultado = getEntityManager()
                .createQuery(cq)
                .getResultList();

        return !resultado.isEmpty();
    }
    
    public boolean entregadorPossuiEncomendas(Entregador entregador) {

        CriteriaBuilder cb = getEntityManager().getCriteriaBuilder();

        CriteriaQuery<Encomenda> cq = cb.createQuery(Encomenda.class);

        Root<Encomenda> root = cq.from(Encomenda.class);

        cq.select(root);

        cq.where(cb.equal(root.get("entregador"), entregador));

        List<Encomenda> resultado = getEntityManager()
                .createQuery(cq)
                .getResultList();

        return !resultado.isEmpty();
    }
}