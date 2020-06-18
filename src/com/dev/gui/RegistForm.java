package com.dev.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.dev.model.board.NoticeDAO;
import com.dev.model.notice.Notice;

public class RegistForm extends JFrame{
	JTextField t_title;
	JTextField t_writer;
	JTextArea area;
	JButton bt;
	NoticeDAO noticeDAO;
	
	public RegistForm() {
		t_title = new JTextField();
		t_writer = new JTextField();
		area = new JTextArea();
		bt = new JButton("게시물 등록");
		noticeDAO = new NoticeDAO();
		
		//style
		t_title.setPreferredSize(new Dimension(200,25));
		t_writer.setPreferredSize(new Dimension(200,30));
		area.setPreferredSize(new Dimension(200,220));
		
		//부착
		setLayout(new FlowLayout());
		add(t_title);
		add(t_writer);
		add(area);
		add(bt);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setSize(250,400);
		setLocationRelativeTo(null);
		
		bt.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { 
				regist();
			}
		});
	}
	public void regist() {
		//CRUD를 담당하는 DAO 공용객체를 사용하자!!
		Notice notice = new Notice();
		notice.setTitle(t_title.getText());
		notice.setWriter(t_writer.getText());
		notice.setContent(area.getText());
		
		int result = noticeDAO.insert(notice);
		
		if(result!=0) {
			JOptionPane.showMessageDialog(this, "등록성공");
		}else {
			JOptionPane.showMessageDialog(this, "등록실패");
		}
	}
	
	public static void main(String[] args) {
		new RegistForm();
	}
}
