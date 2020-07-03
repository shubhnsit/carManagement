package com.example.CarManagement.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Value;

@Value
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GenericResponse<T> {

    @JsonProperty("data")
    private T data;
    @JsonProperty("error")
    ResponseError error;


}
