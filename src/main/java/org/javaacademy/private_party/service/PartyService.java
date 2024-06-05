package org.javaacademy.private_party.service;

import lombok.RequiredArgsConstructor;
import org.javaacademy.private_party.dto.GuestDtoRq;
import org.javaacademy.private_party.entity.Guest;
import org.javaacademy.private_party.entity.GuestView;
import org.javaacademy.private_party.repository.GuestRepository;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartyService {
    private final static String URL = "jdbc:postgresql://psql.sl-grp.ru:5432/private_party";
    private final GuestRepository guestRepository;

    public String addGuest(String login, String password, GuestDtoRq dtoRq) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        Guest guest = new Guest(dtoRq.getName(), dtoRq.getEmail(), dtoRq.getPhone());
        try(Connection connection = DriverManager.getConnection(URL, login, password)) {
            connection.setAutoCommit(false);
            try {
                guestRepository.addGuest(connection, guest);
                connection.commit();
                return guest.getName();
            } catch (Exception e) {
                connection.rollback();
                throw new RuntimeException(e.getMessage());
            }
        }
    }

    public List<String> printAllGuest(String login, String password) throws ClassNotFoundException, SQLException {
        Class.forName("org.postgresql.Driver");
        try(Connection connection = DriverManager.getConnection(URL, login, password)) {
            List<GuestView> guestViews = guestRepository.printAllGuest(connection);
            List<String> list = guestViews.stream().map(e -> e.getName()).toList();
            return list;
        }
    }
}
