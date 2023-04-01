package data.processes;

import java.util.ArrayList;
import java.util.List;

public class CompostCalculator {
    public Material fruitsMat = new Material("fruit",0.52,0.026);
    public Material vegetablesMat = new Material("veg",0.513,0.024);
    public Material mixedMat = new Material("mix", 0.516,0.025);
    public List<Material> materials = new ArrayList<Material>();
    private final int max = 20;
    private double fruit;
    private double veg;
    private double mix;
    private double[] brown;

    public double[] calculate(double startC, double startN){
        if (startC>30*startN){
            double[] empty = new double[getMaterialCount() + 1];
            for (int i=0; i<getMaterialCount(); i++){
                empty[i] = 0;
            }
            return empty;
        }
        int last = materials.size();
        double[] best = null;
        for (int i=0; i<Math.pow(max,materials.size()); i++){
            double[] combo = new double[last+1];
            double remaining = i;
            double carbon = 0;
            double nitrogen = 0;
            for (int j=materials.size()-1; j>=0; j--){
                combo[j] = remaining%max;
                remaining = (int)(remaining/Math.pow(max,j));
                carbon += combo[j] * materials.get(j).carbon;
                nitrogen += combo[j] * materials.get(j).nitrogen;
                combo[last] = (Math.abs(((carbon + startC) / (nitrogen + startN)) - 30));
            }
            if (i==0){
                best = combo;
            } else if (combo[last]<best[last]){
                best = combo;
            }
        }
        return best;
    }

    public void addMaterial(String name, double carbon, double nitrogen){
        materials.add(new Material(name, carbon, nitrogen));
    }

    public double getStartN(){
        return fruit* fruitsMat.nitrogen + veg * vegetablesMat.nitrogen + mix * mixedMat.nitrogen;
    }

    public double getStartC(){
        return fruit* fruitsMat.carbon + veg * vegetablesMat.carbon + mix * mixedMat.carbon;
    }

    public double[] getStartValues(){
        double carbon = getStartC();
        double nitrogen = getStartN();
        for (int i=0; i<getMaterialCount(); i++){
            carbon += brown[i] * materials.get(i).carbon;
            nitrogen += brown[i] * materials.get(i).nitrogen;
        }
        return new double[]{carbon, nitrogen};
    }

    public double getRatio(){
        double brownC = 0;
        double brownN = 0;
        for (int i=0; i< materials.size(); i++){
            Material active = materials.get(i);
            double amount = brown[i];
            brownC += active.carbon * amount;
            brownN += active.nitrogen * amount;
        }
        return (getStartC() + brownC) / (getStartN() + brownN);
    }

    public void setFruit(double fruit) {
        this.fruit = fruit;
    }

    public void setVeg(double veg) {
        this.veg = veg;
    }

    public void setMix(double mix) {
        this.mix = mix;
    }

    public void setBrown(double[] brown) {
        this.brown = brown;
    }

    public double getFruit() {
        return fruit;
    }

    public double getVeg() {
        return veg;
    }

    public double getMix() {
        return mix;
    }

    public int getMaterialCount(){
        return materials.size();
    }
}
