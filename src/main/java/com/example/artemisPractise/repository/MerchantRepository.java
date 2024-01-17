package com.example.artemisPractise.repository;

import com.example.artemisPractise.user.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseStatus;


@Repository
public interface MerchantRepository extends JpaRepository<Merchant, Long> {
}
