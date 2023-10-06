package com.fetchsaudata.utility;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fetchsaudata.bean.JsonBean;

public class RestTemplateService {

	@Value("${sauWebService_serviceName}")
	private String sauWebService;
	@Value("${mergeWebService_serviceName}")
	private String mergeWebService;
	String serviceUriDb;
	private static Logger log = LoggerFactory.getLogger(RestTemplateService.class);

	public String serviceUrl(String serviceName, DiscoveryClient discoveryClient) {
//		if(discoveryClient==null)
//			throw new CustomNullDiscoveryCLientException("DiscoveryClient is null");
		List<ServiceInstance> list = discoveryClient.getInstances(serviceName);
		System.out.println("list " + list);
		if (list != null && list.size() > 0) {
			log.info("Uri " + list.get(0).getUri().toString());
			return list.get(0).getUri().toString();
		}
		return null;
	}

	public HttpHeaders getheader() {
		HttpHeaders headers = new HttpHeaders();
		return headers;
	}

	public ResponseEntity<JSONObject> getRowData(String pkldirectorate, Integer skip, Integer row,
			DiscoveryClient discoveryClient) {
		log.info("Inside getRowData  method");
		log.info("service name is " + sauWebService);
		if (serviceUriDb == null)
			serviceUriDb = serviceUrl(sauWebService, discoveryClient);
		String url = serviceUriDb + "/sauWebService/sau_data/pkl";
		log.info("url " + url);
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("pkldirectorate", pkldirectorate);
		params.add("skip", String.valueOf(skip));
		params.add("row", String.valueOf(row));
		HttpHeaders headers = getheader();
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
		ResponseEntity<JSONObject> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity,
				JSONObject.class);

		log.info("status: " + responseEntity.getStatusCode());
		return responseEntity;
	}

	public ResponseEntity<JSONObject> getbyfilenameData(String pkldirectorate, String filename, String fileno,
			Integer startIndex, Integer endIndex, String branch, DiscoveryClient discoveryClient) {
		log.info("Inside getbyfilenameData  method");
		log.info("service name is " + sauWebService);
		if (serviceUriDb == null)
			serviceUriDb = serviceUrl(sauWebService, discoveryClient);
		String url = serviceUriDb + "/sauWebService/sau_data/filename";
		log.info("url " + url);
		RestTemplate restTemplate = new RestTemplate();
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("pkldirectorate", pkldirectorate);
		params.add("filename", filename);
		params.add("fileno", fileno);
		params.add("branch", branch);
		params.add("startIndex", String.valueOf(startIndex));
		params.add("endIndex", String.valueOf(endIndex));

		HttpHeaders headers = getheader();
		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
		ResponseEntity<JSONObject> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity,
				JSONObject.class);

		log.info("status: " + responseEntity.getStatusCode());
		return responseEntity;
	}

	
	public ResponseEntity<String> mergedoc( JsonBean jb, HttpServletRequest request, DiscoveryClient discoveryClient) {
		log.info("Inside getbyfilenameData  method");
		log.info("service name is " + sauWebService);
		if (serviceUriDb == null)
			serviceUriDb = serviceUrl(mergeWebService, discoveryClient);
		String url = serviceUriDb + "/mergeWebService/mergePCase/mergeDoc";
		log.info("url " + url);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = getheader();
	    headers.add("Authorization", request.getHeader("Authorization"));
	    HttpEntity<JsonBean> entity = new HttpEntity<>(jb, headers);
	    ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
	    log.info("status: " + responseEntity.getStatusCode());
		
	    return responseEntity;
	}

	
	
//	public ResponseEntity<JSONObject> getUserRolesData(DiscoveryClient discoveryClient) {
//		log.info("Inside getUserRolesData  method");
//		log.info("service name is " + sauWebService);
//		if (serviceUriDb == null)
//			serviceUriDb = serviceUrl(sauWebService, discoveryClient);
//		String url = serviceUriDb + "/sauWebService/getUserRoles";
//		log.info("url " + url);
//		RestTemplate restTemplate = new RestTemplate();
//		// MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//
//		HttpHeaders headers = getheader();
//		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);
//		ResponseEntity<JSONObject> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity,
//				JSONObject.class);
//		log.info("status: " + responseEntity.getStatusCode());
//		return responseEntity;
//	}

//	public ResponseEntity<JSONObject> getUserData(String username, DiscoveryClient discoveryClient) {
//		log.info("Inside getUserData  method");
//		log.info("service name is " + sauWebService);
//		if (serviceUriDb == null)
//			serviceUriDb = serviceUrl(sauWebService, discoveryClient);
//		String url = serviceUriDb + "/sauWebService/sau_data/getUser";
//		log.info("url " + url);
//		RestTemplate restTemplate = new RestTemplate();
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("username", username);
//		HttpHeaders headers = getheader();
//		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
//		ResponseEntity<JSONObject> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity,
//				JSONObject.class);
//
//		log.info("status: " + responseEntity.getStatusCode());
//		return responseEntity;
//	}
//
//	public ResponseEntity<JSONObject> getUserRolesData(String username, DiscoveryClient discoveryClient) {
//		log.info("Inside getUserRolesData  method");
//		log.info("service name is " + sauWebService);
//		if (serviceUriDb == null)
//			serviceUriDb = serviceUrl(sauWebService, discoveryClient);
//		String url = serviceUriDb + "/sauWebService/sau_data/getUserRolesData";
//		log.info("url " + url);
//		RestTemplate restTemplate = new RestTemplate();
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("username", username);
//		HttpHeaders headers = getheader();
//		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
//		ResponseEntity<JSONObject> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity,
//				JSONObject.class);
//
//		log.info("status: " + responseEntity.getStatusCode());
//		return responseEntity;
//	}
//
//	public ResponseEntity<JSONObject> getUserRolesforInternalData(String username, String depRole,
//			DiscoveryClient discoveryClient) {
//		log.info("Inside getUserRolesforInternalData  method");
//		log.info("service name is " + sauWebService);
//		if (serviceUriDb == null)
//			serviceUriDb = serviceUrl(sauWebService, discoveryClient);
//		String url = serviceUriDb + "/sauWebService/sau_data/getUserRolesforInternal";
//		log.info("url " + url);
//		RestTemplate restTemplate = new RestTemplate();
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("username", username);
//		params.add("depRole", depRole);
//		HttpHeaders headers = getheader();
//		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
//		ResponseEntity<JSONObject> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity,
//				JSONObject.class);
//
//		log.info("status: " + responseEntity.getStatusCode());
//		return responseEntity;
//	}
//
//	public ResponseEntity<JSONObject> getCauData(String sau, DiscoveryClient discoveryClient) {
//		log.info("Inside getCauData  method");
//		log.info("service name is " + sauWebService);
//		if (serviceUriDb == null)
//			serviceUriDb = serviceUrl(sauWebService, discoveryClient);
//		String url = serviceUriDb + "/sauWebService/sau_data/getCauData";
//		log.info("url " + url);
//		RestTemplate restTemplate = new RestTemplate();
//		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//		params.add("sau", sau);
//		HttpHeaders headers = getheader();
//		HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);
//		ResponseEntity<JSONObject> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity,
//				JSONObject.class);
//
//		log.info("status: " + responseEntity.getStatusCode());
//		return responseEntity;
//	}
//
//	public ResponseEntity<JSONObject> putDataInMongo(List<String> serviceNo, DiscoveryClient discoveryClient) {
//		log.info("Inside putDataInMongo method");
//		log.info("service name is " + sauWebService);
//		if (serviceUriDb == null)
//			serviceUriDb = serviceUrl(sauWebService, discoveryClient);
//		String url = serviceUriDb + "/sauWebService/sau_data/putDataInMongo";
//		log.info("url " + url);
//
//		RestTemplate restTemplate = new RestTemplate();
//		HttpHeaders headers = getheader();
//		HttpEntity<List<String>> entity = new HttpEntity<>(serviceNo, headers);
//
//		ResponseEntity<JSONObject> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity,
//				JSONObject.class);
//
//		log.info("status: " + responseEntity.getStatusCode());
//		return responseEntity;
//	}

}
