/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evospacegame;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import javax.naming.spi.DirStateFactory;
import org.uncommons.maths.binary.BitString;
import org.uncommons.maths.number.NumberGenerator;
import org.uncommons.maths.random.MersenneTwisterRNG;
import org.uncommons.maths.random.PoissonGenerator;
import org.uncommons.maths.random.Probability;
import org.uncommons.watchmaker.framework.CandidateFactory;
import org.uncommons.watchmaker.framework.EvolutionaryOperator;
import org.uncommons.watchmaker.framework.FitnessEvaluator;
import org.uncommons.watchmaker.framework.GenerationalEvolutionEngine;
import org.uncommons.watchmaker.framework.factories.ListPermutationFactory;
import org.uncommons.watchmaker.framework.operators.BitStringCrossover;
import org.uncommons.watchmaker.framework.operators.BitStringMutation;
import org.uncommons.watchmaker.framework.operators.EvolutionPipeline;
import org.uncommons.watchmaker.framework.operators.ListCrossover;
import org.uncommons.watchmaker.framework.operators.ListOrderCrossover;
import org.uncommons.watchmaker.framework.operators.ListOrderMutation;
import org.uncommons.watchmaker.framework.selection.TournamentSelection;

import org.uncommons.watchmaker.framework.termination.TargetFitness;
import java.util.HashMap;
import java.util.Map;
import org.uncommons.watchmaker.examples.EvolutionLogger;

/**
 *
 * @author Caner-Hekim
 */
public class EvoSpaceMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        // TODO code application logic here
         
         ArrayList<Integer> temparli = new ArrayList<>();
         Random r = new Random();
         int num=0;
         for (int i = 1; i <= 5; i++) {
             num=r.nextInt()%11;
             if(num<0)
                 num*=-1;
            temparli.add(num);
        }
        ListPermutationFactory<Integer> lpf = new ListPermutationFactory<Integer>(temparli);
         
         ListOrderCrossover<List<Integer>> loc = new ListOrderCrossover<List<Integer>>(new Probability(0.5d));
        ListOrderMutation<List<Integer>> lom = new ListOrderMutation<List<Integer>>(new PoissonGenerator(1.5d, new Random()),
                new PoissonGenerator(1.5d, new Random()));
        List<EvolutionaryOperator<List<Integer>>> operators = new ArrayList<EvolutionaryOperator<List<Integer>>>(2);
        operators.add((EvolutionaryOperator) loc);
        operators.add((EvolutionaryOperator) lom);
         EvolutionaryOperator<List<Integer>> pipeline = new EvolutionPipeline<List<Integer>>(operators);
      
        
        GameEvoluator ge = new GameEvoluator();
        TournamentSelection t = new TournamentSelection(Probability.ONE);
         
          GenerationalEvolutionEngine<List<Integer>> engine = new GenerationalEvolutionEngine<List<Integer>>(lpf,
                pipeline,
                ge,
                t,
                new MersenneTwisterRNG());
          EvoTF etf = new EvoTF();
        engine.setSingleThreaded(true); // Performs better for very trivial fitness evaluations.
        engine.addEvolutionObserver(new EvolutionLogger<List<Integer>>());
        engine.evolve(5, // 5 individuals in each generation.
                             1, // use elitism.
                             etf); // .        
        
        
    }
    
}
