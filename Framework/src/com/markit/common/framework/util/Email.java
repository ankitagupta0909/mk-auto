package com.markit.common.framework.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.TreeMap;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.xml.sax.SAXException;

import atu.testng.reports.logging.LogAs;

/**
 * This class sends email notification 
 * with attachment of manual &automation
 * mapping sheet and path of HTML report
 *
 */
public class Email {

	public static final Logger log = Logger.getLogger("appLogger");
	public static Map<String,String> mailConfig;
	
	public static void initMailConfig(String filePath){
		mailConfig=Config.loadConfig(filePath);
	}

	/** sends email notification to users
	 * @param subject
	 * @param fileName
	 * @throws IOException
	 */
	public static void emailNotification(String subject, String fileName)
			throws IOException {

		String from = mailConfig.get("Mail.from");
		String host = mailConfig.get("Mail.host");
		String to = mailConfig.get("Mail.to");
		String port=mailConfig.get("Mail.port");
		String propTrue=mailConfig.get("Mail.propertytrue");
		String propFalse=mailConfig.get("Mail.propertyfalse");				
		String filename =mailConfig.get("Mail.fileName");

		String[] recipientList = to.split(",");
		InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
		int counter = 0;
		for (String recipient : recipientList) {
			try {
				recipientAddress[counter] = new InternetAddress(
						recipient.trim());
			} catch (AddressException e) {
				e.printStackTrace();
			}
			counter++;
		}

		// Get system properties
		Properties properties = System.getProperties();
		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("java.net.preferIPv4Stack", propTrue);
		properties.setProperty("mail.smtp.socketFactory.port", port);
		properties.setProperty("mail.smtp.auth", propFalse);

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties);
		try {
			String status=readMethodTestStatus();
			String getPath = returnResultFolderLocation();

			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO, recipientAddress);
			message.setSubject(status+"||"+subject + "| Automation Status Report");

			BodyPart messageBodyPart = new MimeBodyPart();
			messageBodyPart
			.setText("Please find the attached file for test result and "
					+ "refer this path on machine: "
					+ getPath
					+ " to view the Automation HTML report");

			// Create a multipart message with text and attachment
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			messageBodyPart = new MimeBodyPart();
			String s = System.getProperty("user.dir")
					+ "\\src\\testdata\\"+fileName;
			String filePathMapping = s.replace("\\", "\\\\");			
			addAttachment(multipart, filePathMapping, filename);

			// Send the complete message parts
			message.setContent(multipart);
			Transport.send(message);
			log.info("Sent message successfully....");
		} 
		catch (MessagingException mex) {
			mex.printStackTrace();
			log.error("Unable to send Email post execution");
		}
	}

	/** adds attachment to email
	 * @param multipart
	 * @param filepath
	 * @param filename
	 * @throws MessagingException
	 */
	private static void addAttachment(Multipart multipart, String filepath,
			String filename) throws MessagingException {
		DataSource source = new FileDataSource(filepath);
		BodyPart messageBodyPart = new MimeBodyPart();
		messageBodyPart.setDataHandler(new DataHandler(source));
		messageBodyPart.setFileName(filename);
		multipart.addBodyPart(messageBodyPart);
	}

	/** return results folder location
	 * @return
	 * @throws IOException
	 */
	public static String returnResultFolderLocation() throws IOException {
		
		String strRes=System.getProperty("user.dir")+"\\Results\\Reporter\\Results";
	//	String strRes="C:\\CommonAutoFramework\\Framework\\Results\\Reporter\\Results";
				
		String filePath= strRes.replace("\\", "\\\\");
		File file = new File(filePath);

		Map<Long,String> fileDatesMap=new TreeMap<Long,String>(Collections.reverseOrder());
		File[] files = file.listFiles();
		for ( File eachfile : files) {
			if (eachfile.isDirectory()) {
				fileDatesMap.put(eachfile.lastModified(),eachfile.getName());

			}}
		String dirName=null;
		Map.Entry<Long,String> entry=fileDatesMap.entrySet().stream().findFirst().get();
		System.out.println(entry.getValue());
		dirName= entry.getValue();

		String computername=InetAddress.getLocalHost().getHostName();
		String resultDir=computername+":Path-"+strRes+"\\"+dirName+"\\CurrentRun.html";
		log.info("Result Dir:"+resultDir);
		return resultDir;

	}

	/**Return the name of Excel Report Generated
	 * @param resultDir
	 * @return
	 */
	public  String excelReportName(String resultDir){	
		String filePath= resultDir.replace("\\", "\\\\");
		String value=null;
		File file = new File(filePath);	
		File[] files = file.listFiles();
		for ( File eachfile : files) {
		  if (eachfile.getName().endsWith(".xlsx")){
		   value=eachfile.getName();
		   System.out.println(eachfile.getName());
		  }
		}
		return value;
		}	

	/** Return automation method Status from excel
	 * @return
	 */
	public static String readMethodTestStatus() {

		try{
			FileInputStream fis = new FileInputStream(new File("src/testdata/testWorkbook.xlsx"));
			XSSFWorkbook book=new XSSFWorkbook(fis);
			XSSFSheet sheet=book.getSheet("MethodTestStatus");
			int	rowIndex=sheet.getPhysicalNumberOfRows();

			for(int i=1;i<=rowIndex-1;i++){
				XSSFRow row=sheet.getRow(i);
				XSSFCell cell_status=row.getCell(1);
				String statusVal=cell_status.getStringCellValue();
				if((statusVal.equalsIgnoreCase("Fail"))||(statusVal.equalsIgnoreCase("fail"))){
					fis.close();
					book.close();
					return "Failed";
				}
			}
			fis.close();
			book.close();
			return "Passed";
		}
		catch(Exception e){
			e.printStackTrace();
			log.error("Unable to read method status in Email Class"+e.getMessage());
			return null;
		}

	}
}
