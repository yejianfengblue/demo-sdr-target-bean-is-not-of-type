package com.example.demo;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Data
@SuperBuilder
@AllArgsConstructor
@Document
public class Project {

	@Id
	private String id;
	
	private String name;
	
	private List<Scene> scenes;
	
}
