package com.example.Bookstore;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

//NOTE: Test fails likely due to being blocked by spring security (login authentication page redirect)
// -> Error/Failure: "Status expected: <200> but was <302>.


@SpringBootTest
@AutoConfigureMockMvc
public class WebLayerTest {

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testBookListEndpoint() throws Exception {
        this.mockMvc.perform(get("/booklist"))
            .andDo(print()) // Print request and response details 
            .andExpect(status().isOk()) // Expect HTTP status 200 (=ok)
            .andExpect(view().name("booklist")) // Expect the view name to be "booklist" 
            .andExpect(model().attributeExists("books")) // Expect a model attribute named "books" 
            .andExpect(model().attribute("books", ArrayList.class)); // Assert that the "books" model attribute is an ArrayList
	}
}
