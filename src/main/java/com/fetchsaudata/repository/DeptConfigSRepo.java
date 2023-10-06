package com.fetchsaudata.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.fetchsaudata.bean.DeptConfigS;
import com.fetchsaudata.bean.DeptConfigT;

public interface DeptConfigSRepo extends MongoRepository<DeptConfigS, String>{

	Optional<DeptConfigS> findByDeptName(String deptName);

	List<DeptConfigS> findByDeptDisplayNameContaining(String sau);

	Optional<DeptConfigS> findByDeptDisplayName(String deptName);

	List<DeptConfigS> findByDeptDisplayNameContainingIgnoreCase(String upperCase);

	List<DeptConfigS> findByDeptDisplayNameContainingIgnoreCaseAndIsActive(String upperCase, boolean b);

}
