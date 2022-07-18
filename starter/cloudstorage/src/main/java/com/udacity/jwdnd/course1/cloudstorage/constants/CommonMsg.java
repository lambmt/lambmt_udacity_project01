package com.udacity.jwdnd.course1.cloudstorage.constants;

import org.springframework.stereotype.Component;

@Component
public class CommonMsg {
    public static final String ACTION_SUCCESS = "Action Success";
    public static final String ACTION_FAIL = "Action Fail";
    public static final String EMPTY_ENTITY = "Empty input entity";

    public static final String EXISTED_USER = "User is existed";

    public static final Integer FILE_SIZE = 1048576;
    public static final String FILE_TOO_LARGE = "File upload too large. Please try again";
    public static final String EXISTED_FILE = "File is existed";


}
