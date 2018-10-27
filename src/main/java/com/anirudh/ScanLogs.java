package com.anirudh;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

import org.json.JSONException;
import org.json.JSONObject;

import com.logscanner.config.ClusterObject;
import com.logscanner.config.Server;


public class ScanLogs {
	
	
	
	private static String app1="~prdapp1 mdccrmprdapp01 mdccrmprdapp02 mdccrmprdapp03 mdccrmprdapp04 mdccrmprdapp05 mdccrmprdapp07 mdccrmprdapp08 mdccrmprdapp09 mdccrmprdapp10";
	private static String app2="~prdapp2 mdccrmprdapp11 mdccrmprdapp12 mdccrmprdapp13 mdccrmprdapp14 mdccrmprdapp15 mdccrmprdapp17 mdccrmprdapp18 mdccrmprdapp19 mdccrmprdapp20";
	private static String app3="~prdapp3 mdccrmprdapp21 mdccrmprdapp22 mdccrmprdapp23 mdccrmprdapp24 mdccrmprdapp25 mdccrmprdapp27 mdccrmprdapp28 mdccrmprdapp29 mdccrmprdapp30";
	
	private static String igw1="~prdigw1 mdccrmprdigp01 mdccrmprdigp02 mdccrmprdigp03 mdccrmprdigp04 mdccrmprdigp05 mdccrmprdigp06 mdccrmprdigp07 mdccrmprdigp08 mdccrmprdigp09 mdccrmprdigp10 mdccrmprdigp11 mdccrmprdigp12";
	private static String igw2="~prdigw2 mdccrmprdigp13 mdccrmprdigp14 mdccrmprdigp15 mdccrmprdigp16 mdccrmprdigp17 mdccrmprdigp18 mdccrmprdigp19 mdccrmprdigp20 mdccrmprdigp21 mdccrmprdigp22 mdccrmprdigp23 mdccrmprdigp24";
	private static String igw3="~prdigw3 mdccrmprdigp25 mdccrmprdigp26 mdccrmprdigp27 mdccrmprdigp28 mdccrmprdigp29 mdccrmprdigp30 mdccrmprdigp31 mdccrmprdigp32 mdccrmprdigp33 mdccrmprdigp34 mdccrmprdigp34 mdccrmprdigp36";
	
	private static String ivractv="~prdivra mdccrmprdivp01 mdccrmprdivp02 mdccrmprdivp03 mdccrmprdivp04 mdccrmprdivp05 mdccrmprdivp06";
	private static String ivrpsv="~prdivrp mdccrmprdivp01 mdccrmprdivp02 mdccrmprdivp03 mdccrmprdivp04 mdccrmprdivp05 mdccrmprdivp06";
	
	private static String cih="~prdcih mdccrmprdcip01 mdccrmprdcip02 mdccrmprdcip03";
	
	private static String jms="~prdjms1 mdccrmprdjmp01 mdccrmprdjmp02 mdccrmprdjmp03";
	
	private static String bap1="~prdbap1 mdccrmprdbpp01";
	private static String bap2="~prdbap2 mdccrmprdbpp01";
	private static String bap3="~prdbap3 mdccrmprdbpp01";
	private static String bap4="~prdbap4 mdccrmprdbpp02";
	private static String bap5="~prdbap5 mdccrmprdbpp02";
	private static String bap6="~prdbap6 mdccrmprdbpp02";
	
	
	
	private static String user="ffukushi";
	private static String ppk="c:\\prod_key\\ssh_key.ppk";
	private static String plink_path="C:\\Program Files (x86)\\PuTTY\\plink.exe";
	
	private static ArrayList domainLst=new ArrayList();
	
	private HashMap domainMap;
	
	private  String keyword;
	
	private static File outFile=null;
	private static FileWriter fsw=null;
	
	
	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	
	public static void main(String[] args)throws Exception {
		ScanLogs sl=new ScanLogs();
		sl.init();
		sl.setKeyword("com.clarify.webframework.DisplayableException");
		sl.setup();
	}

	public void init(){
	
		domainLst.add(app1);
		domainLst.add(app2);
		domainLst.add(app3);
		domainLst.add(igw1);
		domainLst.add(igw2);
		domainLst.add(igw3);
		domainLst.add(ivractv);
		domainLst.add(ivrpsv);
		domainLst.add(cih);
		domainLst.add(jms);
		domainLst.add(bap1);
		domainLst.add(bap2);
		domainLst.add(bap3);
		domainLst.add(bap4);
		domainLst.add(bap5);
		domainLst.add(bap6);
		
		domainMap=new HashMap<>();
		domainMap.put("app1", "CRM_APP_Domain1/CRM_APP1_Server_1_1/:CRM_APP_Domain1/CRM_APP1_Server_1_2/logs:CRM_APP_Domain1/CRM_APP1_Server_1_3/");
		domainMap.put("app2", "CRM_APP_Domain2/CRM_APP2_Server_11_1/:CRM_APP_Domain2/CRM_APP2_Server_11_2/logs:CRM_APP_Domain2/CRM_APP2_Server_11_3/");
		domainMap.put("app2", "CRM_APP_Domain3/CRM_APP2_Server_21_1/:CRM_APP_Domain2/CRM_APP2_Server_21_2/logs:CRM_APP_Domain2/CRM_APP2_Server_21_3/");
	
//		domainMap.put("igw1", "CRM_IGW_Domain1/CRM_IGW1_Server_1_1/:CRM_IGW_Domain1/CRM_IGW1_Server_1_2/CRM_IGW_Domain1/CRM_IGW1_Server_1_2/");
//		domainMap.put("igw1", "CRM_IGW_Domain1/CRM_IGW1_Server_1_1/:CRM_IGW_Domain1/CRM_IGW1_Server_1_2/CRM_IGW_Domain1/CRM_IGW1_Server_1_2/");
//		domainMap.put("igw1", "CRM_IGW_Domain1/CRM_IGW1_Server_1_1/:CRM_IGW_Domain1/CRM_IGW1_Server_1_2/CRM_IGW_Domain1/CRM_IGW1_Server_1_2/");
	
	}
	
	
	public void setup() throws Exception{
		ArrayList tmplist=new ArrayList<>();
		
		tmplist.add(app1);
		tmplist.add(app2);
		tmplist.add(app3);
		outFile=new File("c:/temp/cksum/scan_log.txt");
		fsw=new FileWriter(outFile);
		tmplist.forEach(S ->{
			try {				
				execFor(S.toString(),"cd ","/JEE/CRMProduct/logs/ClfyLog && grep -i -n "  + getKeyword()+ " clfylog_CRM_APP1*.log");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
		fsw.close();
	}
	
	
	@SuppressWarnings("unchecked")
	public void scanComponent(ArrayList lst) throws Exception{
		outFile=new File("c:/temp/cksum/scan_log.txt");
		fsw=new FileWriter(outFile);	
		
		
		lst.forEach((obj ->{
			ClusterObject objs=(ClusterObject)obj;
			java.util.Arrays.asList(objs.getData()).forEach(new Consumer<Server>() {

				@Override
				public void accept(Server t) {
					Server s=(Server)t;
						String paths[]=s.getPath().split(":");
						for(String path:paths){
							try {
								exec(s.getName(), "cd ", path+  " && grep -i -n "  + getKeyword()+ " " + s.getPattern(),path);
							} catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
				}
			});
		}));
		fsw.close();
	}
	
	@SuppressWarnings("unchecked")
	public void exexCmd4Grp(ArrayList lst, String cmd) throws Exception {
		outFile = new File("c:/temp/cksum/scan_log.txt");
		fsw = new FileWriter(outFile);

		lst.forEach((obj -> {
			ClusterObject objs = (ClusterObject) obj;
			java.util.Arrays.asList(objs.getData()).forEach(new Consumer<Server>() {

				@Override
				public void accept(Server t) {
					Server s = (Server) t;
					String paths[] = s.getPath().split(":");

					try {
						execCommand(s.getName(), cmd);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			});
		}));
		fsw.close();
	}

	public void execCommand(String server,String cmd) throws Exception{
		
		Process process;
		process=Runtime.getRuntime().exec(
		new StringBuffer().append(plink_path).append(" -batch  -i ").append(ppk).append(" ").append(server).append(" -l ").append(user).
		append(" ").append(cmd).toString()
		);
			
		
		 String line;
		  BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
	      int lines=0;  		      
	      System.out.println("scanning" + server);
	      JSONObject jobj=new JSONObject();
	      StringBuffer sb=new StringBuffer();
		  while (true) {
			  line = r.readLine();
			  if (line == null) { break; }
			  	sb.append("\n").append(line);
	       }                  
          jobj.put("server", server);
	        jobj.put("output", sb.toString());	            
	        fsw.write(jobj.toString() +",");		
	}
	
	
	private static synchronized void exec(String server,String command,String cmdParam,String path) throws IOException, JSONException{		
		Process process;
			process=Runtime.getRuntime().exec(
			new StringBuffer().append(plink_path).append(" -batch  -i ").append(ppk).append(" ").append(server).append(" -l ").append(user).
			append(" ").append(command).append(cmdParam).toString()
			);
			 String line;
			  BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
		      int lines=0;  		      
		      System.out.println("scanning" + server);
		      JSONObject jobj=null;
			  while (true) {
				  jobj=new JSONObject();
		            line = r.readLine();
		            System.out.println("Command output=" + line);
		            if (line == null) { break; }
		            String arr[]=line.split(":");
		        System.out.println("Formatted=" + arr[0] + "-" + arr[1] + "-" +arr[2]);
		        jobj.put("server", server);	
		        jobj.put("path", path);
		        jobj.put("line", line);
		        jobj.put("lineNo", arr[0]);
		        
		            fsw.write(jobj.toString() +",");
		        }
//			  fsw.write("\n Total lines in master=" +checkMap.size() + " Compared " + i  );
//			  fsw.write("********************************************************************" );
	
			  
	}
	
	
	private static void execFor(String clsuter,String command,String cmdParam) throws IOException{
		String servers[]= clsuter.split(" ");
		Process process;
		for (int i = 1; i < servers.length; i++) {
			System.out.println("Scanning " + servers[i]);
			System.out.println(			new StringBuffer().append(plink_path).append(" -batch  -i ").append(ppk).append(" ").append(servers[i]).append(" -l ").append(user).
			append(" ").append(command).append(servers[0]).append(cmdParam).toString()
);
			
			process=Runtime.getRuntime().exec(
			new StringBuffer().append(plink_path).append(" -batch  -i ").append(ppk).append(" ").append(servers[i]).append(" -l ").append(user).
			append(" ").append(command).append(servers[0]).append(cmdParam).toString()
			);
			 String line;
			  BufferedReader r = new BufferedReader(new InputStreamReader(process.getInputStream()));
		      int lines=0;  		      
			  while (true) {		        	
		            line = r.readLine();
//		            System.out.println("Command output=" + line);
		            if (line == null) { break; }
		            String arr[]=line.split(":");
		            System.out.println("Formatted=" + arr[0] + "-" + arr[1] + "-" +arr[2]);
		            fsw.write("\n" + line);
		        }
//			  fsw.write("\n Total lines in master=" +checkMap.size() + " Compared " + i  );
//			  fsw.write("********************************************************************" );
		}
	}

}
