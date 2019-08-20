package com.ay.wewin.api.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.Parameter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.ay.wewin.api.util.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "USER_MASTER", 
			uniqueConstraints = {
				@UniqueConstraint(columnNames = {
	                "username"
	            }),
	            @UniqueConstraint(columnNames = {
	                "email"
	            })
    })
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"token", "createdDate", "updatedDate"}, allowGetters = true)
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@GenericGenerator(
			name = "user_seq",
			strategy = "com.ay.wewin.api.util.StringPrefixedSequenceIdGenerator",
			parameters = {
					@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
					@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "AY"),
					@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%04d")
			})
	@Column(unique=true, nullable =false, length=6)
	private String id;
    
	@NotBlank
    @Size(min=3, max = 50)
	private String username;
	
    @NotBlank
    @Size(min=6, max = 100)
	private String password;

	@NotBlank
    @Size(min=3, max = 50)
	private String firstname;
	@NotBlank
    @Size(min=3, max = 50)
	private String lastname;

    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
	private String email;

    private String mobile;
	
	private boolean active;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_ROLES", 
    	joinColumns = @JoinColumn(name = "user_id"), 
    	inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();
	
	private String createdId;
	@CreatedDate
	private LocalDateTime createdDate;
	private String updatedId;
	@LastModifiedDate
	private LocalDateTime updatedDate;
	
	public User() {}
	
	public User(String username, String password, String firstname, String lastname, String email, String mobile) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.mobile = mobile;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	public String getCreatedId() {
		return createdId;
	}

	public void setCreatedId(String createdId) {
		this.createdId = createdId;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedId() {
		return updatedId;
	}

	public void setUpdatedId(String updatedId) {
		this.updatedId = updatedId;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

}
