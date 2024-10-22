package repository;

import org.springframework.data.jpa.repository.JpaRepository;

import entity.Pincode;

public interface PincodeRepository extends JpaRepository<Pincode, String> {
	
}
