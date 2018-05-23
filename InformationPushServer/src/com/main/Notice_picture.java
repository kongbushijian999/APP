package com.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

import com.bean.Notice;

public class Notice_picture {
	
	/*public void download(Notice e) throws IOException  {  
        String url=e.getImg_url();  
        //图片链接有的没  
            if(url=="null"){  
                url="https://ws2.sinaimg.cn/large/6c7edb3fly1fguvf22hznj215o0k67h5.jpg";  
            }  
            Connection conn=Jsoup.connect(url);  
            Response rs=conn.ignoreContentType(true).timeout(3000).ignoreHttpErrors(true).execute();  
            //存放图片的数组  
            byte b[]=rs.bodyAsBytes();  
            File file = new File("D:/Notice_Picture",e.getTitle().replace(":", "")+".jpg");  
            if (!file.exists()) {  
                FileOutputStream raf = new FileOutputStream(file);  
                raf.write(b);  
                raf.close();  
            }  
    }  */

}
