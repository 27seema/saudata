package com.fetchsaudata.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.fetchsaudata.bean.DeptConfigT;

public interface DeptConfigTRepo extends MongoRepository<DeptConfigT, String>{

	List<DeptConfigT> findByDeptUsername(String serNo);

	List<DeptConfigT> findAllByDeptUsernameContaining(String lowerCase);
//	List<DeptConfigT> findAllByDeptUsernameContainingOrDeptRoleDisplayNameIgnoreCaseContainingOrDeptDisplayNameIgnoreCaseContaining(String lowerCase,String role,String username);
	List<DeptConfigT> findAllByDeptNameAndDeptUsernameContaining(String lowerCase, String lowerCase2);

	List<DeptConfigT> findAllByDeptUsernameContainingOrDeptDisplayNameIgnoreCaseContaining(String username,
			String username2);

	List<DeptConfigT> findAllByDeptUsernameContainingOrDeptDisplayNameIgnoreCaseContainingOrDeptDisplayUsernameIgnoreCaseContaining(
			String username, String username2, String username3);
	@Query("{$or: [{deptUsername: { $regex: ?0, $options: 'i' }}, " +
	           "{deptDisplayName: { $regex: ?1, $options: 'i' }}, " +
	           "{deptDisplayUsername: { $regex: ?2, $options: 'i' }}], " +
	           "deptName: ?3}}")
	List<DeptConfigT> findAllByDeptUsernameContainingOrDeptDisplayNameIgnoreCaseContainingOrDeptDisplayUsernameIgnoreCaseContainingAndDeptName(
			String lowerCase, String lowerCase2, String lowerCase3, String lowerCase4);

	List<DeptConfigT> findAllByDeptName(String lowerCase);

}
