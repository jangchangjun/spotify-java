package org.jun.spotify.controller;

import org.jun.spotify.repository.ArtistRepository;
import org.jun.spotify.repository.SongRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {
    private ArtistRepository artistRepository;
    private SongRepository songRepository;

}
