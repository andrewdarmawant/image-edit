// edits an image by changing its colors.
import java.util.*;
import java.awt.*;

public class ImageEdit {
   public static void main(String[] args) {
      Scanner input = new Scanner(System.in);
      Picture pic = new Picture("image.jpg");
      
      int choice = intro(input);   
      
      while(choice > 4 || choice < 1){
         System.out.println("Invalid input");
         System.out.print("Please ");
         choice = intro(input);
      }
      if(choice == 1){
         negative(pic);
         pic.show();
      }else if(choice == 2){
         blur(pic);
         pic.show();
      }else if(choice == 3){
         grayscale(pic);
         pic.show();
      }else{
         System.out.print("Amplify which color?(r/g/b): ");
         String color = input.next();
         color = color.toLowerCase();
      
         changeAHue(pic, color);
         pic.show();
      }
      
   
   }
   
   
   
   
   
   
   
   
   
   
   public static int intro(Scanner input){
      System.out.println("Press 1 to invert image");
      System.out.println("Press 2 to blur image");
      System.out.println("Press 3 to make the image into grayscale");
      System.out.println("Press 4 to amplify a color on the image");
      return input.nextInt();
   }

   
   // amplifies a certain color of an image
   public static void changeAHue(Picture pic, String more){
      Color[][] pixels = pic.getPixels();
      
      for (int i = 0; i < pixels.length; i++) {
         for (int j = 0; j < pixels[i].length; j++) {
            Color color = pixels[i][j];
            int red = color.getRed();
            int green = color.getGreen();
            int blue = color.getBlue();
            Color newColor = new Color(red, green, blue);
            if(more.equals("r") ){
               newColor = new Color(255, green, blue);
            }else if(more.equals("g") ){
               newColor = new Color(red, 255, blue);
            }else if(more.equals("b") ){
               newColor = new Color(red, green, 255);
            }
            pixels[i][j] = newColor;
         }
      }
      pic.setPixels(pixels);
   
   }

   //invert the colors of an image
   public static void negative(Picture pic) {
      Color[][] pixels = pic.getPixels();
      
      for (int i = 0; i < pixels.length; i++) {
         for (int j = 0; j < pixels[i].length; j++) {
            Color color = pixels[i][j];
            int red = color.getRed();
            int green = color.getGreen();
            int blue = color.getBlue();
            
            Color newColor = new Color(255 - red, 255 - green, 255 - blue);
            pixels[i][j] = newColor;
         }
      }
      
      pic.setPixels(pixels);
   }    

   // convert an image to grayscale
   public static void grayscale(Picture pic) {
      Color[][] pixels = pic.getPixels();
      
      for (int i = 0; i < pixels.length; i++) {
         for (int j = 0; j < pixels[i].length; j++) {
            Color color = pixels[i][j];
            int red = color.getRed();
            int green = color.getGreen();
            int blue = color.getBlue();
            int avg = (red + green + blue) / 3;
            
            Color newColor = new Color(avg, avg, avg);
            pixels[i][j] = newColor;
         }
      }
      
      pic.setPixels(pixels);
   }
   
   // blurs an image
   public static void blur(Picture pic) {
      Color[][] pixels = pic.getPixels();
      Color[][] pixelsCopy = pic.getPixels();
      
      for (int i = 0; i < pixels.length; i++) {
         for (int j = 0; j < pixels[i].length; j++) {
         
            int red = 0, green = 0, blue = 0;
            int count = 0;
            for (int dx = -3; dx <= 3; dx++) {
               for (int dy = -3; dy <= 3; dy++) {
                  if (i + dx >= 0 && i + dx < pixels.length &&
                     j + dy >= 0 && j + dy < pixels[i].length) {
                     Color pixel = pixelsCopy[i + dx][j + dy];
                     red += pixel.getRed();
                     green += pixel.getGreen();
                     blue += pixel.getBlue();
                     count++;
                  }
               }
            }
            
            pixels[i][j] = new Color(red / count, green / count, blue / count);
         }
      }
      
      pic.setPixels(pixels);
   }
}
