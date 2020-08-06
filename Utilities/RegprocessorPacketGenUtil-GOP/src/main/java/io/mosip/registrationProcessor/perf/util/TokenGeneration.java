package io.mosip.registrationProcessor.perf.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;

import io.mosip.dbdto.TokenGenerationDto;
import io.mosip.dbentity.TokenGenerationEntity;
import io.restassured.response.Response;

public class TokenGeneration {

	// ApplicationLibrary applnMethods = new ApplicationLibrary();
	private static Logger logger = Logger.getLogger(TokenGeneration.class);

	public TokenGenerationEntity createTokenGeneratorDto(String tokenGenerationFilePath) throws IOException {
		TokenGenerationEntity generateTokenRequest = new TokenGenerationEntity();
		TokenGenerationDto tokenRequestDto = new TokenGenerationDto();
		RegProcApiRequests apiRequests = new RegProcApiRequests();
		Date currentDate = new Date();
		LocalDateTime requestTime = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault());
		Properties prop = new Properties();
		String propertyFilePath = System.getProperty("user.dir") + "/" + tokenGenerationFilePath;
		FileReader reader;
		try {
			reader = new FileReader(new File(propertyFilePath));
			prop.load(reader);
			generateTokenRequest.setId(prop.getProperty("token.request.id"));
			generateTokenRequest.setMetadata("");
			tokenRequestDto.setAppId(prop.getProperty("token.request.appid"));
			tokenRequestDto.setUserName(prop.getProperty("token.request.username"));
			tokenRequestDto.setPassword(prop.getProperty("token.request.password"));
			generateTokenRequest.setRequest(tokenRequestDto);
			generateTokenRequest.setRequesttime(requestTime);
			generateTokenRequest.setVersion(prop.getProperty("token.request.version"));
			reader.close();
		} catch (IOException e) {
			logger.error("Propert File Was Not Found", e);
		}
		String file = System.getProperty("user.dir") + "//src//main//resources//usernames_gop.txt";
		List<String> content = new ArrayList<String>();
		FileReader fileReader = new FileReader(new File(file));
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = "";
		while ((line = bufferedReader.readLine()) != null) {
			content.add(line);
		}
		bufferedReader.close();
		fileReader.close();
		Random rand=new Random();
		int rand_int1 = rand.nextInt(60);
		tokenRequestDto.setUserName(content.get(rand_int1));
		return generateTokenRequest;

	}

	public TokenGenerationEntity createTokenGeneratorDtoWithCurrentUser(String tokenGenerationFilePath, int index)
			throws IOException {
		TokenGenerationEntity generateTokenRequest = new TokenGenerationEntity();
		TokenGenerationDto tokenRequestDto = new TokenGenerationDto();
		RegProcApiRequests apiRequests = new RegProcApiRequests();
		Date currentDate = new Date();
		LocalDateTime requestTime = LocalDateTime.ofInstant(currentDate.toInstant(), ZoneId.systemDefault());
		Properties prop = new Properties();
		String propertyFilePath = System.getProperty("user.dir") + "/" + tokenGenerationFilePath;
		FileReader reader;
		try {
			reader = new FileReader(new File(propertyFilePath));
			prop.load(reader);
			generateTokenRequest.setId(prop.getProperty("token.request.id"));
			generateTokenRequest.setMetadata("");
			tokenRequestDto.setAppId(prop.getProperty("token.request.appid"));
			tokenRequestDto.setUserName(prop.getProperty("token.request.username"));
			tokenRequestDto.setPassword(prop.getProperty("token.request.password"));
			generateTokenRequest.setRequest(tokenRequestDto);
			generateTokenRequest.setRequesttime(requestTime);
			generateTokenRequest.setVersion(prop.getProperty("token.request.version"));
			reader.close();
		} catch (IOException e) {
			logger.error("Property File Was Not Found", e);
		}
		String file = System.getProperty("user.dir") + "//src//main//resources//regProc_users.csv";
		List<String> content = new ArrayList<String>();
		FileReader fileReader = new FileReader(new File(file));
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		String line = "";
		while ((line = bufferedReader.readLine()) != null) {
			content.add(line);
		}
		bufferedReader.close();
		fileReader.close();

		tokenRequestDto.setUserName(content.get(index));
		generateTokenRequest.setRequest(tokenRequestDto);
		return generateTokenRequest;

	}

	@SuppressWarnings("unchecked")
	public String getToken(TokenGenerationEntity tokenGenerateEntity,PropertiesUtil prop) {
		RegProcApiRequests apiRequests = new RegProcApiRequests();
		JSONObject requestToBeSent = new JSONObject();
		JSONObject nestedRequest = new JSONObject();
		nestedRequest.put("appId", tokenGenerateEntity.getRequest().getAppId());
		nestedRequest.put("password", tokenGenerateEntity.getRequest().getPassword());
		nestedRequest.put("userName", tokenGenerateEntity.getRequest().getUserName());
		requestToBeSent.put("id", tokenGenerateEntity.getId());
		requestToBeSent.put("metadata", "");
		requestToBeSent.put("request", nestedRequest);
		requestToBeSent.put("requesttime", tokenGenerateEntity.getRequesttime().atOffset(ZoneOffset.UTC).toString());
		requestToBeSent.put("version", tokenGenerateEntity.getVersion());

		Response response = apiRequests.postRequest("/v1/authmanager/authenticate/useridPwd", requestToBeSent,
				MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON,prop);
		System.out.println(response.getCookie("Authorization"));
		return response.getCookie("Authorization");
	}

	public String readPropertyFile(String tokenGenerationFilePath) {
		Properties prop = new Properties();
		String propertyFilePath = System.getProperty("user.dir") + "/src/config/folderPaths.properties";
		FileReader reader;
		try {
			reader = new FileReader(new File(propertyFilePath));
			prop.load(reader);
		} catch (IOException e) {
			logger.error("Property File Was Not Found", e);
		}
		return prop.getProperty(tokenGenerationFilePath);
	}

}