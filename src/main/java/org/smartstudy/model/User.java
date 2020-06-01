package org.smartstudy.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.NumberFormat;
import org.springframework.lang.NonNull;
import org.springframework.web.multipart.MultipartFile;

@Entity
@Table(name="user")
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable{

	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@NotEmpty(message="Username is required")
	@Column(name="username")
	private String username;
	
	@NotEmpty(message="First Name is required")
	@Column(name="firstname")
	private String firstName;
	
	@NotEmpty(message="Last Name is required")
	@Column(name="lastname")
	private String lastName;
	
	
	@NotEmpty(message="Password is required")
	@Column(name="password")
	private String password;
	
	
	@Email
	@NotEmpty(message="Email is required")
	@Column(name="email")
	private String email;
	
	
	@NotEmpty(message="phone is required")
	@Size(max = 10, min=10, message = "digits should be 10")
	@Column(name="phone_number")
	private String phoneNumber;
	

	@Transient
	private List<MultipartFile> files = new ArrayList<MultipartFile>();
	@Transient
    private List<String> removeImages = new ArrayList<String>();
	
     
	
	public User() {
		
     	}

	
	
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	

	
	public String getUsername() {
		return username;
	}





	public void setUsername(String username) {
		this.username = username;
	}





	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
	public List<String> getRemoveImages() {
		return removeImages;
	}
	public void setRemoveImages(List<String> removeImages) {
		this.removeImages = removeImages;
	}
	
	
	

}
