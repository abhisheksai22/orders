package com.abhi.orders.repo.projections;

public interface ConflictCountProjection {
    long getEmailCount();
    long getPhoneCount();
}
