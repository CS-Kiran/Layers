package com.layers.repository.postgres;

import com.layers.model.entity.user.AccountStatus;
import com.layers.model.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
    
    List<User> findByAccountStatus(AccountStatus accountStatus);
}