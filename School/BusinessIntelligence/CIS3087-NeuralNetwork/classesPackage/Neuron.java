package classesPackage;

public class Neuron {
    public int numOfWeights;
    public double input, output;
    public double[] weights ;
    public Neuron(int numOfWeights, input){
        this.numOfWeights = numOfWeights;
        this.input = input;
    }

    public void setWeights(){
        weights = new double[numOfWeights];
        for(int i = 0; i < weights.length; i++){
            weights[i] = Math.random() * (1 + 1) - 1;
        }
    }

    public void setInput(){

    }


}
