package entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Pincode {
    @Id
    private String pincode;
    private double latitude;
    private double longitude;
    private String polygon; // Optional: store polygon info as a JSON string
}
