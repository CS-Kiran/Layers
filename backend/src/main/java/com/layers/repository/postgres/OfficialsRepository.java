package com.layers.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.layers.model.entity.user.AccountStatus;
import com.layers.model.entity.user.Official;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfficialsRepository extends JpaRepository<Official, Long> {

    Optional<Official> findByEmployeeId(String employeeId);

    List<Official> findByDepartment(String department);
    
    List<Official> findByOfficeCity(String officeCity);
    
    List <Official> findByOfficeState(String officeState);
    
    List<Official> findByAccountStatus(AccountStatus status);
}