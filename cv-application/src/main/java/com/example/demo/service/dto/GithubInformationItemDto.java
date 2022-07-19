package com.example.demo.service.dto;

import com.google.gson.annotations.SerializedName;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GithubInformationItemDto {

	@SerializedName("login")
	private String login;

	@SerializedName("avatar_url")
	private String avatarUrl;

}
