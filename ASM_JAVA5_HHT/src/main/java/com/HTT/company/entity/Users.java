package com.HTT.company.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Users {
	
	@Id
	@Column(name = "user_name")
	private String username;
	
	@Column(name = "pass_word")
	private String password;
	
	@Column(name = "account_name")
	private String accountName;
	
	@Column(name = "account_balance")
	private Double accountBalance;
	
	@Column(name = "number_phone")
	private String numberPhone;
	
	@Column(name = "gmail")
	private String gmail;
	
	@Column(name = "avatar")
	private String avatar;
	
	@Column(name = "is_exist")
	private Boolean isExist;
	
	@Column(name = "is_admin")
	private Boolean isAdmin;
	
	@Column(name = "date_update")
	private Date dateUpdate;
	
	@Column(name = "date_create")
	private Date dateCreate;
}
