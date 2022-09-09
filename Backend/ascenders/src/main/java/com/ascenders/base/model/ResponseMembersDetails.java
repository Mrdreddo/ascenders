package com.ascenders.base.model;

import java.util.List;

import com.ascenders.base.entity.AscendersTeam;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseMembersDetails {
	
	@JsonProperty
	private int statusCode;
	@JsonProperty
	private String msg;
	
	private List<AscendersTeam> ascendersTeams;
}
