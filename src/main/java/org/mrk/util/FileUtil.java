package org.mrk.util;

import lombok.experimental.UtilityClass;
import org.mrk.interfaces.User;

import java.io.*;
import java.net.URISyntaxException;
import java.util.*;
import java.util.List;

@UtilityClass
public class FileUtil {
    public static String TEMP_URL = null;
    static {
        try {
            TEMP_URL = FileUtil.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath();
            if (TEMP_URL.contains(".jar")) TEMP_URL = TEMP_URL.substring(0, TEMP_URL.length() - 4);
            else TEMP_URL = TEMP_URL + "task-manager";
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    //private static final String USERNAMEPATH = "src/main/resources/users/users.txt";

    public static void saveUserObj(User user){
        String path = TEMP_URL +"/"+ user.getFirstName() +".user";
        serialize(path, user);
    }

    public static User loadUserObj(String name){
        String path = TEMP_URL +"/"+ name + ".user";
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

    public static void serialize(String name, Object o){
        try (FileOutputStream fos = new FileOutputStream(name);
                ObjectOutputStream oos = new ObjectOutputStream(fos)){
                oos.writeObject(o);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Optional<Object> deserialize(String name){
        try (FileInputStream fis = new FileInputStream(name);
             ObjectInputStream ois = new ObjectInputStream(fis)){
             return Optional.of(ois.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("User not found, this user will be deleted");
        }
        return Optional.empty();
    }

    public static void saveNewUser(String name) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(TEMP_URL+"/users.txt", true));
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
            File tempDir = new File(TEMP_URL);
            tempDir.mkdirs();
            if (!new File(tempDir, "users.txt").exists()) {
                new File(tempDir, "users.txt").createNewFile();
                saveUsersList(new ArrayList<>());
            }

            List<String> userNames = new ArrayList<>();
            br = new BufferedReader(new FileReader(TEMP_URL+"/users.txt"));
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
            bf = new BufferedWriter(new FileWriter(TEMP_URL+"/users.txt"));
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
            new File(TEMP_URL+"/" + name + ".user").delete();
            saveUsersList(usersList);
        }
    }
}
