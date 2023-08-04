package com.udacity.course3.reviews.service;

import com.udacity.course3.reviews.model.Comment;
import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.repository.ReviewRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;


@Service
public class ReviewService {

    private final ReviewRepo reviewRepo;

    private final ProductService productService;



    @Autowired
    public ReviewService(ReviewRepo reviewRepo,ProductService productService) {
        this.reviewRepo = reviewRepo;
        this.productService= productService;
    }

    public Review saveProductReview(Review review,long productId)
    {
        review.setProductId(productId);
        Review reviewtemp= reviewRepo.save(review);
        productService.getProductById(productId).addReview(reviewtemp);
        return reviewtemp;
    }
    public Review getReviewById(long id)
    {
        return reviewRepo.findById(id).get();
    }
    public Set<Review> getReviewsByProductId(long id)
    {
        return productService.getProductById(id).getReviews();
    }

    public Iterable<Comment> getReviewComments(long reviewid)
    {
        return reviewRepo.findById(reviewid).get().getComments();
    }

}
