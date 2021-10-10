package com.ybj.crawler.utils;





import ws.schild.jave.Encoder;
import ws.schild.jave.EncoderException;
import ws.schild.jave.MultimediaObject;
import ws.schild.jave.encode.AudioAttributes;
import ws.schild.jave.encode.EncodingAttributes;

import java.io.File;

public class VideoUtils {

    public static void main(String[] args) throws EncoderException {
        File source = new File("/Users/yuanbaojian/Downloads/wavAudio.wav");
        File target = new File("/Users/yuanbaojian/Downloads/wavMp3.mp3");
        MultimediaObject multimediaObject = new MultimediaObject(source);
        AudioAttributes audio = new AudioAttributes();
        audio.setCodec("libmp3lame");
        audio.setBitRate(new Integer(128000));
        audio.setChannels(new Integer(2));
        audio.setSamplingRate(new Integer(44100));
        EncodingAttributes attrs = new EncodingAttributes();
        attrs.setOutputFormat("mp3");
        attrs.setAudioAttributes(audio);
        Encoder encoder = new Encoder();
        encoder.encode(multimediaObject, target, attrs);
    }

}
