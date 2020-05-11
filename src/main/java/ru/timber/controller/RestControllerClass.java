package ru.timber.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.timber.model.Songs;
import ru.timber.model.TransferDTO;
import ru.timber.service.SongService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;


@Controller
public class RestControllerClass {

    @Autowired
    private SongService songService;

    @RequestMapping(value = "/songsList", method = RequestMethod.POST, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String transferUpdate(@RequestBody TransferDTO data) {
        for (Songs s:data.getRows())
        {
            System.out.println(s.toString());
            songService.insertSongs(s);
        }
        List<Songs> list = songService.listOfSongs();

                ObjectMapper objectMapper = new ObjectMapper();
        String dataAnswer = null;
        try {
            dataAnswer = objectMapper.writeValueAsString(list);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(dataAnswer);

        return dataAnswer;
    }


    @RequestMapping(value = "/readCSV", method = RequestMethod.GET, produces = "application/json; charset=utf-8")
    @ResponseBody
    public String transferUpdate() {
        System.out.println("Начали");
        Resource resource = new ClassPathResource("/static/pricaForUpload.csv");
        File file = null;
        try {
            file = new File(resource.getURL().getPath());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try(Scanner sc = new Scanner(file)){
            while (sc.hasNext()) {
                String tempString = sc.nextLine();
                Songs inputinSong = new Songs();
                inputinSong.setSongName(tempString);
                System.out.println(inputinSong.toString());
                songService.insertSongs(inputinSong);
            }
        } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        List<Songs> listfromBase = songService.listOfSongs();
        ObjectMapper objectMapper = new ObjectMapper();
        String dataAnswer = null;
        try {
            dataAnswer = objectMapper.writeValueAsString(listfromBase);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(dataAnswer);
        return dataAnswer;
    }
}
