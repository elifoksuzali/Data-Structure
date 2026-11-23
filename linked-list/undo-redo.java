package teslim;
import java.util.ArrayList;
import java.util.Scanner;


import teslim.Link;
public class Main {
	public static void main(String[] args) {

			IkiYonluListe Liste = new IkiYonluListe(); // kullanýcýn girdiði elemanlarýn aktarýldýðý liste
			IkiYonluListe Liste2 =new IkiYonluListe(); // redo iþlemlerinde sayýlarýn kaybolmmamsý için olan liste
			int sayac=0;
				
do {               // kullanýcýdan alýna komutlara göre hareket ediyoruz
                 
					Scanner console = new Scanner(System.in);		
				    System.out.println("-----------------------HOÞ GELDÝNÝZ------------------\n"
								                    + "LÜTFEN YAPACAÐINIZ ÝÞLEMÝ SEÇÝNÝZ:\n"
								                    + "ÝÞLEMLER:\n"
								                    + "---------------------\n"
								                    + "-->LÝSTENÝN BAÞINA ELEMAN EKLEME ÝÞLEMÝ ÝÇÝN  1 TUÞLAYINIZ:\n"
								                    + "-->UNDO ÝÞLEMÝ ÝÇÝN 2 TUÞLAYINIZ:\n"
								                    + "-->REDO  ÝÞLEMÝ ÝÇÝN 3 TUÞLAYINIZ:\n"
								                    + "-->LÝSTENÝN SONUNA ELEMAN EKLEME ÝÞLEMÝ ÝÇÝN 4 TUÞLAYINIZ:\n"
								                    + "-->LÝSTELEMEK ÝÇÝN 5 TUÞLAYINIZ:\n");
						
					int kontrol =console.nextInt();
				    Scanner girilenEleman = new Scanner(System.in);	// kullaýcýdan alýna veriler için
					switch(kontrol) {
					  case 1:
						
						       System.out.println("LÝSTENÝN BAÞINA ELEMAN EKLE");
							   System.out.print("LÜTFEN EKLEMEK ÝSTEDÝÐÝNÝZ ELEMANI GÝRÝNÝZ :");
						       int eleman =girilenEleman.nextInt();
							   Liste=Liste.basaEkle(Liste,eleman);//basaEkle methodu ile asýl listeye veri ekliyoruz 
							   System.out.print(" BAÞARIYLA ELEMAN EKLENDÝ :");
							   Liste.listele(Liste);// listeliyoruz
							   sayac=1;
							break;
							
							
						case 2:
						     
							   System.out.print(" UNDO YAPILAN ELEMAN:  ");
							   Link ListeCikanEleman=Liste.cikar(Liste);// classdan çýkar metotu çaðýrýp ListeCýkanEleman deðiþkeni 
							   //tanýmlýyoruz ardýndan asýl liste için çýkar metotudunu çaðrýp asýl listeden o veriyi çýkartoyoruz
						       System.out.print(" UNDO ÝÞLEMÝNDEN SONRAKÝ GÜNCEL LÝSTE");
							   Liste.listele(Liste);//çýkardýktan sonra listeler
							   // kaybolmasýn diye tutuðunuz eleman
							   System.out.print(" UNDO YAPILANIN KAYBOLMAMASI ÝÇÝN TUTLAN LÝSTEYE EKLENDÝ VE REDO ÝÇÝN BEKLEYENE ELEMAN:");
						       Liste2 =Liste2.basaEkle(Liste2, ListeCikanEleman.veri);//çýkan elemaný getirip geçici listemize ekliyoruz
						       Liste2.listele(Liste2);// listeliyoruz
						       
							break;
							
						case 3:
							
						   if(sayac==1){
							   System.out.println("UNDO ÝÞLEMÝNDEN SONRA ELEMAN EKLENÝRSE REDO YAPAMAZSINIZ");
						 
						   }else {
	                           System.out.print(" REDO YAPILAN ELEMAN: ");
							   Link Liste2Eleman= Liste2.cikar(Liste2);// geçici listeden gidip asýl listeden çýkradýðýmýz verileri geri ekliyoruz
							   System.out.print(" REDO ÝÞLEMÝNDEN SONRAKÝ GÜNCEL LÝSTE");
							   Liste =Liste.basaEkle(Liste, Liste2Eleman.veri);// Liste2 ye git o veriyi al git asýl liseteye ekle demek istiyoruz
							   System.out.println("YENÝ ELEMAN EKLENDÝKTEN SONRA ÖNCEKÝ UNDOLAR GERÝ ALINAMAZ "); 
							   Liste.listele(Liste);// listeleiyoruz
						         }
					  break; 
					    case 4:
								
								System.out.println("LÝSTENÝN SONUNA ELEMAN EKLE");// sona eleman ekleme
						    	System.out.print("LÜTFEN EKLEMEK ÝSTEDÝÐÝNÝZ ELEMANI GÝRÝNÝZ :");
							    int eleman1 =girilenEleman.nextInt();
								Liste=Liste.sonaEkle(Liste,eleman1);//sonaEkle metodu çaðrýlýyor ve girilen veri gidip sona eklenir
								System.out.print(" BAÞARIYLA ELEMAN EKLENDÝ :");
								Liste.listele(Liste);// ekleme iþleminden sonra listeleme yapýlýyor
								
						   break;
						   
					    case 5:

					           System.out.print("TÜM VERÝLER YAZDIRILDI");
						       Liste.listele(Liste);// listeye tüm eklenmiþ veriler yazdýrýlýyor 
						    break;
							
		     	        default:
				
				                 System.out.print("ÝÞLEMÝ BÝTÝRMEK ÝÇÝN SIFIRI TUÞLAYINIZ  ");
		                         int islem = console.nextInt();//iþimiz bittikten sonra iþlemi sonlandýrmak için yapýlan kýsým
		                         if (islem == 0) {
		                         System.out.println(" ÝÞLEM SONLANDI. ");	
				           break;
                 	}
          }	
     }while(true);// þart doðru oldukça çalýþsýn diye true yaptýk

   }
}
