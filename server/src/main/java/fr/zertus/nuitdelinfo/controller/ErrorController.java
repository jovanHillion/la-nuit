package fr.zertus.nuitdelinfo.controller;

import fr.zertus.nuitdelinfo.model.ApiResponse;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ErrorController {

    @Hidden
    @GetMapping("/404")
    public ApiResponse<String> error() {
        return ApiResponse.internalServerError("An error occurred!");
    }

}
