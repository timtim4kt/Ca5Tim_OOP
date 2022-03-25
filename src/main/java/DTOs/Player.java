package DTOs;

import java.time.LocalDate;
import java.util.Objects;

public class Player implements Comparable<Player>{


    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Player p)
    {
        double x = (this.weightKilograms);
        double y = (p.weightKilograms);

        boolean weightKG = x == y;

        if (weightKG)
        {
            return 0;
        }
        else
        {
            if (x - y > 0)
            {
                return -1;
            }
            else
            {
                return 1;
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(name, player.name) && Objects.equals(dob, player.dob);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, dob);
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", dob=" + dob +
                ", weightKilograms=" + weightKilograms +
                ", heightMetres=" + heightMetres +
                ", careerAppearances=" + careerAppearances +
                ", careerGoals=" + careerGoals +
                '}';
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public double getWeightKilograms() {
        return weightKilograms;
    }

    public void setWeightKilograms(double weightKilograms) {
        this.weightKilograms = weightKilograms;
    }

    public double getHeightMetres() {
        return heightMetres;
    }

    public void setHeightMetres(double heightMetres) {
        this.heightMetres = heightMetres;
    }

    public int getCareerAppearances() {
        return careerAppearances;
    }

    public void setCareerAppearances(int careerAppearances) {
        this.careerAppearances = careerAppearances;
    }

    public int getCareerGoals() {
        return careerGoals;
    }

    public void setCareerGoals(int careerGoals) {
        this.careerGoals = careerGoals;
    }

    public Player(String name, String country, LocalDate dob, double weightKilograms, double heightMetres, int careerAppearances, int careerGoals) {
        this.name = name;
        this.country = country;
        this.dob = dob;
        this.weightKilograms = weightKilograms;
        this.heightMetres = heightMetres;
        this.careerAppearances = careerAppearances;
        this.careerGoals = careerGoals;
    }

    private String name;
    private String country;
    private LocalDate dob;
    private double weightKilograms;
    private double heightMetres;
    private int careerAppearances;
    private int careerGoals;


}
