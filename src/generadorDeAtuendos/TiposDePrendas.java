package generadorDeAtuendos;

interface TipoPrenda{
	Categoria categoria();
}

class Remera implements TipoPrenda{
	public Categoria categoria(){
		return Categoria.parteSuperior;
	}
}
	
class Zapatos implements TipoPrenda{
	public Categoria categoria(){
		return Categoria.calzado;
	}
}

class camisaMangasCortas implements TipoPrenda{
	public Categoria categoria(){
		return Categoria.parteSuperior;
	}
}
	
class pantalon implements TipoPrenda{
	public Categoria categoria(){
		return Categoria.parteInferior;
	}
}
	
class anteojos implements TipoPrenda{
	public Categoria categoria(){
		return Categoria.accesorios;
	}
}


enum Categoria{
	parteSuperior,
	calzado,
	parteInferior,
	accesorios
}
