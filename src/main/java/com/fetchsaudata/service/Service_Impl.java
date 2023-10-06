//package com.fetchsaudata.service;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import com.documentum.fc.client.DfQuery;
//import com.documentum.fc.client.IDfCollection;
//import com.documentum.fc.client.IDfQuery;
//import com.documentum.fc.client.IDfSession;
//import com.documentum.fc.common.DfException;
//import com.documentum.fc.common.IDfTime;
//import com.fetchsaudata.bean.Model;
//import com.fetchsaudata.serviceInterface.SauService;
//
//@Service
//public class Service_Impl implements SauService {
//	private static Logger log = LoggerFactory.getLogger(Service_Impl.class);
//	Integer L = 0;
//
//	public boolean checkFileCount(IDfSession session, String countQuery) {
//		IDfCollection coll = null;
//		try {
//			coll = getCollection(session, countQuery);
//			if (coll != null && coll.next()) {
//				int result = coll.getInt("filecount");
//				if (result > 0)
//					return true;
//			}
//		} catch (Exception ex) {
//			log.error("Exception " + ex.getMessage());
//		} finally {
//			try {
//				if (coll != null)
//					coll.close();
//			} catch (DfException e) {
//				log.warn("Exception " + e.getMessage());
//				e.printStackTrace();
//			}
//		}
//		return false;
//	}
//
//	@Override
//	public List<Model> getsaurow(String pkldirectorate, Integer Skip, Integer row, IDfSession session) {
//
//		List<Model> saumodel = new ArrayList<Model>();
//		List<Model> list = new ArrayList<Model>();
//
//		try {
//
//			String gefile = "select r_object_id,file_no, subject,custodian,  pkl_directorate from iaf_file_folder where pkl_directorate  like '%"
//					+ pkldirectorate.toUpperCase() + "%' and object_name not like '%(PC%'";
//			IDfCollection coll = getCollection(session, gefile);
//			while (coll.next()) {
//				Model sm = new Model();
//				String id = coll.getString("r_object_id");
//				String fileno = coll.getString("file_no");
//				String filename = coll.getString("subject");
//				String sau = coll.getString("pkl_directorate");
//				String custodian = coll.getAllRepeatingStrings("custodian", ",");
//
//				// log.info(id+" "+fileno+" "+filename+" "+sau);
//				sm.setId(id);
//				sm.setFilename(filename);
//				sm.setFileno(fileno);
//				sm.setPkldirectorate(sau);
//				sm.setCustodian(custodian);
//				saumodel.add(sm);
//			}
//			// log.info("saumodel"+saumodel);
//			int size = saumodel.size();
//			L = size;
//			int length = size;
//			int end = 0;
//			end = row + Skip;
//			for (int i = Skip; i < end; i++) {
//				if (i < length) {
//					list.add(saumodel.get(i));
//				}
//			}
//			return list;
//		} catch (DfException e) {
//			log.info("exception in catch " + e);
//			e.printStackTrace();
//			return list;
//		}
//	}
//
//	@Override
//	public Integer getLength(String pkldirectorate, IDfSession session) {
//		return L;
//	}
//
//	private IDfCollection getCollection(IDfSession session, String query) {
//		try {
//			IDfQuery dfQuery = new DfQuery();
//			dfQuery.setDQL(query);
//			log.info(query);
//			IDfCollection collection = dfQuery.execute(session, 1);
//			if (collection != null)
//				return collection;
//		} catch (DfException e) {
//
//			e.printStackTrace();
//		}
//		return null;
//	}
//
//	@Override
//	public List<Model> getsaubyfilename(String pkldirectorate, String filename, String fileno, Integer startIndex,
//			Integer endIndex, IDfSession session) {
//		IDfCollection coll = null;
//		try {
//			List<Model> list = new ArrayList<Model>();
//
//			if (fileno != null && filename != null) {
//				if (!filename.isEmpty() && !fileno.isEmpty()) {
//					log.info("enetred in 3 condition");
//					List<Model> saumodel = new ArrayList<Model>();
//					String countQuery = "select count(*) as filecount from iaf_file_folder where pkl_directorate ='"
//							+ pkldirectorate.toUpperCase() + "' and subject like '%" + filename.toUpperCase()
//							+ "%' and file_no like '%" + fileno.toUpperCase() + "%'and object_name not like '%(PC%' and file_status='Closed'";
//					String countQuery2 = "select distinct r_object_id from iaf_file_folder where pkl_directorate ='"
//							+ pkldirectorate.toUpperCase() + "' and subject like '%" + filename.toUpperCase()
//							+ "%' and file_no like '%" + fileno.toUpperCase() + "%'and object_name not like '%(PC%' and file_status='Closed'";
//					if (checkFileCount(session, countQuery)) {
//						String gefile = "select * from iaf_file_folder where r_object_id in  (" + countQuery2
//								+ " ENABLE(RETURN_RANGE " + startIndex + " " + endIndex + " 'r_object_id desc')) "
//								+ " order by r_object_id desc";
//						// String gefile = "select r_object_id,file_no, subject,custodian
//						// ,pkl_directorate from iaf_file_folder where pkl_directorate
//						// ='"+pkldirectorate.toUpperCase()+"' and subject like '%"
//						// +filename.toUpperCase()+ "%' and file_no like '%"+fileno.toUpperCase()+"%'
//						// and file_no not like '%(PC%'";
//						coll = getCollection(session, gefile);
//						while (coll.next()) {
//							Model sm = new Model();
//							String id = coll.getString("r_object_id");
//							String fileno1 = coll.getString("file_no");
//							String filename1 = coll.getString("subject");
//							String sau = coll.getString("pkl_directorate");
//							String custodian = coll.getAllRepeatingStrings("custodian", ",");
//
//							String case_no = coll.getString("case_no");
//							sm.setCase_no(case_no);
//							int part_case = coll.getInt("part_case");
//							String file_bar_code_name = coll.getString("file_bar_code_name");
//							String sau_code = coll.getString("sau_code");
//							String custodian_desig = coll.getAllRepeatingStrings("custodian_desig", ",");
//							String confidential_or_not = coll.getString("confidential_or_not");
//							String file_name = coll.getString("file_name");
//							IDfTime file_created_date = coll.getTime("file_created_date");
//							String creater_role = coll.getString("creater_role");
//							String cau = coll.getString("cau");
//							String display_file_name = coll.getString("display_file_name");
//							String d_file_name = coll.getString("d_file_name");
//							String type_of_file = coll.getString("type_of_file");
//							String block_number = coll.getString("block_number");
//							String confidential_type = coll.getString("confidential_type");
//							String pkl_branch = coll.getString("pkl_branch");
//							String pkl_section = coll.getString("pkl_section");
//							String file_classification = coll.getString("file_classification");
//							String volume_no = coll.getString("volume_no");
//
//							sm.setPart_case(part_case);
//							sm.setFile_bar_code_name(file_bar_code_name);
//							sm.setSau_code(sau_code);
//							sm.setCustodian_desig(custodian_desig);
//							sm.setConfidential_or_not(confidential_or_not);
//							sm.setFile_name(file_name);
//							sm.setFile_created_date(file_created_date.toString());
//							sm.setCreater_role(creater_role);
//							sm.setCau(cau);
//							sm.setDisplay_file_name(display_file_name);
//							sm.setType_of_file(type_of_file);
//							sm.setBlock_number(block_number);
//							sm.setConfidential_type(confidential_type);
//							sm.setPkl_branch(pkl_branch);
//							sm.setPkl_section(pkl_section);
//							sm.setFile_classification(file_classification);
//							sm.setVolume_no(volume_no);
//
//							sm.setId(id);
//							sm.setFilename(filename1);
//							sm.setFileno(fileno1);
//							sm.setPkldirectorate(sau);
//							sm.setCustodian(custodian);
//							saumodel.add(sm);
//						}
//					} else
//						log.warn("filecount is empty");
//					return saumodel;
//				}
//			}
//			if (filename != null) {
//				if (!filename.isEmpty()) {
//					log.info("enetred in 1 condition");
//					List<Model> saumodel = new ArrayList<Model>();
//					String countQuery = "select count(*) as filecount from iaf_file_folder where pkl_directorate ='"
//							+ pkldirectorate.toUpperCase() + "' and subject like '%" + filename.toUpperCase()
//							+ "%' and object_name not like '%(PC%' and file_status='Closed'";
//					String countQuery2 = "select distinct r_object_id from iaf_file_folder where pkl_directorate ='"
//							+ pkldirectorate.toUpperCase() + "' and subject like '%" + filename.toUpperCase()
//							+ "%' and object_name not like '%(PC%' and file_status='Closed'";
//
//					// String gefile = "select r_object_id,file_no, subject,custodian,
//					// pkl_directorate from iaf_file_folder where pkl_directorate
//					// ='"+pkldirectorate.toUpperCase()+"' and subject like '%"
//					// +filename.toUpperCase()+ "%' and file_no not like '%(PC%'";
//					if (checkFileCount(session, countQuery)) {
//						String gefile = "select * from iaf_file_folder where r_object_id in  (" + countQuery2
//								+ " ENABLE(RETURN_RANGE " + startIndex + " " + endIndex + " 'r_object_id desc')) "
//								+ " order by r_object_id desc";
//						coll = getCollection(session, gefile);
//						while (coll.next()) {
//							Model sm = new Model();
//							String id = coll.getString("r_object_id");
//							String fileno1 = coll.getString("file_no");
//							String filename1 = coll.getString("subject");
//							String sau = coll.getString("pkl_directorate");
//							String custodian = coll.getAllRepeatingStrings("custodian", ",");
//							String case_no = coll.getString("case_no");
//							sm.setCase_no(case_no);
//							int part_case = coll.getInt("part_case");
//							String file_bar_code_name = coll.getString("file_bar_code_name");
//							String sau_code = coll.getString("sau_code");
//							String custodian_desig = coll.getAllRepeatingStrings("custodian_desig", ",");
//							String confidential_or_not = coll.getString("confidential_or_not");
//							String file_name = coll.getString("file_name");
//							IDfTime file_created_date = coll.getTime("file_created_date");
//							String creater_role = coll.getString("creater_role");
//							String cau = coll.getString("cau");
//							String display_file_name = coll.getString("display_file_name");
//							String d_file_name = coll.getString("d_file_name");
//							String type_of_file = coll.getString("type_of_file");
//							String block_number = coll.getString("block_number");
//							String confidential_type = coll.getString("confidential_type");
//							String pkl_branch = coll.getString("pkl_branch");
//							String pkl_section = coll.getString("pkl_section");
//							String file_classification = coll.getString("file_classification");
//							String volume_no = coll.getString("volume_no");
//
//							sm.setPart_case(part_case);
//							sm.setFile_bar_code_name(file_bar_code_name);
//							sm.setSau_code(sau_code);
//							sm.setCustodian_desig(custodian_desig);
//							sm.setConfidential_or_not(confidential_or_not);
//							sm.setFile_name(file_name);
//							sm.setFile_created_date(file_created_date.toString());
//							sm.setCreater_role(creater_role);
//							sm.setCau(cau);
//							sm.setDisplay_file_name(display_file_name);
//							sm.setType_of_file(type_of_file);
//							sm.setBlock_number(block_number);
//							sm.setConfidential_type(confidential_type);
//							sm.setPkl_branch(pkl_branch);
//							sm.setPkl_section(pkl_section);
//							sm.setFile_classification(file_classification);
//							sm.setVolume_no(volume_no);
//
//							sm.setId(id);
//							sm.setFilename(filename1);
//							sm.setFileno(fileno1);
//							sm.setPkldirectorate(sau);
//							sm.setCustodian(custodian);
//							saumodel.add(sm);
//						}
//					} else
//						log.warn("filecount is empty");
//					return saumodel;
//				}
//			}
//			if (fileno != null) {
//				if (!fileno.isEmpty()) {
//					log.info("enetred in 2 condition");
//					List<Model> saumodel = new ArrayList<Model>();
//					String countQuery = "select count(*) as filecount from iaf_file_folder where pkl_directorate ='"
//							+ pkldirectorate.toUpperCase() + "' and file_no like '%" + fileno.toUpperCase()
//							+ "%' and object_name not like '%(PC%' and file_status='Closed'";
//					String countQuery2 = "select distinct r_object_id from iaf_file_folder where pkl_directorate ='"
//							+ pkldirectorate.toUpperCase() + "' and file_no like '%" + fileno.toUpperCase()
//							+ "%' and object_name not like '%(PC%' and file_status='Closed'";
//					if (checkFileCount(session, countQuery)) {
//
//						// String gefile = "select r_object_id,file_no, subject,custodian,
//						// pkl_directorate from iaf_file_folder where pkl_directorate
//						// ='"+pkldirectorate.toUpperCase()+"' and file_no like '%"
//						// +fileno.toUpperCase()+ "%' and file_no not like '%(PC%'";
//						String gefile = "select * from iaf_file_folder where r_object_id in  (" + countQuery2
//								+ " ENABLE(RETURN_RANGE " + startIndex + " " + endIndex + " 'r_object_id desc')) "
//								+ " order by r_object_id desc";
//						coll = getCollection(session, gefile);
//						while (coll.next()) {
//							Model sm = new Model();
//							String id = coll.getString("r_object_id");
//							String fileno1 = coll.getString("file_no");
//							String filename1 = coll.getString("subject");
//							String sau = coll.getString("pkl_directorate");
//							String custodian = coll.getAllRepeatingStrings("custodian", ",");
//							String case_no = coll.getString("case_no");
//							sm.setCase_no(case_no);
//							int part_case = coll.getInt("part_case");
//							String file_bar_code_name = coll.getString("file_bar_code_name");
//							String sau_code = coll.getString("sau_code");
//							String custodian_desig = coll.getAllRepeatingStrings("custodian_desig", ",");
//							String confidential_or_not = coll.getString("confidential_or_not");
//							String file_name = coll.getString("file_name");
//							IDfTime file_created_date = coll.getTime("file_created_date");
//							String creater_role = coll.getString("creater_role");
//							String cau = coll.getString("cau");
//							String display_file_name = coll.getString("display_file_name");
//							String d_file_name = coll.getString("d_file_name");
//							String type_of_file = coll.getString("type_of_file");
//							String block_number = coll.getString("block_number");
//							String confidential_type = coll.getString("confidential_type");
//							String pkl_branch = coll.getString("pkl_branch");
//							String pkl_section = coll.getString("pkl_section");
//							String file_classification = coll.getString("file_classification");
//							String volume_no = coll.getString("volume_no");
//
//							sm.setPart_case(part_case);
//							sm.setFile_bar_code_name(file_bar_code_name);
//							sm.setSau_code(sau_code);
//							sm.setCustodian_desig(custodian_desig);
//							sm.setConfidential_or_not(confidential_or_not);
//							sm.setFile_name(file_name);
//							sm.setFile_created_date(file_created_date.toString());
//							sm.setCreater_role(creater_role);
//							sm.setCau(cau);
//							sm.setDisplay_file_name(display_file_name);
//							sm.setType_of_file(type_of_file);
//							sm.setBlock_number(block_number);
//							sm.setConfidential_type(confidential_type);
//							sm.setPkl_branch(pkl_branch);
//							sm.setPkl_section(pkl_section);
//							sm.setFile_classification(file_classification);
//							sm.setVolume_no(volume_no);
//
//							sm.setId(id);
//							sm.setFilename(filename1);
//							sm.setFileno(fileno1);
//							sm.setPkldirectorate(sau);
//							sm.setCustodian(custodian);
//							saumodel.add(sm);
//						}
//
//						return saumodel;
//					}
//				}
//				if (fileno.equals("") && filename.equals("")) {
//					log.info("enetred in 4 condition");
//					List<Model> saumodel = new ArrayList<Model>();
//
//					String countQuery = "select count(*) as filecount from iaf_file_folder where pkl_directorate ='"
//							+ pkldirectorate.toUpperCase() + "' and object_name not like '%(PC%' and file_status='Closed'";
//					String countQuery2 = "select distinct r_object_id from iaf_file_folder where pkl_directorate ='"
//							+ pkldirectorate.toUpperCase() + "' and object_name not like '%(PC%' and file_status='Closed'";
//
//					if (checkFileCount(session, countQuery)) {
//						String gefile = "select * from iaf_file_folder where r_object_id in  (" + countQuery2
//								+ " ENABLE(RETURN_RANGE " + startIndex + " " + endIndex + " 'r_object_id desc')) "
//								+ " order by r_object_id desc";
//						// String gefile = "select * from iaf_file_folder where pkl_directorate
//						// ='"+pkldirectorate.toUpperCase()+"' and object_name not like '%(PC%'";
//						coll = getCollection(session, gefile);
//						if (coll != null) {
//							while (coll.next()) {
//								Model sm = new Model();
//								String id = coll.getString("r_object_id");
//								String fileno1 = coll.getString("file_no");
//								String filename1 = coll.getString("subject");
//								String sau = coll.getString("pkl_directorate");
//								String custodian = coll.getAllRepeatingStrings("custodian", ",");
//								String case_no = coll.getString("case_no");
//								sm.setCase_no(case_no);
//								int part_case = coll.getInt("part_case");
//								String file_bar_code_name = coll.getString("file_bar_code_name");
//								String sau_code = coll.getString("sau_code");
//								String custodian_desig = coll.getAllRepeatingStrings("custodian_desig", ",");
//								String confidential_or_not = coll.getString("confidential_or_not");
//								String file_name = coll.getString("file_name");
//								IDfTime file_created_date = coll.getTime("file_created_date");
//								String creater_role = coll.getString("creater_role");
//								String cau = coll.getString("cau");
//								String display_file_name = coll.getString("display_file_name");
//								String d_file_name = coll.getString("d_file_name");
//								String type_of_file = coll.getString("type_of_file");
//								String block_number = coll.getString("block_number");
//								String confidential_type = coll.getString("confidential_type");
//								String pkl_branch = coll.getString("pkl_branch");
//								String pkl_section = coll.getString("pkl_section");
//								String file_classification = coll.getString("file_classification");
//								String volume_no = coll.getString("volume_no");
//
//								sm.setPart_case(part_case);
//								sm.setFile_bar_code_name(file_bar_code_name);
//								sm.setSau_code(sau_code);
//								sm.setCustodian_desig(custodian_desig);
//								sm.setConfidential_or_not(confidential_or_not);
//								sm.setFile_name(file_name);
//								sm.setFile_created_date(file_created_date.toString());
//								sm.setCreater_role(creater_role);
//								sm.setCau(cau);
//								sm.setDisplay_file_name(display_file_name);
//								sm.setType_of_file(type_of_file);
//								sm.setBlock_number(block_number);
//								sm.setConfidential_type(confidential_type);
//								sm.setPkl_branch(pkl_branch);
//								sm.setPkl_section(pkl_section);
//								sm.setFile_classification(file_classification);
//								sm.setVolume_no(volume_no);
//
//								sm.setId(id);
//								sm.setFilename(filename1);
//								sm.setFileno(fileno1);
//								sm.setPkldirectorate(sau);
//								sm.setCustodian(custodian);
//								saumodel.add(sm);
//							}
//						} else
//							log.info("collection is null ");
//					} else
//						log.info("file count is zero ");
//					return saumodel;
//				}
//			}
//		} catch (DfException e) {
//			log.info("exception in catch " + e);
//			e.printStackTrace();
//		} finally {
//			try {
//				if (coll != null)
//					coll.close();
//			} catch (Exception e) {
//				log.info("exception in catch " + e);
//				e.printStackTrace();
//			}
//		}
//		return null;
//	}
//
//	@Override
//	public int getCountfilename(String pkldirectorate, String filename, String fileno, IDfSession session) {
//		IDfCollection coll = null;
//		int countOfFiles = 0;
//		try {
//			if (fileno.equals("") && filename.equals("")) {
//				log.info("enetred in 4 condition...");
//				String query = "select count(*) as filecount from iaf_file_folder where pkl_directorate ='"
//						+ pkldirectorate.toUpperCase() + "' and object_name not like '%(PC%' and file_status='Closed'";
//				coll = getCollection(session, query);
//				while (coll.next()) {
//					countOfFiles = coll.getInt("filecount");
//					log.info(countOfFiles + " countOfFiles");
//				}
//				return countOfFiles;
//			}
//			if (!filename.isEmpty() && !fileno.isEmpty()) {
//				log.info("enetred in 3 condition");
//				String query = "select count(*) as filecount from iaf_file_folder where pkl_directorate ='"
//						+ pkldirectorate.toUpperCase() + "' and subject like '%" + filename.toUpperCase()
//						+ "%' and file_no like '%" + fileno.toUpperCase() + "%' and file_no not like '%(PC%' and file_status='Closed'";
//				coll = getCollection(session, query);
//				while (coll.next()) {
//					countOfFiles = coll.getInt("filecount");
//					log.info(countOfFiles + " countOfFiles");
//
//				}
//				return countOfFiles;
//			}
//
//			if (!filename.isEmpty()) {
//				log.info("enetred in 1 condition");
//				String query = "select count(*) as filecount from iaf_file_folder where pkl_directorate ='"
//						+ pkldirectorate.toUpperCase() + "' and subject like '%" + filename.toUpperCase()
//						+ "%' and file_no not like '%(PC%' and file_status='Closed'";
//				coll = getCollection(session, query);
//				while (coll.next()) {
//					countOfFiles = coll.getInt("filecount");
//					log.info(countOfFiles + " countOfFiles");
//
//				}
//				return countOfFiles;
//			}
//
//			if (!fileno.isEmpty()) {
//				log.info("enetred in 2 condition");
//				String query = "select count(*) as filecount from iaf_file_folder where pkl_directorate ='"
//						+ pkldirectorate.toUpperCase() + "' and file_no like '%" + fileno.toUpperCase()
//						+ "%' and file_no not like '%(PC%' and file_status='Closed'";
//				coll = getCollection(session, query);
//				while (coll.next()) {
//					countOfFiles = coll.getInt("filecount");
//					log.info(countOfFiles + " countOfFiles");
//
//				}
//				return countOfFiles;
//			}
//			return countOfFiles;
//		} catch (DfException e) {
//			log.info("exception in catch " + e);
//			e.printStackTrace();
//			return 0;
//		} finally {
//			try {
//				if (coll != null)
//					coll.close();
//			} catch (Exception e) {
//				log.info("exception in catch " + e);
//				e.printStackTrace();
//			}
//		}
//	}
//
//}
