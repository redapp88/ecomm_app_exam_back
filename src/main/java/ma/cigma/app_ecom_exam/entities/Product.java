package ma.cigma.app_ecom_exam.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
	@Id
	private String id;
	@NonNull
	private String label;
	@NonNull
	private double price;
	@NonNull
	private int quantity;
	private Date creationDate;
	public Product( String label, double price, int quantity) {
		this.label = label;
		this.price = price;
		this.quantity = quantity;
		this.creationDate=new Date();
	}
	@Override
	public boolean equals(Object o) {
		return this.id.equals(((Product)o).getId());
	}
	
	
	
	
}
