import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		String veri;
		AVLTree agac = new AVLTree();
		do {
			Scanner console = new Scanner(System.in);
			System.out.println( "\nLÜTFEN YAPACAÐINIZ ÝÞLEMÝ SEÇÝNÝZ:\n"
					+ "-->DÜÐÜM EKLEME 1:\n" 
					+ "-->DÜÐÜM SÝLME 2:\n"
					+ "-->DÜÐÜM ATA 3 :\n");
			   int kontrol = console.nextInt();
			   Scanner girilenEleman = new Scanner(System.in);	// kullaýcýdan alýna veriler için
			   switch (kontrol) {
               case 1:
                   System.out.println("DÜÐÜM EKLE");
                   String eleman = girilenEleman.next();
                   agac.Ekle(eleman);
                   System.out.println(" BAÞARIYLA ELEMAN EKLENDÝ :");
                   agac.yazdýr();
                   break;
               case 2:
                   System.out.print(" DÜÐÜM SÝL :  ");
                   String eleman1 =girilenEleman.next();
                   System.out.println(" BAÞARIYLA ELEMAN SÝLÝNDÝ :");
                   agac.Sil(eleman1);
                   System.out.println(" SÝLME ÝÞLEMÝNDEN SONRAKÝ AÐACIN HALÝ :");
                   agac.yazdýr();
                   break;
               case 3:
                   System.out.print(" ATA  :  ");
                   String eleman2 =girilenEleman.next();
                   agac.AtaBul(agac.kok(),eleman2);
                   break;
               default:
                   System.out.print("ÝÞLEMÝ BÝTTÝ");
                       break;
	}	} while (true);	
}}
