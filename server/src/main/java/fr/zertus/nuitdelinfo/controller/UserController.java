package fr.zertus.nuitdelinfo.controller;

import fr.zertus.nuitdelinfo.entity.User;
import fr.zertus.nuitdelinfo.exception.DataNotFoundException;
import fr.zertus.nuitdelinfo.model.ApiResponse;
import fr.zertus.nuitdelinfo.model.CheckDTO;
import fr.zertus.nuitdelinfo.model.LoginDTO;
import fr.zertus.nuitdelinfo.model.RegisterDTO;
import fr.zertus.nuitdelinfo.security.JwtTokenProvider;
import fr.zertus.nuitdelinfo.service.RegisterUserService;
import fr.zertus.nuitdelinfo.service.UserService;
import fr.zertus.nuitdelinfo.util.FileUploadUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RegisterUserService registerUserService;

    @Autowired
    private UserService userService;


    @Operation(summary = "Login", description = "Login to the API", tags = { "User" },
            requestBody=@io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User credentials",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = LoginDTO.class),
                                    examples = {
                                            @ExampleObject(
                                                    name = "Example request",
                                                    description = "This is an example with user credentials",
                                                    value = "{\"email\":\"mysuperemail\",\"password\":\"mysuperpassword\"}"
                                            )
                                    }
                            )
                    }
            ))
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Return user token",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Example response",
                                            description = "This is an example with a token",
                                            value = "{\"status\":200,\"message\":\"OK\",\"data\":\"YourBearerToken\"}"
                                    )
                            }
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Example response",
                                            description = "This is an example with bad credentials",
                                            value = "{\"status\":401,\"message\":\"Bad credentials\"}"
                                    )
                            }
                    )
            ),
    })
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<Object>> login(@RequestBody LoginDTO login) throws IllegalAccessException {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = JwtTokenProvider.createToken(authentication);

            return ResponseEntity.ok(ApiResponse.ok(jwt));
        } catch (AuthenticationException e) {
            throw new IllegalAccessException("Invalid username/password supplied: " + e.getMessage());
        }
    }

    @Operation(summary = "Register new user", description = "Register to the API", tags = { "User" })
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Return User freshly created",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Example response",
                                            description = "This is an example with good register",
                                            value = "{\"status\":200,\"message\":\"OK\",\"data\":\"YourTokenBearer\"}"
                                    )
                            }
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "400",
                    description = "Bad request",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Example response",
                                            description = "This is an example with bad format email",
                                            value = "{\"status\":400,\"message\":\"Bad format email\"}"
                                    )
                            }
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "409",
                    description = "Conflict",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Example response",
                                            description = "This is an example with already used email",
                                            value = "{\"status\":409,\"message\":\"Email already used\"}"
                                    )
                            }
                    )
            ),
    })
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<Object>> register(@RequestBody RegisterDTO register) throws IllegalAccessException {
        ApiResponse<User> response = registerUserService.register(register);
        if (response.getStatus() >= 200 && response.getStatus() < 300) {
            return login(new LoginDTO(register.getUsername(), register.getPassword()));
        }
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Verify", description = "Verify the user account", tags = { "User" },
            parameters = {
                    @Parameter(name = "token", description = "Token to verify", required = true, example = "YourToken")
            })
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "204",
                    description = "Token is valid",
                    content = @Content
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Token is invalid",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(
                                    name = "Example response",
                                    description = "This is an example with invalid token",
                                    value = "{\"status\":401,\"message\":\"Token is invalid\"}"
                            )
                    )
            )
    })
    @PostMapping("/verify")
    public ResponseEntity<ApiResponse<String>> verify(@RequestBody CheckDTO token) {
        if (JwtTokenProvider.validateToken(token.getToken())) {
            return ApiResponse.noContent().toResponseEntity();
        }
        return ApiResponse.unauthorized("Invalid token").toResponseEntity();
    }

    @Operation(summary = "Get current user info", description = "Get the user information", tags = { "User" })
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "200",
                    description = "Return the user information",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class),
                            examples = {
                                    @ExampleObject(
                                            name = "Example response with user",
                                            description = "This is an example with user information",
                                            value = "{\"status\":200,\"message\":\"OK\",\"data\":{\"id\":1,\"username\":\"Zertus\",\"email\":\"zertus2001@gmail.com\",\"admin\":false,\"points\":0}}"
                                    )
                            }
                    )
            ),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = @Content(
                            schema = @Schema(implementation = String.class))
            ),
    })
    @GetMapping("/me")
    public ResponseEntity<ApiResponse<Object>> getCurrentUser() throws IllegalAccessException {
        try {
            User user = userService.getCurrentUser();
            return ResponseEntity.ok(ApiResponse.ok(user));
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            throw new IllegalAccessException("Invalid token");
        }
    }

    /**
     * IMAGE MANAGEMENT
     */
    @PostMapping("/avatar")
    public ResponseEntity<ApiResponse<String>> uploadAvatar(@RequestParam("avatar") MultipartFile avatar) throws DataNotFoundException, IOException {
        User user = userService.getCurrentUser();

        // Check file type
        if (!avatar.getContentType().startsWith("image/")) {
            return ApiResponse.badRequest("File is not an image").toResponseEntity();
        }

        userService.saveUserAvatar(user, avatar);

        return ApiResponse.ok(user.getAvatar()).toResponseEntity();
    }

    @GetMapping("/avatar/{id}")
    public ResponseEntity<?> getAvatar(@PathVariable String id) throws DataNotFoundException, IOException {
        User user = userService.getUser(id);

        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        if (StringUtils.isEmpty(user.getAvatar())) {
            return ResponseEntity.notFound().build();
        }

        return FileUploadUtil.loadFile("users-avatar", user.getId() + "");
    }

}
