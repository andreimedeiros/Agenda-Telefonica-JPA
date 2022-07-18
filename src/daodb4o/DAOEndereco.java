package daodb4o;

import java.util.List;

import com.db4o.query.Query;

import modelo.Endereco;

public class DAOEndereco extends DAO<Endereco>{

@Override	
	public void create(Endereco e) {
		int ultimoId = getMaxId();
		e.setId(ultimoId + 1);
		manager.store(e);
	}
	
	//numero   campo  unico 
	public Endereco read(Object chave) {
		String endereco = (String) chave;
		Query q = manager.query();
		q.constrain(Endereco.class);
		q.descend("logradouro").constrain(endereco);
		List<Endereco> resultados = q.execute();
		if (resultados.size()>0)
			return resultados.get(0);
		else
			return null;
	}
	
	/**********************************************************
	 * 
	 * TODAS AS CONSULTAS DE ENDERECO
	 * 
	 **********************************************************/

	public List<Endereco> readByEndereco(String logradouro) {
		Query q = manager.query();
		q.constrain(Endereco.class);
		q.descend("contato").descend("nome").constrain(logradouro);
		List<Endereco> result = q.execute(); 
		return result;
	}
	
	public List<Endereco> readByBairro(String bairro) {
		Query q = manager.query();
		q.constrain(Endereco.class);
		q.descend("contato").descend("nome").constrain(bairro);
		List<Endereco> result = q.execute(); 
		return result;
	}
}
