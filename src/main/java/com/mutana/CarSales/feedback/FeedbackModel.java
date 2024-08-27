package com.mutana.CarSales.feedback;

import com.mutana.CarSales.user.UserModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "feedback")
@Data
@NoArgsConstructor
public class FeedbackModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long feedbackId;

    @ManyToOne
    @JoinColumn(name = "userId")
    private UserModel user;

    private String content;
    private LocalDate dateSubmitted;


    @ManyToOne
    @JoinColumn(name = "createdBy")
    private UserModel createdBy;

    private LocalDateTime createdAt;

    @ManyToOne(optional = true)
    @JoinColumn(name = "modifiedBy")
    private UserModel modifiedBy;

    private LocalDateTime modifiedAt;
    private String status; // 'Active', 'Inactive'
}

