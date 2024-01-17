package com.example.artemisPractise.repository;

import com.example.artemisPractise.user.entity.FloatDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface FloatDetailsRepository extends JpaRepository<FloatDetails, Long> {
}
