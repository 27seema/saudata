package com.fetchsaudata.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepDetails {
	private String depDesignation;
	private String depSection;
	private String depBranch;
	private String depCoordRole;
	private String deptName;
	private String deptRole;
	private String deptRoleDesignation;
	private String deptUserName;
	private String depDisplayName;

}
