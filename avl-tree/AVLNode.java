public class AVLNode {
	     String key = null;
	     String veri = null;
	     AVLNode solDugum = null;
	     AVLNode sagDugum = null;
	     int height = 0;
	    public AVLNode (String key, String veri){
	        this.key = key;
	        this.veri = veri;
	        solDugum = null;
	        sagDugum = null;
	        height = 0;
	    }

	    public String getKey(){
	        return key;
	    }
	    
	    public String getVeri(){
	        return veri;
	    }

	    public AVLNode getSolDugum (){
	        return solDugum;
	    }

	    public void setSolDugum (AVLNode n)
	    {
	    	solDugum = n;
	    }
	    
	    public AVLNode getSagDugum (){
	        return sagDugum;
	    }

	    public void setSagDugum (AVLNode n){
	    	sagDugum = n;
	    }

	    public int getHeight (){
	        return height;
	    }

	    public void setHeight (int balance){
	        height = balance;
	    }
	}