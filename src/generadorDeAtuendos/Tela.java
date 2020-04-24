package generadorDeAtuendos;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

class Tela{
	Material material;
	Trama trama;
	List<String> colores = new LinkedList<>(); 
	
	Tela(Material unMaterial, Optional<Trama> unaTrama){
		material = unMaterial;
		trama = unaTrama.isPresent()? unaTrama.get() : Trama.lisa;
	}
	
	void agregarColor(String nuevoColor){
		colores.add(nuevoColor);
	}
	
	//validadores
	void validarDatos(){
		if(material==null) throw new RuntimeException("Falta ingresar material");
		if(colores.isEmpty()) throw new RuntimeException("Falta ingresar color/es");
	}
	
	//Getters	
	List<String>colores(){
		return colores;
	}
}

enum Trama{
	lisa, rayada, lunares, cuadros, estampado;
}

enum Material{
	lana, cuero, tela;
}