package com.noovi2.userdept.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_product")
public class Product implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	private String description;
	private Double price;
	private String imgUri;

	@ManyToMany
	//abaixo tabela de associação entre produto e categoria.
	@JoinTable(name = "tb_product_category", 
	joinColumns = @JoinColumn(name = "product_id"), 
	inverseJoinColumns = @JoinColumn(name = "category_id"))	// anotação muitos para um , colocando nome da chave estrangeira do produto
	// Set representa um conjunto , para garantir que o produto não tenha mais de
	// uma categoria
	private Set<Category> categories = new HashSet<>(); // Set Hashset semelhante ao list arraylist

	
	
	
	
	
	@OneToMany(mappedBy = "id.product")// mapear no pk o pediro pelo ID, ver os itens do pedido por ID
	// o id foi mapedo no "private OrderItemPk id = new OrderItemPk();" e o product esta dentro do pk private product product.
	
	
	private Set <OrdenItem> items = new HashSet<>(); // SEt pra informar que não tera repetições no JPA
	
	
	
	
	public Product() {

	}

	public Product(Long id, String name, String description, Double price, String imgUri) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.imgUri = imgUri;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getImgUri() {
		return imgUri;
	}

	public void setImgUri(String imgUri) {
		this.imgUri = imgUri;
	}

	public Set<Category> getCategories() {
		return categories;
	}
	
	@JsonIgnore
	public Set <Order> getOrders (){
		Set <Order> set = new HashSet<>();
		for (OrdenItem x : items) { // percorrendo a coleção Items , vamos adicionar x ao get order.
			set.add(x.getOrder());
		}
		return set;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		return Objects.equals(id, other.id);
	}

}
