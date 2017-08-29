package taller2;

public class Ascensor {

	
	private int piso=1;
	
	@InvocacionMultiple
        @LoggerAnnotation
	public void subir1Piso(){
		piso ++;
		System.out.println("Subiendo 1 piso " + piso);
	}
	
	@InvocacionMultiple
        @LoggerAnnotation
	public void bajar1Piso(){
		piso --;
		System.out.println("Bajando 1 piso " + piso);
	}
	@InvocacionMultiple(vecesAInvocar=2)
        @LoggerAnnotation
        @PostConstructorAnnotation
	public void subir2Pisos(){
		piso ++;
		System.out.println("Subiendo 2 pisos " + piso);
	}
	
	@InvocacionMultiple(vecesAInvocar=3)
        @LoggerAnnotation
        @PostConstructorAnnotation
	public void bajar3Pisos(){
		piso --;
		System.out.println("Bajando 3 pisos " + piso);
	}
	
	
}
