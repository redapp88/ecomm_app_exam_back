package ma.cigma.app_ecom_exam.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.cigma.app_ecom_exam.dao.ProductRepository;
import ma.cigma.app_ecom_exam.domaine.ProductConverter;
import ma.cigma.app_ecom_exam.domaine.ProductVo;
import ma.cigma.app_ecom_exam.entities.Product;
@Service
@Transactional
public class ProductsServiceImp implements ProductsService {
@Autowired
private ProductRepository productRepository;
@Autowired
private LigneCommandeService ligneCommandeService;
	@Override
	public List<ProductVo> getAll() {
		return ProductConverter.toListVo(this.productRepository.findAll());
	}

	@Override
	public ProductVo getProduct(String id) {
		Optional<Product> productOpt = this.productRepository.findById(id);
		if(!productOpt.isPresent())
			throw new RuntimeException("Produit Introuvable");
		
		return ProductConverter.toVo(productOpt.get());
	}
	
	@Override
	public Product get(String id) {
		Optional<Product> productOpt = this.productRepository.findById(id);
		if(!productOpt.isPresent())
			throw new RuntimeException("Produit Introuvable");
		
		return productOpt.get();
	}

	@Override
	public ProductVo addProduct(ProductVo productVo) {
		if(this.productRepository.findById(productVo.getId()).isPresent())
			throw new RuntimeException("La réference: "+productVo.getId()+" existe déja dans la liste des articles"); 
		Product product = ProductConverter.toBo(productVo);
		product.setCreationDate(new Date());
		return ProductConverter.toVo(this.productRepository.save(product)) ;
	}

	@Override
	public ProductVo editProduct(String id, ProductVo productVo) {
		Product product=this.get(id);
		product.setLabel(productVo.getLabel());
		product.setPrice(productVo.getPrice());
		product.setQuantity(productVo.getQuantity());
		return ProductConverter.toVo(this.productRepository.save(product));
	}

	@Override
	public void deleteProduct(String id) {
		Product product=this.get(id);
		int nb = this.ligneCommandeService.getLignesByProduct(product).size();
		if (nb>0)
			throw new RuntimeException("Impossible de supprimer ce produit tant qu'il existe toujours dans le pannier d'un client");
		this.productRepository.delete(product);

	}

	@Override
	public List<ProductVo> getByDetails(String label) {
	label="%"+label+"%";
	return ProductConverter.toListVo(this.productRepository.getByDetails(label));
	}
	


	@Override
	public Product save(Product product) {
	return this.productRepository.save(product);
	}

}
