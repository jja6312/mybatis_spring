package com.ssafy.mvc.model.validator;

import com.ssafy.mvc.model.dto.Dto;

// 유효성 검사를 dto 객체들로부터 분리
public interface Validator<T extends Dto> {
    boolean validate(T t);
}
