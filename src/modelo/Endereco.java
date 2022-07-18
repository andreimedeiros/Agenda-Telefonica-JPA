package modelo;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Version;

@Entity
public class Endereco {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;			//autoincrementado dentro do create() sobrescrito no DAOEndereco(db4o)
	
	@Lob
	@Basic (fetch = FetchType.LAZY)
	private String logradouro;
	
	private String bairro;
	
	@Version
	private long versao;
	
	
	
	//------------  CONSTRUTOR -----------
	public Endereco() {}
	
	
	public Endereco(String logradouro, String bairro) {
		this.logradouro = logradouro;
		this.bairro = bairro;
	}
	
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	
}
