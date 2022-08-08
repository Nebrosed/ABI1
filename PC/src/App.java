import java.io.*;
import java.nio.file.*;

public class App {


    public static void main(String[] args)throws Exception {

        String[][] BD; // importer de la BD
        String[][] cp; // tableau final
        int[][] bd; // ligne trier pour 5 point consecutif et nombre de point consecutif
        int[] ligne; // tableau des lignes en 7m et 15m
        int[][] CP; // tableau des coups de pince

        
        int k = 0;
        int m = 0;
        int n = 0;
        Double tempo = 0.0; // variable temporaire pour etape 4

        double[][] A = {
            {  8,  9,  7.893 },
            {  7, 10,  8.954 },
            {  6, 11, 10.515 },
            {  5, 12, 11.076 },
            {  4, 13, 12.137 },
            {  3, 14, 13.198 },
            {  2, 15, 14.259 },
            {  1, 16,  15.32 },};

            //System.out.println(k);

        /* etape 1
         * etape 2
         * 
         * 
         * 
         * 
         * 
         */

        int x = 0;
        String st;
        String[] mots = null;
        int size = 0; 
        
        File file = new File("DETECTION_DATA_ANODES2.txt");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((st = br.readLine()) != null){
                size = size + 1;
            }
        }
        //System.out.println(size);
        BD = new String[(size)][8];
        ligne = new int[(size)];

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {        

            while ((st = br.readLine()) != null){

                mots = st.split(",");
                for (int i = 0; i < 7; i ++){
                    BD[x][i] = mots[i];
                }    
                x = x +1;
                BD[x-1][7] = String.valueOf(x);
                
            }
        }

        //System.out.print(BD[8478][4]);
        //System.out.print(" - ");
        //System.out.println(BD[8478][7]);

        /*
         * 
         * etape 3
         * 
         * 
         * 
         * 
         */

        m = 0;
        for (int i = 1; i < size; i ++){
            
            if ((Double.valueOf(BD[i][4]) > 7.393) & (Double.valueOf(BD[i][4]) < 15.82)) {              
                ligne[m] = i;
                //System.out.println(ligne[m]);
                m = m + 1;               
            }
        } 
        bd = new int[(m)][8];
        cp = new String[bd.length][8];
        //System.out.println(bd.length);

        /*
         * 
         * 
         * etape 4
         * 
         * 
         * 
         */

        k = 0; // variable pour placer la ligne dans le tableau bd
        n = 0; // variable du nombre de point consecutif
        tempo = 0.0;

        tempo = Double.valueOf(BD[ligne[0]][4]);
        //System.out.print(ligne[0]);
        //System.out.print(" - ");
        //System.out.println(tempo);  
        n = 0;

        for (int i = 1; i < bd.length; i ++){
            if ((Double.valueOf(BD[ligne[i]][4]) - 0.4) < tempo & (Double.valueOf(BD[ligne[i]][4]) + 0.4) > tempo & ligne[i] == ligne[i - 1] + 1){
                    n = n + 1;
                    tempo = Double.valueOf(BD[ligne[i]][4]);
                    //System.out.print(ligne[i]-1);
                    //System.out.print(" - ");
                    //System.out.print(BD[ligne[i]-1][4]);
                    //System.out.print(" - ");
                    //System.out.print(ligne[i]);
                    //System.out.print(" - ");
                    //System.out.println(tempo);
            }
            else {
                if (n > 4){                    
                        bd[k][0] = ligne[i-1];  
                        bd[k][1] = n;      
                        //System.out.print(bd[k][0]);
                        //System.out.print(" - ");
                        //System.out.print(bd[k][1]);   
                        //System.out.print(" - ");
                        //System.out.println(k);           
                        k = k + 1;
                        tempo = Double.valueOf(BD[ligne[i]][4]);
                        n = 1;              
                }
                tempo = Double.valueOf(BD[ligne[i]][4]);
                n = 1;    
            }
            
        } 
        CP = new int[bd.length][2];

        /*
         * 
         * 
         * 
         * etape 5
         * 
         * 
         */

        n = 1; // variable du nombre de point consecutif
        k = 0;
        
        for (int j = 0; j < CP.length; j ++){
        
            for (int i = 0; i < 10; i ++){
            
                if ((Double.valueOf(BD[bd[j][0]+i][4]) > 3.9) & (Double.valueOf(BD[bd[j][0]+i][4]) < 5.1)){
                    n = n + 1;
                    if (n > 2){                    
                        CP[k][0] = bd[j][0]; 
                        CP[k][1] = bd[j][1];           
                        k = k + 1;
                        i = 10;
                        System.out.print(CP[k - 1][0]);
                        System.out.print(" - ");
                        System.out.println(k - 1);
                    }
                }
                else {
                    n = 1;
                }

            }  
            
        }

    }

}

