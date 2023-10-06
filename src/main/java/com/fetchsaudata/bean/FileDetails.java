package com.fetchsaudata.bean;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDetails {
	private String fileName;
	private String fileUrl;
	@Builder.Default
	private String comment = "";
	private int flagNumber;
	@JsonFormat(pattern = "dd MMM yyyy")
	private LocalDateTime signedOn;
	private boolean isSigned;
	private String prevVersionId;
	@Builder.Default
	private String annotationId = "";
	private String subject;
	private String status;
	private String serviceLetterId;
	private String fileId;
	@JsonFormat(pattern = "dd MMM yyyy")
	@Builder.Default
	private LocalDateTime uploadTime = LocalDateTime.now();
	@Builder.Default
	private boolean isCoverNote = false;
	private String uuid; //
	private String uploader;
	private String prevFlagNumber;
	private String flagNumberMarking;
	// migration
	private String objectName;
	private String dakSaveDirs;
	private String signedByUser;
	private String referenceObjectIds;
	private String pr;
	private String mfContent;
	private String creatorRole;
	private String classification;
	private String indexNo;
	private String dakStatus;
	private String indexFrom;
	private String dakType;
	private String fileFolderObjId;
	private String dakNo;
	private String subHead;
	private String docVersion;
	private String originalContent;
	private String signedBy;
	private String signatureDate;
	private String fileVolume;
	private String fileCreatedBy;
	@Builder.Default
	private boolean draft = false;
	private boolean justCreated;
	private String scannedRange;
	private String fromDir;
	private String fileOrDakType;
	private String mfFlagNumber;
	private String policyFile;
	private String fileReferenceNo;
	private String fileStatus;
	private String fileSubject;
	private String mainHead;
	private String caseNo;
	private String signedByDate;
	private String movedContent;
	private String subSection;
	private String directorate;
	private String cau;
	private String fno;
	private String blockNo;
	private String displayFileName;
	private String fr;
	private String scannedContentFlag;
	private String docType;
	private String subjectOfDak;
	private String section;
	private String countOfPages;
	private String folder;
}
