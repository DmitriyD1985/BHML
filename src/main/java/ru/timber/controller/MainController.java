package ru.timber.controller;

import ru.timber.model.Albums;
import ru.timber.model.ProFile;
import ru.timber.model.Songs;
import ru.timber.model.User;
import ru.timber.service.AlbumService;
import ru.timber.service.SongService;
import ru.timber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PreDestroy;
import java.util.ArrayList;
import java.util.List;


@Controller
public class MainController {

    private final UserService userService;
    private final AlbumService albumService;
    private final SongService songService;

    @Autowired
    public MainController(UserService userService, AlbumService albumService, SongService songService) {
        this.userService = userService;
        this.albumService = albumService;
        this.songService = songService;
    }

    @GetMapping("/")
    public String welcome() {
        return "/index";
    }


    @GetMapping("/onetoone")
    public String onetoone(Model model) {
        User user = userService.getByLogin("NewLogin");
        String answer = "Login from User - "+user.getLogin() + " country from profile - " +user.getUserProfile().getCountry() + " city from profile - "+ user.getUserProfile().getCity();
        model.addAttribute("msg", answer);
        return "/onetoone";
    }

    @GetMapping("/manytoone")
    public String manytoone(Model model) {
        StringBuilder answer = new StringBuilder();
        List<Songs> list =  albumService.getSongsByAlbum("NewAlbum");
        for (Songs s:list) {
            answer.append(s.getSongName());
            answer.append(" - ");
        }
        model.addAttribute("msg", answer.toString());
        return "/manytoone";
    }

    @GetMapping("/manytomany1")
    public String manytomany1(Model model) {
        User user = userService.getByLogin("NewLogin");
        String answer = "Logih from User - "+user.getLogin() + " first album from albums list of User - " +user.getUserA().get(0).getAlbumName();
        model.addAttribute("msg", answer);
        return "/manytomany1";
    }

    @GetMapping("/manytomany2")
    public String manytomany2(Model model) {
        Albums albums = albumService.getByName("NewAlbum");
        String answer = "Album with name - " + albums.getAlbumName() +" chised first User in list of Users wth login - " + albums.getUsers().get(0).getLogin();
        model.addAttribute("msg", answer);
        return "/manytomany1";
    }

    @GetMapping("/dataBaseInitialize")
    public String weldataBaseInitializecome(Model model) {
        User u = new User();
        u.setLogin("NewLogin");
        u.setPassword("password");
        Albums a = new Albums();
        a.setAlbumName("NewAlbum");
        Songs s1= new Songs();
        Songs s2= new Songs();
        s1.setSongName("Song1");
        s2.setSongName("Song2");
        List<Songs> lisOfSongs = new ArrayList<>();
        lisOfSongs.add(s1);
        lisOfSongs.add(s2);
        a.setSongs(lisOfSongs);
        List<Albums> listOfAlbums = new ArrayList<>();
        listOfAlbums.add(a);
        u.setUserA(listOfAlbums);
        ProFile pr = new ProFile();
        pr.setCity("NewCity");
        pr.setCountry("NewCountry");
        u.setUserProfile(pr);
        albumService.add(a);
        userService.add(u);
        StringBuilder answer = new StringBuilder();
        answer.append("В базу загружен User - ");
        answer.append(u.toString());
        answer.append(" вместе с ним автоматом загружен профаил со связью OneToOne -");
        answer.append(pr.toString());
        answer.append("загружен альбом со связью OneToMany - ");
        answer.append(a.toString());
        answer.append("С альбомом загружаются 2 песни - ");
        answer.append(s1.toString());
        answer.append("и");
        answer.append(s2.toString());
        answer.append("При загрузке инициализируется промежуточная таблица для организации связи ManyToMany между User и Albums");

        model.addAttribute("msg", answer.toString());
        return "/dbinit";
    }

}
