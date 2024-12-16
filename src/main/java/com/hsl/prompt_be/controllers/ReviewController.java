package com.hsl.prompt_be.controllers;

import com.hsl.prompt_be.entities.models.Review;
import com.hsl.prompt_be.entities.requests.ReviewRequest;
import com.hsl.prompt_be.entities.responses.GenericResponse;
import com.hsl.prompt_be.exceptions.PrinthubException;
import com.hsl.prompt_be.exceptions.ReviewNotFoundException;
import com.hsl.prompt_be.services.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RequestMapping("reviews")
@RequiredArgsConstructor
@RestController
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("{printerId}")
    public Review createReview(@PathVariable UUID printerId, @RequestBody ReviewRequest request) throws PrinthubException {

        return reviewService.createReview(printerId, request);
    }

    @GetMapping("search/{tag}")
    public List<Review> getReviewByPrinterOrUser(@PathVariable UUID tag) {

        return reviewService.getReviewsByPrinterOrUser(tag);
    }

    @GetMapping("{reviewId}")
    public Review getReviewById(@PathVariable UUID reviewId) throws ReviewNotFoundException {

        return reviewService.getReviewById(reviewId);
    }

    @PutMapping("{reviewId}")
    public Review updateReview(@PathVariable UUID reviewId, @RequestBody ReviewRequest request) throws ReviewNotFoundException {

        return reviewService.updateReview(reviewId, request);
    }

    @DeleteMapping("{reviewId}")
    public GenericResponse deleteReview(@PathVariable UUID reviewId) throws PrinthubException {

        return reviewService.deleteReview(reviewId);
    }
}
