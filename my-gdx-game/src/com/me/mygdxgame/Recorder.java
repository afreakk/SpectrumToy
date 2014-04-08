package com.me.mygdxgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.AudioRecorder;

public class Recorder {
	private int samples = 44100;
	private int sampleSize = samples/30;
	private AudioRecorder recorder;
	private short[] temp = null;
	private int storageSize = samples/30;
	private short[] spectrumData = new short[storageSize];
	private boolean isMono = true;
	public Recorder()
	{
		recorder  = Gdx.audio.newAudioRecorder(samples, isMono);
	}
	public int getStorageSize()
	{
		return storageSize;
	}
	public void disposeRecorder()
	{
		recorder.dispose();
	}
	public int spectrumDataLength()
	{
		return spectrumData.length;
	}
	private int placingPos = 0;
	public void record()
	{
		temp = new short[sampleSize];
        recorder.read(temp, 0, temp.length);
        System.arraycopy(temp, 0, spectrumData, Math.min(placingPos, spectrumData.length-temp.length), temp.length);
        placingPos += sampleSize;
        if(placingPos >= storageSize)
        	placingPos = 0;    
	}
	public float scaleFromHalfSpectrum(int start, int end)
	{
		int result = 0; 
		for(int i=start; i< end; i++)
		{
			result += spectrumData[i];
		}
		return Utils.scaleFromSpectrum(result)/(float)(end-start);
	}
	public short[] getSpectrumData()
	{
		return spectrumData;
	}

}
