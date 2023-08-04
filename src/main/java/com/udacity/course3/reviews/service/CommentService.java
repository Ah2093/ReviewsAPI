package com.udacity.course3.reviews.service;

import com.udacity.course3.reviews.model.Comment;
import com.udacity.course3.reviews.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private final CommentRepo commentRepo;
    private final ReviewService reviewService;
    @Autowired
    public CommentService(CommentRepo commentRepo, ReviewService reviewService) {
        this.commentRepo = commentRepo;
        this.reviewService = reviewService;
    }

    public Comment saveComment(Comment comment,long id)
    {
        comment.setReviewId(id);
        Comment comment1= commentRepo.save(comment);
        reviewService.getReviewById(id).addComment(comment1);
        return comment1;
    }
    public List<Comment> getCommentsReviewById(long id) {
        return (List<Comment>) reviewService.getReviewComments(id);
    }

}
