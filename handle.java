package obj;
import java.util.*;
public class handle {
 Random rand;
	int [][] a;
	int [][] b;
	void anngangdoc(int i,int j)
	{
	    for(int x=0;x<9;++x)
	    {
	        b[i][x]=1;
	        b[x][j]=1;
	    }
	}
	void andoc(int i)
	{
	    for(int x=0;x<9;++x)
	    {
	        b[x][i]=1;
	    }
	}
	void anngang(int i)
	{
	    for(int x=0;x<9;++x)
	    {
	        b[i][x]=1;
	    }
	}
	void an6o(int x,int y)
	{
	    for(int i=x-1;i<=x;++i)
	    {
	        for(int j=y-2;j<=y;++j)
	        {
	            b[i][j]=1;
	        }
	    }
	}
	void eat2(int hang,int cot)//nổ loại ô 2
		{
		 if(a[hang][cot]==a[hang-1][cot]&&a[hang+2][cot]==a[hang][cot]&&hang>1||a[hang][cot]==a[hang+1][cot]&&a[hang+2][cot]==a[hang][cot]&&hang<7)
		 {
		     if(a[hang][cot]==a[hang][cot-1]&&a[hang-2][cot]==a[hang][cot-2]&&hang>1)
		     {
		         b[hang][cot]+=2;
		     }

		    else if(a[hang][cot]==a[hang][cot+1]&&a[hang][cot]==a[hang][cot+1]&&hang>1)
		     {
		         b[hang][cot]+=2;
		     }
		 }
		 if(a[hang][cot]==a[hang][cot-1]&&a[hang][cot-2]==a[hang][cot]&&cot>1||a[hang][cot]==a[hang][cot+1]&&a[hang][cot+2]==a[hang][cot]&&cot<7)
		 {
		     if(a[hang][cot]==a[hang-1][cot]&&a[hang+2][cot]==a[hang][cot]&&hang>1||a[hang][cot]==a[hang+1][cot]&&a[hang+2][cot]==a[hang][cot]&&hang<7)
		 {
		      b[hang][cot]+=2;
		 }
		 }
		}
		void eat34(int x, int y)// xuat hien loại no 3 hoặc loại 4
		{
		    if(a[x][y]==a[x][y-1]&&a[x][y]==a[x][y-2]&&a[x][y]==a[x][y-3]&&y>2)
		    {
		        if(a[x][y-1]!=a[x][y]&&a[x][y+4]!=a[x][y])
		        {
		        b[x][y]+=3;}
		        else { b[x][y]+=4;}
		    }
		    else if(a[x][y]==a[x][y+1]&&a[x][y]==a[x][y+2]&&a[x][y]==a[x][y+3]&&y<7)
		    {
		      if(a[x][y+1]!=a[x][y]&&a[x][y-4]!=a[x][y])
		        {
		        b[x][y]+=3;}
		        else { b[x][y]+=4;}
		    }
		    else if(a[x][y]==a[x][y+1]&&a[x][y]==a[x][y+2]&&a[x][y]==a[x][y-1]&&y<7)
		    {
		     if(a[x][y-2]!=a[x][y]&&a[x][y+3]!=a[x][y])
		        {
		        b[x][y]+=3;}
		        else { b[x][y]+=4;}
		    }
		    else if(a[x][y]==a[x][y+1]&&a[x][y]==a[x][y-2]&&a[x][y]==a[x][y-1]&&y<7)
		    {
		      if(a[x][y+2]!=a[x][y]&&a[x][y-3]!=a[x][y])
		        {
		        b[x][y]+=3;}
		        else { b[x][y]+=4;}
		    }

		}
	 void checkngang(int a[][], int hang)
	{ if(hang<9){
	    int i=0;
	    while(i<7){
	        if(a[hang][i]==a[hang][i+1]&&a[hang][i]==a[hang][i+2])
	        {
	          while(a[hang][i]==a[hang][i+1]&&i<8)
	            {
	                b[hang][i]=1;
	                ++i;
	                if(b[hang][i]==4)
	                {
	                    anngangdoc(hang,i);
	                }
	            }
	            b[hang][i]=1;
	        } else { ++i;}
	    }
	    
	    }
	}
	void checkcot(int a[][], int cot)
	{ if(cot<9){
	    int i=0;
	    while(i<7){
	        if(a[i][cot]==a[i+1][cot]&&a[i][cot]==a[i+2][cot])
	        {
	            while(a[i][cot]==a[i+1][cot]&&i<8)
	            {
	                b[i][cot]=1;
	                ++i;
	            }
	            b[i][cot]=1;
	        } else { ++i;}
	    }

	    
	    }
	}
	void moveo(int a[][],int cot,int hang)
	{
	    for(int i=hang;i>=1;--i)
	    {
	        swap(a[i-1][cot],a[i][cot]);
	    }
	}
	private void swap(int i, int j) {
		int c=i;
		i=j;
		j=i;
		
	}
	void sapxep(int cot)
	{
	    if(cot<9){
	    for(int i=0;i<9;++i)
	    {
	        if(b[i][cot]==1)
	        {
	            a[i][cot]=rand % 5+1;
	            b[i][cot]=0;
	            moveo(a,cot,i);
	            moveo(b,cot,i);
	        }
	    }
	    sapxep(cot+1);
	    }

	}}