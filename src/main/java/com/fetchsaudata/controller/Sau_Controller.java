package com.fetchsaudata.controller;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fetchsaudata.MyConfig;
import com.fetchsaudata.bean.DeptConfigS;
import com.fetchsaudata.bean.DeptConfigT;
import com.fetchsaudata.exception.CustomException;
import com.fetchsaudata.repository.DeptConfigSRepo;
import com.fetchsaudata.repository.DeptConfigTRepo;
//import com.fetchsaudata.service.InfoService;
//import com.fetchsaudata.service.Service_Impl;
import com.fetchsaudata.utility.RestTemplateService;

@RestController
@RequestMapping("/sau_data/saufileszip")
@EnableConfigurationProperties(value = MyConfig.class)
public class Sau_Controller {
	private static Logger log = LoggerFactory.getLogger(Sau_Controller.class);
	@Value("${dctm.username}")
	private String user;
	@Value("${dctm.password}")
	private String password;
	@Value("${dctm.repository}")
	private String repo;
	@Autowired
	private RestTemplateService restTemplateService;
	@Autowired
	private DeptConfigSRepo departmentRepo;
	@Autowired
	private DeptConfigTRepo userRepo;
	@Autowired
	private MyConfig config;

//	@Autowired
//	private Service_Impl ss;
	@Autowired
	DiscoveryClient discoveryClient;

	@Value("${version}")
	String version;

	@GetMapping("/version")
	public String getVersion() {
		return version;
	}

	@GetMapping("/getConfigData")
	public MyConfig getConfiguration() {
		return config;
	}

	@PostMapping("/getbypkl")
	public ResponseEntity<JSONObject> getRow(@PathParam("pkldirectorate") String pkldirectorate,
			@PathParam("skip") Integer skip, @PathParam("row") Integer row) {

		ResponseEntity<JSONObject> getrowData = restTemplateService.getRowData(pkldirectorate, skip, row,
				discoveryClient);
		return getrowData;

	}

	@PostMapping("/getbyfilename")
	public ResponseEntity<JSONObject> getbyfilename(@PathParam("pkldirectorate") String pkldirectorate,
			@PathParam("filename") String filename, @PathParam("fileno") String fileno,
			@PathParam("startIndex") Integer startIndex, @PathParam("endIndex") Integer endIndex,
			@PathParam("branch") String branch) {

		ResponseEntity<JSONObject> getfilname = restTemplateService.getbyfilenameData(pkldirectorate, filename, fileno,
				startIndex, endIndex, branch, discoveryClient);
		return getfilname;

	}

}
