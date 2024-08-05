/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.exceptions;

/**
 *
 * @author tranlequocthong313
 */
public class ThreadNotFoundException extends RuntimeException {

	private long threadId;

	public ThreadNotFoundException(long threadId) {
		this.threadId = threadId;
	}

	public long getThreadId() {
		return threadId;
	}

	@Override
	public String getMessage() {
		return "Thread [" + threadId + "] not found";
	}

}
