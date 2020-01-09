package br.com.rcrr.locadora;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

import br.com.rcrr.categoria.Categoria;
import br.com.rcrr.categoria.CategoriaDAO;
import br.com.rcrr.cliente.Cliente;
import br.com.rcrr.cliente.ClienteDAO;
import br.com.rcrr.endereco.Endereco;
import br.com.rcrr.endereco.EnderecoDAO;
import br.com.rcrr.filme.Filme;
import br.com.rcrr.filme.FilmeDAO;
import br.com.rcrr.locacao.Locacao;
import br.com.rcrr.locacao.LocacaoDAO;
import br.com.rcrr.midia.Midia;
import br.com.rcrr.midia.MidiaDAO;
import br.com.rcrr.util.HibernateUtil;

public class Locadora 
{
	public static void main(String[] args)
	{
		HibernateUtil.getSessionFactory().openSession();
		
		Locadora locadora = new Locadora();
		locadora.cadastraCategorias();
		locadora.cadastraFilmes();
		locadora.cadastraMidias();
		
		Endereco endereco = new Endereco();
		Cliente cliente = new Cliente();
		
		cliente.setCelular("(47) 1212-2232");
		cliente.setEmail("sorim@ibm.com.br");
		cliente.setNome("Carlos Sorim");
		cliente.setTelefone("(47) 3232-2332");
		cliente.setEndereco(endereco);
		endereco.setBairro("Centro");
		endereco.setCidade("Belem");
		endereco.setComplemento("casa");
		endereco.setUf("PA");
		endereco.setRua("Av. Duque de Caixas");
		endereco.setCep("88098-234");
		endereco.setNumero(new Integer(1));
		endereco.setCliente(cliente);
		
		EnderecoDAO enderecoDAO = new EnderecoDAO();
		enderecoDAO.salvar(endereco);
		
		ClienteDAO clienteDAO = new ClienteDAO();
		clienteDAO.salvar(cliente);
		
		Locacao locacao = new Locacao();
		locacao.setDataDevolucao(new Date(System.currentTimeMillis()));
		locacao.setDataEmprestimo(new Date(System.currentTimeMillis()));
		locacao.setObservacao("Devolução final de semana");
		locacao.setHoraEmprestimo(new Time(System.currentTimeMillis()));		
		locacao.setCliente(cliente);
		
		MidiaDAO midiaDAO = new MidiaDAO();
		Midia midia = (Midia) midiaDAO.buscaMidia(new Integer(1));
		locacao.setMidia(midia);
		
		LocacaoDAO locacaoDAO = new LocacaoDAO();
		locacaoDAO.salvar(locacao);
		
		System.out.println("Cadastros gerados com sucesso!");
	}
	
	public void cadastraCategorias()
	{
		String categorias[] = {"Aventura", "Ação", "Comédia"};
		Categoria categoria = null;
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		for(int i = 0; i < 3; i++)
		{
			categoria = new Categoria();
			categoria.setDescricao(categorias[i]);
			categoriaDAO.salvar(categoria);
		}
	}
	
	public void cadastraFilmes()
	{
		CategoriaDAO categoriaDAO = new CategoriaDAO();
		String[] filmesDescricao = {"Senhor dos Anéis", "Transformers", "Ghostbusters"};
		Date[] filmesAnoProducao = {new Date(2001), new Date(2007),
				new Date(2011)};
		FilmeDAO filmeDAO = new FilmeDAO();
		Filme filme = null;
		for(int i = 0; i < 3; i++)
		{
			filme = new Filme();
			filme.setDescricao(filmesDescricao[i]);
			filme.setAno(filmesAnoProducao[i]);
			filme.setCategoria(categoriaDAO.buscaCategoria(i + 1));
			filmeDAO.salvar(filme);
		}
	}
	
	public void cadastraMidias()
	{
		Midia midia =  null;
		Filme filme = null;
		MidiaDAO midiaDAO = new MidiaDAO();
		FilmeDAO filmeDAO = new FilmeDAO();
		List<Filme> resultado = filmeDAO.listar();
		for(int i = 0; i < 3; i++)
		{
			midia = new Midia();
			filme = (Filme) resultado.get(i); 
			midia.setFilme(filme);
			midia.setInutilizada("N");
			midiaDAO.salvar(midia);
		}
	}
}
