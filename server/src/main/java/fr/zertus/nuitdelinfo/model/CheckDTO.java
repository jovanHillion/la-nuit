package fr.zertus.nuitdelinfo.model;

import com.google.gson.annotations.Expose;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class CheckDTO {

    @Expose String token;

}