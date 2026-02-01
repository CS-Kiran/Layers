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
@Table(name = "officials")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class Officials extends User {

    private String department;
    
    private String designation;

    private String idProofImageUrl; 
    
    private String employeeId;
    
    private String phoneNumber;
    
    private String officeAddress;
    
    private String officePhoneNumber;
    
    @Builder.Default
    private AccountStatus accountStatus = AccountStatus.PENDING_VERIFICATION;
}
