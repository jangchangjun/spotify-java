package org.jun.spotify.repository;

import org.apache.ibatis.annotations.Mapper;
import org.jun.spotify.entity.Artist;
import org.springframework.stereotype.Repository;

@Mapper
public interface ArtistRepository {
    public int create(Artist artist);
}
