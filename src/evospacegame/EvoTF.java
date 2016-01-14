/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evospacegame;

import java.util.ArrayList;
import org.uncommons.watchmaker.framework.PopulationData;
import org.uncommons.watchmaker.framework.TerminationCondition;

/**
 *
 * @author Caner-Hekim
 */
public class EvoTF implements TerminationCondition {

    @Override
    public boolean shouldTerminate(PopulationData<?> pd) {
       
     if ( pd.getGenerationNumber() >= 4) {
            
             
       
        ArrayList<Integer> best=(ArrayList<Integer>)pd.getBestCandidate();
            
            System.out.printf("Time %.3f seconds  ", pd.getElapsedTime() / 1000.0);
            System.out.println("\t Generation =" + pd.getGenerationNumber() + "\t BestFit " + pd.getBestCandidateFitness());
            System.out.println("Best Attributes Width= "+best.get(0)+",Height="+best.get(1)+" , DetectRange= "+best.get(2)+",Speed= "+best.get(3)+", Level ="+best.get(4)+"");
            System.out.print("END\"");
            return true;
        } else {
            return false;
        }
    
    }
    
}
