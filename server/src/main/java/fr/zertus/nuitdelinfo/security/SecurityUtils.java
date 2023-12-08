package fr.zertus.nuitdelinfo.security;

import fr.zertus.nuitdelinfo.exception.DataNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class SecurityUtils {

    public static long getCurrentUserId() throws DataNotFoundException {
        if (SecurityContextHolder.getContext().getAuthentication() == null)
            throw new DataNotFoundException("Current user not set");
        return (long) SecurityContextHolder.getContext().getAuthentication().getCredentials();
    }

    public static Key getEncryptionKey() {
        String key = "8Ed3wkfXGtThKV731jqvs82n7i4NSIuMJTzB/AHPPGsEt5TmI96Y/nyAWlCcka/PgWBO6exEqJaHSAnNqY/C1hPe+0BHQZOQ6jF6UYG+T4qhLWF5FHBW5EXR7fEyOUgThuac+IjS9ooAwapYJRFOsdYXdbAZ+WfAr94xE5KspSw0Ftzx/4aOw9qEsFQAGOvsnVL7CEFiQstwAv05vplKpZtH2WqbOxCuCBEPLQ==";
        return new SecretKeySpec(key.getBytes(), "HmacSHA512");
    }

}
