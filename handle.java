package obj;
import java.util.*;
public class handle {

	
		distance[] ngang;
		distance[] doc;
		static int b[][];
        public handle(distance []ngang, distance []doc)
        {
        	this.ngang=ngang;
        	this.doc=doc;
        }
        void expand(int a[][],int i,int j )
		 {
			 if(a[i][j]==a[i][j+1]&&b[i][j+1]!=1&&b[i][j+2]!=1&&i<7)
			 {
			     if(a[i][j]==a[i][j+2])
                {
				 b[i][j+1]=1;
				 b[i][j]=1;
				 b[i][j+2]=1;
				 expand(a, i,j+2);
				 }
				 else {
                   if(a[i][j-1]==a[i][j])
                   {
                       b[i][j+1]=1;
                   }
				 }
			 }
			 if(a[i][j]==a[i][j-1]&&b[i][j-1]!=1&&b[i][j-2]!=1&&i>1)
			 {
			     if(a[i][j]==a[i][j-2])
                {
				 b[i][j-1]=1;
				 b[i][j]=1;
				 b[i][j-2]=1;
				 expand(a, i,j-2);}
				 else{
                   if(a[i][j+1]==a[i][j])
                   {
                       b[i][j-1]=1;
                   }
				 }
			 }
			 if(a[i][j]==a[i+1][j]&&b[i+1][j]!=1&&b[i+2][j]!=1&&i<7)
			 {
			     if(a[i][j]==a[i+2][j])
                {
				 b[i+1][j]=1;
				 b[i][j]=1;
				 b[i+2][j]=1;
				 expand(a, i,j+2);}
				 else 
                {
                    if(a[i-1][j]==a[i][j])
                   {
                       b[i+1][j]=1;
                   }
                }
			 }
			 if(a[i][j]==a[i-1][j]&&b[i-1][j]!=1&&b[i-2][j]!=1&&i>1)
			 {
			     if(a[i][j]==a[i-2][j])
                {
                b[i-1][j]=1;
				 b[i][j]=1;
				 b[i-2][j]=1;
				 expand(a, i,j+2);}
				 else {
                    if(a[i+1][j]==a[i][j])
                   {
                       b[i-1][j]=1;
                   }
				 }
			 }
		 }
		
}
