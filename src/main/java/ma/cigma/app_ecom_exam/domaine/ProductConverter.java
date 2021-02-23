package ma.cigma.app_ecom_exam.domaine;

import java.util.ArrayList;
import java.util.List;

import ma.cigma.app_ecom_exam.entities.Product;

public class ProductConverter {
	public static ProductVo toVo(Product bo) {
		 if (bo == null || bo.getId() ==null)
		 return null;
		 ProductVo vo = new ProductVo();
		 vo.setId(bo.getId());
		 vo.setLabel(bo.getLabel());
		 vo.setPrice(bo.getPrice());
		 vo.setQuantity(bo.getQuantity());
		 vo.setCreationDate(bo.getCreationDate());
		 return vo;
		 } 
	
	public static Product toBo(ProductVo vo) {
		
		 Product bo = new Product();
		 bo.setId(vo.getId());
		 bo.setLabel(vo.getLabel());
		 bo.setPrice(vo.getPrice());
		 bo.setQuantity(vo.getQuantity());
		 bo.setCreationDate(vo.getCreationDate());
		 return bo;
		 } 
	
	public static List<ProductVo> toListVo(List<Product> listBo) {
		 List<ProductVo> listVo = new ArrayList<>();
		 for (Product user : listBo) {
		 listVo.add(toVo(user));
		 }
		 return listVo;
		 } 
}
