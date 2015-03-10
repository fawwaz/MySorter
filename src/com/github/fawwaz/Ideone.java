package com.github.fawwaz;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.GridLayout;

import javax.swing.JLabel;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;

import java.awt.FlowLayout;

import javax.swing.JScrollPane;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuItem;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class Ideone {

	private JFrame frame;
	private JTextArea textArea,textArea_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ideone window = new Ideone();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Ideone() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JButton btnSortIt = new JButton("Sort it !");
		btnSortIt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doSorting();
			}
		});
		frame.getContentPane().add(btnSortIt, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		
		JLabel lblInput = new JLabel("Input :");
		scrollPane.setColumnHeaderView(lblInput);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		panel.add(scrollPane_1);
		
		textArea_1 = new JTextArea();
		scrollPane_1.setViewportView(textArea_1);
		
		JLabel lblOutput = new JLabel("Output :");
		scrollPane_1.setColumnHeaderView(lblOutput);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnAbout = new JMenu("About");
		menuBar.add(mnAbout);
		
		JMenuItem mntmHowToUse = new JMenuItem("How To Use");
		mntmHowToUse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				showHowToUseDialog();
			}
		});
		mnAbout.add(mntmHowToUse);
		
		JMenuItem mntmInfo = new JMenuItem("Info");
		mntmInfo.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				showInfoDialog();
			}
		});
		mnAbout.add(mntmInfo);
	}
	
	private void showHowToUseDialog(){
		JOptionPane.showMessageDialog(frame, "You should put the unsorted string on the input field.\nMake sure every item separated by a newline \"\\n\".\nThen press the \"Sort it\" button on the bottom of the window","How To Use",JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void showInfoDialog(){
		JOptionPane.showMessageDialog(frame, "Created by Fawwaz Muhammad","Info",JOptionPane.INFORMATION_MESSAGE);
	}
	
	private void doSorting(){
		ArrayList<String> inputs = new ArrayList<String>(Arrays.asList(textArea.getText().split("\n")));
		
		ArrayList<Word> words = new ArrayList<>();
		for (int i = 0; i < inputs.size(); i++) {
			words.add(new Word(inputs.get(i)));
		}
		
		Collections.sort(words,Word.WordComparator);
		
		String output = "";
		for (int i = 0; i < words.size(); i++) {
			output = output + words.get(i) + "\n";
		}
		
		textArea_1.setText(output);
		//MySorter sorter = new MySorter(words);
		//sorter.doSorting();
		
	}
}
