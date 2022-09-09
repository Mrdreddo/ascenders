package com.ascenders.base.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
@Data
public class Response {
	  @JsonProperty 
		private int statuscode;
	    @JsonProperty
	    private String msg;
	    @JsonProperty
	    
	    private String token;
}
