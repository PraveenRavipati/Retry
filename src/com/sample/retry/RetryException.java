package com.sample.retry;

public class RetryException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Exception originalException;

	public RetryException() {
		super();
	}

	public RetryException(String s) {
		super(s);
	}

	public RetryException(String s, Throwable throwable) {
		super(s, throwable);
	}

	public RetryException(Throwable throwable) {
		super(throwable);
		setOriginalException((Exception) throwable);
	}

	public Exception getOriginalException() {
		return originalException;
	}

	public void setOriginalException(Exception originalException) {
		this.originalException = originalException;
	}

}
