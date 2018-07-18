package webspider3;

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
public class Analyse{

	public static final String down_reg="ftp://[^\\s\"]*.m[kp][v4]";
	
	public boolean is_running=true;
	
	public static int allftp=0;
	
	public static int alltime=0;
	
	//���������Դ��
	public Down down;
	
	
	//���Է�װ��������
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
			//�����̵߳�����
			int ThreadSum=len/10;
			int leave;
			if(len%10==0) {
				leave=0;
				for(int i=0;i<ThreadSum;i++) {
					//System.out.println("������"+(i+1)+"�߳�");
					new Thread(new GetAll(i*10+i,(i+1)*10)).start();
				}
			}else {
				//System.out.println("ThreadSum= "+ThreadSum);
				//System.out.println("����");
				leave=len%10;
				for(int i=0;i<ThreadSum;i++) {
					//System.out.println("������"+i+"�߳�");
					//System.out.println((i*10+1+"    "+(i+1)*10));
					new Thread(new GetAll(i*10+1,(i+1)*10)).start();
				}
				//System.out.println("������"+ThreadSum+"�߳�");
				//System.out.println(ThreadSum*10+1+"   "+len);
				new Thread(new GetAll(ThreadSum*10+1,len)).start();
			}
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
			//System.exit(0);
			
		}
		return visit_url;
		
	}
	
	/**
	 * ��ȡ���е���Դ
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
			System.out.println("endС��start����ʽ����");
			System.exit(0);
		}
		return set;
	}
	
	
	
	/**
	 * ����URL�ҵ�������Դ���ҵ�����Դ��
	 * @param s
	 * @return
	 */
	public String get_downUrl(String s) {
		Get get_visit=new Get(down_reg,s);
		if(get_visit.getUrl().size()==0) {
			System.out.println("û���ҵ�ftp��Դ");
			return null;
		}
		else{
			String re=get_visit.getUrl().get(0);
			//System.out.println(re);
			return re;
		}
	}
	/*
	 *�ڲ��࣬ʵ���̣߳�������ÿ10��URL����һ���̣߳������ٺ���
	 *�����ȡһ�δ��9s
	 */
	class GetAll  implements Runnable{
		public boolean isRunning=true;
		public int start;//�������URL����List����ʼλ
		public int end;////�������URL����List����ֹλ
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
				System.out.println("��ȡ�ķѵ�ʱ��"+(end.getTime()-start.getTime())/1000+"s");
				Analyse.alltime+=(end.getTime()-start.getTime())/1000;
				System.out.println("��������Դ�ܸ��� "+all.size());
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
