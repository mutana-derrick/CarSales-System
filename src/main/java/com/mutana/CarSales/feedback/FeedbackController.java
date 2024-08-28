package com.mutana.CarSales.feedback;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedBackService feedbackService;

    @GetMapping
    @PreAuthorize("hasAnyRole('EDITOR', 'ADMIN')")
    public String getAllFeedback(Model model) {
        model.addAttribute("feedbacks", feedbackService.getAllFeedback());
        return "feedback/list";
    }

    // Other feedback-related methods...
}