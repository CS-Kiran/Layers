package com.layers.model.entity.user;

public enum AccountStatus {
    PENDING_VERIFICATION, // User registered but hasn't verified email/phone
    ACTIVE,               // Fully verified and operational
    SUSPENDED,            // Temporarily blocked (e.g., for suspicious activity)
    BLACKLISTED,          // Permanently banned
    DEACTIVATED           // User deleted their own account (soft delete)
}
