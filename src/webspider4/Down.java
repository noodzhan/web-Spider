package webspider4;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * 下载资源
 * @author zhihu
 *
 */
public class Down {
	public volatile Set<String> set;//存储下载资源
	public Down() {
		set=new HashSet<String>();
	}
	public void get_set() {
		for(String it:Get.getUrl()) {
			set.add(it);
		}
	}
	public void print_set() {
		if(set.size()==0) {
			System.out.println("set is null");
			return ;
		}
		Iterator iter=set.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
	public void getSet(Set<String> s) {
		for(String item : s) {
			set.add(item);
		}
	}
}
