package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashSet;
import java.util.Set;

@Controller
public class HomeController {

    @Autowired
    AlbumRepository albumRepository;

    @RequestMapping("/")
    public String index(Model model){
        //create an album
        Album album = new Album();
        album.setName("As I Am");
        album.setArtist("Alicia Keys");
        album.setYear(2007);

        //create a song
        Song song = new Song();
        song.setTitle("No One");
        song.setDuration("4:13");

        //add song to empty list
        Set<Song> songs = new HashSet<Song>();
        songs.add(song);

        //Create another song
        song = new Song();
        song.setTitle("Wreckless Love");
        song.setDuration("3:52");
        songs.add(song);

        //Add list of songs to the Album's songs' list
        album.setSongs(songs);

        //save the album to the database
        albumRepository.save(album);

        //Grad all the albums from the database and send to template
        model.addAttribute("albums", albumRepository.findAll());
        return "index";
    }
}
