package fr.zertus.nuitdelinfo.entity;

import com.google.gson.annotations.Expose;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "questions")
public class Question {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) @Expose
    private long id;

    @Expose
    private String title;

    @Expose
    private boolean response; // true = yes, false = no

    @Expose
    private String description;

    @Expose
    private String image;

}
