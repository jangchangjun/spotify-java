package org.jun.spotify.controller;

import com.neovisionaries.i18n.CountryCode;
import com.wrapper.spotify.SpotifyApi;
import com.wrapper.spotify.exceptions.SpotifyWebApiException;
import com.wrapper.spotify.model_objects.specification.Artist;
import com.wrapper.spotify.requests.data.artists.GetArtistRequest;
import com.wrapper.spotify.requests.data.artists.GetArtistsTopTracksRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchArtistsRequest;
import com.wrapper.spotify.requests.data.search.simplified.SearchTracksRequest;
import lombok.AllArgsConstructor;
import org.apache.hc.core5.http.ParseException;
import org.jun.spotify.AccessToken;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.wrapper.spotify.model_objects.specification.Track;


import java.io.IOException;
import java.util.Arrays;

@Controller
@AllArgsConstructor
@RequestMapping("/song")
public class SongApiController {

    private SpotifyApi spotifyApi;

    @GetMapping("/search")
    public String searchForm(){
        return "song/search";
    }

    @PostMapping("/search")
    public String searchHandle(@RequestParam("q") String q, Model model) {
        try {
            String token = AccessToken.CreateToken.accesstoken();

            SpotifyApi api = new SpotifyApi.Builder()
                    .setAccessToken(token)
                    .build();

            SearchTracksRequest searchRequest = api.searchTracks(q).limit(10).build();
            Track[] tracks = searchRequest.execute().getItems();

            model.addAttribute("tracks", Arrays.asList(tracks));
            return "song/result";

        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    @GetMapping("artist")
    public String artistForm(){
        return "song/artist";
    }
    @PostMapping("/artist")
    public String artistHandle(@RequestParam("q") String q, Model model){
        try {
            String token = AccessToken.CreateToken.accesstoken();

            SpotifyApi api = new SpotifyApi.Builder()
                    .setAccessToken(token)
                    .build();

            GetArtistRequest getArtistRequest = api.getArtist(q).build();
            Artist artist = getArtistRequest.execute();

            GetArtistsTopTracksRequest topTracksRequest = api.getArtistsTopTracks(q, CountryCode.KR).build();
            Track[] tracks = topTracksRequest.execute();

            model.addAttribute("tracks", Arrays.asList(tracks));
            model.addAttribute("name", artist.getName());
            model.addAttribute("genres", artist.getGenres());
            model.addAttribute("imageUrl", artist.getImages().length > 0 ? artist.getImages()[0].getUrl() : null);

            return "song/artist";

        } catch (Exception e){
            e.printStackTrace();
            return "error";
        }
    }
}
