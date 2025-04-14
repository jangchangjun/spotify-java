package org.jun.spotify.repository;

import org.apache.ibatis.annotations.Mapper;
import org.jun.spotify.entity.Song;
import org.springframework.stereotype.Repository;

@Mapper
public interface SongRepository {
    public int create(Song song);
    public int delete(String id);
    public Song findBySongId(String songId);
    public int update(Song song);
}
