package com.springbootswagger.example;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.*;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * 
 * @author thiyagu.anbu
 * Test the controller
 *JUnit
 *Hamcrest
 *MockMvc -  Web Application Context integration setup
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringBootSwaggerApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductControllerTest {

	private MockMvc mockMvc;

	@Autowired
    private WebApplicationContext wac;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	@Test
	public void getAllProductTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/product/list").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.*",hasSize(6))).andDo(print());
	}
	@Test
	public void getProductByIdTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/product/1").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.id").exists())
		.andExpect(jsonPath("$.version").exists())
		.andExpect(jsonPath("$.productId").exists())
		.andExpect(jsonPath("$.description").exists())
		.andExpect(jsonPath("$.imageUrl").exists())
		.andExpect(jsonPath("$.price").exists())
		.andDo(print());
	}
	@Test
	public void deleteProductByIdTest() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.delete("/product/53").accept(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.code").value(200))
		.andExpect(jsonPath("$.message").value("product deleted successfully"))
		.andDo(print());
	}	
	
	
	
	
}
