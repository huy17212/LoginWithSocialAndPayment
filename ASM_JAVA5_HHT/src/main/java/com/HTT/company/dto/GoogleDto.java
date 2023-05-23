package com.HTT.company.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GoogleDto {

	private String id;

	private String email;

	private boolean verified_email;

	private String name;

	private String given_name;

	private String family_name;

	private String picture;
}
