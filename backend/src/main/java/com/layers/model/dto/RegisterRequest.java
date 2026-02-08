package com.layers.model.dto;

import com.layers.model.entity.user.UserRole;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequest {
	@NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    @NotBlank(message = "Full Name is required")
    private String fullName;

    @Pattern(regexp = "^\\d{10}$", message = "Phone number must be 10 digits")
    private String phoneNumber;

    @NotNull(message = "Role is required")
    private UserRole role;

    // For Citizen
    private String city;
    private String state;
    
    @Pattern(regexp = "^\\d{6}$", message = "PIN Code must be 6 digits")
    private String pinCode;

    // For Official
    private String department;
    private String designation;
    private String employeeId;
    private String officeCity;
    private String officeState;
    
    @Pattern(regexp = "^\\d{6}$", message = "Office PIN Code must be 6 digits")
    private String officePinCode;
    
    @Pattern(regexp = "^\\d{10}$", message = "Office Phone number must be 10 digits")
    private String officePhoneNumber;
}
