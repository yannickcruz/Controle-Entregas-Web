package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Expression;

import modelo.Entregador;

@Stateless
public class EntregadorService extends GenericService<Entregador> {
	
	public EntregadorService() {
		super(Entregador.class);
	}
	
	public List<Entregador> pesquisarEntregadorPorNome(String texto){
	    
	    final CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
	    final CriteriaQuery<Entregador> cQuery = cBuilder.createQuery(Entregador.class);
	    final Root<Entregador> rootEntregador = cQuery.from(Entregador.class);
	    cQuery.select(rootEntregador);

	    final Expression<String> expNome = rootEntregador.get("nome");

	    cQuery.where(cBuilder.like(expNome, "%"+texto+"%"));
	    cQuery.orderBy(cBuilder.asc(expNome));

	    List<Entregador> resultado = getEntityManager().createQuery(cQuery).getResultList();
	            
	    return resultado;
	}
}
