package com.layers.model.entity.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "citizens")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Citizen extends User {

    private String address;
    
    private String phoneNumber;
    
    @Builder.Default
    private AccountStatus accountStatus = AccountStatus.ACTIVE; 
}