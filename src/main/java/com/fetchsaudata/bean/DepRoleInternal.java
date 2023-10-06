package com.fetchsaudata.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DepRoleInternal {
	
	private String deptRole;
	private String deptUserName;
}
