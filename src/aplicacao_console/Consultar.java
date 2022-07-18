package aplicacao_console;


/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * POB - Persistencia de Objetos
 * Prof. Fausto Ayres
 *
 */

import java.util.List;
import java.util.Properties;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import modelo.Contato;
import modelo.Telefone;


/********************************************
 *********************************************
  *********************************************
 CLASSE APENAS PARA REFERÊNCIA. NÃO É UTILIZADA EM LUGAR ALGUM.
  ********************************************
 ********************************************
********************************************/

public class Consultar {
	protected static EntityManager manager;

	public Consultar() {
		Properties config = new Properties();
		String url = "jdbc:postgresql://localhost:5432/agendacontato";
		//String url = "jdbc:mysql://localhost:3306/empresa?createDatabaseIfNotExist=true";
		config.setProperty("jakarta.persistence.jdbc.url", url);

		EntityManagerFactory factory = Persistence.createEntityManagerFactory("eclipselink-postgresql", config);
		manager = factory.createEntityManager();

		consultar();	

		manager.close();
		factory.close();
	}




	private void exibirContatos(List<Contato> con) {
		for (Contato cont : con) 
			System.out.println(cont.toString());
	}
	
	private void exibirTelefones(List<Telefone> tel) {
		for (Telefone tels : tel) 
			System.out.println(tels.toString());
	}
	
//	private void exibirTelefones(List<Telefone> tel) {
//		for (Telefone tels : tel) {
//			System.out.print(tels.toString());
//			List<Contato> contatos = (List<Contato>) tels.getContatos();
//			System.out.print(",   Contatos:");
//			for (Contato cont : contatos) 
//				System.out.print("/"+tels.getNumero());
//			System.out.println();
//		}
//	}	


	public void consultar() {		
		List<Contato> con;
		List<Telefone> tel;
		
		TypedQuery<Contato> qA;
		TypedQuery<Telefone> qB;

		/*
		 * CONSULTA A: Quais os contatos que residem num determinado bairro?
		 */
		System.out.println("\n>Contatos que residem em tambaú:");
		qA = manager.createQuery("select c from Contato c where c.Endereco.bairro = :l", Contato.class);
		qA.setParameter("l", "tambau"); 	 
		con = qA.getResultList();
		exibirContatos(con);
//		List<Contato> contatosresult = qA.getResultList();
		

		/*
		 * CONSULTA B: Quais os telefones que são fixo (iniciam com digito 3)?
		 */

		System.out.println("\n>Telefones fixos: ");
		qB = manager.createQuery("select t from Telefone t where t.numero like :n", Telefone.class);
		qB.setParameter("n", "3%"); 
		tel = qB.getResultList();
		exibirTelefones(tel);
//		List<Telefone> telefonesresult = qB.getResultList();
		
		
		
//
//		System.out.println("\n>empregados que ganham mais que o seu chefe ");
//		query1 = manager.createQuery("select e from Empregado e where e.salario > e.dep.chefe.salario", Empregado.class);
//		emps = query1.getResultList();	//2,4,5
//		exibirEmpregados(emps);
//
//		/*
//		 * NAVEGACAO UM-PARA-MUITOS (join)
//		 */
//
//		System.out.println("\n>departamentos que tem empregados com salario >=3000");
//		query2 = manager.createQuery("select DISTINCT d from Departamento d JOIN d.empregados e where e.salario >= 3000", Departamento.class );
//		//query2 = manager.createQuery("select DISTINCT d from Departamento d, IN(d.empregados) e where e.salario >= 3000", Departamento.class );
//		deps = query2.getResultList();
//		exibirDepartamentos(deps);



		
		 
	}


	//========================================================================
	public static void main(String[] args) throws Exception {
		new Consultar();
	}
}