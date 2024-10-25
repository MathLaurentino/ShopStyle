package com.shopstyle.ms_catalog.web.dto.mapper;

import com.shopstyle.ms_catalog.entity.Media;
import com.shopstyle.ms_catalog.web.dto.MediaGetDto;
import org.modelmapper.ModelMapper;

public class MediaMapper {

    public static MediaGetDto toDto(Media media) {
        return new ModelMapper().map(media, MediaGetDto.class);
    }

}
