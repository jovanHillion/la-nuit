package fr.zertus.nuitdelinfo.entity;

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Expose
    private long id;

    @Expose
    private String username;

    @Expose
    private String email;

    private String password;

    @Expose
    private boolean admin;

    @Expose
    private long points;

    @Expose
    private String avatar;

}