package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.model.Comment;
import com.udacity.course3.reviews.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;

/**
 * Spring REST controller for working with comment entity.
 */
@RestController
@RequestMapping("/comments")
public class CommentController {

    // TODO: Wire needed JPA repositories here or service
    @Autowired
    private CommentService commentService;


    /**
     * Creates a comment for a review.
     *
     * 1. Add argument for comment entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, save comment.
     *
     * @param reviewId The id of the review.
     */
    @PostMapping("/{reviewId}")
    public ResponseEntity<?> createCommentForReview(@PathVariable long reviewId,@RequestBody Comment comment) {
        ResponseEntity<Comment> responseEntity;
        try {
            responseEntity=ResponseEntity.ok(commentService.saveComment(comment,reviewId));
        }catch (Exception exception)
        {
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    /**
     * List comments for a review.
     *
     * 2. Check for existence of review.
     * 3. If review not found, return NOT_FOUND.
     * 4. If found, return list of comments.
     *
     * @param reviewId The id of the review.
     */
    @GetMapping("/{reviewId}")
    public ResponseEntity<?> listCommentsForReview(@PathVariable long reviewId) {
        ResponseEntity<Iterable<Comment>> responseEntity;
        try {
            responseEntity =ResponseEntity.ok(commentService.getCommentsReviewById(reviewId));
        }catch (Exception exception)
        {
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
}