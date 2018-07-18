package webspider;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * ÏÂÔØ×ÊÔ´
 * @author zhihu
 *
 */
public class Down {
	public Set<String> set;
	public Down() {
		set=new HashSet<String>();
	}
	public void get_set() {
		for(String it:Get.getUrl()) {
			set.add(it);
		}
	}
	public void print_set() {
		Iterator iter=set.iterator();
		while(iter.hasNext()) {
			System.out.println(iter.next());
		}
	}
}
