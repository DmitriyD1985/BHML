package ru.timber.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "login is required")
    private String login;

    @Size(min = 1, max = 20, message = "password should be from 1 to 20 symbols")
    private String password;


    @OneToOne(fetch = FetchType.LAZY, optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "profID", nullable = false)
    private ProFile userProfile;

    @ManyToMany(fetch=FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinTable (name="choisedAlbums",
            joinColumns=@JoinColumn (name="user_id", referencedColumnName = "ID"),
            inverseJoinColumns=@JoinColumn(name="album_id", referencedColumnName = "ID"))
    private List<Albums> userA;

    public void addAlbum(Albums albums) {
        this.userA.add(albums);
        albums.getUsers().add(this);
    }

    public void removeAlbum(Albums albums) {
            albums.getUsers().remove(this);
    }
}