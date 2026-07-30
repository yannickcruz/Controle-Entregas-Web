package controle;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import service.ClienteService;
import service.EncomendaService;
import service.EntregadorService;

@ViewScoped
@ManagedBean
public class RelatorioBean {

	@EJB
	private EncomendaService EncomendaService;
	@EJB
	private EntregadorService EntregadorService;
	@EJB
	private ClienteService ClienteService;
	
}
