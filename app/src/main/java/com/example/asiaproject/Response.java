package com.example.asiaproject;

import java.util.List;

public class Response{
	private List<countries> response;

	public void setResponse(List<countries> response){
		this.response = response;
	}

	public List<countries> getResponse(){
		return response;
	}
}