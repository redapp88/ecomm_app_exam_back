package ma.cigma.app_ecom_exam.domaine;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductVo {
	private String id;
	private String label;
	private double price;
	private int quantity;
	private Date creationDate;
}
