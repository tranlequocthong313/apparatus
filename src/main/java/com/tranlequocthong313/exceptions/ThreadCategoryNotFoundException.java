/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.exceptions;

/**
 *
 * @author tranlequocthong313
 */
public class ThreadCategoryNotFoundException extends RuntimeException {

	private long threadCategoryId;

	public ThreadCategoryNotFoundException(long threadCategoryId) {
		this.threadCategoryId = threadCategoryId;
	}

	public long getThreadCategoryId() {
		return threadCategoryId;
	}

	@Override
	public String getMessage() {
		return "Thread category [" + threadCategoryId + "] not found";
	}

}
