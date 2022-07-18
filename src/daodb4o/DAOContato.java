/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 */
package daodb4o;

import java.util.List;

import com.db4o.query.Candidate;
import com.db4o.query.Evaluation;
import com.db4o.query.Query;

import modelo.Contato;
import modelo.Telefone;

public class DAOContato extends DAO<Contato>{

	//nome   usado como campo unico 
	public Contato read (Object chave) {
		String nome = (String) chave;	//casting para o tipo da chave
		Query q = manager.query();
		q.constrain(Contato.class);
		q.descend("nome").constrain(nome);
		List<Contato> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	
	/**********************************************************
	 * 
	 * TODAS AS CONSULTAS DE CONTATO
	 * 
	 **********************************************************/
	
	public  List<Contato> readByCaracteres(String caracteres) {
		Query q = manager.query();
		q.constrain(Contato.class);
		q.descend("nome").constrain(caracteres).like();		//insensitive
		List<Contato> result = q.execute(); 
		return result;
	}
	public Contato readByNumero(String n){
		Query q = manager.query();
		q.constrain(Contato.class);
		q.descend("telefones").descend("numero").constrain(n);
		List<Contato> resultados = q.execute();
		if(resultados.size()==0)
			return null;
		else
			return resultados.get(0);
	}
	
	public List<Contato> readByTelefones(int n) {
		Query q = manager.query();
		q.constrain(Contato.class);
		q.constrain(new Filtro(n));
		List<Contato> result = q.execute(); 
		return result;
	}
	
	public List<Contato> listarContatosPorBairro(String bairro) {
		Query q = manager.query();
		q.constrain(Contato.class);
		q.descend("endereco").descend("bairro").constrain(bairro).like();
		List<Contato> result = q.execute();
		return result;
	}
	

	/*-------------------------------------------------*/
	@SuppressWarnings("serial")
	class Filtro  implements Evaluation {
		private int n;
		public Filtro (int n) {this.n=n;}
		public void evaluate(Candidate candidate) {
			Contato p = (Contato) candidate.getObject();
			candidate.include( p.getTelefones().size()==n );
		}
	}
}
