/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evospacegame;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/** @see http://stackoverflow.com/questions/6067898 */
public class SpinSlider extends JPanel {

  

    public SpinSlider() {
        this.setLayout(new FlowLayout());
        final JSpinner spinner = new JSpinner();
        final JSlider slider = new JSlider();
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider s = (JSlider) e.getSource();
                spinner.setValue(s.getValue());
            }
        });
        this.add(slider);
        spinner.setModel(new SpinnerNumberModel(50, 0, 100, 1));
        spinner.setEditor(new JSpinner.NumberEditor(spinner, "0'%'"));
        spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner s = (JSpinner) e.getSource();
                slider.setValue((Integer) s.getValue());
            }
        });
        this.add(spinner);
    }
}
