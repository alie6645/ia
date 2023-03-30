package data.processes;

import java.util.ArrayList;
import java.util.List;

public class CompostCalculator {
    public Material fruits = new Material("fruit",0.52,0.026);
    public Material vegetables = new Material("veg",0.513,0.024);
    public Material mixed = new Material("mix", 0.516,0.025);
    public List<Material> materials = new ArrayList<Material>();
    public int[] calculate(double startC, double startN){
        int last = materials.size() + 1;
        double[] best = null;
        for (int i=0; i<Math.pow(10,materials.size()); i++){
            double[] combo = new double[last];
            double remaining = i;
            double carbon = 0;
            double nitrogen = 0;
            for (int j=0; j<materials.size(); j++){
                combo[j] = remaining/(Math.pow(10,j));
                remaining = (remaining%Math.pow(10,j));
                carbon += combo[j] * materials.get(j).carbon;
                nitrogen += combo[j] * materials.get(j).nitrogen;
                combo[last] = Math.abs((carbon + startC) / (nitrogen + startN) - 30);
            }
            if (i==0){
                best = combo;
            } else if (combo[last]<best[last]){
                best = combo;
            }
        }
        return null;
    }

    public void addMaterial(String name, double carbon, double nitrogen){
        materials.add(new Material(name, carbon, nitrogen));
    }

    public double getStartN(double fruit, double veg, double mix){
        return fruit*fruits.nitrogen + veg * vegetables.nitrogen + mix * mixed.nitrogen;
    }

    public double getStartC(double fruit, double veg, double mix){
        return fruit*fruits.carbon + veg * vegetables.carbon + mix * mixed.carbon;
    }
}
