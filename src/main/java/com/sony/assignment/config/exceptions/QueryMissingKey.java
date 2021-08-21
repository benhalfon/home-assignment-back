package com.sony.assignment.config.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class QueryMissingKey extends RuntimeException {

	private static final long serialVersionUID = 6113466621468684478L;
	
	public QueryMissingKey(String queryName,String[] keys) {
		super(String.format("Query name %s missing keys:[%s]",
				queryName,
				String.join(",", keys)));
	}
}
