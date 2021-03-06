//
// Created by wlanjie on 2017/9/25.
//

#ifndef FFMPEG_SHORTVIDEO_H
#define FFMPEG_SHORTVIDEO_H

#include <string>
#include <core/formatcontext.h>
#include "core/codeccontext.h"

#include "arguments.h"

namespace av {

class ShortVideo {
public:
    ShortVideo();
    ~ShortVideo();

    int openOutput(std::string& uri);
    int beginSection();
    int endSection();
    int encodeAudio(uint8_t *audioFrame);
    int encodeVideo(uint8_t *videoFrame);
    void setArguments(Arguments& arg);
    int composeVideo(std::vector<char*> inputVideoUri, char* composeUri);
    void close();

private:
    int initVideoEncoderContext();
    int initAudioEncoderContext();

private:
    FormatContext inputContext;
    FormatContext outputContext;
    VideoEncoderContext *videoEncoderContext;
    AudioEncoderContext *audioEncoderContext;

    std::error_code ec;

    int64_t start_time = 0;
    int audioNextPts;
    int videoNextPts;

    Arguments arguments;
};

}

#endif //FFMPEG_SHORTVIDEO_H
