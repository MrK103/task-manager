package org.mrk.util;

import lombok.experimental.UtilityClass;
import org.mrk.interfaces.User;

import java.io.*;
import java.util.*;
import java.util.List;

@UtilityClass
public class ServiceUtil {

    public static void saveUser(User user){
        String path = "src/main/resources/users/"+ user.getFirstName() +".user";
        serialize(path, user);
    }

    public static User loadUser(String name){
        String path = "src/main/resources/users/" + name + ".user";
        Optional<Object> desObj = deserialize(path);
        if (desObj.isEmpty()) {
            deleteUser(name);
        return null;
        }
        User user = null;
        if (desObj.get() instanceof User){
            user = (User) desObj.get();
        }
        return user;
    }

    public static void serialize(String path, Object user){
        try (FileOutputStream fos = new FileOutputStream(path);
                ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeObject(user);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Optional<Object> deserialize(String path){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(path);
            ois = new ObjectInputStream(fis);
            return Optional.of(ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("User not found, this user will be deleted");
            deleteUser(path);
        } finally {
            try {
                if (fis!= null) fis.close();
                if (ois!=null) ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Optional.empty();
    }

    public static void saveUserName(String name) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(Link.USER_NAME_PATH, true));
            bw.newLine();
            bw.write(name);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static List<String> loadUsersList(){
        BufferedReader br = null;
        try {
            List<String> userNames = new ArrayList<>();
            br = new BufferedReader(new FileReader(Link.USER_NAME_PATH));
            br.skip(4);
            String line;// = br.readLine();
            while ((line = br.readLine()) != null)  {
                if (!line.isEmpty()) userNames.add(line);
            }
            return userNames;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br!=null) br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    private static void saveUsersList(List<String> usersList){
        BufferedWriter bf = null;
        try {
            bf = new BufferedWriter(new FileWriter(Link.USER_NAME_PATH));
            bf.write("Name");
            bf.newLine();
            for (String s : usersList) {
                if (!s.equals("")) {
                    try {
                        bf.write(s);
                        bf.newLine();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bf!=null) bf.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteUser(String name){
        List<String> usersList = loadUsersList();
        if (usersList!=null) {
            usersList.remove(name);
            new File("src/main/resources/users/" + name + ".user").delete();
            saveUsersList(usersList);
        }
    }
}
