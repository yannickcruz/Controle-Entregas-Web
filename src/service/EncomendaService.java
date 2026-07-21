package service;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;
import modelo.Encomenda;

@Stateless
public class EncomendaService extends GenericService<Encomenda> {
	
	public EncomendaService() {
		super(Encomenda.class);
	}
	
	public List<Encomenda> pesquisarEncomendaPorCodigo(String texto){
	    
	    final CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
	    final CriteriaQuery<Encomenda> cQuery = cBuilder.createQuery(Encomenda.class);
	    final Root<Encomenda> rootEncomenda = cQuery.from(Encomenda.class);
	    cQuery.select(rootEncomenda);

	    final Expression<String> expNome = rootEncomenda.get("codigo");

	    cQuery.where(cBuilder.like(expNome, "%"+texto+"%"));
	    cQuery.orderBy(cBuilder.asc(expNome));

	    List<Encomenda> resultado = getEntityManager().createQuery(cQuery).getResultList();
	            
	    return resultado;
	}
}
