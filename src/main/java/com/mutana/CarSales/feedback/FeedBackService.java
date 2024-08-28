package com.mutana.CarSales.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class FeedBackService {

    @Autowired
    private FeedBackRepository feedBackRepository;

    public List<FeedbackModel> getAllFeedback() {
        return feedBackRepository.findAll();
    }

    public Optional<FeedbackModel> getFeedbackById(Long id) {
        return feedBackRepository.findById(id);
    }

    @Transactional
    public FeedbackModel saveFeedback(FeedbackModel feedback) {
        return feedBackRepository.save(feedback);
    }

    @Transactional
    public void deleteFeedback(Long id) {
        feedBackRepository.deleteById(id);
    }
}
