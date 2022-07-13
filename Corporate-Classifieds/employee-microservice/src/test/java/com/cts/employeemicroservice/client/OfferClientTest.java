
package com.cts.employeemicroservice.client;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class OfferClientTest {
  
	OfferClient offerClient;
	
	
	@Test
    @DisplayName("Checking if [OfferClient] is loading or not.")
   void packagingClientIsLoaded(){
        assertThat(offerClient).isNull();    
    }
}