/* Code for COMP-102-112 - 2021T1, Assignment 3
 * Name: Annie Cho
 * Username: choanni
 * ID: 300575457
 */

import ecs100.*;
import java.awt.Color;
import java.util.*;

/** The program contains several methods for analysing the readings of the temperature levels over the course of a day.
 *  There are several things about the temperature levels that a user may be interested in: 
 *    The average temperature level.
 *    How the temperatures rose and fell over the day.
 *    The maximum and the minimum temperature levels during the day.
 */
public class TemperatureAnalyser{

    /* analyse reads a sequence of temperature levels from the user and prints out
     *    average, maximum, and minimum level and plots all the levels
     *    by calling appropriate methods
     */
    public void analyse(){
        UI.clearPanes();
        ArrayList<Double> listOfNumbers = UI.askNumbers("Enter levels, end with 'done': ");
        if (listOfNumbers.size() != 0) {
            this.printAverage(listOfNumbers);
            this.plotLevels(listOfNumbers);

            UI.printf("Maximum level was:  %f\n", this.maximumOfList(listOfNumbers));
            UI.printf("Minimum level was:  %f\n", this.minimumOfList(listOfNumbers));
        }
        else {
            UI.println("No readings");
        }
    }

    /** Print the average level
     *   - There is guaranteed to be at least one level,
     *   - The method will need a variable to keep track of the sum, which 
     *     needs to be initialised to an appropriate value.
     *  CORE
     */
    public void printAverage(ArrayList<Double> listOfNumbers) {
        double sum = 0;
        for(int i = 0; i < listOfNumbers.size(); i++){
            sum += listOfNumbers.get(i);
        }
        UI.println(sum/listOfNumbers.size());

    }

    /**
     * Plot a bar graph of the sequence of levels,
     * using narrow rectangles whose heights are equal to the level.
     * [Core]
     *   - Plot the bars.
     * [Completion]
     *   - Draws a horizontal line for the x-axis (or baseline) without any labels.
     *   - Any level greater than 400 should be plotted as if it were just 400, putting an
     *         asterisk ("*") above it to show that it has been cut off.
     * [Challenge:] 
     *   - The graph should also have labels on the axes, roughly every 50 pixels.
     *   - The graph should also draw negative temperature levels correctly.
     *   - Scale the y-axis and the bars so that the largest numbers and the smallest just fit on the graph.
     *     The numbers on the y axis should reflect the scaling.
     *   - Scale the x-axis so that all the bars fit in the window.
     */
    public void plotLevels(ArrayList<Double> listOfNumbers) {
        int base = 420;              //base of the graph
        int left = 50;               //left of the graph
        int step = 50;               //distance between plotted points
        
        UI.setColor(Color.blue);
        for (int i = 0; i<listOfNumbers.size(); i++){
            if (listOfNumbers.get(i) > 400){
                UI.fillRect(left+(step*i), 20, 40, 400); // caps it at 400
                UI.drawString("*", left+(step*i)+18, 18);
            }
            else{
                UI.fillRect(left+(step*i), base-listOfNumbers.get(i), 40, listOfNumbers.get(i));
        }
    }
        UI.setColor(Color.black);
        UI.drawLine(left, base, left+(50*listOfNumbers.size()), base);
            
        UI.println("Finished plotting");
    }

    /** Find and return the maximum level in the list
     *   - There is guaranteed to be at least one level,
     *   - The method will need a variable to keep track of the maximum, which
     *     needs to be initialised to an appropriate value.
     *  COMPLETION
     */
    public double maximumOfList(ArrayList<Double> listOfNumbers) {
        int i = 0;
        double max = listOfNumbers.get(0); // max is the first value in the list
        for (i = 0; i<listOfNumbers.size(); i++){
            if (listOfNumbers.get(i) > max){
                max = listOfNumbers.get(i);
            }
        }
        return max;
    }

    /** Find and return the minimum level in the list
     *   - There is guaranteed to be at least one level,
     *   - The method will need a variable to keep track of the minimum, which
     *     needs to be initialised to an appropriate value.
     *  COMPLETION
     */
    public double minimumOfList(ArrayList<Double> listOfNumbers) {
        int i = 0;
        double min = listOfNumbers.get(0); // max is the first value in the list
        for (i = 0; i<listOfNumbers.size(); i++){
            if (listOfNumbers.get(i) < min){
                min = listOfNumbers.get(i);
            }
        }
        return min;
    }

    public void setupGUI() {
        UI.initialise();
        UI.addButton("Analyse", this::analyse );
        UI.addButton("Quit", UI::quit );
    }

    public static void main(String[] args) {
        TemperatureAnalyser ta = new TemperatureAnalyser();
        ta.setupGUI();
    }

}
