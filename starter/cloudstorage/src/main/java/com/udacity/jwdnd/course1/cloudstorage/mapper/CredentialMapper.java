package com.udacity.jwdnd.course1.cloudstorage.mapper;

import com.udacity.jwdnd.course1.cloudstorage.entity.Credential;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CredentialMapper {

    @Select("SELECT * FROM CREDENTIALS WHERE userid = #{userId}")
    List<Credential> getCredentialsByUserId(Integer userId);

    @Update("UPDATE CREDENTIALS SET url= #{url}, username = #{username}, password = #{password}, key = #{key}, WHERE credentialid = #{credentialId}")
    int updateCredential(Credential credential);

    @Delete("DELETE FROM CREDENTIALS WHERE credentialid = #{credentialId}")
    int deleteCredential(Integer credentialId);

    @Insert("INSERT INTO CREDENTIALS (url, username, password, key, userid) VALUES (#{credential.url}, #{credential.username}, #{credential.password}, #{credential.key})")
    @Options(useGeneratedKeys = true, keyProperty = "credential.credentialId")
    int insertCredential(Credential credential, Integer userId);
}
