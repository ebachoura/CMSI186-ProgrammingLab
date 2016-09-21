import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.HashSet;
import java.util.Collections;
import java.util.Objects;
import java.util.stream.Collectors;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class MapColorer {

   public Region[] backtrack(Region[] regions, int r) {
      if (r == regions.length) {
        return regions;
      }
      int color = regions[r].getColor() == null ? 0 : regions[r].getColor();
      while (!regions[r].canColorWith(color) && color < 4) {
        color++;
      }
      if (color >= 4) {
        if (r == 0) {
          regions[0].setColor(-1);
          return regions;
        } else {
          regions[r].removeColor();
          regions[r-1].setColor(regions[r-1].getColor() + 1);
          backtrack (regions, r - 1);
        }
      } else {
        regions[r].setColor(color);
        backtrack (regions, r + 1);
      }
      return regions;
   }

   public void colorMap() {
      Region[] regions = Region.getAllRegionsAsArray();
      
      regions = backtrack(regions, 0);
      if (regions[0].getColor() == -1) {
        System.out.println("IMPOSSIBLE MAP");
      } else {
          for (Region r : Region.getAllRegionsAsArray()) {
            System.out.println(r + ":" + r.getColor());
            for (Region neighbors : r.getNeighbors()) {
              System.out.println(r + "," + neighbors.getName());
            }
          }
      }
   }

   public void readMapFromStandardInput() {
       new BufferedReader(new InputStreamReader(System.in))
           .lines()
           .forEach(line -> {
               String[] pair = line.trim().split(",");
               Region region0 = Region.forName(pair[0]);
               Region region1 = Region.forName(pair[1]);
               region0.addNeighbor(region1);
           });
   }

   public static void main(String[] args) {
       MapColorer mapColorer = new MapColorer();
       mapColorer.readMapFromStandardInput();
       mapColorer.colorMap();
   }
}

/**
* This class is full of a lot of stuff you don't need to know.
*
* But don't hesitate to ask about such things if you like.
*/
class Region {

   private static Map<String, Region> allRegions = new TreeMap<>();

   public static Region forName(String name) {
       allRegions.putIfAbsent(name, new Region(name));
       return allRegions.get(name);
   }

   public static Region[] getAllRegionsAsArray() {
       return allRegions.values().toArray(new Region[allRegions.size()]);
   }

   private String name;
   private Integer color;
   private Set<Region> neighbors = new HashSet<>();

   private Region(String name) {
       this.name = name;
   }

   public String getName() {
       return name;
   }

   public Integer getColor() {
       return color;
   }

   public boolean canColorWith(int color) {
       return !neighbors.stream().anyMatch(n -> Objects.equals(n.color,color));
   }

   public void setColor(int color) {
       this.color = color;
   }

   public void removeColor() {
       this.color = null;
   }

   public Set<Region> getNeighbors() {
       return neighbors;
   }

   public void addNeighbor(Region region) {
       Objects.requireNonNull(region);
       neighbors.add(region);
       region.neighbors.add(this);
   }

   public String toString() {
      return this.name;
   }
}