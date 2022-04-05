package com.example.demo;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Immutable;

@Data
@NoArgsConstructor
@SuperBuilder(toBuilder = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
	@JsonSubTypes.Type(value = ChromaKey.class, name = "ChromaKey"),
	@JsonSubTypes.Type(value = LumaKey.class, name = "LumaKey")
})
@Immutable
public abstract class UpstreamKey {

	private Integer a;
	
	public abstract String getType();

    protected UpstreamKey(Integer a) {
        this.a = a;
    }
}
