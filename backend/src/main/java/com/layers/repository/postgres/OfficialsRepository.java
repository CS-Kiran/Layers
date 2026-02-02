package com.layers.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.layers.model.entity.user.AccountStatus;
import com.layers.model.entity.user.Officials;

import java.util.List;
import java.util.Optional;

@Repository
public interface OfficialsRepository extends JpaRepository<Officials, Long> {

    Optional<Officials> findByEmployeeId(String employeeId);

    List<Officials> findByDepartment(String department);

    List<Officials> findByAccountStatus(AccountStatus status);
}