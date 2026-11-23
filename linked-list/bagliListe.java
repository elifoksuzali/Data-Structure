package iki_yonlu_BaglantýlýListe;

class Link {
	public long veri; // Düðüme gelecek olan veri
	public Link sonraki; // Listede sonraki düðüm
	public Link onceki; // Listede önceki düðüm
	// -------------------------------------------------------------

	public Link(long d) // Verinin standart geldiði yapýlandýrýcý
	{
		veri = d;
	}

	// -------------------------------------------------------------
	public void listele() // yazdýrma fonksiyonu
	{
		System.out.print(veri + " ");
	}
	// -------------------------------------------------------------
} 

class IkiYonluListe {
	private Link ilk; // ilk veriyi tutan referans
	private Link son; // son veriyi tutan referans
	// -------------------------------------------------------------

	public IkiYonluListe() // Liste yapýlandýrýcýsý
	{
		ilk = null; //  Listeyi boþ olarak tanýmlýyoruz.
		son = null;
	}

	// -------------------------------------------------------------
	public boolean bosMu() // Liste doluluk kontrolü
	{
		return ilk == null;
	}

	// -------------------------------------------------------------
	public void basaEkle(long dd) // Listenin baþýna ekleme fonksiyonu
	{
		Link yeniDugum = new Link(dd); // yeni düðüm oluþturuluyor

		if (bosMu()) // Listenin boþluk kontrolü
			son = yeniDugum;
		else
			ilk.onceki = yeniDugum; // Eski ilk ile yer deðiþiyor
		yeniDugum.sonraki = ilk; 
		ilk = yeniDugum; 
	}


	public void sonaEkle(long dd) // Listenin sonuna ekleme fonksiyonu
	{
		Link yeniDugum = new Link(dd); // yeni düðüm oluþturuyoruz.
		if (bosMu()) //Listenin boþluk kontrolü
			ilk = yeniDugum; 
		else {
			son.sonraki = yeniDugum; // son ile yeni düðüm yer deðiþiyor.
			yeniDugum.onceki = son;
		}
		son = yeniDugum; 
	}


	public Link bastanSil() // Baþtaki veri silme fonksiyonu
	{
		Link yedek = ilk;
		if (ilk.sonraki == null) // Eðer sadece 1 veri varsa
			son = null; // sonuncuyu boþa çýkartýyoruz.
		else
			ilk.sonraki.onceki = null; 
		ilk = ilk.sonraki; 
		return yedek;
	}

	// -------------------------------------------------------------
	public Link sondanSil() // Sondan silme fonksiyonu
	{ 
		Link yedek = son;
		if (ilk.sonraki == null) // Eðer sadece 1 veri varsa
			ilk = null; // ilk veri boþa çýkýyor
		else
			son.onceki.sonraki = null;
		son = son.onceki;
		return yedek;
	}

	public boolean arkasinaEkle(long anahtar, long dd) { 
		Link aktif = ilk;
		while (aktif.veri != anahtar) // Eþleþme bulunana kadar
		{
			aktif = aktif.sonraki; // her seferinde döngü döndürüyoruz
			if (aktif == null)
				return false; // Bulunmayýnca false dönüyor
		}
		Link yeniDugum = new Link(dd); // Yeni düðüm oluþturuyoruz

		if (aktif == son) // Eðer son veri ise
		{
			yeniDugum.sonraki = null; // yeni düðüm boþa çýkýyor
			son = yeniDugum;
		} else
		{
			yeniDugum.sonraki = aktif.sonraki; 
						
			aktif.sonraki.onceki = yeniDugum;
		}
		yeniDugum.onceki = aktif; 
		aktif.sonraki = yeniDugum; 
		return true; 
	}

	public Link seciliSil(long anahtar) // Seçili sýradaki elemaný silmek için kullanýlan fonksiyon
	{ 
		Link aktif = ilk; 
		while (aktif.veri != anahtar) // Eþleþme bulana kadar
		{
			aktif = aktif.sonraki; // Döngü sürekli bir sonrakine aktarýlýyor
			if (aktif == null)
				return null; // eþleþme bulunamadý
		}
		if (aktif == ilk) 
			ilk = aktif.sonraki; 
		else 
				
			aktif.onceki.sonraki = aktif.sonraki;

		if (aktif == son) 
			son = aktif.onceki; 
		else 
			
			aktif.sonraki.onceki = aktif.onceki;
		return aktif; // Bulunan deðer döndürülüyor
	}

	public void yazdir() {
		System.out.print("Liste : ");
		Link aktif = ilk; 
		while (aktif != null) // Listenin sonuna kadar kontrol ediliyor
		{
			aktif.listele(); // Veri yazdýrýlýyor
			aktif = aktif.sonraki; // Döngü döndürülüyor
		}
		System.out.println("");
	}

} 
