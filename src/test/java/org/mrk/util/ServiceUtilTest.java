package org.mrk.util;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mrk.interfaces.User;
import org.mrk.model.user.UserModel;

import java.io.*;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class ServiceUtilTest {
    private static User tempUser;
    private static final String pathTestUser = "src/main/resources/users/TEST.user";
    private static final String nameTempUser = "TEST";

    @BeforeAll
    static void init(){
        tempUser = UserModel.builder().firstName(nameTempUser).build();
    }

    @Test
    void saveUserTest() {
        //when
        ServiceUtil.saveUser(tempUser);

        //then
        User userResult = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(pathTestUser);
            ois = new ObjectInputStream(fis);
            userResult = (User) ois.readObject();
        } catch (IOException | ClassNotFoundException  e) {
            System.err.println("User not found");
        } finally {
            try {
                if (fis != null) fis.close();
                if (ois != null) ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        assertEquals(tempUser, userResult);
    }

    @Test
    void loadUserTest() {
        //given
        try (FileOutputStream fos = new FileOutputStream(pathTestUser);
             ObjectOutputStream oos = new ObjectOutputStream(fos)){
            oos.writeObject(tempUser);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //when
        User userResult = ServiceUtil.loadUser(nameTempUser);
        //then
        assertEquals(tempUser, userResult);
    }

    @Test
    void serializeTest() {
        //when
            ServiceUtil.serialize(pathTestUser,tempUser);
        //then
        var file = new File(pathTestUser);
        assertTrue(file.exists());
        //
    }

    @Test
    void deserializeTest() {
        //give
        ServiceUtil.serialize(pathTestUser,tempUser);
        //when
        var userResult = (User) ServiceUtil.deserialize(pathTestUser).get();
        //then
        assertEquals(tempUser, userResult);
        //
    }

    @Test
    void saveUserNameTest(){
        //give
        //when
        ServiceUtil.saveUserName(nameTempUser);
        //then
        var userList = ServiceUtil.loadUsersList();
        assertTrue(Objects.requireNonNull(userList).contains(nameTempUser));
    }

    @Test
    void loadUsersListTest() {
        //given
        var exceptedSize = Objects.requireNonNull(ServiceUtil.loadUsersList()).size();
        ServiceUtil.saveUserName(nameTempUser);
        //when
        var resultSize = Objects.requireNonNull(ServiceUtil.loadUsersList()).size();
        //then
        assertEquals(exceptedSize+1, resultSize);
    }

    @AfterEach
    void cleanTempUser(){
        ServiceUtil.deleteUser(nameTempUser);
    }
}