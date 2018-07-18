package webspider;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ��ȡ��Դ��url
 * @author zhihu
 *
 */
public class Get {
	private static List<String> Url;
	private static List<String> Res;
	//������ҳ����������
	private  String regular="<a href='/html/[^\\s]*</a>";
	private  String web;
	private final static String webhead="http://www.dytt8.net/";

	static {
		
		Url=new ArrayList<String>();
		Res=new ArrayList<String>();
	}

	
	public Get() {
		
		try {
			web="http://www.dytt8.net/";
			Url=geturl(gethtml(web));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Get(String reg,String web) {
		this.web=web;
		this.regular=reg;
		Url=geturl(gethtml(web));
		
	}

	//��ȡHTML����
    private String gethtml(String url) {
        URL url1=null;
		try {
			url1 = new URL(url);
		} catch (MalformedURLException e) {
			System.out.println("�޷�������");
			System.exit(0);
		}
        URLConnection connection=null;
        InputStream in=null;
		try {
			connection=url1.openConnection();
			in = connection.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        InputStreamReader isr=new InputStreamReader(in);
        BufferedReader br=new BufferedReader(isr);

        String line=null;
        StringBuffer sb=new StringBuffer();
        try {
			while((line=br.readLine())!=null){
			    sb.append(line,0,line.length());
			    sb.append('\n');
			}
			br.close();
		    isr.close();
		    in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
       
        return sb.toString();
        
    }
	
	
	
	 //��ȡ���ص�Ӱ��·����ַ
    private List<String> geturl(String html){
        Matcher matcher=Pattern.compile(regular).matcher(html);
        List<String>listimgurl=new ArrayList<String>();
        while (matcher.find()){
            listimgurl.add(matcher.group());
            //System.out.println(matcher.group());
        }
        return listimgurl;
    }



	public static List<String> getUrl() {
		return Url;
	}
	
	public static List<String> getRes() {
		return Res;
	}
	public  String getWeb() {
		return web;
	}

	public  String getRegular() {
		return regular;
	}
	public static String getWebhead() {
		return webhead;
	}

 
}
