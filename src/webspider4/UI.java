package webspider4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.text.Document;


public class UI extends JFrame {
	private JPanel jp;
	private static MJTextArea mjt,mjt_console;
	private JScrollPane jsp,pane;
	private JLabel jl;//����
	private JTextField jtf;
	private JPanel jpN,jpE;//����north��JPanel
	private JButton jb,jb_down;
	static {
		mjt=new MJTextArea();
		mjt_console=new MJTextArea();
	}
	public UI() {
		jp=new JPanel();
		jpN=new JPanel();
		jpE=new JPanel();
		//mjt=new MJTextArea();
		jsp=new JScrollPane();
		pane=new JScrollPane();
		jl=new JLabel();
		jtf=new JTextField();
		jb=new JButton("��������");
		jb_down=new JButton("Ѹ������");
		this.UI_init();
	}
	public static void write(String s) {
		mjt.setFont(new Font("����",1,15));
		mjt.setText(s);
	}
	public void show(StringBuilder sb) {
		mjt.setFont(new Font("����",1,15));
		mjt.setText(sb.toString());
	}
	
	public static void showUi(StringBuilder sb) {
		mjt.setFont(new Font("����",1,15));
		mjt.setText(sb.toString());
	}
	public void UI_init() {
		
		
		//JFrame ������
		this.jp.add(mjt);
		jsp.setViewportView(jp);
		
		jsp.setPreferredSize(new Dimension(900,0));//���ñ߿�Ŀ�
		this.write("������ȡ���Եȡ�����");
		this.add(jsp,BorderLayout.WEST);
		
		
		
		
		//JFrame�ı���
		this.jl.setText("����:");
		jpN.add(jl);
		jtf.setColumns(10);
		jtf.setAutoscrolls(true);
		
		jtf.setEditable(true);
		jtf.setEnabled(true);
		//���JTextField�Ƿ��»س�
		jtf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==jtf) {
					//System.out.println(jtf.getText());//�˴����Է�װ�������ܺ���
					Function.serach(jtf.getText());
				}
			}
			
		});
		//����������ť
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//System.out.println(jtf.getText());//�˴����Է�װ�������ܺ���
				Function.serach(jtf.getText());
			}
			
		});
		//jb_down ����
		jb_down.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(e.getSource()==jb_down) {
					Function.open_thunder();
				}
			}
			
		});
		jpN.add(jtf);
		jpN.add(jb);
		jpN.add(jb_down);
		jpN.setLayout(new FlowLayout(FlowLayout.LEFT,20,10));
		this.add(jpN,BorderLayout.NORTH);
		
		
		
		
		//�����JPanel
	    MyPrintStream prs=new MyPrintStream(System.out,this.mjt_console);
		System.setOut(prs);
		System.setErr(prs);
		mjt_console.setAutoscrolls(true);
		mjt_console.setFont(new Font("����",Font.BOLD,15));
		pane.setViewportView(mjt_console);
	    pane.setPreferredSize(new Dimension(0,200));
		this.add(pane, BorderLayout.SOUTH);
		
		
		
		//�в���JPanel
		JTextArea jta_help=new JTextArea();
		jta_help.setBackground(Color.green);
		jta_help.setFont(new Font("����",Font.BOLD,15));
		String help="���ߣ�\r\nnoodzhan\r\n"+"���ڵ�bug��\r\n����ʱ,ʹ���ѹ����뷨\r\n����ֿ���,ԭ�������\r\n"
				+ "java swing������ѹ�\r\n���뷨��ͻ��\r\n˵����\r\n�������Ҫ������ȡ��Ӱ\r\n���õ�Ӱ��������Դ��"
				;
		jta_help.setText(help);
		this.add(jta_help, BorderLayout.CENTER);
		
	    
		
		//����JFrame
		this.setTitle("spider");
		this.setSize(1100, 800);
		this.setIconImage(new ImageIcon("./spider1.jpg").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setFocusableWindowState(true);
		this.setVisible(true);
	}
	
}