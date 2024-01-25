package com.example.artemisPractise.user.repository;

import com.example.artemisPractise.user.entity.FloatDetails;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;
import java.util.function.Function;


@Repository
public interface FloatDetailsRepository extends JpaRepository<FloatDetails, Long> {


    @Query("SELECT f FROM FloatDetails f WHERE f.businessUser.id = ?1")
    Optional<FloatDetails> findByBusinessUserId(long bId);

    @Query("SELECT f FROM FloatDetails f WHERE f.merchant.id = ?1")
    Optional<FloatDetails> findByMerchantId(long mId);

//    Optional<FloatDetails> findByBusinessUser_Id(long businessUserId);
//
//    Optional<FloatDetails> findByMerchant_Id (long merchantId);

}
