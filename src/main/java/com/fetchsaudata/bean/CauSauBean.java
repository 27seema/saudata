package com.fetchsaudata.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CauSauBean {
	private String sau;
	private String sau_display_name;
	private String coorduser;
	private String cau;
	private String blockNo;
	private String subsection;
	private String command;
}
