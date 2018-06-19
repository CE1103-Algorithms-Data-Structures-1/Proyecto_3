
package Structures_Logic;

/**
 * Algoritmo de Floyd utilizado para encontrar las rutas más rápidas de un vertice a cualquier otro.
 *
 * @author dgarcia
 */
public class Floyd{

  public String Floyd(long [][] matrizAd){
    int vert = matrizAd.length;
    long [][] matADyacencia = matrizAd;
    String [][] path  = new String [vert][vert];
    String [][] auxPath = new String [vert][vert];
    String roadTravel = "", cad = "",paths ="";
    int i,j,k;
    float temp1,temp2,temp3,temp4,min;
    //Inicializando matriz de caminos y caminos auxiliares

    for(i = 0;i<vert;i++){
      for(j=0;j<vert;j++){
        path[i][j] = "";
        auxPath[i][j] = "";
      }
    }
    for(k = 0; k<vert ; k++){
      for(i = 0; i<vert ; i++){
        for(j = 0 ; j<vert ; j++){
          temp1 = matADyacencia[i][j];
          temp2 = matADyacencia[i][k];
          temp3 = matADyacencia[k][j];
          temp4 = temp2+temp3;
          //encotrando minimo
          min = Math.min(temp1,temp4);
          if(temp1 != temp4){
            if(min == temp4){
              roadTravel ="";
              auxPath[i][j] = k+"";
              path[i][j] = this.pathsR(i ,k , auxPath,roadTravel)+(k+1);
            }
          }
          matADyacencia[i][j] = (long)min;
        }
      }
    }

    for(i = 0 ;i <vert ; i++){
      for(j = 0 ; j<vert ; j++){
        cad = cad + "["+matADyacencia[i][j]+"]";
      }
      cad = cad+"\n";
    }
    //////////////////////
    for(i = 0 ; i<vert ; i++){
      for(j = 0 ; j<vert; j++){
         if(matADyacencia[i][j] != 1000000000){
           if(i != j){
             if(path[i][j].equals("")){
               paths+="De ("+(i+1)+"---->"+(j+1)+") Ir por..("+(i+1)+", "+(j+1)+")\n";


             }else{
               paths+="De ("+(i+1)+"---->"+(j+1)+") Ir por..("+(i+1)+", "+path[i][j]+", "+(j+1)+")\n";

             }
           }
         }
      }
    }
    return "Matriz de caminos cortos: \n"+cad+
    "\nDiferentes caminos:"+paths;

  }

  public String pathsR(int i , int k , String[][] auxP , String roadT){
    if(auxP[i][k].equals("")){
      return "";
    }else{
      //Recursividad
      roadT += pathsR(i,Integer.parseInt(auxP[i][k]),auxP,roadT)+(Integer.parseInt(auxP[i][k])+1)+", ";
      return roadT;
    }
  }
}
