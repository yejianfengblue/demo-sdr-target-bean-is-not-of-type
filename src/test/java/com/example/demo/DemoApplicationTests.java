package com.example.demo;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class DemoApplicationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private ProjectRepo projectRepo;
	
	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private ObjectMapper om;
	
	@Test
	public void editProjectSceneUpstreamKeyChromaKeyToLumaKey() throws Exception {
		
		// Create a Project containing 1 scene with a ChromaKey
		String projectId = projectRepo.save(Project.builder()
			.name("Project 1")
			.scenes(List.of(
				Scene.builder()
					.name("Scene 1")
					.usks(List.of(
						ChromaKey.builder().a(1).b(2).build()
					))
					.build()
			))
			.build()).getId();
		
		// Put the Project with the exact same content except a LumaKey 
		// instead of a ChromaKey in position 0 of the usks list.
		Map<String, Object> putContent = Map.of(
			"name", "Project 1",
			"scenes", List.of(Map.of(
				"name", "Scene 1",
				"usks", List.of(Map.of(
					"a", "1",
					"c", "3",
					"type", "LumaKey"
				))
			))
		);
		
		mvc.perform(MockMvcRequestBuilders
		    .put("/projects/" + projectId)
		    .content(om.writeValueAsString(putContent))
		    .contentType(MediaType.APPLICATION_JSON)
		    .accept(MediaType.APPLICATION_JSON))
		    .andExpect(status().isOk())
		    .andExpect(MockMvcResultMatchers.jsonPath("$.name").exists())
		    .andReturn();
	}

}
