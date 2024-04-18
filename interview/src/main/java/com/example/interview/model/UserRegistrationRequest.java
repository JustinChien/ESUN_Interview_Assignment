package com.example.interview.model;

public class UserRegistrationRequest {
	private String uname;
	private String phone;
	private String email;
    private String password;
    private byte[] coverImage;
    private String biography;
    
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public byte[] getCoverImage() {
		return coverImage;
	}
	public void setCoverImage(byte[] coverImage) {
		this.coverImage = coverImage;
	}
	public String getBiography() {
		return biography;
	}
	public void setBiography(String biography) {
		this.biography = biography;
	}
	
	public UserRegistrationRequest(String phone,String uname, String email, String password, byte[] coverImage, String biography) {
		super();
		this.phone = phone;
		this.uname = uname;
		this.email = email;
		this.password = password;
		this.coverImage = coverImage;
		this.biography = biography;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
    

}
