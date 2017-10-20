import java.util.ArrayList;
import java.util.Scanner;

public class Principal {

	public static void main(String args[]) throws InterruptedException{
        Scanner scan = new Scanner( System.in );

        System.out.println("Escolha o número de cartões:");
        Integer tam= scan.nextInt();
		System.out.println("Agora pense em um número de 0 até " + ((int) Math.pow(2, tam)-1) + ". (Digite \"ok\" quando escolher)");
		scan.next();
		
		ArrayList<ArrayList<Integer>> bin = new ArrayList<>();
		for(int i=0; i<tam; i++) bin.add(new ArrayList<>());

		for( int num=0; num<Math.pow(2, tam); num++ ) testaGrupos(num, bin, tam);
	
		for(ArrayList<Integer> aux: bin) printaGrupo(aux, tam, bin);
		
		System.out.println("Digite os cartões em que seu número aparece (Digite \"ok\" quando acabar. Digite 0 se não aparecer em nenhum cartão)");
		
		System.out.println(daResultado(scan));

		scan.close();
	}

	private static int daResultado(Scanner scan) {
 		String quebra = scan.next();
		return (int) (quebra.equalsIgnoreCase("ok") || quebra.equalsIgnoreCase("0")? 0 : daResultado(scan) + Math.pow(2, (Integer.parseInt(quebra)-1)));
	}

	private static void printaGrupo(ArrayList<Integer> aux, Integer tam, ArrayList<ArrayList<Integer>> bin) {
		System.out.print("Cartão: " + (bin.indexOf(aux)+1));
		for( int i =0; i < aux.size(); i++){
			if(i%(tam-1) == 0) System.out.println("");
			System.out.format("%5d", aux.get(i));
		}
		System.out.println("\n");
		
	}

	private static void testaGrupos(int num, ArrayList<ArrayList<Integer>> bin, Integer tam) {
		String a =completaZeros( Integer.toBinaryString(num),tam);
		for(int aux = 0;aux < tam; aux++)
			if(a.substring(aux, aux+1).equals("1")) bin.get(tam-1-aux).add(num);
	}

	private static String completaZeros(String a, Integer tam) {
		return a.length() < tam ? a=completaZeros(0 + a, tam) : a;
	}

}
