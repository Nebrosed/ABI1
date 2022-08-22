import java.io.*;
import java.nio.file.*;
import java.time.ZoneId;
import java.time.ZoneOffset;

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
        //https://github.com/Nebrosed/ABI1/blob/ef5b82968537c1d85728f105f441b8840e3fd16a/PC/DETECTION_DATA_ANODES2.txt
        //https://github.com/Nebrosed/ABI1/blob/main/PC/DETECTION_DATA_ANODES2.txt
        ///workspace/ABI1/PC/DETECTION_DATA_ANODES2.txt
        //PC/DETECTION_DATA_ANODES2.txt

        File file = new File("PC/DETECTION_DATA_ANODES3.csv");
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while ((st = br.readLine()) != null){
                size = size + 1;
            }
        }
        //System.out.println(size);
        BD = new String[(size)][8]; // tableau 
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
        CP = new int[k][2];

        /*
         * 
         * 
         * 
         * etape 5
         * 
         * 
         */

        n = 0; // variable du nombre de point consecutif
        k = 0;
        int z = 0;

        for (int j = 0; j < CP.length; j ++){
        
            for (int i = 0; i < 10; i ++){
            
                if ((Double.valueOf(BD[bd[j][0]+i][4]) > 3.8) & (Double.valueOf(BD[bd[j][0]+i][4]) < 5.2)){

                    i = 10; 

                    for (int l = 0; l < 10; l ++){

                        if (((Double.valueOf(BD[bd[j][0]-bd[j][1]-l][4]) > 5.2) & (Double.valueOf(BD[bd[j][0]-bd[j][1]-l][4]) < 6))){
                                
                            n = n + 1;
        
                        }
        
                        if (CP[k - z][0] == (Double.valueOf(BD[bd[j][0]-bd[j][1]-l][7])) || n > 1){

                            l = 10;
                            CP[k][0] = bd[j][0];
                            CP[k][1] = bd[j][1];  
                            k = k + 1;
                            z = 1;
                        
                            //System.out.print(BD[bd[j][0]][7]);
                            //System.out.print(bd[j][0]);
                            //System.out.print(" - ");
                            System.out.print(CP[k - 1][0]);
                            System.out.print(" - ");
                            System.out.print(CP[k - 1][1]);
                            System.out.print(" - ");
                            System.out.println(k - 1);
        
                        }

                    }

                }
                
            }                       
            
        }

        /*
         * 
         * 
         * 
         * 
         * etape 6
         * 
         */

        //FileWriter fw = new FileWriter("PC/DETECTION_DATA_ANODES2.txt");

        String fileName = "PC/DETECTION_DATA_ANODES2.csv";
        String encoding = "UTF-8";
        PrintWriter writer = new PrintWriter(fileName, encoding);

                        writer.print("anode_number,");  
                        writer.print("location_name,");    
                        writer.print("scope_time,");  
                        writer.print("timestamp,");    
                        writer.println("line_number,");  

        
        for (int j = 0; j < k; j ++){

            //System.out.print(BD[CP[j][0] - 1][4]);
            //System.out.print(" - ");
            //System.out.println(j);

            for (int i = 0; i < 8; i ++){
            
                if ((Double.valueOf(BD[CP[j][0] - 1][4]) > A[i][2] - 0.5305) & (Double.valueOf(BD[CP[j][0] - 1][4]) <= A[i][2] + 0.5305)){                  
                    cp[j][0] = String.valueOf(A[i][0]); // numero de l<anode
                        //System.out.print(j);
                        //System.out.print(" - ");  
                        writer.print(cp[j][0]+" - "+A[i][1]);
                        writer.print(",");                  
                    cp[j][1] = String.valueOf(BD[CP[j][0]-1][5]); // nom de la location
                        writer.print(cp[j][1]);
                        writer.print(",");
                    cp[j][2] = String.valueOf(CP[j][1] * 5); // nombre de temps passer a l<anode
                        writer.print(cp[j][2]);
                        writer.print("sec,");
                    cp[j][3] = BD[Integer.valueOf(CP[j][0])][6]; // timestamp
                        writer.print(cp[j][3]);
                        writer.print(",");
                    cp[j][4] = String.valueOf(CP[j][0]); // numero de la ligne
                        writer.println(cp[j][4]);

                    i = 8;
                }
                
            }

        }
        writer.close();

        /*
         * 
         * 
         * 
         * 
         * 
         * etape 7
         */

        
    }

}

