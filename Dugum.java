
public class Dugum {
    String deger;
    Dugum soldugum;
    Dugum sagdugum;
    int yukseklik;
    int sagyukseklik;
    int solyukseklik;

    public Dugum() {
        deger ="";
        soldugum=null;
        sagdugum=null;
    }

    public Dugum(String deger) {
        this.deger = deger;
        soldugum=null;
        sagdugum=null;
    }
    public int yuksekligial(){
     if(this.soldugum!=null){
         solyukseklik=soldugum.yuksekligial()+1;
     }
     else{
         solyukseklik=0;
     }
     if(this.sagdugum!=null){
         sagyukseklik=sagdugum.yuksekligial()+1;
     }
     else{
         sagyukseklik=0;
     }
     if(sagyukseklik<solyukseklik){
         yukseklik=solyukseklik;
     }
     else{
         yukseklik=sagyukseklik;
     }
    return yukseklik;             
    }
    
    public int yukseklikfarki(){
        int hdif;
        if(this.soldugum==null){
            solyukseklik=0;
        }
        else{
            solyukseklik=soldugum.yuksekligial()+1;
        }
        if(this.sagdugum==null){
            sagyukseklik=0;
        }
        else {
        	sagyukseklik=sagdugum.yuksekligial()+1;
        }
        hdif=sagyukseklik-solyukseklik;
        return hdif;
    }
    
    public int[] solsagyukseklik() {
    	int[] a=new int[2];  
    	
    	if(this.soldugum==null){
            solyukseklik=0;
        }
        else if(this.soldugum!=null){
            solyukseklik=soldugum.yuksekligial()+1;
        }
        if(this.sagdugum==null){
            sagyukseklik=0;
        }
        else if(this.sagdugum!=null){
            sagyukseklik=sagdugum.yuksekligial()+1;
        }
        a[0]=solyukseklik;
        a[1]=sagyukseklik;
    	return a; 
    }
}
