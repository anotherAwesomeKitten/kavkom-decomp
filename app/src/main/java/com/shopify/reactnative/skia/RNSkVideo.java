package com.shopify.reactnative.skia;

import android.content.Context;
import android.graphics.Point;
import android.hardware.HardwareBuffer;
import android.media.Image;
import android.media.ImageReader;
import android.media.MediaCodec;
import android.media.MediaCrypto;
import android.media.MediaExtractor;
import android.media.MediaFormat;
import android.media.MediaPlayer;
import android.media.MediaSync;
import android.net.Uri;
import android.os.Build;
import android.view.Surface;
import java.io.IOException;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class RNSkVideo {
    private final Context context;
    private MediaCodec decoder;
    private double durationMs;
    private MediaExtractor extractor;
    private double frameRate;
    private ImageReader imageReader;
    private MediaPlayer mediaPlayer;
    private MediaSync mediaSync;
    private Surface outputSurface;
    private final Uri uri;
    private int rotationDegrees = 0;
    private int width = 0;
    private int height = 0;
    private boolean isPlaying = false;

    RNSkVideo(Context context, String str) {
        this.uri = Uri.parse(str);
        this.context = context;
        initializeReader();
    }

    private void initializeReader() {
        MediaExtractor mediaExtractor = new MediaExtractor();
        this.extractor = mediaExtractor;
        try {
            mediaExtractor.setDataSource(this.context, this.uri, (Map<String, String>) null);
            int iSelectVideoTrack = selectVideoTrack(this.extractor);
            if (iSelectVideoTrack < 0) {
                throw new RuntimeException("No video track found in " + this.uri);
            }
            this.extractor.selectTrack(iSelectVideoTrack);
            MediaFormat trackFormat = this.extractor.getTrackFormat(iSelectVideoTrack);
            MediaPlayer mediaPlayer = new MediaPlayer();
            this.mediaPlayer = mediaPlayer;
            mediaPlayer.setDataSource(this.context, this.uri);
            this.mediaPlayer.setAudioStreamType(3);
            this.mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() { // from class: com.shopify.reactnative.skia.RNSkVideo$$ExternalSyntheticLambda0
                @Override // android.media.MediaPlayer.OnPreparedListener
                public final void onPrepared(MediaPlayer mediaPlayer2) {
                    this.f$0.lambda$initializeReader$0(mediaPlayer2);
                }
            });
            this.mediaPlayer.prepareAsync();
            if (trackFormat.containsKey("durationUs")) {
                this.durationMs = trackFormat.getLong("durationUs") / 1000;
            }
            if (trackFormat.containsKey("frame-rate")) {
                this.frameRate = trackFormat.getInteger("frame-rate");
            }
            if (trackFormat.containsKey("rotation-degrees")) {
                this.rotationDegrees = trackFormat.getInteger("rotation-degrees");
            }
            this.width = trackFormat.getInteger("width");
            this.height = trackFormat.getInteger("height");
            if (Build.VERSION.SDK_INT >= 29) {
                this.imageReader = ImageReader.newInstance(this.width, this.height, 34, 2, 256L);
            } else {
                this.imageReader = ImageReader.newInstance(this.width, this.height, 34, 2);
            }
            this.outputSurface = this.imageReader.getSurface();
            MediaCodec mediaCodecCreateDecoderByType = MediaCodec.createDecoderByType(trackFormat.getString("mime"));
            this.decoder = mediaCodecCreateDecoderByType;
            mediaCodecCreateDecoderByType.configure(trackFormat, this.outputSurface, (MediaCrypto) null, 0);
            this.decoder.start();
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize extractor or decoder", e);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$initializeReader$0(MediaPlayer mediaPlayer) {
        this.durationMs = mediaPlayer.getDuration();
        mediaPlayer.start();
        this.isPlaying = true;
    }

    public double getDuration() {
        return this.durationMs;
    }

    public double getFrameRate() {
        return this.frameRate;
    }

    public int getRotationDegrees() {
        return this.rotationDegrees;
    }

    public HardwareBuffer nextImage() {
        if (!decoderOutputAvailable()) {
            decodeFrame();
        }
        Image imageAcquireLatestImage = this.imageReader.acquireLatestImage();
        if (imageAcquireLatestImage == null) {
            return null;
        }
        HardwareBuffer hardwareBuffer = imageAcquireLatestImage.getHardwareBuffer();
        imageAcquireLatestImage.close();
        return hardwareBuffer;
    }

    public void seek(double d) {
        long j = (long) (1000.0d * d);
        this.extractor.seekTo(j, 2);
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.seekTo((int) d, 3);
        }
        MediaCodec mediaCodec = this.decoder;
        if (mediaCodec != null) {
            mediaCodec.flush();
            boolean z = true;
            while (z) {
                decodeFrame();
                if (this.extractor.getSampleTime() >= j) {
                    z = false;
                }
            }
        }
    }

    public Point getSize() {
        return new Point(this.width, this.height);
    }

    private int selectVideoTrack(MediaExtractor mediaExtractor) {
        int trackCount = mediaExtractor.getTrackCount();
        for (int i = 0; i < trackCount; i++) {
            if (mediaExtractor.getTrackFormat(i).getString("mime").startsWith("video/")) {
                return i;
            }
        }
        return -1;
    }

    private boolean decoderOutputAvailable() {
        int iDequeueOutputBuffer = this.decoder.dequeueOutputBuffer(new MediaCodec.BufferInfo(), 0L);
        if (iDequeueOutputBuffer < 0) {
            return false;
        }
        this.decoder.releaseOutputBuffer(iDequeueOutputBuffer, true);
        return true;
    }

    private void decodeFrame() {
        MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
        int iDequeueInputBuffer = this.decoder.dequeueInputBuffer(10000L);
        if (iDequeueInputBuffer >= 0) {
            int sampleData = this.extractor.readSampleData(this.decoder.getInputBuffer(iDequeueInputBuffer), 0);
            if (sampleData < 0) {
                this.decoder.queueInputBuffer(iDequeueInputBuffer, 0, 0, 0L, 4);
            } else {
                this.decoder.queueInputBuffer(iDequeueInputBuffer, 0, sampleData, this.extractor.getSampleTime(), 0);
                this.extractor.advance();
            }
        }
        int iDequeueOutputBuffer = this.decoder.dequeueOutputBuffer(bufferInfo, 10000L);
        if (iDequeueOutputBuffer >= 0) {
            this.decoder.releaseOutputBuffer(iDequeueOutputBuffer, true);
            int i = bufferInfo.flags;
        }
    }

    public void play() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer == null || this.isPlaying) {
            return;
        }
        mediaPlayer.start();
        this.isPlaying = true;
    }

    public void pause() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer == null || !this.isPlaying) {
            return;
        }
        mediaPlayer.pause();
        this.isPlaying = false;
    }

    public void setVolume(float f) {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.setVolume(f, f);
        }
    }

    public void release() {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.mediaPlayer = null;
        }
        MediaCodec mediaCodec = this.decoder;
        if (mediaCodec != null) {
            mediaCodec.stop();
            this.decoder.release();
            this.decoder = null;
        }
        MediaExtractor mediaExtractor = this.extractor;
        if (mediaExtractor != null) {
            mediaExtractor.release();
            this.extractor = null;
        }
    }
}
