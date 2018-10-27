package com.logscanner.config;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

import org.springframework.boot.json.BasicJsonParser;
import org.springframework.boot.json.JsonParser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigReader {

	private static HashMap rootMap = new HashMap();
	private static boolean isInitialiized = false;;

	public synchronized static HashMap readConfig(String file) throws Exception {
		if (isInitialiized) {
			return rootMap;
		}
		JsonParser jsonParser = new BasicJsonParser();
		String fileContents = new String(Files.readAllBytes(Paths.get(file)));
		// System.out.println(fileContents);
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.readTree(fileContents);
		JsonNode rootNode = objectMapper.readTree(fileContents);
		rootNode.get("clusters").forEach(node -> {
				ClusterObject obj;
				try {
					obj = objectMapper.treeToValue(node, ClusterObject.class);
					rootMap.put((node.get("name").toString().replaceAll("\"", "")), obj);
				} catch (JsonProcessingException e) {
					// TODO Auto-generated catch block
					isInitialiized = false;					
				}
				
		});
		isInitialiized = true;
		return rootMap;

	}

	public static void main(String[] args) throws Exception {
		ConfigReader reader = new ConfigReader();
		System.out.println(reader.readConfig("c:\\temp\\serverscan.json").toString());
	}

}
