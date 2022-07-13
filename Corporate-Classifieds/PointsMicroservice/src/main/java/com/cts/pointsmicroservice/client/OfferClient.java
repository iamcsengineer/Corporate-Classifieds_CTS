package com.cts.pointsmicroservice.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import com.cts.pointsmicroservice.model.Offer;

//to connect to offer microservice
@FeignClient(url = "${offer.feign.client}", name = "${offer.feign.name}")
public interface OfferClient {

	@GetMapping("/getOffers/{emp_id}")
	public List<Offer> getOfferByEmpId(@RequestHeader(name = "Authorization") String token,
			@PathVariable("emp_id") int id);

	@GetMapping("/getPoints/{emp_id}")
	public int getPointsById(@RequestHeader("Authorization") String token, @PathVariable("emp_id") int id);
}
