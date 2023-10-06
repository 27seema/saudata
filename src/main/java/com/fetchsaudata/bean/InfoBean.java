package com.fetchsaudata.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InfoBean {
	private String objectName;
	private String descName;
	private String addressLine1;
	private String telephoneS;
	private String telephone;
	private String addressLine3;
	private String faxS;
	private String addressLine2;
	private String fax;
	private String email;
	private String userRole;

}
