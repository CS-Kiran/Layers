package com.layers.repository.postgres;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.layers.model.entity.user.AccountStatus;
import com.layers.model.entity.user.Citizen;
import java.util.List;

@Repository
public interface CitizenRepository extends JpaRepository<Citizen, Long> {

    List<Citizen> findByCity(String city);
    
    List<Citizen> findByAccountStatus(AccountStatus status);
}