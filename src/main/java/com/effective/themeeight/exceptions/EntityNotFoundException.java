package com.effective.themeeight.exceptions;

/**
 * Эксепшен, который выбрасывается, если сущность не найдена
 */
public class EntityNotFoundException extends Exception {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
