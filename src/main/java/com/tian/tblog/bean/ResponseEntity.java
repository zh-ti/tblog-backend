package com.tian.tblog.bean;

import lombok.*;

@Data
@NoArgsConstructor
public class ResponseEntity {
    private ResultStatus resultStatus = new ResultStatus(false, null);
    private Object data;

    public ResponseEntity(ResultStatus resultStatus){
        this.resultStatus = resultStatus;
    }

    public ResponseEntity(Object ...data){
        this.data = data;
    }

    public ResponseEntity(ResultStatus resultStatus, Object ...data){
        this.resultStatus = resultStatus;
        this.data = data;
    }
}

