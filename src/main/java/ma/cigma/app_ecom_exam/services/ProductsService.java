package ma.cigma.app_ecom_exam.services;

import java.util.List;

import ma.cigma.app_ecom_exam.domaine.ProductVo;
import ma.cigma.app_ecom_exam.entities.Product;

public interface ProductsService {
public List<ProductVo> getAll();
public ProductVo getProduct(String id);
public ProductVo addProduct(ProductVo productVo);
public ProductVo editProduct(String id,ProductVo productVo);
public void deleteProduct(String id);
public List<ProductVo> getByDetails(String label);
public Product save(Product product);
public Product get(String id);
}
