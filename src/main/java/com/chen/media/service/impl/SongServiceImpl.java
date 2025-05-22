package com.chen.media.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chen.media.pojo.Song;
import com.chen.media.service.SongService;
import com.chen.media.mapper.SongMapper;
import org.springframework.stereotype.Service;

/**
* @author 陈明亮
* @description 针对表【song】的数据库操作Service实现
* @createDate 2025-05-14 10:13:48
*/
@Service
public class SongServiceImpl extends ServiceImpl<SongMapper, Song>
    implements SongService{

}




