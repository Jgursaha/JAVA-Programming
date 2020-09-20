package com.udacity.pricing;

import com.udacity.pricing.api.PricingController;
import com.udacity.pricing.domain.price.Price;
import com.udacity.pricing.domain.price.PriceRepository;
import com.udacity.pricing.service.PricingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.junit.Assert;

import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PricingController.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PricingServiceApplicationTests {

	//@LocalServerPort

	@Autowired
	private MockMvc mockMvc;

	//@Autowired
	//private TestRestTemplate restTemplate;

	@MockBean
	PricingService pricingService;

	//@MockBean
	//PriceRepository priceRepository;

	@Test
	public void contextLoads() {
	}

	@Test
	public void getPrice() throws Exception{
		mockMvc.perform(get("/services/price/?vehicleId=1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
				//.andExpect(content().json("[]"));

		//verify(pricingService, times(1)).getPrice(1L);
	}

	//@Test
	//public void testSampleData(){
	//	ResponseEntity<Price> response = restTemplate.getForEntity("http://localhost")

	//}
}
