package vn.edu.hcmus.stepic.Base;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Collections;

@Getter
@Setter
public class ResponseBody {
    @JsonProperty("Data")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Object data;

    @JsonProperty("Message")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message;

    public ResponseBody(String msg){
        this.message = msg;
        this.data = null;
    }

    public ResponseBody(Object data){
        this.data = data;
    }

    public ResponseBody(String msg, Object data){
        this.message = msg;
        this.data = data;
    }
}
