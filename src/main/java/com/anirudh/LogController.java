package com.anirudh;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.logscanner.config.ConfigReader;
import com.logscanner.config.LogInputRequestVo;

@RestController
public class LogController {

	@RequestMapping("/geterror")
	@CrossOrigin(origins = "http://localhost:8080")
	public String getService() {
		try {
			ScanLogs sl = new ScanLogs();
			sl.setKeyword("error");
			ArrayList lst = new ArrayList();
			HashMap m = ConfigReader.readConfig("c:\\temp\\serverscan.json");
			lst.add(m.get("CRMAPP1"));
			lst.add(m.get("CRMAPP2"));
			sl.scanComponent(lst);
			return (Files.readAllLines(Paths.get("c:/temp/cksum/scan_log.txt")).toString());
		} catch (Exception e) {
			return "Error";
		}
	}
	
	 @CrossOrigin(origins = "http://localhost:8080")
	@PostMapping(path="/config"			
	)
	public ResponseEntity getConfig() {
		try {		
			HashMap m=ConfigReader.readConfig("c:\\temp\\serverscan.json");			
			
			return new ResponseEntity(m,HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	 
	 @Bean
	    public WebMvcConfigurer corsConfigurer() {
	        return new WebMvcConfigurerAdapter() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/config").allowedOrigins("http://localhost:8080");
	            }
	        };
	    }
	 
	 @PostMapping(path="/action"			
		)
		@CrossOrigin(origins = "http://localhost:8080")
		public ResponseEntity performAction(@RequestBody LogInputRequestVo input) {		 
			ScanLogs sl = new ScanLogs();
			sl.setKeyword(input.getQ());
			String resp=null;;
			ArrayList lst = new ArrayList();
			try{
			HashMap m = ConfigReader.readConfig("c:\\temp\\serverscan.json");
			lst.add(m.get(input.getGroup()));		
			sl.scanComponent(lst);
			resp=Files.readAllLines(Paths.get("c:/temp/cksum/scan_log.txt")).toString();
			if(resp.equalsIgnoreCase("[]")){
				return new ResponseEntity("{}",HttpStatus.OK);
			}
			System.out.println("resp out=" + resp);
			
			resp=new StringBuffer(resp).deleteCharAt(resp.lastIndexOf(",")).toString();
			}catch(Exception e){
				e.printStackTrace();
				return new ResponseEntity("{error:failed}",HttpStatus.OK);
			}
			return new ResponseEntity(resp,HttpStatus.OK);
	 }

	 @PostMapping(path="/command"			
		)
		@CrossOrigin(origins = "http://localhost:8080")
		public ResponseEntity execCommand(@RequestBody LogInputRequestVo input) {		 
			ScanLogs sl = new ScanLogs();
			sl.setKeyword(input.getQ());
			String resp=null;;
			ArrayList lst = new ArrayList();
			try{
			HashMap m = ConfigReader.readConfig("c:\\temp\\serverscan.json");
			lst.add(m.get(input.getGroup()));		
			sl.exexCmd4Grp(lst, input.getQ());
			resp=Files.readAllLines(Paths.get("c:/temp/cksum/scan_log.txt")).toString();
			if(resp.equalsIgnoreCase("[]")){
				return new ResponseEntity("{}",HttpStatus.OK);
			}
			System.out.println("resp out=" + resp);
			
			resp=new StringBuffer(resp).deleteCharAt(resp.lastIndexOf(",")).toString();
			}catch(Exception e){
				e.printStackTrace();
				return new ResponseEntity("{error:failed}",HttpStatus.OK);
			}
			return new ResponseEntity(resp,HttpStatus.OK);
	 }
	 
	 @PostMapping(path="/browse"			
		)
		@CrossOrigin(origins = "http://localhost:8080")
		public ResponseEntity execBrowse(@RequestBody LogInputRequestVo input) {		 
			ScanLogs sl = new ScanLogs();
			sl.setKeyword(input.getQ());
			String resp=null;;
			ArrayList lst = new ArrayList();
			try{
			HashMap m = ConfigReader.readConfig("c:\\temp\\serverscan.json");
			lst.add(m.get(input.getGroup()));		
			sl.scanComponent(lst);
			resp=Files.readAllLines(Paths.get("c:/temp/cksum/scan_log.txt")).toString();
			if(resp.equalsIgnoreCase("[]")){
				return new ResponseEntity("{}",HttpStatus.OK);
			}
			System.out.println("resp out=" + resp);
			
			resp=new StringBuffer(resp).deleteCharAt(resp.lastIndexOf(",")).toString();
			}catch(Exception e){
				e.printStackTrace();
				return new ResponseEntity("{error:failed}",HttpStatus.OK);
			}
			return new ResponseEntity(resp,HttpStatus.OK);
	 }
	 
}
