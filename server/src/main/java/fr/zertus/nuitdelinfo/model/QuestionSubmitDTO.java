package fr.zertus.nuitdelinfo.model;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class QuestionSubmitDTO {

    @Expose private long id;
    @Expose private boolean answer;

}
