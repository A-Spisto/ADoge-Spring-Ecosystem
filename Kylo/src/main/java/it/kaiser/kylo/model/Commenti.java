package it.kaiser.kylo.model;

public class Commenti {
	
	private Integer postId;
	private Integer id;
	private String email;
	private String body;
	
	
	
	public Integer getPostId() {
		return postId;
	}
	public void setPostId(Integer postId) {
		this.postId = postId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "Commenti [postId=" + postId + ", id=" + id + ", email=" + email + ", body=" + body + "]";
	}
	
	
	
	

}
