package edu.handong.csee.java.utils;

public class FileError extends Exception{
	public FileError() {
		super("Wrong file format.");
	}
	public FileError(String message) {
		super(message);	
	}

}