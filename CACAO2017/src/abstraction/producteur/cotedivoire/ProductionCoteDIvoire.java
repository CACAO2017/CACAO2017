package abstraction.producteur.cotedivoire;

import abstraction.fourni.Acteur;
import abstraction.producteur.ameriquelatine.IProducteur;


// by fcadre, comments by antoineroson

public class ProductionCoteDIvoire implements Acteur, IProducteur{
	public static final int  productionmoyenne = 1650000; // Production moyenne de la cote d'ivoire en tonnes
	private int  production; //Liste des productions par périodes
	private Stock stock;          // Represente notre stock 
	private Treso tresorerie;     // Représente notre trésorerie
	
	//Cf marché
	public int hashCode() {
		return this.getNom().hashCode();
	}
	
	//Constructeur Production cote d'ivoire
	public ProductionCoteDIvoire(int prods, Stock stock, Treso treso){ 
		this.production = prods; 
		this.stock=stock;
		this.tresorerie = treso; 
	}
	public ProductionCoteDIvoire() {
		this.production = 0;
		this.stock= new Stock(0);
		this.tresorerie= new Treso();
	}
	
	//Accesseur Production moyenne
	public int getProductionmoyenne(){ 
		return productionmoyenne; 
	}
	
	//Accesseur liste de production
	public int getProductions(){
		return this.production; 
	}
	
	//Accesseur quantité produite
	public int getQuantiteProd(){ 
		return this.getProductions();   
		// Récupére la dernière production sur la période
	}

	// Méthode varitation random de la production
	public void variationProduction(){
		double variation = 0.10;  //Variation de +- 10% 
		//Création d'une enveloppe (prod_min->prod_max)
		double prod_min = this.getProductionmoyenne() - (double)(this.getProductionmoyenne()*variation); 
		double prod_max = this.getProductionmoyenne() + (double)(this.getProductionmoyenne()*variation);
		double prod = prod_min + (double)Math.random()*(prod_max - prod_min); // Production random entre prod_min et prod_max
		production=(int)prod; // ajout dans la liste de production
		this.stock.addStock(prod);
	}
	
	//Accesseur Nom
	public String getNom() {
		return "Production Cote d'Ivoire"; 
	}

	public double quantiteMiseEnvente() {   // correspond a la quantité mise en vente//
		return this.getQuantiteProd()+this.stock.getStock(); 
	}


	public void notificationVente(double quantite, double coursActuel) {	// grace a la notification de vente on met a jour // 
		this.tresorerie.addCa(quantite*coursActuel);                       // notre tresorerie et notre stock //
		this.stock.addStock(-quantite);	
	}
	
	//NEXT "Centre du programme -> Passage à la période suivante" 
	
	public void next() {
		this.variationProduction();	
	}
}
