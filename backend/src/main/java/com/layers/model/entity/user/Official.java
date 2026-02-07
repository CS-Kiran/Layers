package com.layers.model.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
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
public class Official extends User {
	
	@Column(nullable = false)
    private String department;
    
	@Column(nullable = false)
    private String designation;

	@Column(nullable = false)
    private String idProofImageUrl; 
    
	@Column(nullable = false, unique=true)
    private String employeeId;
    
	@Column(nullable = false)
    private String officeCity;
    
	@Column(nullable = false)
    private String officeState;
    
	@Column(nullable = false)
    private String OfficePINCode;
    
	@Column(nullable = false)
    private String officePhoneNumber;
    
	@PrePersist
    public void prePersist() {
        if (this.getAccountStatus() == null) {
            this.setAccountStatus(AccountStatus.PENDING_VERIFICATION);
        }
    }
}
