package com.example.artemisPractise.user.repository;

import com.example.artemisPractise.user.entity.BusinessUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BusinessUserRepository extends JpaRepository <BusinessUser, Long> {
}
