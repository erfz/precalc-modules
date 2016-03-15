import java.util.*;

public class precalcModules{
  public static void main (String[] args){
    
    // double x = 0;
    // String y = "";
    // double magnitude = 0;
    
    // Scanner s1 = new Scanner(System.in);

    // System.out.println();
    
    // boolean getInputBoolean = false;
    // while(!getInputBoolean){
    //     try{
    //       System.out.println("Please enter an angle in degrees: ");
    //       x = s1.nextDouble();
    //       getInputBoolean = true;
    //     }
    //     catch(Exception e){
    //       System.err.println(e);
    //       System.out.println("\nYou entered an invalid input. Try again.");
    //       s1.nextLine();
    //     }
    // }

    // System.out.println();

    // getInputBoolean = false;
    // while(!getInputBoolean){
    //     try{
    //       System.out.println("Please enter the orientation of the bearing (NE, SW, etc.): ");
    //       y = s1.next();
    //       if(y.equals("NE") || y.equals("EN") || y.equals("ES") || y.equals("SE") || y.equals("SW") || y.equals("WS") || y.equals("WN") || y.equals("NW")){
    //         getInputBoolean = true;
    //       }
    //       else{
    //         System.out.println("\nYou did not enter a valid orientation. Try again.");
    //       }
    //     }
    //     catch(Exception e){
    //       System.err.println(e);
    //       System.out.println("\nYou entered an invalid input. Try again.");
    //       s1.nextLine();
    //     }
    //   }

    // System.out.println();

    // getInputBoolean = false;
    // while(!getInputBoolean){
    //     try{
    //       System.out.println("Please enter the magnitude of the vector: ");
    //       magnitude = s1.nextDouble();
    //       getInputBoolean = true;
    //     }
    //     catch(Exception e){
    //       System.err.println(e);
    //       System.out.println("\nYou entered an invalid input. Try again.");
    //       s1.nextLine();
    //     }
    //   }
    
    // s1.close();
    
    // angleClass a = new angleClass(x, y);
    // vectorClass resultantVector = vectorClass.polarToComponent(magnitude, a);
    // System.out.println("\nResultant vector: ");
    // resultantVector.printVector();                                                                    // Obtains magnitude and bearing and prints vector

    vectorClass v = new vectorClass(8, -3);
    vectorClass w = new vectorClass(-5, 2);
    vectorClass u = new vectorClass(-6, 16);
    // System.out.println(vectorClass.getAngleBetween(w, u));
    // vectorClass vectorw_u = w.proj(u);
    // vectorw_u.printVector();
    System.out.println(angleClass.getAngle(w.getComponent(1), w.getComponent(2)));

    // vectorClass b = new vectorClass(48209180, 512390194, 64938298);
    // System.out.println(vectorClass.dotProduct(a,b));

    // System.out.println(a.proj(b).magnitude() * b.magnitude()); // small rounding error

  }
}

class angleClass{
  
  private double theta;
  private String orientation;
  
  public angleClass(double theta_0, String initialOrientation){
   
    theta = theta_0;
    orientation = initialOrientation;
  
  }
  
  public void printAngle(){
  
    System.out.println(theta);
    
    return;
  
  }
  
  public double reorient(){
    
    if (orientation.equals("NE")){
    
      theta = 90-theta;
      
      return theta;
    
    }
   
    if (orientation.equals("EN")) {
    
      return theta;
    
    }
  
    if (orientation.equals("ES")){
    
      theta = 360-theta;
      
      return theta;
    
    }
  
    if (orientation.equals("SE")){
      
      theta = 270+theta;
      
      return theta;
    
    }
 
    if (orientation.equals("SW")){
      
      theta = 270-theta;
      
      return theta;
    
    }
 
    if (orientation.equals("WS")){
      
      theta = 180+theta;
      
      return theta;
    
    }
 
    if (orientation.equals("WN")){
      
      theta = 180-theta;
      
      return theta;
    
    }

    if (orientation.equals("NW")){
      
      theta = 90+theta;

      return theta;
  
    }
  
    else {
  
      throw new IllegalArgumentException("Invalid input");
    
    }
  }

  public static double getAngle(double x, double y){
    
    double angle = Math.atan2(y, x);
    angle = Math.toDegrees(angle);

    return angle;
  }
}

class vectorClass{

  private int arrayLength;
  private double[] vectorArray;
  
  public vectorClass(double... vectorComponents){
    
    arrayLength = vectorComponents.length;

    vectorArray = new double[arrayLength];

    for (int i = 0; i < arrayLength; ++i){
    	vectorArray[i] = vectorComponents[i];
    }
  }

  public double getComponent(int value){
   
  	return vectorArray[value-1];

  }

  public void printVector(){
    
    System.out.print("(");
    for (int i = 0; i < vectorArray.length-1; ++i){
    	System.out.print(getComponent(i+1) + ", ");
    }
    
    System.out.print(getComponent(vectorArray.length) + ")");

  }

  public double magnitude(){
    
    double squareSum = 0;
    for (int i = 0; i < vectorArray.length; ++i){
    	squareSum += Math.pow(vectorArray[i],2);

    }
    
    double norm = Math.sqrt(squareSum);

    return norm;
  
  }

  public vectorClass normalize(){
    
    double norm = magnitude();
    for (int i = 0; i < vectorArray.length; ++i){
    	vectorArray[i] = vectorArray[i]/norm;
    }
    
    vectorClass normalizedVector = new vectorClass(vectorArray);
    
    return normalizedVector;
  
  }

  public static vectorClass vectorSum(vectorClass... vectors){

    int max = 0;

    for (int i = 0; i < vectors.length; ++i){
      if (vectors[i].vectorArray.length > max){
        max = vectors[i].vectorArray.length;
      }
    }

    double[] sum = new double[max];
    
    for (int i = 0; i < vectors.length; ++i){
      vectorClass vector = vectors[i];
      for (int j = 0; j < vector.vectorArray.length; ++j){
      	double x = vector.getComponent(j+1);
      	sum[j] += x;

  		}

  	}
 
    vectorClass summedVector = new vectorClass(sum);
    
    return summedVector;

  }

  public static double dotProduct(vectorClass vector1, vectorClass vector2){
    
    int arrayLength = 0;
    double[] vectorSum;
    
    if (vector1.vectorArray.length < vector2.vectorArray.length){
      arrayLength = vector1.vectorArray.length;
    }
    else{
      arrayLength = vector2.vectorArray.length;
    }
    
    vectorSum = new double[arrayLength];
    for (int i = 0; i < arrayLength; ++i){
      vectorSum[i] = vector1.vectorArray[i] * vector2.vectorArray[i];
    }

//    if (vector1.vectorArray.length > vector2.vectorArray.length){
//      arrayLength = vector1.vectorArray.length;
//      vectorSum = new double[arrayLength];
//      double[] arrayOfZeros = new double[vector1.vectorArray.length-vector2.vectorArray.length];
//      double[] vector2_convert = new double[vector1.vectorArray.length];
//      System.arraycopy(vector2.vectorArray, 0, vector2_convert, 0, vector2.vectorArray.length);
//      System.arraycopy(arrayOfZeros, 0, vector2_convert, vector2.vectorArray.length, arrayOfZeros.length);
//
//      for (int i = 0; i < arrayLength; ++i){
//        vectorSum[i] += vector1.vectorArray[i] * vector2_convert[i];
//      }
//    }
//    else if (vector1.vectorArray.length < vector2.vectorArray.length){
//      arrayLength = vector2.vectorArray.length;
//      vectorSum = new double[arrayLength];
//      double[] arrayOfZeros = new double[vector2.vectorArray.length-vector1.vectorArray.length];
//      double[] vector1_convert = new double[vector2.vectorArray.length];
//      System.arraycopy(vector1.vectorArray, 0, vector1_convert, 0, vector1.vectorArray.length);
//      System.arraycopy(arrayOfZeros, 0, vector1_convert, vector1.vectorArray.length, arrayOfZeros.length);
//
//      for (int i = 0; i < arrayLength; ++i){
//        vectorSum[i] += vector2.vectorArray[i] * vector1_convert[i];
//      }
//    }
//    else{
//      arrayLength = vector1.vectorArray.length;
//      vectorSum = new double[arrayLength];
//      for (int i = 0; i < arrayLength; ++i){
//        vectorSum[i] += vector1.vectorArray[i] * vector2.vectorArray[i];
//      }
//    }
    
    return java.util.stream.DoubleStream.of(vectorSum).sum();

  }

  public vectorClass scalarProduct(double k){
    
    for (int i = 0; i < this.vectorArray.length; ++i){
    	vectorArray[i] = k*vectorArray[i];
    }

    vectorClass vector_k = new vectorClass(vectorArray);

    return vector_k;

  }

  public static vectorClass polarToComponent(double magnitude, angleClass angle){

    double angle1 = angle.reorient();
    angle1 = Math.toRadians(angle1);
    vectorClass polarVector = new vectorClass(magnitude*Math.cos(angle1), magnitude*Math.sin(angle1));

    return polarVector;

  }

  public static double getAngleBetween(vectorClass vector1, vectorClass vector2){
    
    double dotProduct = vectorClass.dotProduct(vector1, vector2);
    double magnitude_vector1 = vector1.magnitude();
    double magnitude_vector2 = vector2.magnitude();
    double angleBetween = Math.acos(dotProduct/(magnitude_vector1 * magnitude_vector2));      // Simple usage of arccos here works because we only care about the smallest angle between the vectors
    angleBetween = Math.toDegrees(angleBetween);                                             // Could instantiate as classAngle but we do NOT want to reorient
                                                                                            // the angle here
    return angleBetween;

  }

  public vectorClass proj(vectorClass vector1){

    double dotProductResult = dotProduct(this, vector1);
    double magnitude_vector1 = vector1.magnitude();
    vectorClass projectionVector = vector1.scalarProduct(dotProductResult/Math.pow(magnitude_vector1, 2));

    return projectionVector;

  }

  public static double Work(vectorClass vector1, vectorClass vector2){

    double work = dotProduct(vector1, vector2);

    return work;

  	}

}