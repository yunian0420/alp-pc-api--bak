package com.lphr.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DetailCandidateDTO {

	@NotNull(message = "id必填")
	private Integer id;

}
