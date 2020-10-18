package com.example.drip;
import java.util.*;

public class ColorMatch {

    public static double[][] getOutfit(String rgb) {

        int[][] palette = stringToRGB(rgb);


        double[][] resultPant = new double[palette.length][3];
        double[][] resultShirt = new double[palette.length][3];
        double[][] resultJacket = new double[palette.length][3];
        double[][] resultMask = new double[palette.length][3];
        double[][] resultSweater = new double[palette.length][3];
        double[][] resultHat = new double[palette.length][3];
        double[][] resultShorts = new double[palette.length][3];
        double[][] result = new double[7][3];

        resultPant = matchClothes(palette, RGB.redPant, RGB.greenPant, RGB.bluePant);
        resultShirt = matchClothes(palette, RGB.redShirt, RGB.greenShirt, RGB.blueShirt);
        resultSweater = matchClothes(palette, RGB.redSweater, RGB.greenSweater, RGB.blueSweater);
        resultMask = matchClothes(palette, RGB.redMask, RGB.greenMask, RGB.blueMask);
        resultJacket = matchClothes(palette, RGB.redJacket, RGB.greenJacket, RGB.blueJacket);
        resultHat = matchClothes(palette, RGB.redHat, RGB.greenHat, RGB.blueHat);
        resultShorts = matchClothes(palette, RGB.redShort, RGB.greenShort, RGB.blueShort);

        result[0] = resultPant[(int)(3 * Math.random())];
        result[1] = getNextBest(resultPant, resultShirt);
        result[2] = getNextBest(resultShirt, resultSweater);
        result[3] = resultMask[(int)(3*Math.random())];
        result[4] = resultJacket[0];
        result[5] = resultHat[0];
        result[6] = resultShorts[(int)(3 * Math.random())];

        return result;
    }

    public static double getOutfitPrice (double[][] outfit) {
        double totalPrice = 0;
        totalPrice += RGB.pantPrice[(int)outfit[0][1]];
        totalPrice += RGB.shirtPrice[(int)outfit[1][1]];
        totalPrice += RGB.sweaterPrice[(int)outfit[2][1]];
        totalPrice += RGB.maskPrice[(int)outfit[3][1]];
        totalPrice += RGB.hatPrice[(int)outfit[5][1]];

        return totalPrice;
    }

    public static double[][] matchClothes(int[][] palette, int[] r, int[] g, int[] b) {
        double[][] distances = new double[palette.length][r.length];  // array of distances, row is palette color, lists distances from palette color
        int[] colors = new int[palette.length];  // stores index of lowest distance for each palette color
        double matchedColor[][] = new double[palette.length+1][3];  // first index is the color in palette that was matched to, second index is the index of the matched pant
        double lowest = 255;

        // iterates through palette array, finds distance of catalog element from palette, record in distance array
        // System.out.println(palette.length);
        for (int i = 0; i < palette.length; i++) {
            // System.out.println("\n");
            for(int j = 0; j < r.length; j++) {
                distances[i][j] = (Math.sqrt(Math.pow(palette[i][0]-r[j],2) + Math.pow(palette[i][1]-g[j],2) + Math.pow(palette[i][2]-b[j],2)));
                // System.out.println(distances[i][j]);
            }
        }

        // iterates through color array, finds the index of the minimum value of distance for each color
        // if the distance is lower than the current lowest distance, the new color is i
        for (int i = 0; i < colors.length; i++) {
            colors[i] = findMinIdx(distances[i]);
            // System.out.println(distances[i][colors[i]]);
            matchedColor[i+1][0] = i;
            matchedColor[i+1][1] = colors[i];
            matchedColor[i+1][2] = distances[i][colors[i]];
            if (distances[i][colors[i]] < lowest) {
                lowest = distances[i][colors[i]];
                matchedColor[0][0] = i;
                matchedColor[0][1] = colors[i];
                matchedColor[0][2] = distances[i][colors[i]];
                // System.out.println(matchedColor[0] + " " + matchedColor[1]);
            }
        }
        return matchedColor;
    }

    public static double[] getNextBest(double[][] pant, double[][] shirt) {
        double[] nextBest = shirt[0];
        int mixItUp = (int)(3*Math.random());
        if (shirt[0][0] == pant[0][0]) {
            // print2D(shirt);
            Arrays.sort(shirt, new Comparator<double[]>() {
                @Override
                public int compare(double[] o1, double[] o2) {

                    return Double.compare(o2[2], o1[2]);
                }
            });
            nextBest = shirt[mixItUp];
            // print2D(shirt);
            return nextBest;
        } else {
            return nextBest;
        }
    }

    public static int findMinIdx(double[] numbers) {
        if (numbers == null || numbers.length == 0) return -1; // Saves time for empty array
        // As pointed out by ZouZou, you can save an iteration by assuming the first index is the smallest
        double minVal = numbers[0]; // Keeps a running count of the smallest value so far
        int minIdx = 0; // Will store the index of minVal
        for(int idx=1; idx<numbers.length; idx++) {
            if(numbers[idx] < minVal) {
                minVal = numbers[idx];
                minIdx = idx;
            }
        }
        return minIdx;
    }

    // convert 1,1,1;2,2,2;3,3,3;4,4,4 to 2d rgb value
    public static int[][] stringToRGB(String rgb) {
        rgb = rgb + ";";
        String str = "";
        int temp1 = 0;
        int num = 0;
        int count2 = 0;
        int[] ints = new int[3];
        int[][] ints2 = new int[4][3];
        int[][] finalArray = new int[4][3];
        for (int i = 0; i < rgb.length(); i++) {
            //System.out.println("i" + i);
            if (rgb.substring(i, i + 1).equals(";")) {
                str = rgb.substring(temp1, i) + ",";
                temp1 = i + 1;
                int count1 = 0;
                int temp2 = 0;
                //System.out.println("i" + i);
                //System.out.println(str);
                for (int j = 0; j < str.length(); j++) {
                    //System.out.println("j" + j);
                    if (str.substring(j, j + 1).equals(",")) {
                        num = Integer.parseInt(str.substring(temp2, j));
                        ints[count1] = num;
                        //System.out.println(ints[count1]);
                        count1++;
                        temp2 = j + 1;
                    }
                }
                ints2[count2] = ints;
                finalArray[count2][0] = ints2[count2][0];
                finalArray[count2][1] = ints2[count2][1];
                finalArray[count2][2] = ints2[count2][2];
                // System.out.println(finalArray[count2][0]);
                // System.out.println(finalArray[count2][1]);
                // System.out.println(finalArray[count2][2]);
                count2++;
            }
        }
        return finalArray;
    }

    public static void print2D(int mat[][])
    {
        // Loop through all rows
        for (int i = 0; i < mat.length; i++) {
            // Loop through all elements of current row
            for (int j = 0; j < mat[i].length; j++) {
                System.out.print(mat[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
    public static void main(String[] args) {

        int[][] palette = {{255, 226, 108}, {108, 137, 255}, {142, 134, 104}, {54, 69, 128}};
        double[][] resultPant = new double[palette.length][3];
        double[][] resultShirt = new double[palette.length][3];
        double[][] resultJacket = new double[palette.length][3];
        double[][] resultMask = new double[palette.length][3];
        double[][] resultSweater = new double[palette.length][3];
        double[][] result = new double[5][3];

        resultPant = matchClothes(palette, RGB.redPant, RGB.greenPant, RGB.bluePant);
        resultShirt = matchClothes(palette, RGB.redShirt, RGB.greenShirt, RGB.blueShirt);
        resultJacket = matchClothes(palette, RGB.redJacket, RGB.greenJacket, RGB.blueJacket);
        resultMask = matchClothes(palette, RGB.redMask, RGB.greenMask, RGB.blueMask);
        resultSweater = matchClothes(palette, RGB.redSweater, RGB.greenSweater, RGB.blueSweater);

        int mixItUp = (int)(3 * Math.random());
        result[0] = resultPant[mixItUp];
        result[1] = getNextBest(resultPant, resultShirt);
        result[2] = getNextBest(resultPant, resultSweater);
        result[3] = resultJacket[0];
        result[4] = resultMask[0];

        System.out.println();
        print2D(getOutfit());

        int[][] rgb = stringToRGB("255,255,255;2,2,2;3,3,3;4,4,4");
        print2D(rgb);
    }
     */


}
