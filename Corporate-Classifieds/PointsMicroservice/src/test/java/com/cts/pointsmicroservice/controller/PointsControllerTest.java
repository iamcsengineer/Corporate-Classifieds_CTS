package com.cts.pointsmicroservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.cts.pointsmicroservice.client.AuthClient;
import com.cts.pointsmicroservice.client.OfferClient;
import com.cts.pointsmicroservice.model.AuthResponse;
import com.cts.pointsmicroservice.model.Offer;
import com.cts.pointsmicroservice.service.PointsService;

@SpringBootTest
@AutoConfigureMockMvc
public class PointsControllerTest {

	@Mock
	private PointsService pointsService;

	@InjectMocks
	private PointsController pointsController;

	@MockBean
	private AuthClient authClient;

	@MockBean
	private OfferClient offerClient;

	@Autowired
	private MockMvc mvc;

	@Test
	public void getPointsByEmpId() throws Exception {
		ResponseEntity<AuthResponse> response = new ResponseEntity<>(new AuthResponse(1, "ram", true), HttpStatus.OK);
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";
		when(authClient.verifyToken(token)).thenReturn(response);
		MvcResult mvcResult = mvc.perform(get("/getpointsbyemp/{id}", 1).header("Authorization", token)).andReturn();
		int actualValue = mvcResult.getResponse().getStatus();
		assertEquals(200, actualValue);
		}

	@Test
	public void refreshPoints() throws Exception {
		List<Offer> offer = Arrays.asList(new Offer(1, "name", "offer description", "category",new Date(), null,null, 100));
		ResponseEntity<AuthResponse> response = new ResponseEntity<>(new AuthResponse(1, "ram", true), HttpStatus.OK);
		List<Offer> offerResponse = offer;
		String token = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZXlhIiwiZXhwIjoxNjA3Nzc0MTI3LCJpYXQiOjE2MDc3NzMyMjd9.nBr3qDGV2txIfx_Z-U-CjZalODWV7HqWjPYRly_oYMk";
		when(authClient.verifyToken(token)).thenReturn(response);
		when(offerClient.getOfferByEmpId(token, 1)).thenReturn(offerResponse);
		MvcResult mvcResult = mvc
				.perform(post("/refreshpointsbyemp/{id}", 1).header("Authorization", token)
						.contentType(MediaType.APPLICATION_JSON))
				.andReturn();
		int actualValue = mvcResult.getResponse().getStatus();
		assertEquals(500, actualValue);
		}

}
