/**********************************
 * IFPB - SI
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 **********************************/

package daojpa;

import java.util.List;

import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import modelo.Contato;


public class DAOContato extends DAO<Contato>{


	public Contato read (Object chave){
		try{
			String nome = (String) chave;
			TypedQuery<Contato> q = manager.createQuery("select p from Contato p where p.nome=:n",Contato.class);
			q.setParameter("n", nome);
			return q.getSingleResult();
			
		}catch(NoResultException e){
			return null;
		}
	}

	//  sobrescrever o metodo readAll da classe DAO 
	
	//JÁ ESTOA SENDO FEITO NO DAO GENERICO
//	public List<Contato> readAll(){
//		TypedQuery<Contato> q = manager.createQuery("select p from Contato p order by p.nome", Contato.class);
//		return  q.getResultList();
//	}

	//--------------------------------------------
	//  consultas
	//--------------------------------------------
	
	
	public List<Contato> listarContatosPorBairro(String bairro) {
		TypedQuery<Contato> qA;
		qA = manager.createQuery("select c from Contato c where c.endereco.bairro = :l", Contato.class);
		qA.setParameter("l", "tambau"); 	 		
		return qA.getResultList();
	}
	
	
	
	public  List<Contato> readByCaracteres(String caracteres) {
		TypedQuery<Contato> q = manager.createQuery
				("select p from Contato p where p.nome like :x  order by p.nome",Contato.class);
		q.setParameter("x", "%"+caracteres+"%");
		return q.getResultList();
	}

	public  List<Contato>  readByNTelefones(int n) {
		TypedQuery<Contato> q = manager.createQuery
				("select p from Contato p where SIZE(p.telefones) = :x",Contato.class);
		q.setParameter("x", n);
		return q.getResultList(); 
	}

	public  boolean  temTelefoneCelular(String nome) {
		try{
			Query q = manager.createQuery
					("select count(t) from Contato p join p.telefones t where p.nome = :x and t.numero like :y");
			q.setParameter("x", nome);
			q.setParameter("y", "9%"); //inicia com 9
			long cont = (Long) q.getSingleResult();
			return cont>0;	
		}catch(NoResultException e){
			return false;
		}
	}

	public  boolean  temTelefoneFixo(String nome) {
		try{
			Query q = manager.createQuery
					("select count(t) from Contato p join p.telefones t where p.nome = :x and t.numero like :y");
			q.setParameter("x", nome);
			q.setParameter("y", "3%"); //inicia com 3

			long cont = (Long) q.getSingleResult();
			return cont>0;	
		}catch(NoResultException e){
			return false;
		}
	}


}

