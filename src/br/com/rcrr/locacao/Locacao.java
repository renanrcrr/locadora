package br.com.rcrr.locacao;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.rcrr.cliente.Cliente;
import br.com.rcrr.midia.Midia;

@Entity
@Table(name="locacao")
public class Locacao implements Serializable 
{
	
	private static final long serialVersionUID = -6431609359051417783L;

	@Id
	@GeneratedValue
	@Column(name = "cod_locacao")
	private Integer locacao;
	
	@ManyToOne
	@JoinColumn(name = "cod_cliente")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "cod_midia")
	private Midia midia;
	
	@Column(name = "data_emprestimo", updatable = false)
	private Date dataEmprestimo;
	
	@Column(name = "hora_emprestimo", updatable = false)
	private Time horaEmprestimo;
	
	@Column(name = "data_devolucao")
	private Date dataDevolucao;
	
	@Column(name = "obs")
	private String observacao;

	public Integer getLocacao() {
		return locacao;
	}

	public void setLocacao(Integer locacao) {
		this.locacao = locacao;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Midia getMidia() {
		return midia;
	}

	public void setMidia(Midia midia) {
		this.midia = midia;
	}

	public Date getDataEmprestimo() {
		return dataEmprestimo;
	}

	public void setDataEmprestimo(Date dataEmprestimo) {
		this.dataEmprestimo = dataEmprestimo;
	}

	public Time getHoraEmprestimo() {
		return horaEmprestimo;
	}

	public void setHoraEmprestimo(Time horaEmprestimo) {
		this.horaEmprestimo = horaEmprestimo;
	}

	public Date getDataDevolucao() {
		return dataDevolucao;
	}

	public void setDataDevolucao(Date dataDevolucao) {
		this.dataDevolucao = dataDevolucao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataDevolucao == null) ? 0 : dataDevolucao.hashCode());
		result = prime * result
				+ ((dataEmprestimo == null) ? 0 : dataEmprestimo.hashCode());
		result = prime * result
				+ ((horaEmprestimo == null) ? 0 : horaEmprestimo.hashCode());
		result = prime * result + ((locacao == null) ? 0 : locacao.hashCode());
		result = prime * result + ((midia == null) ? 0 : midia.hashCode());
		result = prime * result
				+ ((observacao == null) ? 0 : observacao.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Locacao other = (Locacao) obj;
		if (dataDevolucao == null) {
			if (other.dataDevolucao != null)
				return false;
		} else if (!dataDevolucao.equals(other.dataDevolucao))
			return false;
		if (dataEmprestimo == null) {
			if (other.dataEmprestimo != null)
				return false;
		} else if (!dataEmprestimo.equals(other.dataEmprestimo))
			return false;
		if (horaEmprestimo == null) {
			if (other.horaEmprestimo != null)
				return false;
		} else if (!horaEmprestimo.equals(other.horaEmprestimo))
			return false;
		if (locacao == null) {
			if (other.locacao != null)
				return false;
		} else if (!locacao.equals(other.locacao))
			return false;
		if (midia == null) {
			if (other.midia != null)
				return false;
		} else if (!midia.equals(other.midia))
			return false;
		if (observacao == null) {
			if (other.observacao != null)
				return false;
		} else if (!observacao.equals(other.observacao))
			return false;
		return true;
	}	
	
}
