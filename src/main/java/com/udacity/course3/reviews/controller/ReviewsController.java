package com.udacity.course3.reviews.controller;

import com.udacity.course3.reviews.model.Review;
import com.udacity.course3.reviews.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpServerErrorException;


/**
 * Spring REST controller for working with review entity.
 */
@RestController
@RequestMapping("/reviews/products")
public class ReviewsController {

    // TODO: Wire JPA repositories here or serviec
    private final ReviewService reviewService ;
    @Autowired
    public ReviewsController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    /**
     * Creates a review for a product.
     * <p>
     * 1. Add argument for review entity. Use {@link RequestBody} annotation.
     * 2. Check for existence of product.
     * 3. If product not found, return NOT_FOUND.
     * 4. If found, save review.
     *
     * @param productId The id of the product.
     * @return The created review or 404 if product id is not found.
     */
    @PostMapping("/{productId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createReviewForProduct(@RequestBody Review review, @PathVariable Integer productId) {
        try {
            return ResponseEntity.ok(reviewService.saveProductReview(review,productId));
        } catch (Exception exception) {
            throw new HttpServerErrorException(HttpStatus.FOUND);
        }
    }

    /**
     * Lists reviews by product.
     *
     * @param productId The id of the product.
     * @return The list of reviews.
     */
    @GetMapping("/{productId}")
    public ResponseEntity<?> listReviewsForProduct(@PathVariable long productId) {
        try {
            return ResponseEntity.ok(reviewService.getReviewsByProductId(productId));
        } catch (Exception exception) {
            throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
        }
    }
}