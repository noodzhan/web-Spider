package webspider;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ������Դ
 * @author zhihu
 *
 */
public class Analyse {

	public static final String down_reg="ftp://[^\\s\"]*.mkv";
	
	
	
	public Analyse(String s) {
		
		String ss=get_downUrl(build_down_url(get_like(s)));
		System.out.println(ss);
	}
	
	public Analyse() {
		Set<String> all=get_allDownUrl();
		System.out.println(all.size());
		for(String htp:all) {
			System.out.println(htp);
		}
	}
	
	
	
	/**
	 * �����Ƿ���key��URL
	 * @param s
	 * @return
	 */
	public  String get_like(String s) {
		List <String> temp=Get.getUrl();
		String re = null;
		for(String sm:temp) {
			if(sm.contains(s)) {
				re=sm;
			}
		}
		if(null==re) {
			System.out.println("û���ҵ�������Ҫ����Դ");
			System.exit(0);
		}
		return re;
	}
	
	
	/**
	 * ��ȡ����������Դ��URl
	 * @param url
	 * @return
	 */
	public String build_down_url(String url) {
		String re=null;
		String visit_url=null;
		Pattern p=Pattern.compile("html[^\\s]*.html");
		Matcher m=p.matcher(url);
		while(m.find()) {
			visit_url=new StringBuilder(Get.getWebhead()).append(m.group()).toString();
			//System.out.println(visit_url);
		}
		if(null==visit_url) {
			System.out.println("û�д˵�ӰѸ����Դ");
			System.exit(0);
			
		}
		return visit_url;
		
	}
	
	/**
	 * ��ȡ���е���Դ
	 */
	public Set<String> get_allDownUrl(){
		List <String> re=Get.getUrl();
		System.out.println(re.size());
		Set <String> res=new HashSet<String>();
		//Date start=new Date();
		//System.out.println(start);
		for(int i=0;i<10;i++) {
			String downUrl=get_downUrl(build_down_url(re.get(i)));
			if(null!=downUrl)
				res.add(get_downUrl(build_down_url(re.get(i))));
		}
		return res;
	}
	
	
	
	/**
	 * ����URL�ҵ�������Դ���ҵ�����Դ��
	 * @param s
	 * @return
	 */
	public String get_downUrl(String s) {
		Get get_visit=new Get(down_reg,s);
		if(get_visit.getUrl().size()==0) {
			return null;
		}
		else{
			String re=get_visit.getUrl().get(0);
			//System.out.println(re);
			return re;
		}
	}
	
}
