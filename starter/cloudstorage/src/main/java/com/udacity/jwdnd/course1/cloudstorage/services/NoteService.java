package com.udacity.jwdnd.course1.cloudstorage.services;

import com.udacity.jwdnd.course1.cloudstorage.entity.Note;

public interface NoteService {

    String insertNote(Note note, String username);

    String deleteNote(Integer noteId);
}
