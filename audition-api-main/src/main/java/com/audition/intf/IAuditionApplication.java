package com.audition.intf;

import com.audition.model.AuditionComment;

import java.util.List;

public interface IAuditionApplication {

    List<com.audition.model.AuditionPost> getPosts();

    com.audition.model.AuditionPost getPostById(final String id);

    List<com.audition.model.AuditionComment> getCommentsForPost(final String id);

    List<AuditionComment> getCommentForParticularPost(final String id);
}
