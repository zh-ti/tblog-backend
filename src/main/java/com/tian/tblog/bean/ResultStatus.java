package com.tian.tblog.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResultStatus {
    private boolean hasError;
    private String reason;

    public ResultStatus(String reason){
        this.reason = reason;
        this.hasError = true;
    }
}
