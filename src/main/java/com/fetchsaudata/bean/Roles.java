package com.fetchsaudata.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "roles")
public class Roles {
	@Id
	private String id;
	
	private String roleName;
	
	private String displayRoleName;
	
	private String departmentId;
	
//	private String displayDepartment;
	
	

}
