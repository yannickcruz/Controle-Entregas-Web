package modelo;

import javax.persistence.Entity;

@Entity
public class Cliente extends Pessoa {

	private String cpf;

	public Cliente() {}

	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}