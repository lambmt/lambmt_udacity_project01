package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.constants.CommonMsg;
import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.Principal;

@Controller
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/note")
    public String insertNote(@ModelAttribute Note note, Principal principal, Model model) {
        String result = noteService.insertNote(note, principal.getName());
        if(CommonMsg.ACTION_SUCCESS.equals(result)) {
            model.addAttribute("success", result);
        } else model.addAttribute("error", result);
        return "result";
    }

    @GetMapping("/note/delete")
    public String deleteNote(@RequestParam Integer noteId, Model model) {
        String result = noteService.deleteNote(noteId);
        if(CommonMsg.ACTION_SUCCESS.equals(result)) {
            model.addAttribute("success", result);
        } else model.addAttribute("error", result);
        return "result";
    }
}
