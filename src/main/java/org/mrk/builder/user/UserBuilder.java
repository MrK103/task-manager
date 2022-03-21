package org.mrk.builder.user;

import org.mrk.interfaces.Task;
import org.mrk.interfaces.User;
import org.mrk.model.user.UserModel;

import java.util.TreeSet;

public class UserBuilder<T> {
        private T id;
        private String lastName;
        private String firstName;
        private TreeSet<Task> tasks;

        public UserBuilder<T> setTasks (TreeSet<Task> tasks){
            this.tasks = tasks;
            return this;
        }

        public UserBuilder<T> setId(T id) {
            this.id = id;
            return this;
        }

        public UserBuilder<T> setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public UserBuilder<T> setFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public User<T> build() {
            return new UserModel<>(id, lastName, firstName, tasks);
        }
    }

