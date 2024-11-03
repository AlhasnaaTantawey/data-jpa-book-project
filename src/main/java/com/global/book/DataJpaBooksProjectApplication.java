package com.global.book;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SpringBootApplication
public class DataJpaBooksProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataJpaBooksProjectApplication.class, args);
		JButton button = new JButton("Click Me");

		// Using an anonymous class to implement ActionListener
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("Button clicked!");
			}
		});
	}

}
