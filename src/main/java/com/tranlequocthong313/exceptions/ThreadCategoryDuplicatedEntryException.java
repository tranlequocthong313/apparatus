/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tranlequocthong313.exceptions;

import com.tranlequocthong313.models.ThreadCategory;

/**
 *
 * @author tranlequocthong313
 */
public class ThreadCategoryDuplicatedEntryException extends RuntimeException {

	private ThreadCategory threadCategory;

	public ThreadCategoryDuplicatedEntryException(ThreadCategory threadCategory) {
		this.threadCategory = threadCategory;
	}

	public ThreadCategory getThreadCategory() {
		return threadCategory;
	}

	@Override
	public String getMessage() {
		return "Thread category [" + threadCategory.getName() + "] already exists";
	}

}
