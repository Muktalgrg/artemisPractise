package com.example.artemisPractise.user.repository;

import com.example.artemisPractise.user.entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
}
