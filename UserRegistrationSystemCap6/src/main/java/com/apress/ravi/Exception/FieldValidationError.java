package com.apress.ravi.Exception;

import java.awt.TrayIcon.MessageType;

public class FieldValidationError {
	private String field;
	private String message;
	private MessageType type;
	// Getter & Setter
	
	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}
	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the type
	 */
	public MessageType getType() {
		return type;
	}
	/**
	 * @param type the type to set
	 */
	public void setType(MessageType type) {
		this.type = type;
	}
	
}
