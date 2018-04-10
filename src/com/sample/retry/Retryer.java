package com.sample.retry;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.function.Supplier;


public class Retryer {
	
	public static <T> Object retry(Supplier<T> runnable, int retryCount) throws Exception {
		Exception exception = null;
		do {
			try {
				return runnable.get();
			} catch (Exception e) {
				retryCount--;
				if(retryCount == 0) {
					exception = e;
				}
			}
		} while(0<retryCount);
		throw exception;
	}
	
	public static void main(String[] args){
		String svgPath = "https://commons.wikimedia.org/wiki/File:Ubuntu_logoib.svg";
		try {
			lamdaGetStream(svgPath);
		} catch (RetryException e) {
			Exception exception = e.getOriginalException();
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void lamdaGetStream(String svgPath) throws Exception {
		InputStream inputStream = null;
		inputStream = (InputStream) retry(() -> {
					try {
						return getStream(svgPath);
					} catch (Exception e) {
						throw new RetryException();
					}
			}, 5);
	}
	
	private static InputStream getStream(String path) throws IOException {
		URL url = new URL(path);
		InputStream inputStream = url.openStream();
		return inputStream;
	}
}
