package webspider4;

public class Main {
	public static void main(String args[]) {
		// TODO Auto-generated constructor stub
		
		
		
		
		UI ui=new UI();
		
		
		Get get =new Get();
		//Analyse a =new Analyse("�Խ��");
		Analyse a =new Analyse();
		try {
			Thread.sleep(9000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//�ȴ��߳̽���������ʱ��
		System.out.println("��Դ�ܸ���"+a.down.set.size());
		//a.down.print_set();
	
		StringBuilder sb=new StringBuilder();
		for(String item:a.down.set) {
			item+="\r\n";
			sb.append(item);
		}
		ui.show(sb);
	}
}
