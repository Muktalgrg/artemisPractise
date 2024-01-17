package com.example.artemisPractise.repository;

import com.example.artemisPractise.user.entity.BusinessUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BusinessUserRepository extends JpaRepository <BusinessUser, Long> {
}
