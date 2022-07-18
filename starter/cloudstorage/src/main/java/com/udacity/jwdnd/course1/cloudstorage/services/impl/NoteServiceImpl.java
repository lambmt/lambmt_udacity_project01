package com.udacity.jwdnd.course1.cloudstorage.services.impl;

import com.udacity.jwdnd.course1.cloudstorage.constants.CommonMsg;
import com.udacity.jwdnd.course1.cloudstorage.entity.Note;
import com.udacity.jwdnd.course1.cloudstorage.mapper.NoteMapper;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Service;

@Service
public class NoteServiceImpl implements NoteService {

    private final UserService userService;
    private final NoteMapper noteMapper;

    public NoteServiceImpl(UserService userService, NoteMapper noteMapper) {
        this.userService = userService;
        this.noteMapper = noteMapper;
    }

    @Override
    public String insertNote(Note note, String username) {
        Integer userId = userService.getUser(username).getUserId();
        if (null != note.getNoteId()) {
            if (0 > noteMapper.updateNote(note)) {
                return CommonMsg.ACTION_FAIL;
            }
        } else {
            if(0 > noteMapper.insertNote(note, userId)) {
                return CommonMsg.ACTION_FAIL;
            }
        }
        return CommonMsg.ACTION_SUCCESS;
    }

    @Override
    public String deleteNote(Integer noteId) {
        if(0 > noteMapper.deleteNote(noteId)) {
            return CommonMsg.ACTION_FAIL;
        } else return CommonMsg.ACTION_SUCCESS;
    }
}
