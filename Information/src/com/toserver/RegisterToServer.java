package com.toserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

public class RegisterToServer {
	//����Tomcat�����������ڱ��أ�urlΪ����Tomcat�����url��IP��ַΪ��������IP��ַ  
    private String url = "http://115.154.121.154:8080/InformationPushServer/RegisterServlet";  
    //���������صĽ��  
    String result = "";  
    
    /** 
     * ʹ��Post��ʽ��������������󲢷�����Ӧ 
     * @param username ���ݸ���������username 
     * @param password ���ݸ���������password 
     * @return 
     */  
    public String doPost(String username, String hobby, String email, String password, String passwordtwo, String college) throws IOException {  
        HttpClient httpClient = new DefaultHttpClient();  
        HttpPost httpPost = new HttpPost(url);  
        //��username��password����װ��List��  
        NameValuePair param1 = new BasicNameValuePair("username", username);  
        NameValuePair param2 = new BasicNameValuePair("hobby", hobby); 
        NameValuePair param3 = new BasicNameValuePair("email", email); 
        NameValuePair param4 = new BasicNameValuePair("password", password); 
        NameValuePair param5 = new BasicNameValuePair("passwordtwo", passwordtwo); 
        NameValuePair param6 = new BasicNameValuePair("college", college); 
        
        List<NameValuePair> params = new ArrayList<NameValuePair>();  
        params.add(param1);  
        params.add(param2);  
        params.add(param3);  
        params.add(param4);  
        params.add(param5);  
        params.add(param6);  
        //��������װ��HttpEntity�в�����HttpPost����������  
        HttpEntity httpEntity = new UrlEncodedFormEntity(params, "UTF-8");
        httpPost.setEntity(httpEntity);  
  
        HttpResponse httpResponse = httpClient.execute(httpPost);  
        //�����Ӧ�ɹ�  
        if(httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK){  
            //�õ���Ϣ��  
            HttpEntity entity = httpResponse.getEntity();  
            InputStream inputStream = entity.getContent();  
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));  
            String readLine = null;  
            while((readLine = br.readLine()) != null){  
                result += readLine;  
            }  
            inputStream.close();  
            return result;  
        }  
        //��Ӧʧ��  
        else{  
            return "Error";  
        }  
    }  
}
