package com.baltinfo.radius.documents.generator;

/**
 * Base class for application level exceptions.
 */
public class DocxGeneratorException extends Exception {

	private static final long serialVersionUID = 3257286950284046390L;

	public DocxGeneratorException(String message) {
		super(message);
	}

	public DocxGeneratorException(String message, Throwable cause) {
		super(message, cause);
	}

	public DocxGeneratorException(Throwable cause) {
		super(cause);
	}

}