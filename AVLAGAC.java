
import java.util.Scanner;


public class AVLAGAC {
    public static Dugum kokdugum,iter;
    static Scanner input=new Scanner(System.in);
    public static void main(String[] args) {
        

        
        while(true){
            System.out.print("ekle/sil/ata/yazdir islemlerinden birini giriniz: ");
            String girilen=input.next().toLowerCase();
            
            switch(girilen){
                case "ekle":
                    System.out.print("Eklemek istediginiz elemani giriniz (en az 3 karakter):");
                    
                    String girileneleman=input.next().toLowerCase();
                    if(girileneleman.length()<3){
                        System.out.println("3 karakterden az girdiniz!");
                        
                    }
                    else{
                        try {
                        if(kokdugum==null){//eger liste bossa ekleneni kokdugum yap.
                        Dugum dugum1=new Dugum(girileneleman);
                        AVLAGAC.kokdugum=dugum1;
                        System.out.println("Eleman eklendi!");
                        
                    }
                    else{//eger kokdugum doluysa ekle metodunu cagir.
                        iter=kokdugum;//Ekle metodu hem recursive hem de iter'i icerdigi icin burada iter'i kokdugum'e esitliyoruz.
                        Ekle(girileneleman);
                        System.out.println("Eleman eklendi!");
                        Duzenle();//ekleme isleminin ardindan duzenleme islemini yap.
                        
                        
                    }
                        
                    } catch (Exception e) {
                        System.out.println("dogru girdiginizden emin olun!");
                    }
                    
                    }
                    
                    break;
                case "sil":
                    iter=kokdugum;//yine sil islemi de recursive ve iter kullaniliyor. dolayisiyla burada kokdugume esitledim.
                    System.out.print("Silmek istediginiz elemani giriniz:");
                    String silinecekdeger=input.next().toLowerCase();   
                    try {
                        Sil(silinecekdeger);
                        Duzenle();
                    } catch (Exception e) {
                        System.out.println("Dogru girdiginizden emin olun!");
                    }
                    break;
                case "yazdir":
                    iter=kokdugum;
                    System.out.print("kok : ");
                    Yazdir(kokdugum);//yazdir metodu Ata'dan farkli olarak kosulsuz listedeki tum elemanlari yazdiriyor.
                    break;
                case "ata":
                    Ata();
                    break;
                case"y":
                	int[] a =new int[2];
                	a[0]=kokdugum.solsagyukseklik()[0];
                	a[1]=kokdugum.solsagyukseklik()[1];
                	System.out.println(a[0]+" sol");
                	System.out.println(a[1]+" sag");
                	break;
                default :
                    System.out.println("Islem girdiginizden emin olun!");
            }
        }
    }
    public static void Ekle(String deger){
        Dugum dugum=new Dugum(deger);
        boolean tempb=kelimekiyasla(dugum.deger, iter.deger,'<');//girilen kelime ile iter'in degerini kiyasliyor ona gore iter soldugume ya da sag dugume ilerliyor.
        if(tempb==true){
            if(iter.soldugum!=null){
                iter=iter.soldugum;
                Ekle(deger);
                
            }
            else{
                iter.soldugum=dugum;

            }
        }
        else{
            if(iter.sagdugum!=null){
                iter=iter.sagdugum;
                Ekle(deger);
            }
            else{
                iter.sagdugum=dugum;
            }
        }  
    }
    public static void Sil(String silinecek){
        Dugum silinenyerinegelecek;
        Dugum temp = new Dugum();
        Dugum tasinaninparenti = new Dugum();//silinen elemanin yerine gelecek elemanin atasi
        iter=kokdugum;
        if(iter==null){
            System.out.println("Zaten liste Bos");
        }
        else{
            Dugum ParentofDeleting=parentibul(silinecek);//elemanin atasini buluyoruz. Eger kokdugum ise de kendisini return ediyor.
        if(ParentofDeleting==null){//eger null return ederse listede eleman yok demektir.
            System.out.println("Bu eleman listede yok.");     
        }
        else{//eleman listede varsa
                 if(ParentofDeleting.deger.equals(kokdugum.deger)){//eger atasi kokdugumse
                if(ParentofDeleting.sagdugum!=null){//eger null ise syntax hatasi vermesin diye 
                    if(ParentofDeleting.sagdugum.deger.equals(silinecek)){//silecegimiz elemanin, kokun sagdugumu olup olmadigina bakiyoruz
                        
                        temp=kokdugum.sagdugum;//eger oyleyse dugumun child'larini kaybetmemek icin temp degerinde tutuyoruz.
                        if(silinenyerine(ParentofDeleting.sagdugum)!=null){//syntax hatasi vermesin diye
                            kokdugum.sagdugum=silinenyerine(ParentofDeleting.sagdugum);//silinen yerine fonksiyonu silinen elemanin yerine hangi elemanin gelecegini buluyor bize.
                            System.out.println("Eleman silindi!");
                        }
                        else{
                            kokdugum.sagdugum=null;//eger silinenyerine metodu null deger dondururse yerine gelecek bir eleman yok demektir.
                            System.out.println("Eleman silindi!");
                        }
                        if(kokdugum.sagdugum!=null){//syntax hatasi vermemesi icin
                            if(kokdugum.sagdugum.soldugum!=null){// //syntax hatasi vermemesi icin
                            kokdugum.sagdugum.soldugum=temp.soldugum;
                            System.out.println("Eleman silindi!");
                        }
                        }
                        
                        
                    }
                }
            if(ParentofDeleting.soldugum!=null){//syntax hatasi vermemesi icin
                if(ParentofDeleting.soldugum.deger.equals(silinecek)){//simdi de sol dugume bakiyoruz.
                    temp=kokdugum.soldugum;
                    kokdugum.soldugum=silinenyerine(ParentofDeleting.soldugum);//elemani yerine aliyoruz.
                    if(temp.soldugum!=null){//syntax icin
                        kokdugum.soldugum=temp.soldugum;//herhangi bir elemanin kaybolmamasi icin
                    }
                    System.out.println("Eleman silindi!");
                }
                
            }

            }
            else{//eger silinecek elemanin atasi kokdugum degilse
                     
                boolean tempb=kelimekiyasla(silinecek, ParentofDeleting.deger,'<');//silinecek parent'inin solunda ise
                if(tempb==true){
                    silinenyerinegelecek=silinenyerine(ParentofDeleting.soldugum);//silinen eleman yerine gelecek elemani buluyoruz.
                    if(silinenyerinegelecek!=null){//eger silinen elemanin yerine gelebilecek eleman varsa
                        tasinaninparenti=parentibul(silinenyerine(ParentofDeleting.soldugum).deger);//silinen yerine gelecek elemanin parentini buluyoruz.
                        if(tasinaninparenti.soldugum.deger.equals(silinenyerinegelecek.deger)){//eger parentin solunda ise solunu siliyoruz. Tasiyoruz cunku
                        tasinaninparenti.soldugum=null;
                        }
                        else if(tasinaninparenti.sagdugum.equals(silinenyerinegelecek.deger)){//eger parentin saginda ise saginden siliyoruz.
                        tasinaninparenti.sagdugum=null;
                        }
                    }
                    if(silinenyerinegelecek==null){//eger yerine gelecek eleman yoksa
                        ParentofDeleting.soldugum=null;//silecegimiz elemani siliyoruz. yerine birsey getirmiyoruz.
                        System.out.println("Eleman silindi!");
                    }
                    else{//eger silinen yerine gelebilecek eleman varsa:
                        temp=ParentofDeleting.soldugum;//silinecek elemani temp'e aliyoruz cunku daha sonra ihtiyacimiz olacak.
                        ParentofDeleting.soldugum=silinenyerinegelecek;//yerine diger elemani getirdik.
                        if(temp.soldugum!=null){//eger silinenin soldugumu bos degilse onu da yerinegelen elemana baglamaliyiz.
                        	silinenyerinegelecek.soldugum=temp.soldugum;//bos olmadigi icin onu da yerlestirdik.

                        }
                        if(temp.sagdugum!=null){//eger silinen elemanin sagi null degilse sagini da tasiyoruz.
                        	silinenyerinegelecek.sagdugum=temp.sagdugum;


                        }
                        System.out.println("Eleman silindi!");
                    }
            
            
                }
                else{//silinecek parentinin sag dugumunde ise
                	//yukaridaki islemlerin aynisini sag eleman icin uyarladik.
                    silinenyerinegelecek=silinenyerine(ParentofDeleting.sagdugum);
                    if(silinenyerinegelecek!=null){
                        tasinaninparenti=parentibul(silinenyerine(ParentofDeleting.sagdugum).deger);
                        if(tasinaninparenti.sagdugum.deger.equals(silinenyerinegelecek.deger)){
                        tasinaninparenti.sagdugum=null;
                        }
                        else if(tasinaninparenti.sagdugum.deger.equals(silinenyerinegelecek.deger)){
                        tasinaninparenti.sagdugum=null;
                        }
                    }                        
                    if(silinenyerinegelecek==null){
                        ParentofDeleting.sagdugum=null;
                        System.out.println("Eleman silindi!");

                    }
                    else{
                        temp=ParentofDeleting.sagdugum;
                        ParentofDeleting.sagdugum=silinenyerinegelecek;
                        if(temp.soldugum!=null){
                            ParentofDeleting.sagdugum.soldugum=temp.soldugum;

                        }
                        
                        if(temp.sagdugum!=null){
                            ParentofDeleting.sagdugum.sagdugum=temp.sagdugum;

                        }
                        System.out.println("Eleman silindi!");
                    }   
                }
                
            }
            
        
            }
        } 
    }
    public static void Yazdir(Dugum dugum){//yazdir metodumuz recursive sekilde calisacak
        if(kokdugum!=null){//syntax hatasi almamak icin
            System.out.println(iter.deger);   
            if(iter.soldugum!=null && iter.sagdugum==null){//eger bir taraf null'se diger tarafi yazdirip iter'i o yone ilerletiyoruz.
            System.out.print(iter.deger+" degerinin ");
            iter=iter.soldugum;
            System.out.print("sol: ");
            Yazdir(iter.soldugum);
        }
        else if(iter.soldugum==null && iter.sagdugum!=null){//eger bir taraf null'se diger tarafi yazdirip iter'i o yone ilerletiyoruz.
            System.out.print(iter.deger+" degerinin ");
            iter=iter.sagdugum;
            System.out.print("sag: ");
            Yazdir(iter.sagdugum);
        }
        else if(iter.soldugum!=null && iter.sagdugum!=null){//eger iki taraf da null den farkli ise iki yone dogru fonksiyonu tekrar cagiriyoruz.
            Dugum tempsol, tempsag, tempkendi;
            tempsol=iter.soldugum;
            tempsag=iter.sagdugum;
            tempkendi=iter;
            System.out.print(iter.deger+" degerinin ");
            iter=tempsol;
            System.out.print("sol :");
            Yazdir(iter);
            iter=tempkendi;
            System.out.print(iter.deger+" degerinin ");
            System.out.print("sag: ");
            iter=tempsag;
            Yazdir(iter);
        }
        
        }
        else{
            System.out.println("Liste bos!");
        }
             
        
        
    }
    public static Dugum parentibul(String bulunacak){//bulunacak degeri cagiriyoruz, o degerin ata degerini buluyor.

    if(iter.deger.equals(bulunacak)){//eger bulmak istedigimiz kokdugumse, yine kendisini return ediyoruz.
       return kokdugum; 
    }
    else {//aranan kokdugumden farkliysa
        boolean tempb=kelimekiyasla(bulunacak, iter.deger,'<');
        boolean tempc=kelimekiyasla(bulunacak , iter.deger,'>');
        if(tempb==true){//iteri o yone dogru ilerletmek icin elemanin bulundugu yonu buluyoruz.
            if(iter.soldugum==null){//eger aradigimizin bulundugu yonde eleman yoksa, o eleman da listede yoktur.
                return null;
            }
        }
        else if(tempc==true){//sag yone dogru ilerleme kosulu
            if(iter.sagdugum==null){//usttekinin aynisi
                return null;
            }
        }
        if(iter!=null){//syntax hatasi engellemek icin
            if(iter.sagdugum==null&&iter.soldugum==null){
                return null;
            }
            else if(iter.soldugum==null){//eger iterin solu null'se sag tarafi kontrol et dogru ise return et degilse tekrar cagir
                if(iter.sagdugum.deger.equals(bulunacak)==false){
                    iter=iter.sagdugum;
                    parentibul(bulunacak);
                }
                else{
                    return iter;
                }
            }
            else if(iter.sagdugum==null){//yukaridakinin aynisi
                if(iter.soldugum.deger.equals(bulunacak)==false){
                    iter=iter.soldugum;
                parentibul(bulunacak);
                }
                else{
                    return iter;
                }
            }
            else{//eger ikisi de nullden farkli ise 
                if(iter.soldugum.deger.equals(bulunacak)==false && iter.sagdugum.deger.equals(bulunacak)==false){//ikisi de aranan degilse
                    tempb=kelimekiyasla(bulunacak, iter.deger,'<');
                    if(tempb==true){//tekrar kontorl ediyoruz yerini ne yondeyse oraya ilerliyoruz.
                        iter=iter.soldugum;
                    }
                    else{
                        iter=iter.sagdugum;
                    }
                parentibul(bulunacak);
                }
            }
        }
        
        
        
    }
        return iter;//parent'i buluyor.
    }
    public static Dugum elemanibul(String bulunacak){//parenti bul fonksiyonunun ayni islemlerini yapiyor ama dugumun kendisini buluyor.
    if(iter.deger.equals(bulunacak)){
       return kokdugum; 
    }
    else {//aranan kokdugumden farkliysa
        char[] bulunacakc=bulunacak.toCharArray();
        char[] iterc=iter.deger.toCharArray();
        boolean tempb=kelimekiyasla(bulunacak, iter.deger,'<');
        boolean tempc=kelimekiyasla(bulunacak, iter.deger,'>');
        if(tempb==true){
            if(iter.soldugum==null){
                return null;
            }
        }
        else if(tempc==true){
            if(iter.sagdugum==null){
                return null;
            }
        }
        if(iter!=null){
            if(iter.sagdugum==null&&iter.soldugum==null){
                return null;
            }
            else if(iter.soldugum==null){
                if(iter.sagdugum.deger.equals(bulunacak)==false){
                    iter=iter.sagdugum;
                    elemanibul(bulunacak);
                }
                else{
                    iter=iter.sagdugum;
                }
            }
            else if(iter.sagdugum==null){
                if(iter.soldugum.deger.equals(bulunacak)==false){
                    iter=iter.soldugum;
                elemanibul(bulunacak);
                }
                else{
                     iter=iter.soldugum;
                }
            }
            else{
                if(iter.soldugum.deger.equals(bulunacak)==false && iter.sagdugum.deger.equals(bulunacak)==false){
                     tempb=kelimekiyasla(bulunacak, iter.deger,'<');
                    if(tempb==true){
                        iter=iter.soldugum;
                    }
                    else{
                        iter=iter.sagdugum;
                    }
                elemanibul(bulunacak);
                }
            }
        }
        
        
        
    }
        return iter;
    }
    public static Dugum silinenyerine(Dugum dugum){//Silinenin yerine gececek olan elemani buluyoruz.
        if(dugum!=null){//bu metoda girebilecek null metodlar var dolayisiyla bu kosulu ekliyoruz.
            if(dugum.sagdugum!=null){//yerine gececek eleman, o elemandan buyuk olan en kucuk elemani getiriyorum dolayisiyla sagindaki dugume gelip eger varsa ordan en sola gidiyoruz
            dugum=dugum.sagdugum;
            while (dugum.soldugum!=null) {     
                dugum=dugum.soldugum;
            }
        }
        else if(dugum.soldugum!=null){//eger sagdugum null ise soldugumu koyuyoruz.
            dugum=dugum.soldugum;
        }
        else{
            dugum=null;//yaprak eleman ise
        }
        }
        return dugum;
    }
    public static void Ata(){
        if(kokdugum!=null){//liste bos ise syntax hatasi almamak icin
            iter=kokdugum;
        System.out.print("yazdirilacak elemani giriniz: ");
        String yazdirilacakdeger=input.next().toLowerCase();
        if(yazdirilacakdeger.equals(kokdugum.deger)){
          System.out.println("Bu eleman agacin kok dugumudur" );
        }
        iter=parentibul(yazdirilacakdeger);//yazdirilacak elemanin parenti
        if(iter.deger.equals(kokdugum.deger)== false){
          System.out.println("Atasi :" + iter.deger);
        }
        
        	if(iter.sagdugum!=null){//parentin diger dugumunu yazdirmamak icin
                if(iter.sagdugum.deger.equals(yazdirilacakdeger)){
                    System.out.println("degerinin sagi: "+ iter.sagdugum.deger);
                    iter=iter.sagdugum;
                }
            }
        	if(iter.soldugum!=null){//parentin diger dugumunu yazdirmamak icin
                if(iter.soldugum.deger.equals(yazdirilacakdeger)){
             	   Yazdir(iter.soldugum);
             	  System.out.print("degerinin solu: "+ iter.soldugum.deger);
                 iter=iter.soldugum;
                 } 
             }
        if(iter.soldugum!=null && iter.sagdugum==null){//hangi yon doluysa o yone dogru ilerleyip yazdir metodunu cagiriyoruz.
            System.out.print(iter.deger+" degerinin ");
            iter=iter.soldugum;
            System.out.print("sol: ");
            Yazdir(iter.soldugum);
        }
        else if(iter.soldugum==null && iter.sagdugum!=null){//hangi yon doluysa o yone dogru ilerleyip yazdir metodunu cagiriyoruz.
            System.out.print(iter.deger+" degerinin ");
            iter=iter.sagdugum;
            System.out.print("sag: ");
            Yazdir(iter.sagdugum);
        }
        else if(iter.soldugum!=null && iter.sagdugum!=null){//hangi yon doluysa o yone dogru ilerleyip yazdir metodunu cagiriyoruz.
            Dugum tempsol, tempsag, tempkendi;
            tempsol=iter.soldugum;
            tempsag=iter.sagdugum;
            tempkendi=iter;
            System.out.print(iter.deger+" degerinin ");
            iter=tempsol;
            System.out.print("sol :");
            Yazdir(iter);
            iter=tempkendi;
            System.out.print(iter.deger+" degerinin ");
            System.out.print("sag: ");
            iter=tempsag;
            Yazdir(iter);
        }
        }
        else{
            System.out.println("Liste bos!");
        }

    }
    public static void Duzenle(){//duzenleme algoritmalari dogru calismiyor. ufak syntax hatalari var fakat duzeltemedim.Yukseklik dogru hesaplaniyor ama algoritmalar calismiyor.
        Dugum iter1=kokdugum;
        if(kokdugum.yukseklikfarki()==2){//duzenlemler tam calismadigi icin koke ekstra condition ekledim denemek icin
            Dugum tempkok=new Dugum();
            tempkok.deger=kokdugum.deger;
            if(kokdugum.soldugum!=null){
                tempkok.soldugum=kokdugum.soldugum;
            }
            kokdugum=kokdugum.sagdugum;
            kokdugum.soldugum=tempkok;
            System.out.println("Agac duzenlendi!");
        }
        else if(kokdugum.yukseklikfarki()==-2){//duzenleme tam calismadigi icin koke ekstra condition ekledim denemek icin
            Dugum tempkok=new Dugum();
            tempkok.deger=kokdugum.deger;
            if(kokdugum.sagdugum!=null){
                tempkok.sagdugum=kokdugum.sagdugum;
            }
            kokdugum=kokdugum.soldugum;
            kokdugum.sagdugum=tempkok;
            System.out.println("Agac duzenlendi!");
        }
        else if(kokdugum.yukseklikfarki()>=2){//Eger dengesizlik Right ile basliyorsa
             iter1=kokdugum;
            while (iter1.yukseklikfarki()!=2) {//pivot degerimizi buluyoruz. yani dengesizlige sebep olan son eklenen elemanin 2 ustu
                if(iter1.yukseklikfarki()<0){
                    iter1=iter1.soldugum;
                }
                else{
                    iter1=iter1.sagdugum;
                }
            }
            if(iter1.yukseklikfarki()<=-2){//dengesizlik pivotun solunda mi saginda mi onu ogreniyoruz. Bu condition'da dengesizlik RL. cunku yukseklik sol tarafinki daha yuksekse -'li bir deger, sag taraf yuksekse +'li.
                if(iter1.soldugum.yukseklikfarki()<0){//dengesizlige sebep olan elemanin atasinin sag dugumu mu yoksa sol duguumu mu onu ogreniyoruz.
                    Dugum yenikokdugum=iter1.soldugum;
                       iter1.soldugum=iter1.soldugum.soldugum;//koke yerlesecek elemani pivot'un solundan aldigimiza gore yeni ekleneni pivot'un soldugumune aliyoruz.
                       Dugum tempkok=new Dugum();//kokdugumun yerine yeni biri gelecegi icin eski koku kaybetmemek icin bir tempkok olusturuyoruz.
                       tempkok.deger=kokdugum.deger;
                       tempkok.sagdugum=kokdugum.sagdugum;
                       Dugum temp2 = null;//eger koke yerlesecek olanin diger dugumu de doluysa onu da kaybetmemek icin temp degerine atayip tekrar ekleyecegiz.
                       if(kokdugum.soldugum!=null){//kokdugumun solu null ise hata vermesin diye onu da if kosulunun icinde yaziyoruz.
                           tempkok.soldugum=kokdugum.soldugum;
                       }
                       if(yenikokdugum.sagdugum!=null){//yeni kokdugum olacak degerin eger sag tarafi bos degilse kaybolmasin diye tutuyoruz.
                           temp2=yenikokdugum.sagdugum;
                       }
                       yenikokdugum.sagdugum=null;//gerekli degisme islemlerini yapiyoruz.
                       kokdugum=yenikokdugum;
                       kokdugum.soldugum=tempkok;
                       kokdugum.sagdugum=tempkok.sagdugum;
                       if(temp2!=null){
                           Ekle(temp2.deger);
                       }
                }//Yine RL dengesizligi. Bu kez dengesizlige sebep olan elemanin, atasinin sag dugumunde  mi yoksa sol dugumunde mi onu ogreniyoruz.
                else if(iter1.soldugum.yukseklikfarki()>0){//iter1 koke yerlesecek olanin atasini temsil ediyor.
                        Dugum yenikokdugum=iter1.soldugum;
                       iter1.soldugum=iter1.soldugum.sagdugum;//koke yerlesecek elemani pivot'un solundan aldigimiza gore yeni ekleneni pivot'un soldugumune aliyoruz.
                       Dugum tempkok=new Dugum();//kokdugumun yerine yeni biri gelecegi icin eski koku kaybetmemek icin bir tempkok olusturuyoruz.
                       tempkok.deger=kokdugum.deger;
                       tempkok.sagdugum=kokdugum.sagdugum;
                       Dugum temp2 = null;//eger koke yerlesecek olanin diger dugumu de doluysa onu da kaybetmemek icin temp degerine atayip tekrar ekleyecegiz.
                       if(kokdugum.soldugum!=null){//kokdugumun solu null ise hata vermesin diye onu da if kosulunun icinde yaziyoruz.
                           tempkok.soldugum=kokdugum.soldugum;
                       }
                       if(yenikokdugum.sagdugum!=null){
                           temp2=yenikokdugum.sagdugum;
                       }
                       yenikokdugum.soldugum=null;
                       kokdugum=yenikokdugum;
                       kokdugum.soldugum=tempkok;
                       kokdugum.sagdugum=tempkok.sagdugum;
                       if(temp2!=null){
                           Ekle(temp2.deger);
                       }
                }
            }
            else if(iter1.yukseklikfarki()>=2){//RR dengesizligi ise
                if(iter1.sagdugum.yukseklikfarki()>0){//dengesizligi bozan, atasinin saginda 
                    Dugum temp2=null;//eger tasinacak kokun child'i varsa.
                    Dugum iterparent=iter1;
                    Dugum tempkok=new Dugum();
                    while (parentibul(iterparent.deger)!=kokdugum) {//kokdugumun bir altindakini bul sonra onu kokdugum yap, kokdugumu de onun sagina ekle.eger kokdugumun altindakinin child'i varsa onu da listeye ekle.
                        iterparent=parentibul(iterparent.deger);
                        
                    }
                    if(iterparent.soldugum!=null){
                        temp2=iterparent.soldugum;
                    }
                    tempkok.deger=kokdugum.deger;
                    if(kokdugum.soldugum!=null){
                        tempkok.soldugum=kokdugum.soldugum;
                    }
                    kokdugum=iterparent;
                    iterparent.soldugum=tempkok;
                    if(temp2!=null){
                        iter=kokdugum;
                        Ekle(temp2.deger); 
                    }
                }
                else if(iter1.sagdugum.yukseklikfarki()<0){//yine RR. Dengesizligi bozan, atasinin solunda 
                	//yukardaki islemin aynisi
                        Dugum tasinacak=iter1.soldugum;
                    Dugum tempkok=new Dugum();
                        tempkok.deger=kokdugum.deger;
                    Dugum temp2=null;//eger tasinacak kokun child'i varsa.
                    Dugum iterparent=parentibul(iter1.deger);
                    while (iterparent!=kokdugum) {                        
                        iterparent=parentibul(iterparent.deger);
                    }
                    iterparent=kokdugum;
                    kokdugum.soldugum=tasinacak;
                    tasinacak.soldugum=tempkok;
                    if(tasinacak.soldugum!=null){
                        temp2=tasinacak.soldugum;
                    }
                    else if(tasinacak.sagdugum!=null){
                        temp2=tasinacak.sagdugum;
                    }
                    if(temp2!=null){
                        iter=kokdugum;

                        Ekle(temp2.deger);
                    }
                }
            }
            System.out.println("Agac duzenlendi!");
        }
       else if(kokdugum.yukseklikfarki()<=-2){//Dengesizlik left ile basliyorsa
             iter1=kokdugum;
            while (iter1.yukseklikfarki()!=-2) {//once dengesizligi bozanin 2 ustunu buluyoruz.
                if(iter1.yukseklikfarki()<0){
                    iter1=iter1.soldugum;
                }
                else{
                    iter1=iter1.sagdugum;
                }
            }
            if(iter1.yukseklikfarki()>=2){//Dengesizlik LR ise
                if(iter1.sagdugum.yukseklikfarki()<0){//dengesizligi bozan atasinin soluna eklenmis ise

                    Dugum yenikokdugum=iter1.sagdugum;
                       Dugum tempkok=new Dugum();
                       Dugum temp2 = null;//rl'deki islemin aynisi, dengesizligi bozanın atasini koke al dengesizligi bozani bi adim uste tasi.
                       if(yenikokdugum.sagdugum!=null){
                           temp2=yenikokdugum.sagdugum;
                       }
                       tempkok.deger=kokdugum.deger;
                       if(kokdugum.sagdugum!=null){
                           tempkok.sagdugum=kokdugum.sagdugum;
                       }
                       kokdugum=yenikokdugum;
                       yenikokdugum.sagdugum=tempkok;
                       if(temp2!=null){
                           Ekle(temp2.deger); 
                       } 
                }
                else if(iter1.sagdugum.yukseklikfarki()>0){//yine LR fakat bu kez dengesizligi bozan atasinin sagina eklenmis ise
                	System.out.println("girdi");
                        Dugum yenikokdugum=iter1.sagdugum;
                       Dugum tempkok=new Dugum();
                       Dugum temp2 = null;//rl'deki islemin aynisi, dengesizligi bozanın atasini koke al dengesizligi bozani bi adim uste tasi. 
                       if(yenikokdugum.soldugum!=null){
                           temp2=yenikokdugum.soldugum;
                       }
                       tempkok.deger=kokdugum.deger;
                       if(kokdugum.sagdugum!=null){
                           tempkok.sagdugum=kokdugum.sagdugum;
                       }

                       kokdugum=yenikokdugum;
                       yenikokdugum.sagdugum=tempkok;
                       if(temp2!=null){
                           Ekle(temp2.deger);
                       } 
            }
            }
            else if(iter1.yukseklikfarki()<=-2){//LL Dengesizligi ise
                if(iter1.soldugum.yukseklikfarki()>0){//Dengesizligi bozan atasinin sagina eklenmis ise
                        Dugum iterparent=iter1;
                       while (kokdugum!=parentibul(iterparent.deger)) {                        
                        iterparent=parentibul(iterparent.deger);//rr dengesizligindeki islemin aynisi ll icin de yapiliyor.
                    }
                    Dugum tempkok=new Dugum();
                    Dugum temp2 = null;
                    tempkok.deger=kokdugum.deger;
                    if(tempkok.sagdugum!=null){
                           tempkok.sagdugum=kokdugum.sagdugum;
                       }
                    if(iterparent.sagdugum!=null){
                           temp2=iterparent.sagdugum;
                       }
                       kokdugum=iterparent;
                       iterparent.sagdugum=tempkok;
                       if(temp2!=null){
                           Ekle(temp2.deger);
                       }
                }
                else if(iter1.sagdugum.yukseklikfarki()<0){//dengesizligi bozan atasinin soluna eklenmis ise
                       Dugum iterparent=iter1;
                       while (kokdugum!=parentibul(iterparent.deger)) { //rr dengesizligindeki islemin aynisi ll icin de yapiliyor.           
                       iterparent=parentibul(iterparent.deger);
                    }
                    Dugum tempkok=new Dugum();
                    Dugum temp2 = null;
                    tempkok.deger=kokdugum.deger;
                    if(kokdugum.sagdugum!=null){
                           tempkok.sagdugum=kokdugum.sagdugum;
                       }
                    if(iterparent.sagdugum!=null){
                           temp2=iterparent.sagdugum;
                       }
                       kokdugum=iterparent;
                       iterparent.sagdugum=tempkok;
                       if(temp2!=null){
                           Ekle(temp2.deger);
                       }
            }
        }
            System.out.println("Agac duzenlendi!");
        }
    }
    public static boolean kelimekiyasla(String a,String b,char d){//kelimeleri char dizisi haline getirip ilk 3 harfini kiyasliyor.
            char[] ac=a.toCharArray();
        char[] bc=b.toCharArray();
        boolean c = false;
        
        if(d=='<'){
            if(ac[0]<bc[0]){
            c=true;
            }
            else if(ac[0]==bc[0]){
                if(ac[1]<bc[1]){
                c=true;
                }
                else if(ac[1]==bc[1]){
                    if(ac[2]<bc[2]){
                        c=true;
                    }
                }
            }
        }
        else if(d=='>'){
            if(ac[0]>bc[0]){
            c=true;
            }
            else if(ac[0]==bc[0]){
                if(ac[1]>bc[1]){
                c=true;
                }
                else if(ac[1]==bc[1]){
                    if(ac[2]>bc[2]){
                        c=true;
                    }
                }
            }
        }
            return c;
    }
}
