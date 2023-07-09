package com.audition.web;

import com.audition.common.exception.SystemException;
import com.audition.model.AuditionComment;
import com.audition.model.AuditionPost;
import com.audition.service.AuditionService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuditionController {

    @Autowired
    AuditionService auditionService;
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    // The API returns the filter response based on filterQuery supplied in the QueryString.i.e.
    //http://localhost:8080/posts?filter=1&filter=2
    //where filter=postId
    @RequestMapping(value = "/posts", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<AuditionPost> getPosts(
        @RequestParam(value = "filter", required = false) String[] filterQuery) {

        // Logic that filters response data based on the query param
        List<AuditionPost> listPost = auditionService.getPosts();
        return filterQuery != null && filterQuery.length != 0 ? listPost.stream()
            .filter(auditionPost -> Arrays.asList(filterQuery).contains(String.valueOf(auditionPost.getId()))).collect(
                Collectors.toList()) : listPost;
    }

    @RequestMapping(value = "/posts/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody AuditionPost getPosts(@PathVariable("id") final String postId) {
        //input validation
        if (postId==null || postId.isEmpty()) {
            throw new SystemException("Invalid post id : " + postId);
        }
        return auditionService.getPostById(postId);
    }

    @RequestMapping(value = "/posts/{id}/comments", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<AuditionComment> getCommentsForPostById(@PathVariable("id") final String postId) {
        return auditionService.getCommentsForPost(postId);

    }

    @RequestMapping(value = "/posts/comments/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody List<AuditionComment> getCommentForParticularPost(@PathVariable("id") final String postId) {
        return auditionService.getCommentForParticularPost(postId);

    }

}
