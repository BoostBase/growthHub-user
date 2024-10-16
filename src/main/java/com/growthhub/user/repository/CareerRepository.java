package com.growthhub.user.repository;

import com.growthhub.user.domain.Career;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CareerRepository extends JpaRepository<Career, Long> {
    List<Career> findByPartContaining(String part);
}