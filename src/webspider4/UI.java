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
	private JLabel jl;//搜索
	private JTextField jtf;
	private JPanel jpN,jpE;//放在north的JPanel
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
		jb=new JButton("立即搜索");
		jb_down=new JButton("迅雷下载");
		this.UI_init();
	}
	public static void write(String s) {
		mjt.setFont(new Font("宋体",1,15));
		mjt.setText(s);
	}
	public void show(StringBuilder sb) {
		mjt.setFont(new Font("宋体",1,15));
		mjt.setText(sb.toString());
	}
	
	public static void showUi(StringBuilder sb) {
		mjt.setFont(new Font("宋体",1,15));
		mjt.setText(sb.toString());
	}
	public void UI_init() {
		
		
		//JFrame 的西部
		this.jp.add(mjt);
		jsp.setViewportView(jp);
		
		jsp.setPreferredSize(new Dimension(900,0));//设置边框的宽长
		this.write("正在爬取请稍等。。。");
		this.add(jsp,BorderLayout.WEST);
		
		
		
		
		//JFrame的北部
		this.jl.setText("搜索:");
		jpN.add(jl);
		jtf.setColumns(10);
		jtf.setAutoscrolls(true);
		
		jtf.setEditable(true);
		jtf.setEnabled(true);
		//检测JTextField是否按下回车
		jtf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==jtf) {
					//System.out.println(jtf.getText());//此处可以封装搜索功能函数
					Function.serach(jtf.getText());
				}
			}
			
		});
		//监听搜索按钮
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//System.out.println(jtf.getText());//此处可以封装搜索功能函数
				Function.serach(jtf.getText());
			}
			
		});
		//jb_down 监听
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
		
		
		
		
		//南面的JPanel
	    MyPrintStream prs=new MyPrintStream(System.out,this.mjt_console);
		System.setOut(prs);
		System.setErr(prs);
		mjt_console.setAutoscrolls(true);
		mjt_console.setFont(new Font("宋体",Font.BOLD,15));
		pane.setViewportView(mjt_console);
	    pane.setPreferredSize(new Dimension(0,200));
		this.add(pane, BorderLayout.SOUTH);
		
		
		
		//中部的JPanel
		JTextArea jta_help=new JTextArea();
		jta_help.setBackground(Color.green);
		jta_help.setFont(new Font("楷体",Font.BOLD,15));
		String help="作者：\r\nnoodzhan\r\n"+"存在的bug：\r\n输入时,使用搜狗输入法\r\n会出现卡顿,原因可能是\r\n"
				+ "java swing组件与搜狗\r\n输入法冲突。\r\n说明：\r\n该软件主要用于爬取电影\r\n天堂的影视下载资源。"
				;
		jta_help.setText(help);
		this.add(jta_help, BorderLayout.CENTER);
		
	    
		
		//设置JFrame
		this.setTitle("spider");
		this.setSize(1100, 800);
		this.setIconImage(new ImageIcon("./spider1.jpg").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setFocusableWindowState(true);
		this.setVisible(true);
	}
	
}