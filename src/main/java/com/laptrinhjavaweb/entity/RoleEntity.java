package com.laptrinhjavaweb.entity;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "role")
public class RoleEntity extends BaseEntity{

	@Column
	private String code;
	
	@Column
	private String name;
	
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
	          CascadeType.PERSIST,
	          CascadeType.MERGE
	      },
			mappedBy = "roles")
	@JsonIgnore
	private Set<UserEntity> users = new HashSet<>();

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<UserEntity> getUsers() {
		return users;
	}

	public void setUsers(Set<UserEntity> users) {
		this.users = users;
	}
	
	
}
