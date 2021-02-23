package ma.cigma.app_ecom_exam.web;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import ma.cigma.app_ecom_exam.domaine.AppUserVo;
import ma.cigma.app_ecom_exam.domaine.CommandeVo;
import ma.cigma.app_ecom_exam.domaine.LigneCommandeVo;
import ma.cigma.app_ecom_exam.domaine.ProductVo;
import ma.cigma.app_ecom_exam.entities.AppUser;
import ma.cigma.app_ecom_exam.entities.Commande;
import ma.cigma.app_ecom_exam.entities.LigneCommande;
import ma.cigma.app_ecom_exam.services.CommandesService;
import ma.cigma.app_ecom_exam.services.LigneCommandeService;
import ma.cigma.app_ecom_exam.services.ProductsService;
import ma.cigma.app_ecom_exam.services.UsersService;

@RestController
public class MainController {
	@Autowired
	private UsersService usersService;
	@Autowired
	private ProductsService ProductsService;
	@Autowired
	private CommandesService commandesService;
	@Autowired
	private LigneCommandeService ligneCommandeService;

	/****** subscription & userManagement *****/

	@PostMapping("/subscribe")
	public AppUserVo addUser(@RequestBody AppUserVo appUserVo) {
		return this.usersService.addUser(appUserVo);
	}

	@PutMapping("/user/users/edit")
	public AppUserVo editUser(@RequestParam String username, @RequestBody AppUserVo appUserVo) {
		return this.usersService.editUser(username, appUserVo);
	}

	@PutMapping("/user/users/editpassword")
	public AppUserVo editpassword(@RequestParam String username, @RequestBody AppUserVo appUserVo) {
		return this.usersService.editPassword(username, appUserVo);
	}

	@GetMapping("/manager/users")
	public List<AppUserVo> getUsers(@RequestParam String keyword, @RequestParam String state) {
		return this.usersService.getUsers(keyword, state);
	}
	
	@GetMapping("/user/users/get")
	public List<AppUserVo> getUser(@RequestParam String username) {
		AppUserVo userVo = this.usersService.getUser(username);
		List<AppUserVo> list=new ArrayList<AppUserVo>();
		list.add(userVo);
		return list;
	}

	/****** Products *****/
	@GetMapping("/user/products")
	public List<ProductVo> getProducts(@RequestParam String label) {
		return this.ProductsService.getByDetails(label);
	}

	@PostMapping("/admin/products/add")
	public ProductVo addProduct(@RequestBody ProductVo productVo) {
		return this.ProductsService.addProduct(productVo);
	}

	@PutMapping("/admin/products/edit")
	public ProductVo editProduct(@RequestParam String id, @RequestBody ProductVo productVo) {
		return this.ProductsService.editProduct(id, productVo);
	}

	@DeleteMapping("/admin/products/delete")
	public void deleteProduct(@RequestParam String id) {
		this.ProductsService.deleteProduct(id);
	}

	/***** cart ****/
	@GetMapping("/user/cart")
	public List<LigneCommandeVo> getcart(@RequestParam String username) {
		return this.ligneCommandeService.getCart(username);
	}

	/**** commande ***/
	@GetMapping("/user/commandes")
	public List<CommandeVo> getcommandes(@RequestParam String username,@RequestParam String state) {
		return this.commandesService.getCommandes(username,state);
	}

	@PostMapping("/user/commandes/add")
	public CommandeVo addcommandes(@RequestBody CommandeVo commandeVo) {
		return this.commandesService.addCommande(commandeVo);
	}

	@DeleteMapping("/user/commandes/delete")
	public void deletecommandes(@RequestParam Long id) {
		this.commandesService.deleteCommande(id);
	}

	@PutMapping("/admin/commandes/edit")
	public CommandeVo editcommandes(@RequestParam Long id, @RequestBody CommandeVo commandeVo) {
		return this.commandesService.editCommande(id, commandeVo);
	}

	/***** ligne commande **/
	@DeleteMapping("/user/cart/deleteLigne")
	public void deleteLigneCommande(@RequestParam Long id) {
		this.ligneCommandeService.deleteLigneCommande(id);
	}

	@PostMapping("/user/cart/addLigne")
	public LigneCommandeVo addLigneCommande(@RequestBody LigneCommandeVo ligneCommandeVo) {
		return this.ligneCommandeService.addLigneCommnde(ligneCommandeVo);
	}

	@PutMapping("/user/cart/editLigne")
	public LigneCommandeVo editLigneCommande(@RequestParam Long id, @RequestBody LigneCommandeVo ligneCommandeVo) {
		return this.ligneCommandeService.editLigneCommande(id, ligneCommandeVo);
	}

/*********** csv export *********/
	@GetMapping("/user/commandeCsvExport")
	public void csv2(HttpServletResponse response, @RequestParam Long id) throws IOException {
		Commande commande = this.commandesService.getCommande(id);
		String fileName = "commande_" + commande.getId()+".csv";
		response.setContentType("text/plain");
		response.setHeader("Content-Disposition", "attachment;filename="+fileName);
		ServletOutputStream out = response.getOutputStream();
		out.println(commande.getCommandeDetails());
		out.flush();
		out.close();
	}

}
