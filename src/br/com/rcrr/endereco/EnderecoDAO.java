package br.com.rcrr.endereco;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.rcrr.util.HibernateUtil;

public class EnderecoDAO 
{
	private Session sessao;
	private Transaction transacao;
	
	public void salvar(Endereco endereco)
	{		
		try
		{
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(endereco);
			this.transacao.commit();
		}
		catch(Throwable e)
		{
			System.out.println("Não foi possível salvar o contato. Erro: " + e.getMessage());
		}
		finally
		{
			try
			{
				if(this.sessao.isOpen())
					this.sessao.close();
			}
			catch(HibernateException e)
			{
				System.out.println("Erro ao fechar operação de inserção. Mensagem: " + e.getMessage());
			}
		}
	}
	
	public void atualizar(Endereco endereco)
	{
		try
		{
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.update(endereco);
			this.transacao.commit();
		}
		catch(HibernateException e)
		{
			System.out.println("Não foi possível alterar o contato. Erro: " + e.getMessage());
		}
		finally
		{
			try
			{
				if(this.sessao.isOpen())
					sessao.close();
			}
			catch(Throwable e)
			{
				System.out.println("Erro ao fechar a operação de atualização. Erro: " + e.getMessage());
			}
		}
	}
	
	public void excluir(Endereco endereco)
	{
		try
		{
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.delete(endereco);
			this.transacao.commit();
		}
		catch(HibernateException e)
		{
			System.out.println("Não foi possível excluir o contato. Erro: " + e.getMessage());
		}
		finally
		{
			try
			{
				if(this.sessao.isOpen())
				sessao.close();
			}
			catch(Throwable e)
			{
				System.out.println("Erro ao fechar a operação de exclusão. Erro: " + e.getMessage());
			}
		}
	}
	
	public Endereco buscaContato(Integer codigo)
	{
		Endereco endereco = null;
		
		try
		{
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Endereco.class);
			filtro.add(Restrictions.eq("enredeco", codigo));
			endereco = (Endereco) filtro.uniqueResult();
			transacao.commit();			
		}
		catch(Throwable e)
		{
			if(this.transacao.isActive())
				this.transacao.rollback();
		}
		finally
		{
			try
			{
				sessao.close();
			}
			catch(Throwable e)
			{
				System.out.println("Erro ao fechar a operação buscar. Erro: " + e.getMessage());
			}
		}
		return endereco;
	}
	
	public List<Endereco> listar()
	{
		List<Endereco> enderecos = null;
		
		try
		{
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Endereco.class);
			enderecos = filtro.list();
			this.transacao.commit();			
		}
		catch(HibernateException e)
		{
			if(this.transacao.isActive())
				this.transacao.rollback();
		}
		finally
		{
			try
			{
				if(this.sessao.isOpen())
				this.sessao.close();
			}
			catch(Throwable e)
			{
				System.out.println("Erro ao fechar a operação de consulta. Erro: " + e.getMessage());
			}
		}
		return enderecos;
	}

}
