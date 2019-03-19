package com.demo.web.service;

import com.demo.common.UserQo;
import com.google.gson.Gson;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserRestService {
	 @Autowired
	 private UserClient userClient;

	@HystrixCommand(fallbackMethod = "findByIdFallback")
	public String findById(String id){
		return userClient.findById(id);
	}

	private String findByIdFallback(String id){
		return null;
	}


	@HystrixCommand(fallbackMethod = "findPageFallback")
	public String findPage(UserQo userQo){
		return userClient.findPage(userQo);
	}

	private String findPageFallback(UserQo userQo){
		Map<String, Object> page = new HashMap<>();
		page.put("list", null);
		page.put("pages", 0);
		page.put("total", 0);
		return new Gson().toJson(page);
	}

	@HystrixCommand(fallbackMethod = "createFallback")
	public String create(UserQo userQo){
		return userClient.create(userQo);
	}

	private String createFallback(UserQo userQo) {
		return "-1";
	}

	@HystrixCommand(fallbackMethod = "updateFallback")
	public String update(UserQo userQo){
		return userClient.update(userQo);
	}

	private String updateFallback(UserQo userQo) {
		return "-1";
	}

	@HystrixCommand(fallbackMethod = "deleteFallback")
	public String delete(String id){
		return userClient.delete(id);
	}

	private String deleteFallback(Long id) {
		return "-1";
	}
}
