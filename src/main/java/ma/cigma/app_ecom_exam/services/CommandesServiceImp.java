package ma.cigma.app_ecom_exam.services;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ma.cigma.app_ecom_exam.dao.CommandeRepository;
import ma.cigma.app_ecom_exam.domaine.CommandeConverter;
import ma.cigma.app_ecom_exam.domaine.CommandeVo;
import ma.cigma.app_ecom_exam.entities.AppUser;
import ma.cigma.app_ecom_exam.entities.Commande;
import ma.cigma.app_ecom_exam.entities.LigneCommande;
import ma.cigma.app_ecom_exam.entities.Product;

@Service
@Transactional
public class CommandesServiceImp implements CommandesService {
	@Autowired
	private CommandeRepository commandeRepository;
	@Autowired
	private UsersService usersService;
	@Autowired
	private LigneCommandeService ligneCommandeService;
	@Autowired
	private MailingService mailingService;
	final char CSV_SEPARATOR = ';';


	@Override
	@Transactional
	public CommandeVo addCommande(CommandeVo commandeVo) {
		// System.out.println(commandeVo);
		AppUser user = this.usersService.get(commandeVo.getUser().getUsername());
		Commande commande = new Commande(user, commandeVo.getPayementMethode(), commandeVo.getShippingAdress(),
				commandeVo.getShippingCity(), commandeVo.getTotal());
		commande = this.commandeRepository.save(commande);
		List<LigneCommande> cart = ligneCommandeService.getLignesByUsername(user.getUsername());
		
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy à HH:mm");
		float total = 0;
		StringBuilder details = new StringBuilder();
		details
		.append("Commande N:"+commande.getId())
		.append(System.lineSeparator())
		.append("Au profit de "+user.getFirstName()+" "+user.getLastName())
		.append(System.lineSeparator())
		.append("Le "+format.format( commande.getDateCommande()  ))
		.append(System.lineSeparator())
		.append("methode de payement "+commande.getPayementMethode())
		.append(System.lineSeparator())
		.append("Adresse de livraison"+commande.getShippingAdress()+" "+commande.getShippingCity())
		.append(System.lineSeparator());
		for (LigneCommande ligne : cart) {
			user.getCart().remove(ligne);
			// ligne.setUser(null);
			// ligne.setCommande(commande);
			Product p = ligne.getProduct();
			total += p.getPrice() * ligne.getQuantity();
			details
			.append(p.getId()).append(CSV_SEPARATOR)
			.append(p.getLabel()).append(CSV_SEPARATOR)
			.append(p.getPrice()).append(CSV_SEPARATOR)
			.append(ligne.getQuantity()).append(CSV_SEPARATOR).append((p.getPrice() * ligne.getQuantity())+" Dhs")
			.append(CSV_SEPARATOR)
			.append(System.lineSeparator());
			p.setQuantity(p.getQuantity() - ligne.getQuantity());
			this.ligneCommandeService.deleteLigneCommande(ligne.getId());
			//this.ligneCommandeService.save(ligne);
		}
		details.
		append("Total : "+total+" Dhs")
		.append(System.lineSeparator());
		commande.setCommandeDetails(details.toString());
		commande.setTotal(total);
		this.usersService.save(user);
		commande=this.commandeRepository.save(commande);
		
		try {

			    File tmpFile = File.createTempFile("commande_"+commande.getId().toString(), ".csv");
			    FileWriter writer = new FileWriter(tmpFile);
			    writer.write(commande.getCommandeDetails());
			    writer.close();
			this.mailingService.
			sendEmail("Falicitation"+commande.getUser().getFirstName()+ " Votre commande est enregistré! en piece jointe vous trouverez les details de votre commande "
					,"Details de votre commande" , commande.getUser().getMail(), "cigmaEcomm", commande.getId()+".csv", tmpFile);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CommandeConverter.toVo(this.commandeRepository.save(commande));
	}

	@Override
	public CommandeVo editCommande(Long id, CommandeVo commandeVo) {
		Commande commande = this.getCommande(id);
		if(!commande.getState().equals("WAITING"))
			throw new RuntimeException("Commande immodifiable");
		commande.setState(commandeVo.getState());
		//commande.setShippingAdress(commandeVo.getShippingAdress());
		//commande.setShippingCity(commandeVo.getShippingCity());
		//commande.setPayementMethode(commandeVo.getPayementMethode());
		String mailbody="<p> Bonjour "+commande.getUser().getFirstName()+ "</p>";
		mailbody+="<p> le status de votre commade:"+commande.getId()+" a changer consulter votre compte pour visualiser les changements <p>";
		try {
			this.mailingService.sendEmail(mailbody, "Changement du status de votre Commande", commande.getUser().getMail(), "CigmaEcomm", null, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return CommandeConverter.toVo(this.commandeRepository.save(commande));
	}

	@Override
	public List<CommandeVo> getCommandes(String username,String state) {
		if (username.equals("*"))
			username = "%%";
		if (state.equals("*"))
			state = "%%";
		return CommandeConverter.toListVo(this.commandeRepository.getByUsernameAndState(username,state));
	}

	@Override
	public void deleteCommande(Long id) {
		Commande commande = this.getCommande(id);
		this.commandeRepository.delete(commande);
	}

	@Override
	public Commande getCommande(Long id) {
		Optional<Commande> commandeOpt = this.commandeRepository.findById(id);
		if (!commandeOpt.isPresent())
			throw new RuntimeException("Commande Introuvable");

		return commandeOpt.get();
	}

	@Override
	public Commande Save(Commande commande) {
		return this.commandeRepository.save(commande);
	}

}
