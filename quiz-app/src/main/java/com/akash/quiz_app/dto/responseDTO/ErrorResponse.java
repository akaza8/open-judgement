package com.akash.quiz_app.dto.responseDTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse{
    private String message;
    private String error;
    private int status;
   private LocalDateTime timestamp;
   private List<FieldError> errors;
}
