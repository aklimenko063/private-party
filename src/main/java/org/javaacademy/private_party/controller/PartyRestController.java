package org.javaacademy.private_party.controller;

import lombok.RequiredArgsConstructor;
import org.javaacademy.private_party.dto.GuestDtoRq;
import org.javaacademy.private_party.service.PartyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;
import static org.springframework.http.ResponseEntity.status;

@RestController
@RequiredArgsConstructor
public class PartyRestController {
    private final PartyService partyService;

    @PostMapping("/add-guest")
    public ResponseEntity<?> addGuest(@RequestHeader String login,
                                      @RequestHeader String password,
                                      @RequestBody GuestDtoRq guestDtoRq) {
        try {
            return status(CREATED).body(partyService.addGuest(login, password, guestDtoRq));
        } catch (Exception e) {
            return status(BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/all-guests")
    public ResponseEntity<?> allGuest(@RequestHeader String login,
                                      @RequestHeader String password) {
        try {
            return status(OK).body(partyService.printAllGuest(login, password));
        } catch (Exception e) {
            return status(BAD_REQUEST).body(e.getMessage());
        }
    }
}
