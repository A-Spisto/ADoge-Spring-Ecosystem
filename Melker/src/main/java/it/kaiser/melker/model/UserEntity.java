package it.kaiser.melker.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Document
@Data
public class UserEntity {
		
	private String id;
	private String name;
	private String email;
	private String EncryptedPassword;
	private String role;
	

}
