package com.github.nitish_chandra_m.ad_campaign_tool.utils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class EnumValueValidator implements ConstraintValidator<EnumValue, CharSequence> {
    private List<String> acceptedValues;

    @Override
    public void initialize(EnumValue constraintAnnotation) {
        acceptedValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.toList());
    }

    @Override
    public boolean isValid(CharSequence charSequence, ConstraintValidatorContext constraintValidatorContext) {
        if (charSequence == null || charSequence.toString().isEmpty()) {
            return true;
        }
        return acceptedValues.contains(charSequence.toString());
    }
}
