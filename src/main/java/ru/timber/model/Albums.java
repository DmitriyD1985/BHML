package ru.timber.model;

import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Albums")
public class Albums {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "albumName is required")
    private String albumName;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.MERGE, CascadeType.PERSIST},
            mappedBy = "userA")
    private List<User> users;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "albums")
    private List<Songs> songs;

    public void setSongs(List<Songs> songs) {
        if(songs != null){
            songs.forEach(a->a.setAlbums(this));
        }
        this.songs = songs;
    }

    public void removeUser(User user) {
        this.getUsers().remove(user);
        user.getUserA().remove(this);
    }
}
