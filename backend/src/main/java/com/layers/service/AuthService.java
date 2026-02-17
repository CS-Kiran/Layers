package com.layers.service;

import lombok.RequiredArgsConstructor;

import org.apache.coyote.BadRequestException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.layers.exception.*;
import com.layers.model.dto.AuthResponse;
import com.layers.model.dto.LoginRequest;
import com.layers.model.dto.RefreshTokenRequest;
import com.layers.model.dto.RegisterRequest;
import com.layers.model.dto.UserDTO;
import com.layers.model.entity.user.AccountStatus;
import com.layers.model.entity.user.Admin;
import com.layers.model.entity.user.Citizen;
import com.layers.model.entity.user.Official;
import com.layers.model.entity.user.User;
import com.layers.repository.postgres.UserRepository;
import com.layers.security.JwtTokenProvider;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final String CITIZEN = null;
	private static final String OFFICIAL = null;
	private static final String ADMIN = null;
	private final UserRepository userRepository = null;
    private final PasswordEncoder passwordEncoder = null;
    private final JwtTokenProvider jwtTokenProvider = new JwtTokenProvider();
    private final AuthenticationManager authenticationManager = null;

    @Transactional
    public AuthResponse register(RegisterRequest request) throws BadRequestException {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already in use");
        }

        User user = createUserEntity(request);

        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        
        User savedUser = userRepository.save(user);

        String accessToken = jwtTokenProvider.generateToken(savedUser);
        String refreshToken = jwtTokenProvider.generateRefreshToken(savedUser.getEmail());

        return new AuthResponse(accessToken, refreshToken, refreshToken, 0, mapToUserDTO(savedUser));
    }

    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", request.getEmail()));

        if (user.getAccountStatus() != AccountStatus.ACTIVE) {
            throw new UnauthorizedException("Account is " + user.getAccountStatus() + ". Please contact support.");
        }

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String accessToken = jwtTokenProvider.generateToken(user);
        String refreshToken = jwtTokenProvider.generateRefreshToken(user.getEmail());

        return new AuthResponse(accessToken, refreshToken, refreshToken, 0, mapToUserDTO(user));
    }

    public UserDTO getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
                
        return mapToUserDTO(user);
    }

    public AuthResponse refreshToken(RefreshTokenRequest request) {
        String token = request.getRefreshToken();
        if (!jwtTokenProvider.validateToken(token)) {
            throw new UnauthorizedException("Invalid or expired refresh token");
        }

        String email = jwtTokenProvider.getEmailFromToken(token);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));

        String newAccessToken = jwtTokenProvider.generateToken(user);

        return new AuthResponse(newAccessToken, request.getRefreshToken(), newAccessToken, 0, mapToUserDTO(user));
    }

    private UserDTO mapToUserDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                null, user.getRole(),
                user.getAccountStatus()
        );
    }

    // Helper: Factory method for User Entities
    private User createUserEntity(RegisterRequest request) throws BadRequestException {
        return switch (request.getRole()) {
            case CITIZEN -> {
                Citizen citizen = new Citizen();
                citizen.setCity(request.getCity());
                citizen.setState(request.getState());
                citizen.setPinCode(request.getPinCode());
                citizen.setPhoneNumber(request.getPhoneNumber());
                yield citizen;
            }
            case OFFICIAL -> {
                Official official = new Official();
                official.setDepartment(request.getDepartment());
                official.setDesignation(request.getDesignation());
                official.setEmployeeId(request.getEmployeeId());
                official.setOfficeCity(request.getOfficeCity());
                official.setOfficeState(request.getOfficeState());
                official.setOfficePINCode(request.getOfficePinCode());
                official.setOfficePhoneNumber(request.getOfficePhoneNumber());
                yield official;
            }
            case ADMIN -> new Admin();
            default -> throw new BadRequestException("Invalid User Role");
        };
    }
}
