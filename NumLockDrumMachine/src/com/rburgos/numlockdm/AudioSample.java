package com.rburgos.numlockdm;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioSample
{
	private String filename;
	private File audioFile;
	private AudioInputStream stream;
	private AudioFormat format;
	private DataLine.Info info;
	private Clip audioClip;
	
	/**** Constructor 
	 * @throws IOException 
	 * @throws UnsupportedAudioFileException 
	 * @throws LineUnavailableException ****/
	public AudioSample(String filename) throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		this.filename = filename;
		audioFile = new File(this.filename);
		stream = AudioSystem.getAudioInputStream(audioFile);
		format = stream.getFormat();
		info = new DataLine.Info(Clip.class, format);
		// audioClip = AudioSystem.getClip();
		audioClip = (Clip)AudioSystem.getLine(info);
		audioClip.open(stream);
	}

	public void setFramePosition(int frames)
	{
		audioClip.setFramePosition(frames);
	}

	public void start()
	{
		audioClip.start();
	}

	public void stop()
	{
		audioClip.stop();
	}
	
	public void flush()
	{
		audioClip.flush();
	}
}
