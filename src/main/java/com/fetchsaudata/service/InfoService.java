//package com.fetchsaudata.service;
//
//import java.util.LinkedHashMap; 
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Optional;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.documentum.fc.client.DfQuery;
//import com.documentum.fc.client.IDfCollection;
//import com.documentum.fc.client.IDfQuery;
//import com.documentum.fc.client.IDfSession;
//import com.documentum.fc.common.DfException;
//import com.fetchsaudata.bean.CauSauBean;
//import com.fetchsaudata.bean.DepDetails;
//import com.fetchsaudata.bean.DepRoleInternal;
//import com.fetchsaudata.bean.DeptConfigS;
//import com.fetchsaudata.bean.DeptConfigT;
////import com.fetchsaudata.bean.DeptConfigT;
//import com.fetchsaudata.bean.EyeOnlyBean;
//import com.fetchsaudata.bean.InfoBean;
//import com.fetchsaudata.bean.UpdateJson;
//import com.fetchsaudata.repository.DeptConfigSRepo;
//import com.fetchsaudata.repository.DeptConfigTRepo;
//
//@Service
//public class InfoService {
//	Logger log=Logger.getLogger(InfoService.class);
//	
//
//	@Autowired
//	private DeptConfigSRepo departmentRepo;
//	@Autowired
//	private DeptConfigTRepo userRepo;
//
//	public LinkedList<InfoBean> getInfo(IDfSession session, String userRole) {
//		log.info("getInfo method");
//		IDfCollection coll=null;
//		LinkedList<InfoBean> list=new LinkedList<InfoBean>();
//		String query="select * from iaf_fev_temp where user_role='"+userRole.toLowerCase()+"'";
//		try {
//			IDfQuery q=new DfQuery();
//			q.setDQL(query);
//			System.out.println(q.getDQL());
//			coll=q.execute(session, 1);
//			while(coll.next()) {
//				InfoBean dto=new InfoBean();
//				String object_name=coll.getString("object_name");
//				String user_role=coll.getString("user_role");
//				String address_line_1=coll.getString("address_line_1");
//				String telephone_s=coll.getString("telephone_s");
//				String telephone=coll.getString("telephone");
//				String address_line_3=coll.getString("address_line_3");
//				String fax_s=coll.getString("fax_s");
//				String address_line_2=coll.getString("address_line_2");
//				String fax=coll.getString("fax");
//				String email=coll.getString("email");
//				dto.setAddressLine1(address_line_1);
//				dto.setAddressLine2(address_line_2);
//				dto.setAddressLine3(address_line_3);
//				dto.setEmail(email);
//				dto.setFax(fax);
//				dto.setFaxS(fax_s);
//				dto.setObjectName(object_name);
//				dto.setTelephone(telephone);
//				dto.setTelephoneS(telephone_s);
//				dto.setUserRole(userRole);
//				if(!dto.getObjectName().isEmpty()) {					 
//					q.setDQL("select description from dm_user where user_login_name='"+dto.getObjectName()+"'");
//					System.out.println(q.getDQL());
//					IDfCollection collec=q.execute(session, 1);
//					while(collec.next()) {
//						dto.setDescName(collec.getString("description"));
//					}
//					collec.close();
//				}
//				list.add(dto);
//			}
//
//			System.out.println("list "+list);
//			System.out.println("listSize "+list.size());
//
//		}
//		catch (Exception e) {
//			System.out.println("Exception "+e.toString());
//		}
//		finally {
//			try {
//				if(coll!=null)
//					coll.close();
//			} catch (DfException e) {
//				System.out.println("Error in closing Exception");				
//				e.printStackTrace();
//			}
//		}
//		return list;
//	}
//
//	public String updateInfo(IDfSession session, UpdateJson json) {
//		IDfCollection coll=null;
//		try {
//			IDfQuery q=new DfQuery();
//			q.setDQL("update iaf_fev_temp objects set email='"+json.getEmail()+"' set telephone_s='"+json.getTelephoneS()+"' set address_line_1='"+json.getAddressLine1()+"' set address_line_2='"+json.getAddressLine2()+"' set address_line_3='"+json.getAddressLine3()+"' where user_role='"+json.getUserRole()+"'");
//			System.out.println(q.getDQL());
//			coll=q.execute(session, 1);
//			int res=0;
//			while(coll.next()) {
//				res=coll.getInt("objects_updated");
//				System.out.println("res "+res);
//			}
//			if(res>0)
//				return "done";
//		}catch (Exception e) {
//			System.out.println("Exception "+e.toString());
//		}
//		finally {
//			try {
//				if(coll!=null)
//					coll.close();
//			} catch (DfException e) {
//				System.out.println("Error in closing Exception");				
//				e.printStackTrace();
//			}
//		}
//
//		return "fail";
//	}
//
//	public List<DepDetails> getUser(IDfSession session, String username) {
//		IDfCollection coll=null;
//		IDfCollection coll2=null;
//		List<DepDetails> depdetailslist=new LinkedList<DepDetails>();
//		try {
//			IDfQuery q=new DfQuery();
//			q.setDQL("select group_display_name from dm_group where any i_all_users_names='"+username+"' and group_name like '%.%.%' ");
//			System.out.println("query "+q.getDQL());
//			coll=q.execute(session, 1);
//			LinkedList<String> deplist=new LinkedList<String>();
//
//			while(coll.next()) {
//				String userRole=coll.getString("group_display_name");
//				System.out.println("userRole "+userRole);
//				deplist.add(userRole);
//			}
//			for(int i=0;i<deplist.size();i++) {
//				q.setDQL("select dep_designation,dep_section,dep_coord_role,dep_name,dep_roles,dep_full_name,dep_roles_desg,dep_branch from iaf_department_config where  any dep_roles ='"+deplist.get(i)+"' enable (RETURN_TOP  1) ");
//				System.out.println("query "+q.getDQL());
//				coll2=q.execute(session, 1);
//				while(coll2.next()) {
//					DepDetails depdetails=new DepDetails();
//					String dep_full_name=coll2.getString("dep_full_name");
//
//					String dep_designation=coll2.getString("dep_designation");
//					String dep_section=coll2.getString("dep_section");
//					String dep_coord_role=coll2.getString("dep_coord_role");
//					String dep_name=coll2.getString("dep_name");
//					String dep_roles=coll2.getString("dep_roles");
//					String dep_roles_desg=coll2.getString("dep_roles_desg");
//					String dep_branch=coll2.getString("dep_branch");
//					depdetails.setDeptRole(dep_roles);
//					depdetails.setDeptUserName(username);
//					depdetails.setDeptRoleDesignation(dep_roles_desg);
//					depdetails.setDepBranch(dep_branch);
//					depdetails.setDeptName(dep_name);
//					depdetails.setDepSection(dep_section);
//					depdetails.setDepDesignation(dep_designation);
//					depdetails.setDepCoordRole(dep_coord_role);
//					depdetails.setDepDisplayName(dep_full_name);
//					depdetailslist.add(depdetails);
//
//				}
//			}
//			return depdetailslist;
//		}
//		catch(DfException e) {
//			System.out.println("DfException "+e.toString());
//			return null;
//
//		}
//		finally {
//			try {
//				if(coll!=null)
//					coll.close();
//				if(coll2!=null)
//					coll2.close();
//			}
//			catch(Exception e)			{
//				System.out.println("Exception while closing collection"+e.toString());
//			}
//		}
//	}
//
//	public LinkedList<EyeOnlyBean> getUserRoles(IDfSession session, String username) {
//		IDfCollection coll=null;
//		IDfCollection coll2=null;
//		LinkedList<EyeOnlyBean> eyeOnlyobj=new LinkedList<EyeOnlyBean>();
//		try {
//			IDfQuery q=new DfQuery();
//			q.setDQL("select i_all_users_names,group_display_name from dm_group where any i_all_users_names like '"+username+"%' and group_name like '%.%.%' ");
//			System.out.println("query "+q.getDQL());
//			coll=q.execute(session, 1);
//			while(coll.next()) {
//				String userRole=coll.getString("group_display_name");
//				String i_all_users_names=coll.getString("i_all_users_names");
//				System.out.println("userRole "+userRole);
//				EyeOnlyBean obj=new EyeOnlyBean();
//				obj.setDeptRole(userRole);
//				obj.setDeptUserName(i_all_users_names);
//				eyeOnlyobj.add(obj);
//			}
//
//			return eyeOnlyobj;
//		}
//		catch(DfException e) {
//			System.out.println("DfException "+e.toString());
//			return null;
//
//		}
//		finally {
//			try {
//				if(coll!=null)
//					coll.close();
//				if(coll2!=null)
//					coll2.close();
//			}
//			catch(Exception e)			{
//				System.out.println("Exception while closing collection"+e.toString());
//			}
//		}
//	}
//
//	public LinkedList<DepRoleInternal> getUserRolesforInternal(IDfSession session, String username, String depRole) {
//		IDfCollection coll=null;
//		IDfCollection coll2=null;
//		IDfCollection coll3=null;
//		LinkedList<DepRoleInternal> list=new LinkedList<DepRoleInternal>();
//
//		try {
//			IDfQuery q=new DfQuery();
////			q.setDQL("select i_all_users_names,group_display_name from dm_group where any i_all_users_names like '"+username+"%' and group_name like '"+depRole+"' ");
////			System.out.println("query "+q.getDQL());
////			coll=q.execute(session, 1);
////			while(coll.next()) {
////				String userRole=coll.getString("group_display_name");
////				String i_all_users_names=coll.getString("i_all_users_names");
////				System.out.println("userRole "+userRole);
//				q.setDQL("select dep_roles from iaf_department_config where any dep_roles in ('"+depRole+"') order by dep_roles   ");
//				System.out.println("query "+q.getDQL());
//				coll2=q.execute(session, 1);
//				while(coll2.next()) {
//					if(coll2.getString("dep_roles")!=null) {
//						q.setDQL("select i_all_users_names from dm_group where  group_name='"+coll2.getString("dep_roles")+"' ");
//						System.out.println("query "+q.getDQL());
//						coll3=q.execute(session, 1);
//						while(coll3.next()) {
//							DepRoleInternal obj=new DepRoleInternal();
//
//							if(coll3.getString("i_all_users_names")!=null) {
//								obj.setDeptRole(coll2.getString("dep_roles"));
//								obj.setDeptUserName(coll3.getString("i_all_users_names"));
//								list.add(obj);
//							}
//						}
//						coll3.close();
//					}
////				}
////				coll2.close();
////
////
//			}
//
//			return list;
//		}
//		catch(DfException e) {
//			System.out.println("DfException "+e.toString());
//			return null;
//
//		}
//		finally {
//			try {
//				if(coll!=null)
//					coll.close();
//				if(coll2!=null)
//					coll2.close();
//				if(coll3!=null)
//					coll3.close();
//			}
//			catch(Exception e)			{
//				System.out.println("Exception while closing collection"+e.toString());
//			}
//		}
//	}
//	public LinkedList<CauSauBean> getCauData(IDfSession session, String sau) {
//		log.info("getCauData method");
//
//		IDfCollection coll=null;
//		try {
//			String query="select * from dm_dbo.CAU_SAU_Mapping where sau like '%"+sau.toUpperCase()+"%'";
//			IDfQuery q=new DfQuery();
//			q.setDQL(query);
//			log.info("query "+q.getDQL());
//			coll=q.execute(session, 1);
//			LinkedList<CauSauBean> list=new LinkedList<CauSauBean>();
//
//			while(coll.next()) {
//				CauSauBean obj=new  CauSauBean();
//				String dep=coll.getString("sau");
//				String sauDisplayName=coll.getString("sau_display_name");
//				String cau=coll.getString("cau");
//				String blockNo=coll.getString("block_no");
//				String subsection=coll.getString("subsection");
//				String command=coll.getString("command");
//				String coorduser=coll.getString("coorduser");
//				if(coorduser.equals(null)) {
//					coorduser=coll.getString("oic");
//				}
//				obj.setCoorduser(coorduser);
//				obj.setSau_display_name(sauDisplayName);
//				obj.setSau(dep);
//				obj.setCau(cau);
//				obj.setCommand(command);
//				obj.setBlockNo(blockNo);
//				obj.setSubsection(subsection);
//              list.add(obj);
//			}
//			return list;
//		}
//		catch(DfException e) {
//			System.out.println("DfException "+e.toString());
//			return null;
//
//		}
//		finally {
//			try {
//				if(coll!=null)
//					coll.close();
//			}
//			catch(Exception e)			{
//				System.out.println("Exception while closing collection"+e.toString());
//			}
//		}
//	}
//	public LinkedHashMap<String, List<DepDetails>> getUserData(IDfSession session, List<String> serviceNo) {
//		LinkedHashMap<String , List<DepDetails>> map=new LinkedHashMap<String , List<DepDetails>>();
//		for (String serNo : serviceNo) {
//			List<DepDetails> depList=new LinkedList<DepDetails>();
//			depList=getUser(session, serNo);
//			map.put(serNo, depList);
//			List<DeptConfigT> users = userRepo.findByDeptUsername(serNo);
////			if(users.isEmpty())
////				setDataInMongoDB(depList,serNo);
//		}
//		return map;
//	}
//	
////	private void setDataInMongoDB(List<DepDetails> depList, String serNo) {
////		DeptConfigT user = new DeptConfigT();
////		for (DepDetails depDetails : depList) {
////			
////			user.setDeptDisplayUsername(depDetails.getDeptRoleDesignation());//need to discuss what is display username
////			user.setDeptName(depDetails.getDeptName().toLowerCase().replace(".", ""));
////			user.setDeptRole(depDetails.getDeptRole());
////			user.setDeptRoleDisplayName(depDetails.getDeptRoleDesignation());
////			user.setDeptUsername(depDetails.getDeptUserName());
////			user.setApptId(depDetails.getDeptRoleDesignation());//need to check
////			user.setApptDisplay(depDetails.getDepDesignation());
////			user.setBranch(depDetails.getDepBranch());
////			user.setRankName(depDetails.getDeptRoleDesignation());//need to check
////			user.setDeptDisplayName(depDetails.getDeptName());
////			userRepo.save(user);
////			
////			Optional<DeptConfigS> department= departmentRepo.findByDeptDisplayName(depDetails.getDeptName());
////			if(!department.isPresent() && !depDetails.getDepCoordRole().equals(".none."))
////				setDeptInMongo(depDetails.getDeptName(),depDetails.getDepCoordRole(),depDetails.getDeptName().toLowerCase().replace(".", ""));		
////		}
////	}
//
//	private void setDeptInMongo(String deptDisplayName, String depCoordRole, String deptName) {
//		// TODO Auto-generated method stub
//		DeptConfigS deptConfigS = new DeptConfigS();
//		deptConfigS.setDeptCoordRole(depCoordRole);
//		deptConfigS.setDeptName(deptName);
//		deptConfigS.setDeptDisplayName(deptDisplayName);
//		departmentRepo.save(deptConfigS);		
//	}
//}
