package com.fetchsaudata.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateJson {
	private String userRole;
	private String addressLine2;
	private String addressLine3;
	private String addressLine1;
	private String telephoneS;
	private String email;

}
