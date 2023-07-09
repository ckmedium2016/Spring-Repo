package com.audition.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuditionPost {

    private int userId;
    private int id;
    private String title;
    private String body;

    /*
    public AuditionPost(AuditionPostBuilder builder) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public static class AuditionPostBuilder {

        // required parameters
        private int userId;
        private int id;
        private String title;
        private String body;

        public AuditionPostBuilder() {
            this.userId = userId;
            this.id = id;
            this.title = title;
            this.body = body;
        }

        public AuditionPostBuilder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public AuditionPostBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public AuditionPostBuilder setTitle(String title) {
            this.title = title;
            return this;
        }

        public AuditionPostBuilder setBody(String body) {
            this.body = body;
            return this;
        }

        public AuditionPost build() {
            return new AuditionPost(this);
        }
    }
  */
}
