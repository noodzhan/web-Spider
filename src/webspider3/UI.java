package webspider3;

import java.awt.BorderLayout;
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
	private MJTextArea mjt;
	private JScrollPane jsp;
	private JLabel jl;//搜索
	private JTextField jtf;
	private JPanel jpN;//放在north的JPanel
	private JButton jb;
	private JButton jb_down;
	public UI() {
		jp=new JPanel();
		jpN=new JPanel();
		mjt=new MJTextArea();
		jsp=new JScrollPane();
		jl=new JLabel();
		jtf=new JTextField();
		jb=new JButton("立即搜索");
		jb_down=new JButton("迅雷下载");
		this.UI_init();
		
	}
	public void write(String s) {
		mjt.setFont(new Font("宋体",1,15));
		mjt.setText(s);
	}
	public void show(StringBuilder sb) {
		mjt.setFont(new Font("宋体",1,15));
		mjt.setText(sb.toString());
	}

	public void UI_init() {
		
		this.jl.setText("搜索:");
		this.jp.add(mjt);
		jsp.setViewportView(jp);
		//jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER );
		
		jsp.setPreferredSize(new Dimension(900,31));//设置边框的宽长
		//System.out.println(jsp.getPreferredSize());
		
		//写入文字
		/*StringBuilder s=new StringBuilder("ftp://ygdy8:ygdy8@yg45.dydytt.net:7209/阳光电影www.ygdy8.com.窃画行动.HD.720p.中文字幕.mkv");
		for(int i=0;i<100;i++) {
			s.append("\r\n");
			s.append("ftp://ygdy8:ygdy8@yg45.dydytt.net:7209/阳光电影www.ygdy8.com.窃画行动.HD.720p.中文字幕.mkv");
		}
		this.write(s.toString());
		*/
		
		
		this.getContentPane().add(jsp,BorderLayout.WEST);
		
		
		
		
		
	
		jpN.add(jl);
		jtf.setColumns(10);
		jtf.setAutoscrolls(true);
		jtf.setEditable(true);
		jtf.setEnabled(true);
		jtf.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getSource()==jtf) {
					System.out.println(jtf.getText());//此处可以封装搜索功能函数
				}
			}
			
		});
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.out.println(jtf.getText());//此处可以封装搜索功能函数
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
		
		//设置JFrame
		this.setTitle("spider");
		this.setSize(1300, 800);
		this.setIconImage(new ImageIcon("./spider1.jpg").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setFocusableWindowState(true);
		this.setVisible(true);
	}
	
}










class MJTextArea extends JTextArea implements MouseListener {
	 
	private JPopupMenu pop = null; // 弹出菜单
	private JMenuItem copy = null, paste = null, cut = null; // 三个功能菜单
 
	public MJTextArea() {
		super();
		init();
	}
	public MJTextArea(Document doc) {
		super(doc);
		init();
	}
	public MJTextArea(int rows, int columns){
		super(rows, columns);
		init();
	}
	public MJTextArea(String text){
		super(text);
		init();
	}
	public MJTextArea(String text, int rows, int columns){
		super(text, rows, columns);
		init();
	}
	public MJTextArea(Document doc, String text, int rows, int columns){
		super(doc, text, rows, columns);
		init();
	}
	
	private void init() {
		this.addMouseListener(this);
		pop = new JPopupMenu();
		pop.add(copy = new JMenuItem("复制"));
		pop.add(paste = new JMenuItem("粘贴"));
		pop.add(cut = new JMenuItem("剪切"));
		copy.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_MASK));
		paste.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_MASK));
		cut.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_MASK));
		copy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action(e);
			}
		});
		paste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action(e);
			}
		});
		cut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				action(e);
			}
		});
		this.add(pop);
	}
 
	public void action(ActionEvent e) {
		String str = e.getActionCommand();
		if (str.equals(copy.getText())) { // 复制
			this.copy();
		} else if (str.equals(paste.getText())) { // 粘贴
			this.paste();
		} else if (str.equals(cut.getText())) { // 剪切
			this.cut();
		}
	}
 
	public JPopupMenu getPop() {
		return pop;
	}
 
	public void setPop(JPopupMenu pop) {
		this.pop = pop;
	}
 
	/**
	 * 剪切板中是否有文本数据可供粘贴
	 * 
	 * @return true为有文本数据
	 */
	public boolean pastable() {
		boolean b = false;
		Clipboard clipboard = this.getToolkit().getSystemClipboard();
		Transferable content = clipboard.getContents(this);
		try {
			if (content.getTransferData(DataFlavor.stringFlavor) instanceof String) {
				b = true;
			}
		} catch (Exception e) {
		}
		return b;
	}
 
	/**
	 * 文本组件中是否具备复制的条件
	 * 
	 * @return true为具备
	 */
	public boolean copyable() {
		boolean b = false;
		int start = this.getSelectionStart();
		int end = this.getSelectionEnd();
		if (start != end)
			b = true;
		return b;
	}
 
	public void mouseClicked(MouseEvent e) {
	}
 
	public void mouseEntered(MouseEvent e) {
	}
 
	public void mouseExited(MouseEvent e) {
	}
 
	public void mousePressed(MouseEvent e) {
		if (e.getButton() == MouseEvent.BUTTON3) {
			copy.setEnabled(copyable());
			paste.setEnabled(pastable());
			cut.setEnabled(copyable());
			pop.show(this, e.getX(), e.getY());
		}
	}
 
	public void mouseReleased(MouseEvent e) {
	}
 
}