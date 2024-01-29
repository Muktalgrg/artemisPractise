package com.example.artemisPractise.user.service.impl;

import com.example.artemisPractise.user.entity.*;
import com.example.artemisPractise.user.producer.MessageProducer;
import com.example.artemisPractise.user.repository.BusinessUserRepository;
import com.example.artemisPractise.user.repository.FloatDetailsRepository;
import com.example.artemisPractise.user.exception.CustomException;
import com.example.artemisPractise.user.service.PaymentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

    static Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);

    private final BusinessUserRepository businessUserRepository;
    private final FloatDetailsRepository floatDetailsRepository;
    private final MessageProducer messageProducer;

    public PaymentServiceImpl(BusinessUserRepository businessUserRepository, FloatDetailsRepository floatDetailsRepository, MessageProducer messageProducer) {
        this.businessUserRepository = businessUserRepository;
        this.floatDetailsRepository = floatDetailsRepository;
        this.messageProducer = messageProducer;
    }

    @Override
    public void pay(PaymentDetails paymentDetails) {

            Optional<BusinessUser> businessUser = businessUserRepository.findById(paymentDetails.getPayerId());
            if (businessUser.isEmpty())
                throw new CustomException("Business user does not exists for id: " + paymentDetails.getPayerId());

            Merchant associatedMerchant = businessUser.get().getMerchant();


            Optional<FloatDetails> floatDetailsForBusinessUser = floatDetailsRepository.findByBusinessUserId(businessUser.get().getId());

            if (floatDetailsForBusinessUser.isEmpty())
                throw new CustomException("Business user does not exists for id: " + paymentDetails.getPayerId());

            Optional<FloatDetails> floatDetailsForMerchant = floatDetailsRepository.findByMerchantId(associatedMerchant.getId());


            if (floatDetailsForMerchant.isEmpty())
                throw new CustomException("Business user does not exists for id: " + paymentDetails.getPayerId());

            BigDecimal floatAmountForBusinessUser = BigDecimal.valueOf(floatDetailsForBusinessUser.get().getFloatBalance());
            floatAmountForBusinessUser = floatAmountForBusinessUser.subtract(BigDecimal.valueOf(paymentDetails.getTxnAmount()));
            floatDetailsForBusinessUser.get().setFloatBalance((floatAmountForBusinessUser.doubleValue()));

            BigDecimal floatAmountForMerchant = BigDecimal.valueOf(floatDetailsForMerchant.get().getFloatBalance());
            floatAmountForMerchant = floatAmountForMerchant.subtract(BigDecimal.valueOf(paymentDetails.getTxnAmount()));
            floatDetailsForMerchant.get().setFloatBalance(floatAmountForMerchant.doubleValue());


            floatDetailsRepository.save(floatDetailsForBusinessUser.get());
            floatDetailsRepository.save(floatDetailsForMerchant.get());

            // push to queue
//            messageProducer.sendJsonMessage(businessUser.get());


        NotificationDTO notificationDTO = NotificationDTOMapper.INSTANCE.businessUserToNotificationDTO(businessUser.get());
            messageProducer.sendJsonMessage(notificationDTO);

            BusinessUserDTO businessUserDTO = BusinessUserDTOMapper.INSTANCE.businessUserToBusinessUserDTO(businessUser.get());
            messageProducer.sendJsonMessage(businessUserDTO);
        }



}
