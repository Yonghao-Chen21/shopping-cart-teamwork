package com.careerit.sc.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
	private String Username;
	private String password;
	private String email;
	
}