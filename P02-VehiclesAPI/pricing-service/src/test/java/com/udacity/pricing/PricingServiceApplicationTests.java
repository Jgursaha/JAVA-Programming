package com.udacity.pricing;

import com.udacity.pricing.api.PricingController;
import com.udacity.pricing.service.PricingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PricingController.class)
//@SpringBootTest
public class PricingServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	PricingService pricingService;

	@Test
	public void contextLoads() {
	}

	@Test
	public void getPrice() throws Exception{
		mockMvc.perform(get("/services/price/?id=1"))
				.andExpect(status().isOk());
				//.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				//.andExpect(content().json("[]"));

		verify(pricingService, times(1)).getPrice(1L);
	}
}
