package org.jun.spotify.entity;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Artist {
    private int id;
    private String artistName;
    private String artistId;
    private String image;
    private String genre;
}
