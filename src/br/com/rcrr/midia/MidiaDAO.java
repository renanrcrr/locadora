package br.com.rcrr.midia;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import br.com.rcrr.util.HibernateUtil;

public class MidiaDAO 
{
	private Session sessao;
	private Transaction transacao;
	
	public void salvar(Midia midia)
	{		
		try
		{
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(midia);
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
	
	public void atualizar(Midia midia)
	{
		try
		{
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.update(midia);
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
	
	public void excluir(Midia midia)
	{
		try
		{
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.delete(midia);
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
	
	public Midia buscaMidia(Integer codigo)
	{
		Midia midia = null;
		
		try
		{
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Midia.class);
			filtro.add(Restrictions.eq("midia", codigo));
			midia = (Midia) filtro.uniqueResult();
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
		return midia;
	}
	
	public List<Midia> listar()
	{
		List<Midia> midias = null;
		
		try
		{
			this.sessao = HibernateUtil.getSessionFactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Midia.class);
			midias = filtro.list();
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
		return midias;
	}

}
