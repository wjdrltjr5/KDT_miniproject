package org.kdt.service;

import java.sql.SQLException;

public class DuplicatedProductnoException extends Exception {

	public DuplicatedProductnoException(String message, SQLException cause) {
        super(message, cause);
    }

	  public DuplicatedProductnoException(String message) {
	        super(message);
	    }

}
