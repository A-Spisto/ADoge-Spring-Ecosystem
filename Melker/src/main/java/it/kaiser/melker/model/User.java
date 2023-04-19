package it.kaiser.melker.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Document
@Data
public class User {
		
	private String name;
	private String role;
	

}
