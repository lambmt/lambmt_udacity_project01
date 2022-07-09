package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import org.springframework.web.bind.annotation.ModelAttribute;

public interface CredentialService {

    String insertCredential(@ModelAttribute Credential credential, String username);

    String deleteCredential(Integer credentialId);
}
