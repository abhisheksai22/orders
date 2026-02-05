package com.abhi.orders.repo;

import com.abhi.orders.entity.UserDetail;
import com.abhi.orders.repo.projections.ConflictCountProjection;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDetailRepository extends JpaRepository<UserDetail, Long> {

    @Query(""" 
                    select
                      count(case when u.email = :email then 1 end) as emailCount,
                      count(case when u.phoneNumber = :phone then 1 end) as phoneCount
                    from UserDetail u
                    where u.email = :email or u.phoneNumber = :phone
            """)
    ConflictCountProjection findConflicts(String email, String phone);

}