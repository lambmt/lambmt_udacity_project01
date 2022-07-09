package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.constants.CommonMsg;
import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class CredentialController {

    private final CredentialService credentialService;

    public CredentialController(CredentialService credentialService) {
        this.credentialService = credentialService;
    }

    @PostMapping("/credential")
    public String insertCredential(@ModelAttribute Credential credential, Principal principal, Model model) {
        String result = credentialService.insertCredential(credential, principal.getName());
        if(CommonMsg.ACTION_SUCCESS.equals(result)) {
            model.addAttribute("success", result);
        } else model.addAttribute("error", result);
        return "result";
    }

    @GetMapping("/credential/delete")
    public String deleteCredential(@RequestParam Integer credentialId, Model model) {
        String result = credentialService.deleteCredential(credentialId);
        if(CommonMsg.ACTION_SUCCESS.equals(result)) {
            model.addAttribute("success", result);
        } else model.addAttribute("error", result);
        return "result";
    }
}
