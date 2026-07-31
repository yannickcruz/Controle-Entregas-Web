package service;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import modelo.Encomenda;
import modelo.Entregador;

@Stateless
public class EncomendaService extends GenericService<Encomenda> {
	
	public EncomendaService() {
		super(Encomenda.class);
	}
	
	public List<Encomenda> filtrosRelatorio(Long idEntregador, String cidadeCliente, Double precoEncomenda){
		final CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
	    final CriteriaQuery<Encomenda> cQuery = cBuilder.createQuery(Encomenda.class);
	    final Root<Encomenda> rootEncomenda = cQuery.from(Encomenda.class);
	    cQuery.select(rootEncomenda);
	    
	    final Expression<Long> expEntregador = rootEncomenda.get("entregador").get("id");
	    final Expression<String> expCidadeCliente = rootEncomenda.get("cliente").get("endereco").get("cidade");
	    final Expression<Double> expPrecoEncomenda = rootEncomenda.get("valor");
	    final Expression<String> expClienteNome = rootEncomenda.get("cliente").get("nome");
	    
	    List<Predicate> predicados = new ArrayList<Predicate>();
	    
	    if(idEntregador != null) {
	    	final Predicate entregadorSelecionado = cBuilder.equal(expEntregador, idEntregador);
	    	predicados.add(entregadorSelecionado);
	    }
	    if(cidadeCliente != null) {
	    	final Predicate cidadeLike = cBuilder.like(expCidadeCliente, "%"+cidadeCliente+"%");
	    	predicados.add(cidadeLike);
	    }
	    if(precoEncomenda != null) {
	    	final Predicate precoMenor = cBuilder.lt(expPrecoEncomenda, precoEncomenda);
	    	predicados.add(precoMenor);
	    }
	    
	    if(predicados.isEmpty() == false) {
	    	cQuery.where(predicados.toArray(new Predicate[0]));
	    }
	    
	    cQuery.orderBy(cBuilder.asc(expClienteNome));
	    
	    List<Encomenda> list = getEntityManager().createQuery(cQuery).getResultList();
    	return list;
	}
	
	public boolean existeEncomendaEntregador(Long idEntregador) {
		final CriteriaBuilder cBuilder = getEntityManager().getCriteriaBuilder();
	    final CriteriaQuery<Long> cQuery = cBuilder.createQuery(Long.class);
	    final Root<Encomenda> rootEncomenda = cQuery.from(Encomenda.class);
	    
	    final Expression<Long> expIdEntregador = rootEncomenda.get("entregador").get("id");
	    final Expression<Long> expTotalEncomendas = cBuilder.count(rootEncomenda);
	    
	    final Predicate entregadorIgual = cBuilder.equal(expIdEntregador, idEntregador);

	    cQuery.select(expTotalEncomendas);
	    cQuery.where(entregadorIgual);
	    
	    Long quantidade = getEntityManager().createQuery(cQuery).getSingleResult();
	    if(quantidade > 0) {
	    	return true;
	    }
	    return false;

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
