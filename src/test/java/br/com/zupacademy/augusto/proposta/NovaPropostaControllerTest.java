package br.com.zupacademy.augusto.proposta;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import br.com.zupacademy.augusto.proposta.novaproposta.NovaPropostaRequest;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
public class NovaPropostaControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Autowired
	ObjectMapper objectMapper;

	@Test
	void deveCriarUmaNovaProposta() throws Exception {
		
		NovaPropostaRequest request = 
				new NovaPropostaRequest("54.363.981/0001-60","augusto@email.com", "Augusto",
						"Rua Maria de Faria, 210", BigDecimal.valueOf(2500.0));
		
		mockMvc.perform(post("/propostas")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated())
		.andExpect(redirectedUrlPattern("**/propostas/*"));
	}
	
	@Test
	void naoDeveCriarUmaNovaPropostaQuandoEmailInvalido() throws Exception {
		
		NovaPropostaRequest request = 
				new NovaPropostaRequest("89.602.506/0001-84","augusto", "Augusto",
						"Rua Maria de Faria, 210", BigDecimal.valueOf(2500.0));
		
		mockMvc.perform(post("/propostas")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andExpect(content().string("{\"globalErrorMessages\":[],"
				+ "\"fieldErrors\":["
				+ "{\"field\":\"email\","
				+ "\"error\":\"must be a well-formed email address\"}"
				+ "]}"));
	}
	
	@Test
	void naoDeveCriarUmaNovaPropostaQuandoSalarioIgualZero() throws Exception {
		
		NovaPropostaRequest request = 
				new NovaPropostaRequest("395.142.140-13","augusto@email.com", "Augusto",
						"Rua Maria de Faria, 210", BigDecimal.valueOf(0.0));
		
		mockMvc.perform(post("/propostas")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andExpect(content().string("{\"globalErrorMessages\":[],"
				+ "\"fieldErrors\":["
				+ "{\"field\":\"salario\","
				+ "\"error\":\"must be greater than 0\"}"
				+ "]}"));
	}
	
	@Test
	void naoDeveCriarUmaNovaPropostaQuandoSalarioMenorQueZero() throws Exception {
		
		NovaPropostaRequest request = 
				new NovaPropostaRequest("395.142.140-13","augusto@email.com", "Augusto",
						"Rua Maria de Faria, 210", BigDecimal.valueOf(-10.0));
		
		mockMvc.perform(post("/propostas")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andExpect(content().string("{\"globalErrorMessages\":[],"
				+ "\"fieldErrors\":["
				+ "{\"field\":\"salario\","
				+ "\"error\":\"must be greater than 0\"}"
				+ "]}"));
	}

	@Test
	void naoDeveCriarUmaNovaPropostaQuandoDocumentoJaCadastrado() throws Exception {
		
		NovaPropostaRequest request = 
				new NovaPropostaRequest("380.252.390-39","augusto@email.com", "Augusto",
						"Rua Maria de Faria, 210", BigDecimal.valueOf(2500.0));
		
		mockMvc.perform(post("/propostas")
				.content(objectMapper.writeValueAsString(request))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isCreated());
		
		NovaPropostaRequest request2 = 
				new NovaPropostaRequest("380.252.390-39","augusto@email.com", "Augusto",
						"Rua Maria de Faria, 210", BigDecimal.valueOf(2500.0));
		
		mockMvc.perform(post("/propostas")
				.content(objectMapper.writeValueAsString(request2))
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isBadRequest())
		.andExpect(content().string("{\"globalErrorMessages\":[],"
				+ "\"fieldErrors\":["
				+ "{\"field\":\"documento\","
				+ "\"error\":\"O valor passado no campo documento jÃ¡ foi cadastrado e deve ser Ãºnico!\"}"
				+ "]}"));
	}
}
