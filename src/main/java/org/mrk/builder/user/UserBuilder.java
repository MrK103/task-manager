package org.mrk.builder.user;

import org.mrk.interfaces.Task;
import org.mrk.interfaces.User;
import org.mrk.model.user.UserModel;

import java.util.TreeSet;

public class UserBuilder {
        private int id;
        private String lastName;
        private String firstName;
        private TreeSet<Task> tasks;

        public UserBuilder setTasks (TreeSet<Task> tasks){
            this.tasks = tasks;
            return this;
        }

        public UserBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public User build() {
            return new UserModel(id, lastName, firstName, tasks);
        }
    }

