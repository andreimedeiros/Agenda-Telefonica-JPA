package modelo;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Version;

/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programação Orientada a Objetos
 * Prof. Fausto Maranhão Ayres
 **********************************/

@Entity
public class Telefone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String numero;
	
	@ManyToMany(
			// O 'mappedBy = "telefones",' não se aplica, pois este parâmetro só pode ficar de um lado do relacionamento
			cascade = {CascadeType.PERSIST, CascadeType.MERGE}, 
			fetch = FetchType.LAZY)   // Default = lazy
	private List<Contato> contatos = new ArrayList<>() ;
	
	
	@Version
	private long versao;

	
	//------------  CONSTRUTOR -----------
	public Telefone() {}
	
	public Telefone(String numero) {
		this.numero = numero;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public void adicionar(Contato p){
		contatos.add(p);
	}
	public void remover(Contato p){
		contatos.remove(p);
	}

	public Contato localizar(String nome){
		for(Contato p : contatos){
			if(p.getNome().equals(nome))
				return p;
		}
		return null;
	}



	@Override
	public String toString() {
		String texto = "numero=" + numero ;
		texto += ", contatos:";

		for(Contato p: contatos) 
			texto += " " + p.getNome() ;

		return texto ;
	}

}
