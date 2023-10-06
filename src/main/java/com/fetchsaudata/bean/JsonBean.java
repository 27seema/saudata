package com.fetchsaudata.bean;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class JsonBean {
	private String id;
	private String pcFileNumber;
	private String nofFileName;
	private String objId;
	private String displayPcFileNumber;
	private String fromRole;
	private String toRole;
	private int maxFlagNumber;
	private String paNumber;
	private String serviceLetterId = "";
	private List<FileDetails> notingList;
	private List<String> deptList;
	private List<FileDetails> enclosureList;
	@JsonFormat(pattern = "dd MMM yyyy")
	private LocalDateTime createdOn;
	private String hrmRole;
	private boolean hasCoverNote = false;
	private boolean enableAddNoting;
	private String createdBy;
	private String status;
	private String bookedOutBy;
	private String oldFile;
	private String residingWith;
	private List<String> custodian;
	private String subject;
	private String file;
	private String department;
	private String type;
	private boolean closeButton;
	private int volume;
	private String dFileName;
	private String displayFileName;
	private String confidentialOrNot;

	private String pklSection;
	private String fileClassification;

	private String financialYear;
	private boolean isMainFile;

}