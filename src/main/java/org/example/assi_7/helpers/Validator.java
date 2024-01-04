package org.example.assi_7.helpers;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Validator<T> {
    private final T value;
    private boolean isValid;
    private final List<String> errorMessages;

    private Validator(T value) {
        this.value = value;
        this.isValid = true;
        this.errorMessages = new ArrayList<>();
    }

    public static Validator<String> forString(String value) {
        return new Validator<>(value);
    }

    public static Validator<Integer> forInt(int value) {
        return new Validator<>(value);
    }

    public static Validator<Double> forDouble(double value) {
        return new Validator<>(value);
    }


    public Validator<T> isNotNull() {
        return validate(value != null, "Value must not be null.");
    }

    public Validator<T> isNotNull(String errorMessage) {
        return validate(value != null, errorMessage);
    }

    public Validator<T> hasLengthBetween(int minLength, int maxLength) {
        return validate(
                value != null && value.toString().length() >= minLength && value.toString().length() <= maxLength,
                "Length must be between " + minLength + " and " + maxLength + ".");
    }

    public Validator<T> isBetween(int minValue, int maxValue) {
        return validate(value != null && (int) value >= minValue && (int) value <= maxValue,
                "Value must be between " + minValue + " and " + maxValue + ".");
    }

    public Validator<T> isBetween(double minValue, double maxValue) {
        return validate(value != null && (double) value >= minValue && (double) value <= maxValue,
                "Value must be between " + minValue + " and " + maxValue + ".");
    }

    public Validator<T> isInteger() {
        return validate(value != null && value.toString().matches("^-?\\d+$"),
                "Value must be an integer.");
    }

    public Validator<T> isInValues(T[] values) {
        // check if value is in values
        return validate(
                // use uppercase to compare
                value != null && Arrays.stream(values)
                        .anyMatch(val -> val.toString().equalsIgnoreCase(value.toString())),
                "Value must be one of " + Arrays.toString(values) + ".");
    }

    // Add more validation methods as needed

    public boolean isValid() {
        return isValid;
    }

    public List<String> getErrorMessages() {
        return errorMessages;
    }

    public T get() {
        return value;
    }

    private Validator<T> validate(boolean condition, String errorMessage) {
        isValid = isValid && condition;
        if (!condition) {
            errorMessages.add(errorMessage);
        }
        return this;
    }
}