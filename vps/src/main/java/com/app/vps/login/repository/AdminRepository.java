package com.app.vps.login.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.app.vps.login.model.User;

@Repository
public interface AdminRepository extends JpaRepository<User, Long> {

	Optional<User> findByEmployeeCodeAndEmpStatus(@Param("employeeCode") long username, @Param("empStatus") int status);
	
	@Query(value="", nativeQuery=true)
	Optional<User> findEmployeeEmailByEmployeeCode(@Param("employeeCode") long empCode);

	Optional<User> findByEmployeeCode(@Param("employeeCode")long empCode);
	
}