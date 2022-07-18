/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 */
package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Contato;
import modelo.Telefone;

public class DAOTelefone extends DAO<Telefone> {

	// numero campo unico
	public Telefone read(Object chave) {
		String numero = (String) chave;
		Query q = manager.query();
		q.constrain(Telefone.class);
		q.descend("numero").constrain(numero);
		List<Telefone> resultados = q.execute();
		if (resultados.size() > 0)
			return resultados.get(0);
		else
			return null;
	}

	/**********************************************************
	 * 
	 * TODAS AS CONSULTAS DE TELEFONE
	 * 
	 **********************************************************/

	public List<Telefone> readByDigitos(String digitos) {
		Query q = manager.query();
		q.constrain(Telefone.class);
		q.descend("numero").constrain(digitos).like();
		List<Telefone> result = q.execute();
		return result;
	}

	public List<Telefone> readByNome(String nome) {
		Query q = manager.query();
		q.constrain(Telefone.class);
		q.descend("contato").descend("nome").constrain(nome);
		List<Telefone> result = q.execute();
		return result;
	}

	public List<Telefone> listarTelefonesFixos() {
		Query q = manager.query();
		q.constrain(Telefone.class);
		q.descend("numero").constrain("3").startsWith(true);
		return q.execute();
	}

}
