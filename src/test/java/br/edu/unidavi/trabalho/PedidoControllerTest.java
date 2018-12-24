package br.edu.unidavi.trabalho;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.edu.unidavi.trabalho.controller.PedidoRestController;
import br.edu.unidavi.trabalho.dto.ItemDTO;
import br.edu.unidavi.trabalho.dto.PedidoDTO;

@RunWith(SpringRunner.class)
@WebMvcTest(PedidoRestController.class)
public class PedidoControllerTest
{
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	ObjectMapper jsonParser;

	@Test
	public void testeGeral() throws Exception
	{
		List<ItemDTO> items = new ArrayList<>();
		items.add(ItemDTO.builder()
					.produto(1l)
					.quantidade(1)
					.total(2.5d)
					.build());

		PedidoDTO dto = PedidoDTO.builder()
				.numero(1l)
				.cliente(1l)
				.total(2.5d)
				.items(items)
				.build();


		String accessToken = obtainAccessToken("root", "t0ps3cr3t");

		mockMvc.perform(post("/pedidos").contentType(MediaType.APPLICATION_JSON)
				.header("Authorization", "Bearer " + accessToken)
				.content(jsonParser.writeValueAsString(dto)))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.id", equalTo(1)));
	}
	
	private String obtainAccessToken(String username, String password) throws Exception {
		  
	    MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	    params.add("grant_type", "password");
	    params.add("username", username);
	    params.add("password", password);
	 
	    ResultActions result 
	      = mockMvc.perform(post("/oauth/token")
	        .params(params)
	        .accept("application/json;charset=UTF-8"))
	        .andExpect(status().isOk())
	        .andExpect(content().contentType("application/json;charset=UTF-8"));
	 
	    String resultString = result.andReturn().getResponse().getContentAsString();
	 
	    JacksonJsonParser jsonParser = new JacksonJsonParser();
	    return jsonParser.parseMap(resultString).get("access_token").toString();
	}
}
