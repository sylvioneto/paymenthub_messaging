package br.com.spedroza.PaymentHub.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PHTB_INC_MESSAGE")
public class IncomingMessage {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String fromBank;
	private String fromBranch;
	private String fromAccountNumber;
	private String toBank;
	private String toBranch;
	private String toAccountNumber;
	private BigDecimal amount;
	
	
	public IncomingMessage(String fromBank, String fromBranch, String fromAccountNumber, String toBank, String toBranch, String toAccountNumber, BigDecimal amount) {
		this.fromBank = fromBank;
		this.fromBranch = fromBranch;
		this.fromAccountNumber = fromAccountNumber;
		this.toBank = toBank;
		this.toBranch = toBranch;
		this.toAccountNumber = toAccountNumber;
		this.amount = amount;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getFromBank() {
		return fromBank;
	}
	public void setFromBank(String fromBank) {
		this.fromBank = fromBank;
	}
	public String getFromBranch() {
		return fromBranch;
	}
	public void setFromBranch(String fromBranch) {
		this.fromBranch = fromBranch;
	}
	public String getFromAccountNumber() {
		return fromAccountNumber;
	}
	public void setFromAccountNumber(String fromAccountNumber) {
		this.fromAccountNumber = fromAccountNumber;
	}
	public String getToBank() {
		return toBank;
	}
	public void setToBank(String toBank) {
		this.toBank = toBank;
	}
	public String getToBranch() {
		return toBranch;
	}
	public void setToBranch(String toBranch) {
		this.toBranch = toBranch;
	}
	public String getToAccountNumber() {
		return toAccountNumber;
	}
	public void setToAccountNumber(String toAccountNumber) {
		this.toAccountNumber = toAccountNumber;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
	@Override
	public String toString() {
		return "IncomingMessage [id=" + id + ", fromBank=" + fromBank + ", fromBranch=" + fromBranch + ", fromAccountNumber=" + fromAccountNumber + ", toBank=" + toBank
				+ ", toBranch=" + toBranch + ", toAccountNumber=" + toAccountNumber + ", amount=" + amount + "]";
	}
	
}
