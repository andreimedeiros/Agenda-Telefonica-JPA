/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
//import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import modelo.Endereco;


public class DAOEndereco extends DAO<Endereco>{


	public Endereco read (Object chave){
		try{
			String logradouro = (String) chave;
			TypedQuery<Endereco> q = manager.createQuery("select p from Endereco p where p.logradouro=:n",Endereco.class);
			q.setParameter("n", logradouro);
			return q.getSingleResult();
			
		}catch(NoResultException e){
			return null;
		}
	}

	
	//  sobrescrever o metodo readAll da classe DAO 
	
	// JA ESTA SENDO FEITO NO DAO GENERICO
//	public List<Endereco> readAll(){
//		TypedQuery<Endereco> q = manager.createQuery("select p from Endereco p order by p.id", Endereco.class);
//		return  q.getResultList();
//	}

	
	
	
	//--------------------------------------------
	//  consultas
	//--------------------------------------------
	public  List<Endereco> readByCaracteres(String caracteres) {
		TypedQuery<Endereco> q = manager.createQuery
				("select p from Endereco p where p.logradouro like :x  order by p.logradouro",Endereco.class);
		q.setParameter("x", "%"+caracteres+"%");
		return q.getResultList();
	}


	


}

