package com.fetchsaudata.bean;

import java.util.List;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "user")
public class DeptConfigT {
	@Id
	private String id;	

	@Field(name = "dept_name")
	private String deptName;
	
	@Field(name = "dept_role")
	private Roles deptRole;	

	@Field(name = "dept_username")
	private String deptUsername;
	
	@Field(name = "dept_display_name")
	private String deptDisplayName;
	
	@Field(name = "dept_id")
	private String deptId;
	
	@Field(name = "dept_display_username")
	private String deptDisplayUsername;
	
	@Field(name = "apptid")
	private String apptId;
	
	private boolean admin;
	
	@Field(name = "apptdisplay")
	private String apptDisplay;
	
	
	@Field(name = "branch")
	private String branch;
	
	@Field(name = "rankname")
	private String rankName;
	
	@Field(name = "active")
	private boolean active;
	
	@Field(name = "designation")
	private String designation; //DGM
	
	@Field(name = "location")
	private String location; //MRO
	
	@Field(name = "email")
	private String email;
	
	@Field(name = "mobile_number")
	private String mobile_number;
	
	@Field(name="group_names")
	private List<String> groupNames;

	
}
