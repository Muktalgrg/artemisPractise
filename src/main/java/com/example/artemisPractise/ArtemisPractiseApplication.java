package com.example.artemisPractise;

import com.example.artemisPractise.user.service.impl.SimpleEmailNotificationAdapterImpl;
import com.example.artemisPractise.user.repository.BeneficiaryRepository;
import com.example.artemisPractise.user.repository.BusinessUserRepository;
import com.example.artemisPractise.user.repository.FloatDetailsRepository;
import com.example.artemisPractise.user.repository.MerchantRepository;
import com.example.artemisPractise.user.entity.BusinessUser;
import com.example.artemisPractise.user.entity.FloatDetails;
import com.example.artemisPractise.user.entity.Merchant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ArtemisPractiseApplication implements CommandLineRunner {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private SimpleEmailNotificationAdapterImpl simpleEmailNotificationAdapter;

	@Autowired
	private MerchantRepository merchantRepository;
	@Autowired
	private FloatDetailsRepository floatDetailsRepository;
	@Autowired
	private BusinessUserRepository businessUserRepository;
	@Autowired
	private BeneficiaryRepository beneficiaryRepository;

//	@Autowired
//	private MessageProducer messageProducer;

	public static void main(String[] args) {
		SpringApplication.run(ArtemisPractiseApplication.class, args);


	}

	@Override
	public void run(String... args) throws Exception {
		loadData();
		/*
		List<String> to = new ArrayList<>();
		to.add("mukta.gurung@inventecsolutions.com");
//		to.add("muktalgrg@gmail.com");
		to.add("muktalgurung@gmail.com");

		String subject = "payment mail";
		String content = "payment successful";


		System.out.println("-------------- sending email!");
//		simpleEmailNotificationAdapter.sendEmail(to, null, subject,  content);
		simpleEmailNotificationAdapter.sendEmail("mukta.gurung@inventecsolutions.com", "about payment", "Payment done!");
		System.out.println("-------------- mail send process completed.");


		 */

	}

////	@Override
//	public void run(String... args) throws Exception {
//
//		ExecutorService executorService = Executors.newFixedThreadPool(8);
//
//		for(int i = 0; i < 5; i++){
//			executorService.submit(()->{
//				EmailMessage msg = EmailMessage.builder()
//						.to("Mukta")
//						.content("hello world "+ UUID.randomUUID())
////				.content("hello world ")
//						.subject("my email")
//						.build();
//
//		messageProducer.sendJsonMessage(msg);
//			});
//		}
//
//		executorService.shutdown();
//	}

	private void loadData(){


		FloatDetails floatDetails1 = new FloatDetails();
		floatDetails1.setFloatBalance(1000.00);

		FloatDetails floatDetails2 = new FloatDetails();
		floatDetails2.setFloatBalance(1000.00);

		FloatDetails floatDetails3 = new FloatDetails();
		floatDetails3.setFloatBalance(1000.00);

		FloatDetails floatDetails4 = new FloatDetails();
		floatDetails4.setFloatBalance(1000.00);

		FloatDetails floatDetails5 = new FloatDetails();
		floatDetails5.setFloatBalance(1000.00);

		FloatDetails floatDetails6 = new FloatDetails();
		floatDetails6.setFloatBalance(1000.00);

		FloatDetails floatDetails7 = new FloatDetails();
		floatDetails7.setFloatBalance(1000.00);



		FloatDetails floatDetails8 = new FloatDetails();
		floatDetails8.setFloatBalance(1000.00);

		FloatDetails floatDetails9 = new FloatDetails();
		floatDetails9.setFloatBalance(1000.00);

		FloatDetails floatDetails10 = new FloatDetails();
		floatDetails10.setFloatBalance(1000.00);

		FloatDetails floatDetails11 = new FloatDetails();
		floatDetails11.setFloatBalance(5000.00);

		FloatDetails floatDetails12 = new FloatDetails();
		floatDetails12.setFloatBalance(5000.00);

		//===============================================================

		Merchant merchant1 = new Merchant();
		merchant1.setName("Sapphire");
		merchant1.setFloatDetails(floatDetails11);
		merchantRepository.save(merchant1);

		Merchant merchant2 = new Merchant();
		merchant2.setName("Costal communication");
		merchant2.setFloatDetails(floatDetails12);
		merchantRepository.save(merchant2);

		//===============================================================

		BusinessUser businessUser1 = new BusinessUser();
		businessUser1.setName("Mukta Gurung");
		businessUser1.setEmail("muktalgrg@gmail.com");
		businessUser1.setMerchant(merchant1);
		businessUser1.setFloatDetails(floatDetails1);
		businessUserRepository.save(businessUser1);

		BusinessUser businessUser2 = new BusinessUser();
		businessUser2.setName("Ryan giggs");
		businessUser2.setEmail("muktalgrg@gmail.com");
		businessUser2.setMerchant(merchant1);
		businessUser2.setFloatDetails(floatDetails2);
		businessUserRepository.save(businessUser2);

		BusinessUser businessUser3 = new BusinessUser();
		businessUser3.setName("Paul Scholes");
		businessUser3.setEmail("muktalgrg@gmail.com");
		businessUser3.setMerchant(merchant1);
		businessUser3.setFloatDetails(floatDetails3);
		businessUserRepository.save(businessUser3);

		BusinessUser businessUser4 = new BusinessUser();
		businessUser4.setName("Van der sar");
		businessUser4.setEmail("muktalgrg@gmail.com");
		businessUser4.setMerchant(merchant1);
		businessUser4.setFloatDetails(floatDetails4);
		businessUserRepository.save(businessUser4);

		BusinessUser businessUser5 = new BusinessUser();
		businessUser5.setName("Cristiano Ronaldo");
		businessUser5.setEmail("muktalgrg@gmail.com");
		businessUser5.setMerchant(merchant1);
		businessUser5.setFloatDetails(floatDetails5);
		businessUserRepository.save(businessUser5);

		BusinessUser businessUser6 = new BusinessUser();
		businessUser6.setName("Roborto carlos");
		businessUser6.setEmail("muktalgrg@gmail.com");
		businessUser6.setMerchant(merchant2);
		businessUser6.setFloatDetails(floatDetails6);
		businessUserRepository.save(businessUser6);

		BusinessUser businessUser7 = new BusinessUser();
		businessUser7.setName("Luis Figo");
		businessUser7.setEmail("muktalgrg@gmail.com");
		businessUser7.setMerchant(merchant2);
		businessUser7.setFloatDetails(floatDetails7);
		businessUserRepository.save(businessUser7);

		BusinessUser businessUser8 = new BusinessUser();
		businessUser8.setName("Iker Cassilas");
		businessUser8.setEmail("muktalgrg@gmail.com");
		businessUser8.setMerchant(merchant2);
		businessUser8.setFloatDetails(floatDetails8);
		businessUserRepository.save(businessUser8);

		BusinessUser businessUser9 = new BusinessUser();
		businessUser9.setName("Raul");
		businessUser9.setEmail("muktalgrg@gmail.com");
		businessUser9.setMerchant(merchant2);
		businessUser9.setFloatDetails(floatDetails9);
		businessUserRepository.save(businessUser9);

		BusinessUser businessUser10 = new BusinessUser();
		businessUser10.setName("Toni Kroos");
		businessUser10.setEmail("muktalgrg@gmail.com");
		businessUser10.setMerchant(merchant2);
		businessUser10.setFloatDetails(floatDetails10);
		businessUserRepository.save(businessUser10);






	}
}
