package br.com.rcrr.cliente;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.rcrr.util.HibernateUtil;

public class ClienteDAO 
{
	private Session sessao;
	private Transaction transacao;
	
	public void salvar(Cliente cliente)
	{		
		try
		{
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(cliente);
			this.transacao.commit();
		}
		catch(Throwable e)
		{
			System.out.println("N�o foi poss�vel salvar o contato. Erro: " + e.getMessage());
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
				System.out.println("Erro ao fechar opera��o de inser��o. Mensagem: " + e.getMessage());
			}
		}
	}
	
	public void atualizar(Cliente cliente)
	{
		try
		{
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.update(cliente);
			this.transacao.commit();
		}
		catch(HibernateException e)
		{
			System.out.println("N�o foi poss�vel alterar o contato. Erro: " + e.getMessage());
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
				System.out.println("Erro ao fechar a opera��o de atualiza��o. Erro: " + e.getMessage());
			}
		}
	}
	
	public void excluir(Cliente cliente)
	{
		try
		{
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.delete(cliente);
			this.transacao.commit();
		}
		catch(HibernateException e)
		{
			System.out.println("N�o foi poss�vel excluir o contato. Erro: " + e.getMessage());
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
				System.out.println("Erro ao fechar a opera��o de exclus�o. Erro: " + e.getMessage());
			}
		}
	}
	
	public Cliente buscaContato(Integer codigo)
	{
		Cliente cliente = null;
		
		try
		{
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Cliente.class);
			filtro.add(Restrictions.eq("categoria", codigo));
			cliente = (Cliente) filtro.uniqueResult();
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
				System.out.println("Erro ao fechar a opera��o buscar. Erro: " + e.getMessage());
			}
		}
		return cliente;
	}
	
	public List<Cliente> listar()
	{
		List<Cliente> clientes = null;
		
		try
		{
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Cliente.class);
			clientes = filtro.list();
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
				System.out.println("Erro ao fechar a opera��o de consulta. Erro: " + e.getMessage());
			}
		}
		return clientes;
	}

}
