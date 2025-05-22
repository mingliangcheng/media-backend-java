package com.chen.media.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.media.pojo.Video;
import com.chen.media.service.VideoService;
import com.chen.media.mapper.VideoMapper;
import org.springframework.stereotype.Service;

/**
* @author 陈明亮
* @description 针对表【video】的数据库操作Service实现
* @createDate 2025-05-14 10:13:48
*/
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video>
    implements VideoService{

}




