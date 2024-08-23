package serach.engine.demo.CrawlingData;

import serach.engine.demo.CrawlingData.WebGraph;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PageRanking {
    WebGraph Wb;
    double Ranking;
    int Number_of_Elements;
    List<Page> C;
    public PageRanking(WebGraph WG){
        this.Wb=WG;
        this.Ranking=1/this.Wb.getAllGraph().size();
        this.Number_of_Elements=this.Wb.getAllGraph().size();
        this.C=WG.getAllGraph();
    }

    public double[][] CalculateRank(){
        double[][] MatriceAdjacent=this.TransformeToAdjascent();
        double[][] MatrixTransition=this.TransformeToTransition(MatriceAdjacent);
        double threashould=0.0001;
        double beta=0.8;
        double[][] r_new=this.ColumnMatrix();
        double[][] r_prev=r_new;
        double[][] Teleported=this.TeleportedMatrix(r_new,beta);
        double [][] TransitionMultpliedByBeta=this.TeleportedMatrix(MatrixTransition,beta);
        while(true){
            r_new=this.Sommation(this.multiplyMatrices(TransitionMultpliedByBeta,r_prev),Teleported);

            if(this.Difference(r_new,r_prev)<threashould){
                break;
            }
            r_prev=r_new;
        }
        return r_new;

    }

    public double[][] TransformeToAdjascent(){
        double [][] adjacent=new double[this.Number_of_Elements][this.Number_of_Elements];
        for(int i=0;i<Number_of_Elements;i++){
            for(int j=0;j<Number_of_Elements;j++){
                if(this.Wb.ifPointed(this.C.get(j).url,this.C.get(i).url)){
                    System.out.println("yes");
                    adjacent[i][j]=1;
                }
            }
        }
       /* for(int i=0;i<adjacent.length;i++){
            for(int j=0;j<adjacent[i].length;j++){
                System.out.println(adjacent[i][j]);
            }
        }*/
        return adjacent;
    }
    public double[][] TransformeToTransition(double[][] Adjascent) {
        double[][] Transition = new double[Adjascent.length][Adjascent.length];
        for (int j = 0; j < Adjascent.length; j++) {
            int n = 0;
            for (int i = 0; i < Adjascent.length; i++) {
                n += Adjascent[i][j];
            }
            if (n != 0) {
                for (int i = 0; i < Adjascent.length; i++) {
                    Transition[i][j] = Adjascent[i][j] / (double) n;
                }
            }
        }
        /*for(int i=0;i<Transition.length;i++){
            for(int j=0;j<Transition[i].length;j++){
                System.out.println(Transition[i][j]);
            }
        }*/
        return Transition;
    }

    public double[][] ColumnMatrix() {
        double[][] r = new double[this.Number_of_Elements][1];
        for (int i = 0; i < r.length; i++) {
            r[i][0] = 1.0 / this.Number_of_Elements;
        }
        return r;
    }

    public  double[][] multiplyMatrices(double[][] matrixA, double[][] matrixB) {
        int m = matrixA.length;
        int n = matrixA[0].length;
        int p = matrixB[0].length;

        double[][] result = new double[m][p];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < p; j++) {
                result[i][j] = 0;
                for (int k = 0; k < n; k++) {
                    result[i][j] += matrixA[i][k] * matrixB[k][j];
                }
            }
        }

        return result;
    }
    public double[][] TeleportedMatrix(double [][] r,double beta){
        double [][] Teleported=new double[this.Number_of_Elements][r[0].length];
        if(r[0].length==1){
            for(int i=0;i<Teleported.length;i++){
                for(int j=0;j<Teleported[i].length;j++){
                    Teleported[i][j]=r[i][j]*(1-beta);
                }
            }
        }
        else {
            for(int i=0;i<Teleported.length;i++){
                for(int j=0;j<Teleported[i].length;j++){
                    Teleported[i][j]=r[i][j]*(beta);
                }
            }
        }

        return Teleported;
    }

    public double Difference(double[][] r_new,double[][] r_prev){
        double n=0;
        for(int i=0;i<r_prev.length;i++){
            for (int j=0;j<r_prev[i].length;j++){
                n+= Math.abs(r_new[i][j] - r_prev[i][j]);
            }
        }
        return n;
    }
    public double[][] Sommation(double[][] r1,double[][] r2){
        double[][] Sum=new double[r1.length][r1[0].length];
        for (int i=0;i<r2.length;i++){
            for (int j=0;j<r2[i].length;j++){
                Sum[i][j]=r1[i][j]+r2[i][j];
            }
        }
        return Sum;
    }

}
