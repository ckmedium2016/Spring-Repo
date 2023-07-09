package com.audition.service;

import com.audition.integration.AuditionIntegrationClient;
import com.audition.model.AuditionPost;
import com.audition.model.AuditionComment;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuditionService implements com.audition.intf.IAuditionApplication {

    @Autowired
    private AuditionIntegrationClient auditionIntegrationClient;


    @Override
    public List<AuditionPost> getPosts() {
        return auditionIntegrationClient.getPosts();
    }

    @Override
    public AuditionPost getPostById(final String postId) {
        return auditionIntegrationClient.getPostById(postId);
    }

    @Override
    public List<AuditionComment> getCommentsForPost(final String postId) {
        return auditionIntegrationClient.getCommentsForPost(postId);
    }

    @Override
    public List<AuditionComment> getCommentForParticularPost(String postId) {
        return auditionIntegrationClient.getCommentForParticularPost(postId);
    }
}
