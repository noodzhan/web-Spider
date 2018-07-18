package webspider3;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 分析资源
 * @author zhihu
 *
 */
public class Analyse{

	public static final String down_reg="ftp://[^\\s\"]*.m[kp][v4]";
	
	public boolean is_running=true;
	
	public static int allftp=0;
	
	public static int alltime=0;
	
	//存放下载资源的
	public Down down;
	
	
	//可以封装搜索功能
	public Analyse(String s) {
		
		String ss=get_downUrl(build_down_url(get_like(s)));
		//System.out.println(ss);
	}
	
	public Analyse() {
		down=new Down();
		this.allocationThread();
	}
	
	protected void allocationThread() {
		int len=Get.getUrl().size();
		//System.out.println(len);
		//len=16;
		if(len<=10) {
			new Thread(new GetAll(0,len)).start();
		}else {
			//分析线程的数量
			int ThreadSum=len/10;
			int leave;
			if(len%10==0) {
				leave=0;
				for(int i=0;i<ThreadSum;i++) {
					//System.out.println("开启第"+(i+1)+"线程");
					new Thread(new GetAll(i*10+i,(i+1)*10)).start();
				}
			}else {
				//System.out.println("ThreadSum= "+ThreadSum);
				//System.out.println("草拟");
				leave=len%10;
				for(int i=0;i<ThreadSum;i++) {
					//System.out.println("开启第"+i+"线程");
					//System.out.println((i*10+1+"    "+(i+1)*10));
					new Thread(new GetAll(i*10+1,(i+1)*10)).start();
				}
				//System.out.println("开启第"+ThreadSum+"线程");
				//System.out.println(ThreadSum*10+1+"   "+len);
				new Thread(new GetAll(ThreadSum*10+1,len)).start();
			}
		}
	}
	
	/**
	 * 分析是否含有key的URL
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
			System.out.println("没有找到你所需要的资源");
			System.exit(0);
		}
		return re;
	}
	
	
	/**
	 * 获取含有下载资源的URl
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
			System.out.println("没有此电影迅雷资源");
			//System.exit(0);
			
		}
		return visit_url;
		
	}
	
	/**
	 * 获取所有的资源
	 */
	public Set<String> get_allDownUrl(int start,int end){
		List <String> re=Get.getUrl();
		//System.out.println(re.size());
		Set <String> set=new HashSet<String>();
		if(end>start) {
			for(int i=start;i<end;i++) {
				//System.out.println(i);
				String downUrl=get_downUrl(build_down_url(re.get(i)));
				if(null!=downUrl)
					set.add(downUrl);
			}
		}else if(end==start) {
			String downUrl=get_downUrl(build_down_url(re.get(start)));
			if(null!=downUrl)
				set.add(downUrl);
		}else {
			System.out.println("end小于start，格式错误");
			System.exit(0);
		}
		return set;
	}
	
	
	
	/**
	 * 分析URL找到下载资源（找到的资源）
	 * @param s
	 * @return
	 */
	public String get_downUrl(String s) {
		Get get_visit=new Get(down_reg,s);
		if(get_visit.getUrl().size()==0) {
			System.out.println("没有找到ftp资源");
			return null;
		}
		else{
			String re=get_visit.getUrl().get(0);
			//System.out.println(re);
			return re;
		}
	}
	/*
	 *内部类，实现线程，具体是每10个URL开启一个线程，若网速合理，
	 *大概爬取一次大概9s
	 */
	class GetAll  implements Runnable{
		public boolean isRunning=true;
		public int start;//检索存放URL容器List的起始位
		public int end;////检索存放URL容器List的终止位
		public GetAll(int start,int end) {
			this.start=start;
			this.end=end;
		}
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			while(isRunning) {
				Date start=new Date();
				Set<String> all=get_allDownUrl(this.start,this.end);
				down.getSet(all);
				//System.out.println(down.set.size());
				
				/*Date end=new Date();
				System.out.println("爬取耗费的时间"+(end.getTime()-start.getTime())/1000+"s");
				Analyse.alltime+=(end.getTime()-start.getTime())/1000;
				System.out.println("爬到的资源总个数 "+all.size());
				Analyse.allftp+=all.size();
				for(String htp:all) {
					System.out.println("all  "+htp);
				}*/
				//break;
				isRunning=false;
			}
		}
		
	}
}
