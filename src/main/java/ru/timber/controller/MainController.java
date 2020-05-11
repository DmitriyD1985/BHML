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

        Albums albumForInitialBD = new Albums();
        albumForInitialBD.setAlbumName("NewAlbum");
        Songs songOne= new Songs();
        Songs songTwo= new Songs();
        songOne.setSongName("Song1");
        songTwo.setSongName("Song2");
        List<Songs> lisOfSongs = new ArrayList<>();
        lisOfSongs.add(songOne);
        lisOfSongs.add(songTwo);
        albumForInitialBD.setSongs(lisOfSongs);

        User userForInitialBD = new User();
        ProFile pr = new ProFile();
        pr.setCity("NewCity");
        pr.setCountry("NewCountry");
        userForInitialBD.setLogin("NewLogin");
        userForInitialBD.setPassword("password");
        List<Albums> listOfAlbums = new ArrayList<>();
        listOfAlbums.add(albumForInitialBD);
        userForInitialBD.setUserA(listOfAlbums);
        userForInitialBD.setUserProfile(pr);
        userService.insertUser(userForInitialBD);

        String answer = "В базу загружен User - " +
                userForInitialBD.toString() +
                " вместе с ним автоматом загружен профаил со связью OneToOne -" +
                pr.toString() +
                "загружен альбом со связью OneToMany - " +
                albumForInitialBD.toString() +
                "С альбомом загружаются 2 песни - " +
                songOne.toString() +
                "и" +
                songTwo.toString() +
                "При загрузке инициализируется промежуточная таблица для организации связи ManyToMany между User и Albums";
        model.addAttribute("msg", answer);
        return "/dbinit";
    }

    @GetMapping("/deleteAlbum")
    public String deleteAlbum(Model model) {
        Albums albumForDelete = albumService.getByName("NewAlbum");
        albumService.removeAlbum(albumForDelete);
        String answer = "альбом удален";
        model.addAttribute("msg", answer);
        return "/deleteResult";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(Model model) {
        User userForDelete = userService.getByLogin("NewLogin");
        userService.removeUser(userForDelete);
        String answer = "Юзер удален";
        model.addAttribute("msg", answer);
        return "/deleteResult";
    }

    @GetMapping("/upload")
    public String index() {
        return "upload";
    }

    @GetMapping("/readCSV")
    public String readerF() {
        return "readfile";
    }

}
