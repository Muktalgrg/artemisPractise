package com.example.artemisPractise.repository;

import com.example.artemisPractise.user.entity.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BeneficiaryRepository extends JpaRepository<Beneficiary, Long> {
}
