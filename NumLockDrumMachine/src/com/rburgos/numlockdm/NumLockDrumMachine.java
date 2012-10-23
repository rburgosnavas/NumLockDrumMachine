package com.rburgos.numlockdm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class NumLockDrumMachine extends JFrame
{

	private JLabel title;
	private JButton kickBtn, clapBtn, clHatBtn, opHatBtn, claveBtn, sweepXBtn,
			sweepYBtn, mod01Btn, mod02Btn;
	private JPanel buttonPanel;
	private Color lightSkyBlue, darkKhaki, bisque;
	
	AudioSample kick, clap, chat, ohat, clave, sweepx, sweepy, mod01, mod02;
	
	Timer delay;

	public NumLockDrumMachine() throws LineUnavailableException,
			UnsupportedAudioFileException, IOException
	{
		init();
	}

	public void init() throws LineUnavailableException,
			UnsupportedAudioFileException, IOException
	{
		lightSkyBlue = new Color(135, 206, 250);
		darkKhaki = new Color(189, 183, 107);
		bisque = new Color(205, 183, 158);

		addKeyListener(new KeyTrigger());

		initSoundClips();

		initButtons();

		buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(3, 3));
		buttonPanel.setPreferredSize(new Dimension(300, 300));
		buttonPanel.add(sweepYBtn);
		buttonPanel.add(mod01Btn);
		buttonPanel.add(mod02Btn);
		buttonPanel.add(opHatBtn);
		buttonPanel.add(claveBtn);
		buttonPanel.add(sweepXBtn);
		buttonPanel.add(kickBtn);
		buttonPanel.add(clapBtn);
		buttonPanel.add(clHatBtn);

		add(buttonPanel);

		title = new JLabel("//");
		title.setPreferredSize(new Dimension(-1, 22));
		add(title, BorderLayout.SOUTH);
		
		delay = new Timer(5000, new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				title.setText("//");
			}
		});

		/**** JFrame settings ****/
		setBackground(Color.lightGray);
		setSize(300, 320);
		setTitle(">> numLockDrumMachine <<");
		setLocationRelativeTo(null);
		setAlwaysOnTop(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		pack();
	}

	private void initButtons()
	{
		kickBtn = new JButton("1");
		kickBtn.setBackground(bisque);
		kickBtn.setFocusable(false);

		clapBtn = new JButton("2");
		clapBtn.setBackground(bisque);
		clapBtn.setFocusable(false);

		clHatBtn = new JButton("3");
		clHatBtn.setBackground(Color.lightGray);
		clHatBtn.setFocusable(false);

		opHatBtn = new JButton("4");
		opHatBtn.setBackground(Color.lightGray);
		opHatBtn.setFocusable(false);

		claveBtn = new JButton("5");
		claveBtn.setBackground(Color.gray);
		claveBtn.setFocusable(false);

		sweepXBtn = new JButton("6");
		sweepXBtn.setBackground(lightSkyBlue);
		sweepXBtn.setFocusable(false);

		sweepYBtn = new JButton("7");
		sweepYBtn.setBackground(lightSkyBlue);
		sweepYBtn.setFocusable(false);

		mod01Btn = new JButton("8");
		mod01Btn.setBackground(darkKhaki);
		mod01Btn.setFocusable(false);

		mod02Btn = new JButton("9");
		mod02Btn.setBackground(darkKhaki);
		mod02Btn.setFocusable(false);
	}

	private void initSoundClips() throws LineUnavailableException,
			UnsupportedAudioFileException, IOException
	{
		kick = new AudioSample("sounds/kick_mid.au");
		clap = new AudioSample("sounds/clap_lo.au");
		chat = new AudioSample("sounds/hat_cl.au");
		ohat = new AudioSample("sounds/hat_op.au");
		clave = new AudioSample("sounds/metal_clave.au");
		sweepx = new AudioSample("sounds/sweep_x.au");
		sweepy = new AudioSample("sounds/sweep_y.au");
		mod01 = new AudioSample("sounds/kwik_mod_01.au");
		mod02 = new AudioSample("sounds/kwik_mod_02.au");
	}

	private class KeyTrigger implements KeyListener
	{
		public void keyPressed(KeyEvent e)
		{
			System.out.println("Key code: " + e.getKeyCode());
			
			switch (e.getKeyCode())
			{
				case KeyEvent.VK_NUMPAD1:
					kick.start();
					kickBtn.setBackground(Color.orange);
					title.setText("// kick --");
					break;
				case KeyEvent.VK_NUMPAD2:
					clap.start();
					clapBtn.setBackground(Color.orange);
					title.setText("// clap --");
					break;
				case KeyEvent.VK_NUMPAD3:
					chat.start();
					ohat.stop();
					clHatBtn.setBackground(Color.orange);
					title.setText("// close hat --");
					break;
				case KeyEvent.VK_NUMPAD4:
					ohat.start();
					chat.stop();
					opHatBtn.setBackground(Color.orange);
					title.setText("// open hat --");
					break;
				case KeyEvent.VK_NUMPAD5:
					clave.start();
					claveBtn.setBackground(Color.orange);
					title.setText("// beep --");
					break;
				case KeyEvent.VK_NUMPAD6:
					sweepx.start();
					sweepXBtn.setBackground(Color.orange);
					title.setText("// sweep x --");
					break;
				case KeyEvent.VK_NUMPAD7:
					mod01.start();
					sweepYBtn.setBackground(Color.orange);
					title.setText("// sweep y --");
					break;
				case KeyEvent.VK_NUMPAD8:
					mod02.start();
					mod01Btn.setBackground(Color.orange);
					title.setText("// mod 01 --");
					break;
				case KeyEvent.VK_NUMPAD9:
					clap.start();
					mod02Btn.setBackground(Color.orange);
					title.setText("// mod 02 --");
					break;
			}
		}

		public void keyTyped(KeyEvent e)
		{
		}

		public void keyReleased(KeyEvent e)
		{
			delay.start();
			switch (e.getKeyCode())
			{
				case KeyEvent.VK_NUMPAD1:
					kick.setFramePosition(0);
					kickBtn.setBackground(bisque);
					break;
				case KeyEvent.VK_NUMPAD2:
					clap.setFramePosition(0);
					clapBtn.setBackground(bisque);
					break;
				case KeyEvent.VK_NUMPAD3:
					chat.setFramePosition(0);
					ohat.setFramePosition(0);
					clHatBtn.setBackground(Color.lightGray);
					break;
				case KeyEvent.VK_NUMPAD4:
					chat.setFramePosition(0);
					ohat.setFramePosition(0);
					opHatBtn.setBackground(Color.lightGray);
					break;
				case KeyEvent.VK_NUMPAD5:
					clave.setFramePosition(0);
					claveBtn.setBackground(Color.gray);
					break;
				case KeyEvent.VK_NUMPAD6:
					sweepx.setFramePosition(0);
					sweepXBtn.setBackground(lightSkyBlue);
					break;
				case KeyEvent.VK_NUMPAD7:
					sweepy.setFramePosition(0);
					sweepYBtn.setBackground(lightSkyBlue);
					break;
				case KeyEvent.VK_NUMPAD8:
					mod01.setFramePosition(0);
					mod01Btn.setBackground(darkKhaki);
					break;
				case KeyEvent.VK_NUMPAD9:
					mod02.setFramePosition(0);
					mod02Btn.setBackground(darkKhaki);
					break;
			}
		}
	}

	public static void main(String[] args) throws IOException
	{
		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run()
			{
				NumLockDrumMachine nldm;
				try
				{
					nldm = new NumLockDrumMachine();
					nldm.setVisible(true);
				} catch (LineUnavailableException
						| UnsupportedAudioFileException | IOException e)
				{
					e.printStackTrace();
				}
			}
		});
	}

}
