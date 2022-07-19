package com.example.demo.service.dto;

import java.util.List;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GithubInformationDto {

	@SerializedName("total_count")
	private Integer totalCount;

	@SerializedName("incomplete_results")
	private Boolean incompleteResults;

	@SerializedName("items")
	private List<GithubInformationItemDto> items;
}
