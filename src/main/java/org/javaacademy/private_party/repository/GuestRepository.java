package org.javaacademy.private_party.repository;

import org.javaacademy.private_party.entity.Guest;
import org.javaacademy.private_party.entity.GuestView;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class GuestRepository {

    public void addGuest(Connection connection, Guest guest) throws SQLException {
        String sqlPattern = "insert into guests (name, email, phone) values (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sqlPattern)) {
            statement.setString(1, guest.getName());
            statement.setString(2, guest.getEmail());
            statement.setString(3, guest.getPhone());
            int countChangedRows = statement.executeUpdate();
        }
    }

    public List<GuestView> printAllGuest(Connection connection) throws SQLException {
        String sqlPattern = "select * from guests_view";
        ArrayList<GuestView> guestViews = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sqlPattern);
            while (resultSet.next()) {
                GuestView guestView = new GuestView();
                guestView.setName(resultSet.getString("name"));
                guestViews.add(guestView);
            }
        }
        return guestViews;
    }
}
