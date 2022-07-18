/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/
package daojpa;

import java.util.List;

import com.db4o.query.Query;

import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import modelo.Telefone;

public class DAOTelefone  extends DAO<Telefone>{
	
	public Telefone read (Object chave){
		try{
			String numero = (String) chave;
			TypedQuery<Telefone> q = manager.createQuery("select p from Telefone p where p.numero = :n ",Telefone.class);
			q.setParameter("n", numero);

			return q.getSingleResult();
		}catch(NoResultException e){
			return null;
		}
	}

	//  sobrescrever o metodo readAll da classe DAO 
	
	//JÁ ESTA SENDO FEITO NO DAO GENERICO
	
//	public List<Telefone> readAll(){
//		TypedQuery<Telefone> q = manager.createQuery("select t from Telefone t order by t.contato.nome", Telefone.class);
////		TypedQuery<Telefone> q = manager.createQuery("select t from Telefone t order by t.numero", Telefone.class);
//		return  q.getResultList();
//	}

	
	//--------------------------------------------
	//  consultas
	//--------------------------------------------
	
	public List<Telefone> listarTelefonesFixos() {
		TypedQuery<Telefone> qB;
		qB = manager.createQuery("select t from Telefone t where t.numero like :n", Telefone.class);
		qB.setParameter("n", "3%"); 
		return qB.getResultList();
		
	}

	public List<Telefone> readByDigitos (String digitos){		
		TypedQuery<Telefone> q = manager.createQuery
				("select t from Telefone t where t.numero like :x order by t.numero",Telefone.class);
		q.setParameter("x", "%"+digitos+"%");
		return q.getResultList();
	}
}
