package com.careerit.sc.domain;
import java.util.*;
import lombok.Data;
import lombok.Builder;

@Data
@Builder



public class User {
	
	
	private String Username;
	private String password;
	private String email;
	
}
