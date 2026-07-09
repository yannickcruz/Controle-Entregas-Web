package modelo;

import javax.persistence.Entity;

@Entity
public class Cliente extends Pessoa {
	
    @Column(unique = true, nullable = false)
    private String cpf;


	private String cpf;

	public Cliente() {}

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}