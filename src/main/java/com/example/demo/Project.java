package com.example.demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor(onConstructor = @__(@PersistenceConstructor))
@Document
public class Project {

	@Id
	private String id;
	
	private String name;
	
	private List<Scene> scenes;

	@JsonCreator
    public Project(String name, List<Scene> scenes) {
        this.name = name;
        this.scenes = scenes;
    }
}
