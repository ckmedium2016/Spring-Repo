package com.audition.integration;

import com.audition.common.exception.RestTemplateErrorHandler;
import com.audition.common.exception.SystemException;
import com.audition.model.AuditionPost;
import com.audition.model.AuditionComment;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
public class AuditionIntegrationClient {


    @Autowired
    private RestTemplate restTemplate;
    private RestTemplateErrorHandler restTemplateErrorHandler;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    public List<AuditionPost> getPosts() {
        // makes RestTemplate call to get Posts from https://jsonplaceholder.typicode.com/posts
        try {
            String url = "https://jsonplaceholder.typicode.com/posts/";
            ResponseEntity<AuditionPost[]> auditionPostEntity = restTemplate.getForEntity(url, AuditionPost[].class);
            AuditionPost[] auditionPostArray = auditionPostEntity.getBody();
            return Arrays.asList(auditionPostArray);
        } catch (final HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.error("SystemException:::Resource Not Found::::::" + e.getStackTrace());
                throw new SystemException("Resource Not Found",
                    404);
            } else {
                if (restTemplateErrorHandler.hasError(e)) {
                    log.error("SystemException:::Unknown Error::::::" + e.getStackTrace());
                    restTemplateErrorHandler.handleError(e);
                }

            }
        }
        return null;
    }

    public AuditionPost getPostById(final String id) {
        // Spring Config to be used for https://jsonplaceholder.typicode.com/posts/
        try {
            String url = "https://jsonplaceholder.typicode.com/posts/{idValue}";
            url = url.replace("{idValue}", id);
            ResponseEntity<AuditionPost> auditionPostEntity = restTemplate.getForEntity(url, AuditionPost.class);
            return auditionPostEntity.getBody();
        } catch (final HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                log.error("SystemException:::Resource Not Found::::::" + e.getStackTrace());
                throw new SystemException("Cannot find a Post with id " + id, "Resource Not Found",
                    404);
            } else {
                log.error("SystemException:::Unknown Error::::::" + e.getStackTrace());
                if (restTemplateErrorHandler.hasError(e)) {
                    restTemplateErrorHandler.handleError(e);
                }
            }
        }
        return null;
    }

    // Write a method GET comments for a post from https://jsonplaceholder.typicode.com/posts/{postId}/comments - the comments must be returned as part of the post.

    public List<AuditionComment> getCommentsForPost(final String id) {
        // Spring Config to be used for https://jsonplaceholder.typicode.com/posts/{postId}/comments
        try {
            String url = "https://jsonplaceholder.typicode.com/posts/{idValue}/comments";
            url = url.replace("{idValue}", id);
            ResponseEntity<AuditionComment[]> auditionCommentEntity = restTemplate.getForEntity(url,
                AuditionComment[].class);
            AuditionComment[] auditionCommentArray = auditionCommentEntity.getBody();
            return Arrays.asList(auditionCommentArray);
        } catch (final HttpClientErrorException e) {
            log.error("SystemException:::Resource Not Found::::::" + e.getStackTrace());
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new SystemException("Cannot find a Post with id " + id, "Resource Not Found",
                    404);
            } else {
                log.error("SystemException:::Unknown Error::::::" + e.getStackTrace());
                if (restTemplateErrorHandler.hasError(e)) {
                    restTemplateErrorHandler.handleError(e);
                }
            }
        }
        return null;
    }

    // write a method. GET comments for a particular Post from https://jsonplaceholder.typicode.com/comments?postId={postId}.
    // The comments are a separate list that needs to be returned to the API consumers. Hint: this is not part of the AuditionPost pojo.
    public List<AuditionComment> getCommentForParticularPost(final String id) {
        // Spring Config to be used for https://jsonplaceholder.typicode.com/comments?postId={postId}
        try {
            String url = "https://jsonplaceholder.typicode.com/comments?postId={idValue}";
            url = url.replace("{idValue}", id);
            ResponseEntity<AuditionComment[]> auditionCommentEntity = restTemplate.getForEntity(url,
                AuditionComment[].class);
            AuditionComment[] auditionCommentArray = auditionCommentEntity.getBody();
            return Arrays.asList(auditionCommentArray);
        } catch (final HttpClientErrorException e) {
            log.error("SystemException:::Resource Not Found::::::" + e.getStackTrace());
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                throw new SystemException("Cannot find a Post with id " + id, "Resource Not Found",
                    404);
            } else {
                log.error("SystemException:::Unknown Error::::::" + e.getStackTrace());
                if (restTemplateErrorHandler.hasError(e)) {
                    restTemplateErrorHandler.handleError(e);
                }
            }
        }
        return null;
    }
}
