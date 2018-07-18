package webspider3;

import java.io.IOException;

public class Function {
	public static void open_thunder() {
		Process pro=null;
		try {
			pro = Runtime.getRuntime().exec("G:\\thunder\\Program\\ThunderStart.exe");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			//e1.printStackTrace();
			System.out.println("ÄúÉÐÎ´°²×°Ñ¸À×");
		}
        new Thread(new Runnable(){
           public void run(){
            try {
                Thread.sleep(5000);
            } catch (Exception e) {}
			//pro.destroy();
            System.out.print("test\n");
           }
        }).start();
        int exitcode=0;
		try {
			exitcode = pro.waitFor();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       System.out.println("finish:"+exitcode);
	}
	
}

