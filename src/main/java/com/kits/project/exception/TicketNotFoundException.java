package com.kits.project.exception;

public class TicketNotFoundException extends RuntimeException {

        public TicketNotFoundException() { }

        public TicketNotFoundException(String message) {
            super(message);
        }
}