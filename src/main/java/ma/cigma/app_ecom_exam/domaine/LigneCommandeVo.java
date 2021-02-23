package ma.cigma.app_ecom_exam.domaine;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LigneCommandeVo {
	private Long id;
	private ProductVo product;
	private int quantity;
	private AppUserVo user;
}
