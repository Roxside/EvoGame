/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evospacegame;

import java.util.List;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

/**
 *
 * @author Caner-Hekim
 */
public class GameEvoluator implements FitnessEvaluator<List<Integer>>{

    @Override
    public double getFitness(List<Integer> candidate, List<? extends List<Integer>> population) {
    
        GameTutoria g = new GameTutoria();
        g.initialize(candidate.get(0),candidate.get(1),candidate.get(2),candidate.get(3),candidate.get(4));
       double fit=0.0;
       fit= g.run();
       g.dispose();
       int sum=0;
            
       for (int i = 0; i < candidate.size(); i++) {
       sum+=candidate.get(i);
            }
       System.out.print ("Fitness "+fit);
       System.out.println("Best Attributes Width= "+candidate.get(0)+",Height="+candidate.get(1)+" , DetectRange= "+candidate.get(2)+",Speed= "+candidate.get(3)+", Level ="+candidate.get(4)+"");
            
       
    return fit;
    
    }

    @Override
    public boolean isNatural() {
        return true;
    }
    
}
