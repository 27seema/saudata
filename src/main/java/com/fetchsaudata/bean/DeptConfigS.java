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
@Document(collection = "departments")
public class DeptConfigS {
	@Id
	private String id;	

	@Field(name = "dept_name")
	private String deptName;
	
	@Field(name = "dept_coord_role")
	private String deptCoordRole;
	
	@Field(name = "dept_display_name")
	private String deptDisplayName;
	
	@Field(name = "dept_role_display_name")
	private String deptRoleDisplayName;
	
	@Field(name = "branch")
	private String branch;
	
	@Field(name = "section")
	private List<String> section;
	
	@Field(name = "roles")
	private List<Roles> roles;
	
	@Field(name = "sub_section")
	private List<String> subSection;

	@Field(name = "block_no")
	private List<String> blockNo;
	
	@Field(name = "cau")
	private String cau;
	
	private boolean isActive ;
	
	
	
	
	
	
}