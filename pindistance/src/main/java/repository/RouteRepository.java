package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Route;

public interface RouteRepository extends JpaRepository<Route, Long> {
    Route findByFromPincodeAndToPincode(String fromPincode, String toPincode);
}



