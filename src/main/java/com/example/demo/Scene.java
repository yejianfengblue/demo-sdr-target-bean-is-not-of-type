package com.example.demo;

import java.util.List;

import org.springframework.data.annotation.PersistenceConstructor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@Data
@SuperBuilder
@AllArgsConstructor(onConstructor = @__(@PersistenceConstructor))
@EqualsAndHashCode(callSuper = false)
public class Scene {
	
	private String name;

	private List<UpstreamKey> usks;
	
}
