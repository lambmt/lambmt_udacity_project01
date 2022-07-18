package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import com.udacity.jwdnd.course1.cloudstorage.constants.CommonMsg;
import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialMapper;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Base64;

@Service
public class CredentialServiceImpl implements CredentialService {

    private final UserService userService;
    private final EncryptionService encryptionService;
    private final CredentialMapper credentialMapper;

    public CredentialServiceImpl(UserService userService, EncryptionService encryptionService, CredentialMapper credentialMapper) {
        this.userService = userService;
        this.encryptionService = encryptionService;
        this.credentialMapper = credentialMapper;
    }

    @Override
    public String insertCredential(Credential credential, String username) {
        Integer userId = userService.getUser(username).getUserId();

        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[16];
        secureRandom.nextBytes(key);
        credential.setKey(Base64.getEncoder().encodeToString(key));
        credential.setPassword(encryptionService.encryptValue(credential.getPassword(), credential.getKey()));

        if (null != credential.getCredentialId()) {
            if (0 > credentialMapper.updateCredential(credential)) {
                return CommonMsg.ACTION_FAIL;
            }
        } else {
            if (0 > credentialMapper.insertCredential(credential, userId)) {
                return CommonMsg.ACTION_FAIL;
            }
        }
        return CommonMsg.ACTION_SUCCESS;
    }

    @Override
    public String deleteCredential(Integer credentialId) {
        if (0 > credentialMapper.deleteCredential(credentialId)) {
            return CommonMsg.ACTION_FAIL;
        } else return CommonMsg.ACTION_SUCCESS;
    }
}
